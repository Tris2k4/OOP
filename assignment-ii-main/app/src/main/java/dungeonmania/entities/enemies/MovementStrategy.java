package dungeonmania.entities.enemies;

import dungeonmania.Game;

public interface MovementStrategy {
    public void move(Enemy enemy, Game game);
}
