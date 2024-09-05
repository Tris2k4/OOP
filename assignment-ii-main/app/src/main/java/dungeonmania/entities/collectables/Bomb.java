package dungeonmania.entities.collectables;

import dungeonmania.util.Position;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import dungeonmania.entities.Entity;
import dungeonmania.entities.Pickupable;
import dungeonmania.entities.Player;
import dungeonmania.entities.Switch;
import dungeonmania.entities.conductor.Conductor;
import dungeonmania.entities.inventory.InventoryItem;
import dungeonmania.map.GameMap;
import dungeonmania.entities.logical.LogicalEntity;

public class Bomb extends Entity implements InventoryItem, Pickupable, LogicalEntity {
    public enum State {
        SPAWNED, INVENTORY, PLACED
    }

    public static final int DEFAULT_RADIUS = 1;
    private State state;
    private int radius;
    private boolean activated;
    private String logic;

    private List<Switch> subs = new ArrayList<>();

    public Bomb(Position position, int radius) {
        super(position);
        state = State.SPAWNED;
        this.radius = radius;
        this.activated = true;
        this.logic = null;
    }

    // Constructor with logic
    public Bomb(Position position, int radius, String logic) {
        super(position);
        state = State.SPAWNED;
        this.radius = radius;
        this.logic = logic;
        this.activated = false; // Default to false for logical bombs
    }

    public void subscribe(Switch s) {
        this.subs.add(s);
    }

    public void notify(GameMap map) {
        if (logic == null)
            explode(map);
        return;
    }

    @Override
    public boolean canMoveOnto(GameMap map, Entity entity) {
        return true;
    }

    @Override
    public void onOverlap(GameMap map, Entity entity) {
        if (state != State.SPAWNED)
            return;
        if (entity instanceof Player) {
            if (!pickUp((Player) entity))
                return;
            subs.stream().forEach(s -> s.unsubscribe(this));
            map.destroyEntity(this);
        }
        this.state = State.INVENTORY;
    }

    @Override
    public boolean pickUp(Player player) {
        return player.getInventory().add((InventoryItem) this);
    }

    public void onPutDown(GameMap map, Position p) {
        translate(Position.calculatePositionBetween(getPosition(), p));
        map.addEntity(this);
        this.state = State.PLACED;
        List<Position> adjPosList = getPosition().getCardinallyAdjacentPositions();
        adjPosList.stream().forEach(node -> {
            List<Entity> entities = map.getEntities(node).stream().filter(e -> (e instanceof Switch))
                    .collect(Collectors.toList());
            entities.stream().map(Switch.class::cast).forEach(s -> s.subscribe(this, map));
            entities.stream().map(Switch.class::cast).forEach(s -> this.subscribe(s));
        });
        updateState(map);
    }

    public void explode(GameMap map) {
        int x = getPosition().getX();
        int y = getPosition().getY();
        for (int i = x - radius; i <= x + radius; i++) {
            for (int j = y - radius; j <= y + radius; j++) {
                List<Entity> entities = map.getEntities(new Position(i, j));
                entities = entities.stream().filter(e -> !(e instanceof Player)).collect(Collectors.toList());
                for (Entity e : entities) {
                    if (e instanceof Conductor) {
                        ((Conductor) e).setActivated(false, map);
                    }
                }
                for (Entity e : entities) {
                    if (e instanceof Conductor) {
                        ((Conductor) e).propagateCurrent(map, new Position(i, j), new HashSet<>());
                    }
                    map.destroyEntity(e);
                }
            }
        }
    }

    public State getState() {
        return state;
    }

    @Override
    public void updateState(GameMap map) {
        if (logic == null) {
            return; // Non-logical bombs do not update state
        }

        List<Position> adjPosList = getPosition().getCardinallyAdjacentPositions();
        int activeConductorCount = 0;
        boolean allAdjacentConductorsActive = true;
        boolean areAdjacentConductorsActivatedSameTick = true;
        int activationTick = -1;

        for (Position pos : adjPosList) {
            List<Entity> entities = map.getEntities(pos);
            boolean hasActiveConductor = true;
            for (Entity entity : entities) {
                if (entity instanceof Conductor && ((Conductor) entity).isActivated()) {
                    activeConductorCount++;
                    int conductorActivationTick = ((Conductor) entity).getLastActivationTick();
                    if (activationTick == -1) {
                        activationTick = conductorActivationTick;
                    } else if (activationTick != conductorActivationTick) {
                        areAdjacentConductorsActivatedSameTick = false;
                    }
                }
                if (entity instanceof Conductor && !((Conductor) entity).isActivated()) {
                    hasActiveConductor = false;
                }
            }
            if (!hasActiveConductor) {
                allAdjacentConductorsActive = false;
            }
        }

        switch (logic) {
        case "and":
            activated = activeConductorCount >= 2 && allAdjacentConductorsActive;
            break;
        case "or":
            activated = activeConductorCount >= 1;
            break;
        case "xor":
            activated = activeConductorCount == 1;
            break;
        case "co_and":
            activated = activeConductorCount >= 2 && allAdjacentConductorsActive
                    && areAdjacentConductorsActivatedSameTick;
            break;
        default:
            activated = false;
        }

        if (activated) {
            explode(map);
        }
    }

    @Override
    public boolean isActivated() {
        return activated;
    }

    @Override
    public String getLogic() {
        return logic;
    }
}
