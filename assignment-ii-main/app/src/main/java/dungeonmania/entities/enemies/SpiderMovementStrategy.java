package dungeonmania.entities.enemies;

import java.util.List;
import dungeonmania.Game;
import dungeonmania.entities.Boulder;
import dungeonmania.entities.Entity;
import dungeonmania.map.GameMap;
import dungeonmania.util.Position;

public class SpiderMovementStrategy implements MovementStrategy {
    private List<Position> movementTrajectory;
    private int nextPositionElement;
    private boolean forward;

    public SpiderMovementStrategy(Position initialPosition) {
        movementTrajectory = initialPosition.getAdjacentPositions();
        nextPositionElement = 1;
        forward = true;
    }

    @Override
    public void move(Enemy enemy, Game game) {
        GameMap map = game.getMap();
        Position nextPos = movementTrajectory.get(nextPositionElement);
        List<Entity> entities = map.getEntities(nextPos);

        if (entities != null && !entities.isEmpty() && entities.stream().anyMatch(e -> e instanceof Boulder)) {
            forward = !forward;
            updateNextPosition();
            updateNextPosition();
        }

        nextPos = movementTrajectory.get(nextPositionElement);
        entities = map.getEntities(nextPos);

        if (entities == null || entities.isEmpty() || entities.stream().allMatch(e -> e.canMoveOnto(map, enemy))) {
            map.moveTo(enemy, nextPos);
            updateNextPosition();
        }
    }

    private void updateNextPosition() {
        if (forward) {
            nextPositionElement++;
            if (nextPositionElement == 8) {
                nextPositionElement = 0;
            }
        } else {
            nextPositionElement--;
            if (nextPositionElement == -1) {
                nextPositionElement = 7;
            }
        }
    }
}
