package dungeonmania.entities;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import dungeonmania.entities.collectables.Bomb;
import dungeonmania.map.GameMap;
import dungeonmania.util.Position;
import dungeonmania.entities.conductor.Conductor;
import dungeonmania.entities.logical.LogicalEntity;

public class Switch extends Entity implements Conductor {
    private boolean activated;
    private List<Bomb> bombs = new ArrayList<>();
    private int lastActivationTick;

    public Switch(Position position) {
        super(position.asLayer(Entity.ITEM_LAYER));
        this.activated = false;
        this.lastActivationTick = -1;
    }

    public void subscribe(Bomb b) {
        bombs.add(b);
    }

    public void subscribe(Bomb bomb, GameMap map) {
        bombs.add(bomb);
        if (activated) {
            bombs.stream().forEach(b -> b.notify(map));
            bombs.stream().forEach(b -> b.updateState(map));
        }
    }

    public void unsubscribe(Bomb b) {
        bombs.remove(b);
    }

    @Override
    public boolean canMoveOnto(GameMap map, Entity entity) {
        return true;
    }

    @Override
    public void onOverlap(GameMap map, Entity entity) {
        if (!activated) {
            activated = true;
            bombs.forEach(b -> b.notify(map));
            setLastActivationTick(map.getGame().getTick());
            propagateCurrent(map, getPosition(), new HashSet<>());
        }
    }

    @Override
    public void onMovedAway(GameMap map, Entity entity) {
        if (entity instanceof Boulder) {
            activated = false;
            propagateCurrent(map, getPosition(), new HashSet<>());
            this.lastActivationTick = -1;
        }
    }

    public boolean isActivated() {
        return activated;
    }

    public void setActivated(boolean activated, GameMap map) {
        this.activated = activated;
    }

    @Override
    public void propagateCurrent(GameMap map, Position position, Set<Position> visited) {
        if (visited.contains(position)) {
            return; // Already visited this position, avoid infinite loop
        }
        visited.add(position);

        List<Position> adjPosList = position.getCardinallyAdjacentPositions();
        adjPosList.forEach(pos -> {
            List<Entity> entities = new ArrayList<>(map.getEntities(pos));
            entities.forEach(entity -> {
                if (entity instanceof Wire) {
                    ((Wire) entity).updateState(map); // Update the wire's state
                    ((Conductor) entity).propagateCurrent(map, pos, visited); // Then propagate the current
                } else if (entity instanceof LogicalEntity) {
                    ((LogicalEntity) entity).updateState(map);
                }
            });
        });
    }

    @Override
    public int getLastActivationTick() {
        return lastActivationTick;
    }

    @Override
    public void setLastActivationTick(int tick) {
        lastActivationTick = tick;
    }

}
