package dungeonmania.mvp;


import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import dungeonmania.DungeonManiaController;
import dungeonmania.response.models.DungeonResponse;
import dungeonmania.util.Direction;


public class SunStoneTest {
    @Test
    @Tag("2D-1")
    @DisplayName("Testing treasure goal by pick up sun stone")
    public void treasureGoalSunstone() {
        DungeonManiaController dmc = new DungeonManiaController();
        DungeonResponse res = dmc.newGame("d_basicSunStoneTest", "c_task2Test");

        res = dmc.tick(Direction.RIGHT);
        res = dmc.tick(Direction.RIGHT);
        assertTrue(TestUtils.getGoals(res).contains(":treasure"));

        res = dmc.tick(Direction.RIGHT);
        res = dmc.tick(Direction.RIGHT);
        assertTrue(TestUtils.getGoals(res).contains(":treasure"));

        res = dmc.tick(Direction.RIGHT);
        assertEquals(4, TestUtils.getInventory(res, "sun_stone").size());
        assertEquals("", TestUtils.getGoals(res));

    }
}
