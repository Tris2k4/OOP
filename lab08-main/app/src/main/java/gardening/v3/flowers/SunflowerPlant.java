package gardening.v3.flowers;

public class SunflowerPlant implements Sunflower {
    public static final int MAX_SEEDS = 7;
    private static final int SEED_GROWTH = 3;

    private int numSeeds = 0;
    private int bloom = 0;

    @Override
    public void grow() {
        this.numSeeds = Math.min(MAX_SEEDS, this.numSeeds + SEED_GROWTH);
        this.bloom = this.numSeeds;
    }

    public int harvestSeeds() {
        int tmpSeeds = numSeeds;
        numSeeds = 0;
        return tmpSeeds;
    }

    @Override
    public boolean canHarvest() {
        return numSeeds == MAX_SEEDS;
    }

    @Override
    public int getPrice() {
        return 15 + bloom;
    }

}
