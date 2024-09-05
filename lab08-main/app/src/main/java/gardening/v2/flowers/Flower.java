package gardening.v2.flowers;

// abstract class for protected method
public abstract class Flower {

    // each time called by the flower box, the flower grows a litte bit
    protected abstract void grow();

    // once a flower is fully grown, it can be harvested
    protected abstract boolean canHarvest();

    public abstract int getPrice();
}
