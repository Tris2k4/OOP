package dungeonmania.mvp;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import dungeonmania.DungeonManiaController;
import dungeonmania.exceptions.InvalidActionException;
import dungeonmania.response.models.DungeonResponse;
import dungeonmania.util.Direction;

public class MidnightArmourTest {
    @Test
    @Tag("2D-4")
    @DisplayName("Test building midnight_armour")
    public void buildMidnightArmour() {
        DungeonManiaController dmc;
        dmc = new DungeonManiaController();
        DungeonResponse res = dmc.newGame("d_createMidnightArmour", "c_task2Test");

        res = dmc.tick(Direction.DOWN);
        res = dmc.tick(Direction.UP);
        res = dmc.tick(Direction.RIGHT);

        assertEquals(0, TestUtils.getInventory(res, "midnight_armour").size());
        res = assertDoesNotThrow(() -> dmc.build("midnight_armour"));
        assertEquals(1, TestUtils.getInventory(res, "midnight_armour").size());

    }

    @Test
    @Tag("2D-5")
    @DisplayName("Test building midnight_armour with a zombie")
    public void buildMidnightArmourWithZombie() {
        DungeonManiaController dmc;
        dmc = new DungeonManiaController();
        DungeonResponse res = dmc.newGame("d_createMidnightArmourWithZombie", "c_task2Test");

        res = dmc.tick(Direction.DOWN);
        res = dmc.tick(Direction.DOWN);
        res = dmc.tick(Direction.DOWN);

        assertEquals(0, TestUtils.getInventory(res, "midnight_armour").size());
        assertThrows(InvalidActionException.class, () -> dmc.build("midnight_armour"));
        assertEquals(0, TestUtils.getInventory(res, "midnight_armour").size());

        res = dmc.tick(Direction.RIGHT);
        res = dmc.tick(Direction.DOWN);
        res = dmc.tick(Direction.DOWN);

        res = assertDoesNotThrow(() -> dmc.build("midnight_armour"));
        assertEquals(1, TestUtils.getInventory(res, "midnight_armour").size());

    }
}
