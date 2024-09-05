package dungeonmania.goals;

import org.json.JSONArray;
import org.json.JSONObject;

public class GoalFactory {
    public static Goal createGoal(JSONObject jsonGoal, JSONObject config) {
        JSONArray subgoals;
        switch (jsonGoal.getString("goal")) {
        case "AND":
            subgoals = jsonGoal.getJSONArray("subgoals");
            return new AndStrategy(createGoal(subgoals.getJSONObject(0), config),
                    createGoal(subgoals.getJSONObject(1), config));
        case "OR":
            subgoals = jsonGoal.getJSONArray("subgoals");
            return new OrStrategy(createGoal(subgoals.getJSONObject(0), config),
                    createGoal(subgoals.getJSONObject(1), config));
        case "exit":
            return new ExitStrategy();
        case "boulders":
            return new BouldersStrategy();
        case "treasure":
            int treasureGoal = config.optInt("treasure_goal", 1);
            return new TreasureStrategy(treasureGoal);
        case "enemies":
            int enemyGoal = config.optInt("enemy_goal", 1);
            return new EnemyGoalStrategy(enemyGoal);
        default:
            return null;
        }
    }
}
