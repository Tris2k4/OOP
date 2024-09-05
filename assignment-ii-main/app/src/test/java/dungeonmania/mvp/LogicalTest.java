package dungeonmania.mvp;

import dungeonmania.DungeonManiaController;
import dungeonmania.response.models.DungeonResponse;
import dungeonmania.util.Direction;
import dungeonmania.util.Position;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LogicalTest {
    private boolean bulbOffAt(DungeonResponse res, int x, int y) {
        Position pos = new Position(x, y);
        return TestUtils.getEntitiesStream(res, "light_bulb_off").anyMatch(it -> it.getPosition().equals(pos));
    }

    private boolean bulbOnAt(DungeonResponse res, int x, int y) {
        Position pos = new Position(x, y);
        return TestUtils.entityAtPosition(res, "light_bulb_on", pos);
    }

    @Test
    @Tag("2f_1")
    @DisplayName("Or Light bulb Test")
    public void lightBulb() {
        DungeonManiaController controller = new DungeonManiaController();
        DungeonResponse res = controller.newGame("d_basicLightTest", "c_logicalGoalsTest");

        assertTrue(bulbOffAt(res, 7, 2));

        res = controller.tick(Direction.RIGHT);
        res = controller.tick(Direction.UP);
        res = controller.tick(Direction.DOWN);
        assertTrue(bulbOffAt(res, 7, 2));

        res = controller.tick(Direction.RIGHT);
        assertTrue(bulbOnAt(res, 7, 2));

        res = controller.tick(Direction.RIGHT);
        assertTrue(bulbOffAt(res, 7, 2));
    }

    @Test
    @Tag("2f_2")
    @DisplayName("And Light bulb Test")
    public void lightBulbAnd() {
        DungeonManiaController controller = new DungeonManiaController();
        DungeonResponse res = controller.newGame("d_andLightTest", "c_logicalGoalsTest");
        assertTrue(bulbOffAt(res, 8, 4));

        res = controller.tick(Direction.RIGHT);
        assertTrue(bulbOffAt(res, 8, 4));

        res = controller.tick(Direction.RIGHT);
        assertTrue(bulbOnAt(res, 8, 4));

        res = controller.tick(Direction.RIGHT);
        assertTrue(bulbOffAt(res, 8, 4));
    }

    @Test
    @Tag("2f_3")
    @DisplayName("And Light bulb Test")
    public void lightBulbXor() {
        DungeonManiaController controller = new DungeonManiaController();
        DungeonResponse res = controller.newGame("d_xorLightTest", "c_logicalGoalsTest");
        assertTrue(bulbOffAt(res, 8, 4));

        res = controller.tick(Direction.RIGHT);
        res = controller.tick(Direction.UP);
        res = controller.tick(Direction.DOWN);
        assertTrue(bulbOffAt(res, 8, 4));

        res = controller.tick(Direction.RIGHT);
        assertTrue(bulbOnAt(res, 8, 4));

        res = controller.tick(Direction.RIGHT);
        assertTrue(bulbOffAt(res, 8, 4));
    }

    @Test
    @Tag("2f_4")
    @DisplayName("Co_and Light bulb Test")
    public void lightBulbCoAnd() {
        DungeonManiaController controller = new DungeonManiaController();
        DungeonResponse res = controller.newGame("d_coandLightTest", "c_logicalGoalsTest");
        assertTrue(bulbOffAt(res, 8, 4));

        res = controller.tick(Direction.RIGHT);
        assertTrue(bulbOffAt(res, 8, 4));

        res = controller.tick(Direction.RIGHT);
        assertTrue(bulbOnAt(res, 8, 4));

        res = controller.tick(Direction.RIGHT);
        assertTrue(bulbOffAt(res, 8, 4));
    }
    @Test
    @Tag("2f_5")
    @DisplayName("Or Switch Door test")
    public void orSwitchDoorTest() {
        DungeonManiaController controller = new DungeonManiaController();
        DungeonResponse res = controller.newGame("d_orSwitchDoorTest", "c_logicalGoalsTest");

        res = controller.tick(Direction.UP);
        Position pos = TestUtils.getEntities(res, "player").get(0).getPosition();
        res = controller.tick(Direction.RIGHT);
        assertEquals(pos, TestUtils.getEntities(res, "player").get(0).getPosition());

        res = controller.tick(Direction.LEFT);
        res = controller.tick(Direction.DOWN);
        res = controller.tick(Direction.DOWN);
        res = controller.tick(Direction.RIGHT);
        res = controller.tick(Direction.UP);
        res = controller.tick(Direction.UP);
        pos = TestUtils.getEntities(res, "player").get(0).getPosition();
        res = controller.tick(Direction.RIGHT);
        assertNotEquals(pos, TestUtils.getEntities(res, "player").get(0).getPosition());
    }
    @Test
    @Tag("2f_6")
    @DisplayName("And Switch Door test")
    public void andSwitchDoorTest() {
        DungeonManiaController controller = new DungeonManiaController();
        DungeonResponse res = controller.newGame("d_andSwitchDoorTest", "c_logicalGoalsTest");

        Position pos = TestUtils.getEntities(res, "player").get(0).getPosition();
        res = controller.tick(Direction.RIGHT);
        assertEquals(pos, TestUtils.getEntities(res, "player").get(0).getPosition());

        res = controller.tick(Direction.LEFT);
        res = controller.tick(Direction.DOWN);
        res = controller.tick(Direction.DOWN);
        res = controller.tick(Direction.RIGHT);
        res = controller.tick(Direction.UP);
        res = controller.tick(Direction.UP);
        pos = TestUtils.getEntities(res, "player").get(0).getPosition();
        res = controller.tick(Direction.RIGHT);
        assertNotEquals(pos, TestUtils.getEntities(res, "player").get(0).getPosition());
    }
    @Test
    @Tag("2f_7")
    @DisplayName("Xor Switch Door test")
    public void xorSwitchDoorTest() {
        DungeonManiaController controller = new DungeonManiaController();
        DungeonResponse res = controller.newGame("d_orSwitchDoorTest", "c_logicalGoalsTest");

        res = controller.tick(Direction.UP);
        Position pos = TestUtils.getEntities(res, "player").get(0).getPosition();
        res = controller.tick(Direction.RIGHT);
        assertEquals(pos, TestUtils.getEntities(res, "player").get(0).getPosition());

        res = controller.tick(Direction.LEFT);
        res = controller.tick(Direction.DOWN);
        res = controller.tick(Direction.DOWN);
        res = controller.tick(Direction.RIGHT);
        res = controller.tick(Direction.UP);
        res = controller.tick(Direction.UP);
        pos = TestUtils.getEntities(res, "player").get(0).getPosition();
        res = controller.tick(Direction.RIGHT);
        assertNotEquals(pos, TestUtils.getEntities(res, "player").get(0).getPosition());
    }
    @Test
    @Tag("2f_8")
    @DisplayName("Co_and Switch Door test")
    public void coandSwitchDoorTest() {
        DungeonManiaController controller = new DungeonManiaController();
        DungeonResponse res = controller.newGame("d_coandSwitchDoorTest", "c_logicalGoalsTest");

        Position pos = TestUtils.getEntities(res, "player").get(0).getPosition();
        res = controller.tick(Direction.RIGHT);
        assertEquals(pos, TestUtils.getEntities(res, "player").get(0).getPosition());

        res = controller.tick(Direction.LEFT);
        res = controller.tick(Direction.DOWN);
        res = controller.tick(Direction.DOWN);
        res = controller.tick(Direction.RIGHT);
        res = controller.tick(Direction.UP);
        res = controller.tick(Direction.UP);
        pos = TestUtils.getEntities(res, "player").get(0).getPosition();
        res = controller.tick(Direction.RIGHT);
        assertNotEquals(pos, TestUtils.getEntities(res, "player").get(0).getPosition());
    }
    @Test
    @Tag("2f_9")
    @DisplayName("Or and xor Bomb test")
    public void orAndxorBombTest() {
        DungeonManiaController controller;
        DungeonResponse res;
        String[] gameSetups = {"d_orBombTest", "d_xorBombTest"};
        for (String setup : gameSetups) {
            controller = new DungeonManiaController(); // Create a new controller for each setup
            res = controller.newGame(setup, "c_logicalGoalsTest");
            assertEquals(1, TestUtils.getEntities(res, "bomb").size());

            res = controller.tick(Direction.RIGHT);

            assertEquals(0, TestUtils.getEntities(res, "bomb").size());
        }
    }
    @Test
    @Tag("2f_10")
    @DisplayName("And and coand Bomb test")
    public void andAndcoandBombTest() {
        DungeonManiaController controller;
        DungeonResponse res;
        String[] gameSetups = {"d_andBombTest", "d_coandBombTest"};
        for (String setup : gameSetups) {
            controller = new DungeonManiaController(); // Create a new controller for each setup
            res = controller.newGame(setup, "c_logicalGoalsTest");
            assertEquals(1, TestUtils.getEntities(res, "bomb").size());

            res = controller.tick(Direction.LEFT);
            res = controller.tick(Direction.DOWN);
            res = controller.tick(Direction.DOWN);
            res = controller.tick(Direction.RIGHT);

            assertEquals(0, TestUtils.getEntities(res, "bomb").size());
        }
    }


}
