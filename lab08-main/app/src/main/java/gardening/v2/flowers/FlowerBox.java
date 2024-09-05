// package gardening.v2.flowers;

// import java.util.ArrayList;
// import java.util.List;
// import java.util.stream.Collectors;

// // TODO for students: Add in appropriate generic parameters
// public class FlowerBox<> {
//     private List<Flower> flowers = new ArrayList<>();

//     public void addFlower(Flower flower) {
//         flowers.add(flower);
//     }

//     public int numHarvestable() {
//         // safely convert from long to int. stream's count method returns a long
//         return Math.toIntExact(flowers.stream().filter(flower -> flower.canHarvest()).count());
//     }

//     public List<Flower> harvestFlowers() {
//         return extractReadyFlowers();
//     }

//     public void growFlowers() {
//         flowers.forEach((flower) -> flower.grow());
//     }

//     // A helper method. The types of the objects ready to be harvested
//     // may be different from the types you return from your flower box.
//     // e.g seedlings are ready to harvest but you return flowers once harvested
//     protected List<Flower> extractReadyFlowers() {
//         List<Flower> readyFlowers = flowers.stream().filter((Flower flower) -> flower.canHarvest())
//                 .collect(Collectors.toList());
//         readyFlowers.forEach(flower -> flowers.remove(flower));
//         return readyFlowers;
//     }
// }
