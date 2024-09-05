package dungeonmania.goals;

import dungeonmania.Game;

public class TreasureStrategy implements Goal {
    private int target;

    public TreasureStrategy(int target) {
        this.target = target;
    }
    @Override
    public boolean achieved(Game game) {
        if (game.getPlayer() == null)
            return false;
        return game.getCollectedTreasureCount() >= target;
    }

    @Override
    public String toString(Game game) {
        if (this.achieved(game))
            return "";
        return ":treasure";
    }
}
