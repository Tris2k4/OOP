package dungeonmania.entities;

import dungeonmania.map.GameMap;
import dungeonmania.util.Position;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import dungeonmania.entities.conductor.Conductor;
import dungeonmania.entities.logical.LogicalEntity;

public class Wire extends Entity implements Conductor {
    private boolean activated;
    private int lastActivationTick;

    public Wire(Position position) {
        super(position.asLayer(Entity.ITEM_LAYER));
        this.activated = false;
        this.lastActivationTick = -1;
    }

    @Override
    public void onOverlap(GameMap map, Entity entity) {
        return;
    }

    @Override
    public void onMovedAway(GameMap map, Entity entity) {
        return;
    }

    @Override
    public void onDestroy(GameMap gameMap) {
        return;
    }

    public void setActivated(boolean activated, GameMap map) {
        if (this.activated != activated) {
            this.activated = activated;
            if (activated) {
                this.lastActivationTick = map.getGame().getTick();
            } else {
                this.lastActivationTick = -1;
            }
        }
    }

    public boolean isActivated() {
        return activated;
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

    public void updateState(GameMap map) {
        boolean hasActivePath = hasActivePathToSwitch(map, getPosition(), new HashSet<>());
        setActivated(hasActivePath, map);
    }

    private boolean hasActivePathToSwitch(GameMap map, Position position, Set<Position> visited) {
        if (visited.contains(position)) {
            return false; // Already visited this position, avoid infinite loop
        }
        visited.add(position);

        List<Position> adjPosList = position.getCardinallyAdjacentPositions();
        for (Position pos : adjPosList) {
            List<Entity> entities = new ArrayList<>(map.getEntities(pos));
            for (Entity entity : entities) {
                if (entity instanceof Switch && ((Switch) entity).isActivated()) {
                    return true; // Found an active switch
                }
                if (entity instanceof Wire && ((Wire) entity).isActivated()) {
                    if (hasActivePathToSwitch(map, pos, visited)) {
                        return true; // Found an active path through another wire
                    }
                }
            }
        }
        return false; // No active path found
    }

    @Override
    public boolean canMoveOnto(GameMap map, Entity entity) {
        return true;
    }
}
