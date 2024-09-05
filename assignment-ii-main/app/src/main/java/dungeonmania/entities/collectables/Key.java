package dungeonmania.entities.collectables;

import dungeonmania.entities.Entity;
import dungeonmania.entities.Pickupable;
import dungeonmania.entities.Player;
import dungeonmania.entities.inventory.InventoryItem;
import dungeonmania.map.GameMap;
import dungeonmania.util.Position;

public class Key extends Entity implements InventoryItem, Pickupable {
    private int number;

    public Key(Position position, int number) {
        super(position);
        this.number = number;
    }

    @Override
    public boolean canMoveOnto(GameMap map, Entity entity) {
        return true;
    }

    @Override
    public void onOverlap(GameMap map, Entity entity) {
        if (entity instanceof Player) {
            if (!((Player) entity).pickUp(this))
                return;
            map.destroyEntity(this);
        }
    }

    @Override
    public boolean pickUp(Player player) {
        if (player.countEntityOfType(Key.class) > 0) {
            return false;
        }
        return player.getInventory().add((InventoryItem) this);
    }

    public int getnumber() {
        return number;
    }

}
