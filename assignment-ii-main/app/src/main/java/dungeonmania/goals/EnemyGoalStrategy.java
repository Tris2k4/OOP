package dungeonmania.goals;

import dungeonmania.Game;
import dungeonmania.entities.enemies.ZombieToastSpawner;

public class EnemyGoalStrategy implements Goal {
    private int requiredEnemies;

    public EnemyGoalStrategy(int requiredEnemies) {
        this.requiredEnemies = requiredEnemies;
    }

    @Override
    public boolean achieved(Game game) {
        int defeatedEnemiesCount = game.getPlayer().getDefeatedEnemiesCount();
        long spawnerCount = game.getMap().getEntities(ZombieToastSpawner.class).size();

        return defeatedEnemiesCount >= requiredEnemies && spawnerCount == 0;
    }

    @Override
    public String toString(Game game) {
        return achieved(game) ? "" : ":enemies";
    }
}
