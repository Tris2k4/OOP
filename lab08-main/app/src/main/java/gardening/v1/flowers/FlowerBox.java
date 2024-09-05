package gardening.v1.flowers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FlowerBox<T extends Flower> {
    private List<T> flowers = new ArrayList<>();

    public void addFlower(T flower) {
        flowers.add(flower);
    }

    public int numHarvestable() {
        // safely convert from long to int. stream's count method returns a long
        return Math.toIntExact(flowers.stream().filter(flower -> flower.canHarvest()).count());
    }

    public List<T> harvestFlowers() {
        return extractReadyFlowers();
    }

    public void growFlowers() {
        flowers.forEach((flower) -> flower.grow());
    }

    protected List<T> extractReadyFlowers() {
        List<T> readyFlowers = flowers.stream().filter((Flower flower) -> flower.canHarvest())
                .collect(Collectors.toList());
        readyFlowers.forEach(flower -> flowers.remove(flower));
        return readyFlowers;
    }
}
