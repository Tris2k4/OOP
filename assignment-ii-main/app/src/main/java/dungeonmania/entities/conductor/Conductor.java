package dungeonmania.entities.conductor;

import java.util.Set;

import dungeonmania.map.GameMap;
import dungeonmania.util.Position;

public interface Conductor {
    void setActivated(boolean bool, GameMap map);

    boolean isActivated();

    void propagateCurrent(GameMap map, Position position, Set<Position> visited);

    int getLastActivationTick();

    void setLastActivationTick(int tick);
}
