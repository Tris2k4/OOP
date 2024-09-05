package gardening.v3.flowers;

public class RoseRandomiserMutator implements RoseMutationMutator<Rose, RoseRandomiser> {
    @Override
    public RoseRandomiser mutateFlower(Rose flower) {
        return new RoseRandomiser(flower);
    }

}
