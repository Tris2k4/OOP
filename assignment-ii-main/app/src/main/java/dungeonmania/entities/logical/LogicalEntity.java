package dungeonmania.entities.logical;
import dungeonmania.map.GameMap;
public interface LogicalEntity {
    void updateState(GameMap map);
    boolean isActivated();
    String getLogic();
}
