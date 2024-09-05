package dungeonmania.entities.buildables;

import dungeonmania.Game;
import dungeonmania.battles.BattleStatistics;

public class MidnightArmour extends Buildable {
    private double attackDamage;
    private double protection;

    public MidnightArmour(int attackDamage, int protection) {
        super(null, Integer.MAX_VALUE);
        this.attackDamage = attackDamage;
        this.protection = protection;
    }

    @Override
    public BattleStatistics applyBuff(BattleStatistics origin) {
        return BattleStatistics.applyBuff(origin, new BattleStatistics(0, attackDamage, protection, 0, 0));
    }

    @Override
    public void use(Game game) {

    }
}
