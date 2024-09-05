package gardening.v3.flowers;

// want rose mutations to also be treated like roses
public abstract class RoseMutation<T extends Rose> extends Mutation<T> implements Rose {
    public RoseMutation(T flower) {
        super(flower);
    }

    @Override
    public String revealMessage() {
        return getFlower().revealMessage();
    }

}
