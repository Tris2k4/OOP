package dungeonmania.entities.enemies;

import dungeonmania.Game;
import dungeonmania.entities.collectables.potions.InvincibilityPotion;
import dungeonmania.map.GameMap;
import dungeonmania.util.Position;

public class ZombieToast extends Enemy {
    public static final double DEFAULT_HEALTH = 5.0;
    public static final double DEFAULT_ATTACK = 6.0;

    public ZombieToast(Position position, double health, double attack) {
        super(position, health, attack);
        setMovementStrategy(new RandomMovementStrategy());
        Game.incrementActiveZombie();
    }

    @Override
    public void move(Game game) {
        GameMap map = game.getMap();
        if (map.getPlayer().getEffectivePotion() instanceof InvincibilityPotion) {
            setMovementStrategy(new InvincibilityPotionMovementStrategy());
        } else {
            setMovementStrategy(new RandomMovementStrategy());
        }
        super.move(game);
    }

    @Override
    public void onDestroy(GameMap map) {
        Game g = map.getGame();
        g.unsubscribe(getId());
        Game.decrementActiveZombie();
    }
}
