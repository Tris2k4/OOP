package dungeonmania.entities.buildables;

import dungeonmania.Game;
import dungeonmania.entities.BattleItem;
import dungeonmania.entities.Entity;
import dungeonmania.entities.inventory.InventoryItem;

import dungeonmania.util.Position;

public abstract class Buildable extends Entity implements InventoryItem, BattleItem {
    private int durability;

    public Buildable(Position position, int durability) {
        super(position);
        this.durability = durability;
    }
    public int getDurability() {
        return durability;
    }

    protected void decreaseDurability(Game game) {
        durability--;
        if (durability <= 0) {
            game.getPlayer().remove(this);
        }
    }
}
