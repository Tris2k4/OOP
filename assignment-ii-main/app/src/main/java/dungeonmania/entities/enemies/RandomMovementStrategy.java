package dungeonmania.entities.enemies;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import dungeonmania.Game;
import dungeonmania.map.GameMap;
import dungeonmania.util.Position;

public class RandomMovementStrategy implements MovementStrategy {
    private Random randGen = new Random();

    @Override
    public void move(Enemy enemy, Game game) {
        GameMap map = game.getMap();
        List<Position> pos = enemy.getPosition().getCardinallyAdjacentPositions();
        pos = pos.stream().filter(p -> map.canMoveTo(enemy, p)).collect(Collectors.toList());
        Position nextPos = pos.isEmpty() ? enemy.getPosition() : pos.get(randGen.nextInt(pos.size()));
        map.moveTo(enemy, nextPos);
    }
}
