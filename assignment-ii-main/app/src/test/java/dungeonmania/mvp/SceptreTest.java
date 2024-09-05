package dungeonmania.mvp;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import dungeonmania.DungeonManiaController;
import dungeonmania.response.models.DungeonResponse;
import dungeonmania.util.Direction;
import dungeonmania.util.Position;

public class SceptreTest {
    @Test
    @Tag("2D-2")
    @DisplayName("Creating a sceptre test")
    public void buildSceptreWithOneSunstone() {
        DungeonManiaController dmc;
        dmc = new DungeonManiaController();
        DungeonResponse res = dmc.newGame("d_sceptreCreateTest", "c_task2Test");

        res = dmc.tick(Direction.DOWN);
        res = dmc.tick(Direction.DOWN);
        assertEquals(1, TestUtils.getInventory(res, "sun_stone").size());
        Position pos = TestUtils.getEntities(res, "player").get(0).getPosition();
        res = dmc.tick(Direction.DOWN);

        assertNotEquals(pos, TestUtils.getEntities(res, "player").get(0).getPosition());
        assertEquals(1, TestUtils.getInventory(res, "sun_stone").size());
        res = dmc.tick(Direction.DOWN);
        res = dmc.tick(Direction.DOWN);
        res = dmc.tick(Direction.DOWN);

        assertEquals(0, TestUtils.getInventory(res, "sceptre").size());
        res = assertDoesNotThrow(() -> dmc.build("sceptre"));
        assertEquals(1, TestUtils.getInventory(res, "sceptre").size());

        assertEquals(0, TestUtils.getInventory(res, "key").size());
        assertEquals(0, TestUtils.getInventory(res, "arrow").size());
        assertEquals(0, TestUtils.getInventory(res, "sun_stone").size());

    }
    @Test
    @Tag("2D-3")
    @DisplayName("Mind control test")
    public void buildSceptreWithTwoSunstone() {
        DungeonManiaController dmc;
        dmc = new DungeonManiaController();
        DungeonResponse res = dmc.newGame("d_mindControlTest", "c_task2Test");

        String mercId = TestUtils.getEntitiesStream(res, "mercenary").findFirst().get().getId();

        res = dmc.tick(Direction.DOWN);
        res = dmc.tick(Direction.DOWN);
        assertEquals(1, TestUtils.getInventory(res, "sun_stone").size());
        Position pos = TestUtils.getEntities(res, "player").get(0).getPosition();
        res = dmc.tick(Direction.DOWN);

        assertNotEquals(pos, TestUtils.getEntities(res, "player").get(0).getPosition());
        assertEquals(1, TestUtils.getInventory(res, "sun_stone").size());
        res = dmc.tick(Direction.DOWN);
        res = dmc.tick(Direction.DOWN);

        assertEquals(0, TestUtils.getInventory(res, "sceptre").size());
        res = assertDoesNotThrow(() -> dmc.build("sceptre"));
        assertEquals(1, TestUtils.getInventory(res, "sceptre").size());

        assertEquals(0, TestUtils.getInventory(res, "treasure").size());
        assertEquals(0, TestUtils.getInventory(res, "wood").size());
        assertEquals(1, TestUtils.getInventory(res, "sun_stone").size());

        res = assertDoesNotThrow(() -> dmc.interact(mercId));
        res = dmc.tick(Direction.UP);
        res = dmc.tick(Direction.UP);
        res = assertDoesNotThrow(() -> dmc.interact(mercId));
    }
}
