package gardening.v3.flowers;

public class SunflowerSeedMultiplier extends SunflowerMutation {
    public SunflowerSeedMultiplier(Sunflower sunflower) {
        super(sunflower);
    }

    @Override
    public int harvestSeeds() {
        return super.harvestSeeds() * 5;
    }
}
