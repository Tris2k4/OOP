package gardening.v3.flowers;

public class SeedMultiplierMutator<T extends Sunflower, R extends SunflowerSeedMultiplier>
        implements SunflowerMutationMutator {

    // return seed multiplier instead of more generic sunflower mutation
    // strengthened post conditions
    @Override
    public SunflowerSeedMultiplier mutateFlower(Sunflower flower) {
        return new SunflowerSeedMultiplier(flower);
    }

}
