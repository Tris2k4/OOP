package gardening.v3.flowers;

// mutators wrap flowers with a specific mutation
public interface Mutator<T extends Flower, R extends Mutation<T>> {
    public R mutateFlower(T flower);
}
