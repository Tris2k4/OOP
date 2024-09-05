package dungeonmania.entities.collectables;

import dungeonmania.entities.Entity;
import dungeonmania.entities.Pickupable;
import dungeonmania.entities.Player;
import dungeonmania.entities.inventory.InventoryItem;
import dungeonmania.map.GameMap;
import dungeonmania.util.Position;

public class Treasure extends Entity implements InventoryItem, Pickupable {
    public Treasure(Position position) {
        super(position);
    }

    @Override
    public boolean canMoveOnto(GameMap map, Entity entity) {
        return true;
    }

    @Override
    public void onOverlap(GameMap map, Entity entity) {
        if (entity instanceof Player) {
            if (!pickUp((Player) entity))
                return;
            map.destroyEntity(this);
        }
    }

    @Override
    public boolean pickUp(Player player) {
        player.setCollectedTreasureCount(1);
        return player.getInventory().add((InventoryItem) this);
    }
}
