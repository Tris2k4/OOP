package gardening.v3.flowers;

// generic so subclasses can get back the underlying without a cast
// subclasses needing the more specific
public abstract class Mutation<T extends Flower> implements Flower {
    private T flower; // mutator wraps around flower

    public Mutation(T flower) {
        this.flower = flower;
    }

    protected T getFlower() {
        return flower;
    }

    @Override
    public void grow() {
        flower.grow();
    }

    @Override
    public boolean canHarvest() {
        return flower.canHarvest();
    }

    @Override
    public int getPrice() {
        return flower.getPrice();
    }

}
