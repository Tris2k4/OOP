package dungeonmania.entities.buildables;

import dungeonmania.Game;
import dungeonmania.battles.BattleStatistics;

public class Sceptre extends Buildable {
    private int numberOfTicks;
    private static final int DURABILITY = 0;

    public Sceptre(int numberOfTicks) {
        super(null, DURABILITY);
        this.numberOfTicks = numberOfTicks;
    }

    public BattleStatistics applyBuff(BattleStatistics origin) {
        return BattleStatistics.applyBuff(origin, new BattleStatistics(0, 0, 0, 0, 0));
    }

    @Override
    public void use(Game game) {

    }

    public int getNumberOfTicks() {
        return numberOfTicks;
    }

    public void setNumberOfTicks(int numberOfTicks) {
        this.numberOfTicks = numberOfTicks;
    }
}
