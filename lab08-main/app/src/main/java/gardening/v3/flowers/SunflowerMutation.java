package gardening.v3.flowers;

// this abstraction is really for type safety
public abstract class SunflowerMutation extends Mutation<Sunflower> implements Sunflower {
    public SunflowerMutation(Sunflower flower) {
        super(flower);
    }

    @Override
    public int harvestSeeds() {
        return getFlower().harvestSeeds();
    }
}
