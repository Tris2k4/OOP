package dungeonmania.mvp;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import dungeonmania.DungeonManiaController;
import dungeonmania.exceptions.InvalidActionException;
import dungeonmania.response.models.DungeonResponse;
import dungeonmania.response.models.EntityResponse;
import dungeonmania.util.Direction;

public class EnemyGoalTest {
    @Test
    @Tag("2A-1")
    @DisplayName("Test basic enemies goal")
    public void enemiesGoal() {
        DungeonManiaController controller = new DungeonManiaController();
        DungeonResponse res = controller.newGame("d_basicGoalTest_enemiesGoal", "c_basicGoalTest_enemiesGoal");

        assertTrue(TestUtils.getGoals(res).contains(":enemies"));
        res = controller.tick(Direction.DOWN);
        List<EntityResponse> entities = res.getEntities();
        int mercCount = TestUtils.countEntityOfType(entities, "mercenary");
        int spiderCount = TestUtils.countEntityOfType(entities, "spider");
        int zombieCount = TestUtils.countEntityOfType(entities, "zombie_toast");
        assertEquals(1, mercCount);
        assertEquals(1, spiderCount);
        assertEquals(0, zombieCount);

        assertTrue(TestUtils.getGoals(res).contains(":enemies"));

        res = controller.tick(Direction.DOWN);
        entities = res.getEntities();
        spiderCount = TestUtils.countEntityOfType(entities, "spider");
        zombieCount = TestUtils.countEntityOfType(entities, "zombie_toast");
        mercCount = TestUtils.countEntityOfType(entities, "mercenary");
        assertEquals(1, spiderCount);
        assertEquals(0, zombieCount);
        assertEquals(0, mercCount);

        assertTrue(TestUtils.getGoals(res).contains(":enemies"));

        res = controller.tick(Direction.DOWN);
        res = controller.tick(Direction.DOWN);
        entities = res.getEntities();
        zombieCount = TestUtils.countEntityOfType(entities, "zombie_toast");
        spiderCount = TestUtils.countEntityOfType(entities, "spider");
        mercCount = TestUtils.countEntityOfType(entities, "mercenary");
        assertEquals(0, zombieCount);
        assertEquals(0, spiderCount);
        assertEquals(0, mercCount);
        assertEquals(1, TestUtils.countEntityOfType(entities, "player"));

        assertEquals("", TestUtils.getGoals(res));
    }

    @Test
    @Tag("2A-2")
    @DisplayName("Test enemies goal with spawner")
    public void enemiesGoalWithSpawner() {

        DungeonManiaController controller = new DungeonManiaController();
        DungeonResponse res = controller.newGame("d_basicGoalTest_enemiesGoalWithSpawner",
                "c_basicGoalTest_enemiesGoalWithSpawner");
        assertEquals(1, TestUtils.getEntities(res, "zombie_toast_spawner").size());
        String spawnerId = TestUtils.getEntities(res, "zombie_toast_spawner").get(0).getId();

        assertEquals(0, TestUtils.getInventory(res, "sword").size());

        assertTrue(TestUtils.getGoals(res).contains(":enemies"));

        res = controller.tick(Direction.RIGHT);
        assertEquals(1, TestUtils.getInventory(res, "sword").size());
        assertThrows(InvalidActionException.class, () -> controller.interact(spawnerId));
        res = controller.tick(Direction.RIGHT);
        res = controller.tick(Direction.RIGHT);

        assertEquals(1, TestUtils.getInventory(res, "sword").size());
        assertThrows(IllegalArgumentException.class, () -> controller.interact("random_invalid_id"));

        res = assertDoesNotThrow(() -> controller.interact(spawnerId));

        assertEquals(0, TestUtils.countType(res, "zombie_toast_spawner"));

        assertEquals("", TestUtils.getGoals(res));
    }
}
