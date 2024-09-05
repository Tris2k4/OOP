package gardening.v3.flowers;

// this interface is basically a typedef. serving as a relabelling of
// Mutator<Sunflower, SunflowerMutation> to make other parts of code more
// concise and clear
// avoiding nested generics hell
public interface SunflowerMutationMutator extends Mutator<Sunflower, SunflowerMutation> {

}
