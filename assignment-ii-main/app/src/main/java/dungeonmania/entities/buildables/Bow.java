package dungeonmania.entities.buildables;

import dungeonmania.Game;
import dungeonmania.battles.BattleStatistics;

public class Bow extends Buildable {

    public Bow(int durability) {
        super(null, durability);
    }

    @Override
    public void use(Game game) {
        decreaseDurability(game);
    }

    @Override
    public BattleStatistics applyBuff(BattleStatistics origin) {
        return BattleStatistics.applyBuff(origin, new BattleStatistics(0, 0, 0, 2, 1));
    }

}
