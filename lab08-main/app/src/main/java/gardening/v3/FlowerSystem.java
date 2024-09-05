// package gardening.v3;

// import java.util.ArrayList;
// import java.util.Arrays;
// import java.util.List;
// import java.util.Scanner;

// import gardening.v3.flowers.Flower;
// import gardening.v3.flowers.Mutation;
// import gardening.v3.flowers.MutationFlowerBox;
// import gardening.v3.flowers.Rose;
// import gardening.v3.flowers.RosePlant;
// import gardening.v3.flowers.RoseRandomiser;
// import gardening.v3.flowers.RoseRandomiserMutator;
// import gardening.v3.flowers.SeedMultiplierMutator;
// import gardening.v3.flowers.StandardFlowerBox;
// import gardening.v3.flowers.Sunflower;
// import gardening.v3.flowers.SunflowerMutation;
// import gardening.v3.flowers.SunflowerPlant;

// public class FlowerSystem {
//     private static final int ROSE_SHIPMENT_DELAY = 2;

//     private boolean suppression = false;

//     private List<Rose> roses = new ArrayList<>();
//     private List<Sunflower> sunflowers = new ArrayList<>();

//     private StandardFlowerBox<Rose> roseBox = new StandardFlowerBox<>(); // allowing anything that looks like a rose,
//                                                                          // not just rose plant
//     private StandardFlowerBox<Sunflower> sunflowerBox = new StandardFlowerBox<>();

//     // happy as long as it uses a valid SunflowerMutation
//     private MutationFlowerBox<Sunflower, SunflowerMutation> mutatedSunflowerBox = new MutationFlowerBox<>(
//             new SeedMultiplierMutator<>());

//     // explicitly wants to use
//     // a RoseRandomiser mutation
//     private MutationFlowerBox<Rose, RoseRandomiser> mutatedRoseBox = new MutationFlowerBox<>(
//             new RoseRandomiserMutator());

//     private List<Flower> flowersToSell = new ArrayList<>();

//     private int balance = 0;
//     private int numSunflowerSeeds = 0;
//     private List<String> readMessages = new ArrayList<>();
//     private int roseShipmentPeriod = 0;

//     private void newShipment() {
//         roseShipmentPeriod = (roseShipmentPeriod + 1) % ROSE_SHIPMENT_DELAY;

//         if (roseShipmentPeriod == 0)
//             roses.add(new RosePlant());

//         sunflowers.add(new SunflowerPlant());
//     }

//     private void sellFlowers() {
//         balance += flowersToSell.stream().mapToInt(flower -> flower.getPrice()).sum();
//         flowersToSell.clear();
//     }

//     // helper function for code reuse and also ensuring type safety
//     private static <T extends Flower, R extends Mutation<T>> int mutateFlowers(List<T> flowers,
//             MutationFlowerBox<T, R> flowerBox, int numToMutate) {

//         numToMutate = Math.min(numToMutate, flowers.size());

//         flowers.subList(0, numToMutate).forEach(flower -> flowerBox.addFlower(flower));
//         flowers.subList(0, numToMutate).clear();
//         return numToMutate;
//     }

//     private void routeMutate(String[] args) {
//         int numToMutate = 1;
//         if (args.length > 1)
//             numToMutate = Integer.parseInt(args[1]);

//         int numMutated = 0;
//         switch (args[0]) {
//         case "rose":
//             numMutated = mutateFlowers(roses, mutatedRoseBox, numToMutate);
//             break;
//         case "sunflower":
//             numMutated = mutateFlowers(sunflowers, mutatedSunflowerBox, numToMutate);
//             break;
//         default:
//             System.out.println("Parsing error");
//             break;
//         }

//         if (numMutated == 0)
//             System.out.println("You have no " + (args[0] + "s") + " to be mutated");
//         else if (numToMutate > numMutated)
//             System.out.println("You have less " + (args[0] + "s") + " than requested. Adding all to be mutated");
//         else
//             System.out.println("Adding " + numToMutate + " " + (args[0] + "s") + " to be mutated");

//     }

//     private void tick() {
//         this.newShipment();
//         roseBox.growFlowers();
//         sunflowerBox.growFlowers();
//         mutatedRoseBox.growFlowers();
//         mutatedSunflowerBox.growFlowers();
//     }

//     private void displayInfo() {
//         System.out.println("Number of roses in storage: " + roses.size());
//         System.out.println("Number of sunflowers in storage: " + sunflowers.size());
//         System.out.println("Number of roses to harvest: " + roseBox.numHarvestable());
//         System.out.println("Number of sunflowers to harvest: " + sunflowerBox.numHarvestable());
//         System.out.println("Number of mutated roses to harvest: " + mutatedRoseBox.numHarvestable());
//         System.out.println("Number of mutated sunflowers to harvest: " + mutatedSunflowerBox.numHarvestable());
//         System.out.println("Number of messages read: " + readMessages.size());
//         System.out.println("Number of seeds: " + numSunflowerSeeds);
//         System.out.println("Number of flowers to sell: " + flowersToSell.size());
//         System.out.println("Net worth: " + balance);
//     }

//     private void printMessageLog() {
//         if (readMessages.size() == 0)
//             System.out.println("No messages");
//         else
//             readMessages.forEach(message -> System.out.println(message));
//     }

//     private void routePackage(String[] args) {
//         int numToSell = 1;
//         if (args.length > 1)
//             numToSell = Integer.parseInt(args[1]);

//         List<? extends Flower> chosenFlowers;

//         switch (args[0]) {
//         case "rose":
//             chosenFlowers = roses;
//             break;
//         case "sunflower":
//             chosenFlowers = sunflowers;
//             break;
//         default:
//             System.out.println("Parsing error");
//             return;
//         }

//         if (chosenFlowers.size() == 0) {
//             System.out.println("You have no " + (args[0] + "s") + " to be sold");
//         } else if (numToSell > chosenFlowers.size()) {
//             System.out.println("You have less " + (args[0] + "s") + " than requested. Adding all to be sold");
//             flowersToSell.addAll(chosenFlowers);
//             chosenFlowers.clear();
//         } else {
//             System.out.println("Adding " + numToSell + " " + (args[0] + "s") + " to be sold");
//             flowersToSell.addAll(chosenFlowers.subList(0, numToSell));
//             chosenFlowers.subList(0, numToSell).clear();
//         }
//     }

//     private void routePlant(String[] args) {
//         switch (args[0]) {
//         case "rose":

//             if (roses.size() > 0) {
//                 roseBox.addFlower(roses.get(0));
//                 roses.remove(0);
//             } else {
//                 System.out.println("You have no roses to be planted");
//             }

//             break;

//         case "sunflower":

//             if (sunflowers.size() > 0) {
//                 sunflowerBox.addFlower(sunflowers.get(0));
//                 sunflowers.remove(0);
//             } else {
//                 System.out.println("You have no sunflowers to be planted");
//             }

//             break;

//         default:
//             System.out.println("Parsing error");
//             break;
//         }
//     }

//     private void routeHarvest(String[] args) {
//         if (args.length > 1 && !args[1].equals("mutation")) {
//             System.out.println("Parsing error");
//             return;
//         }

//         switch (args[0]) {
//         case "rose":
//             if (args.length > 1) {

//                 List<RoseRandomiser> harvestedRoseMutations = mutatedRoseBox.harvestFlowers();
//                 harvestedRoseMutations.forEach(mutatedRose -> {
//                     System.out.println("Mutated Message: " + mutatedRose.revealMessage() + " Original Message: "
//                             + mutatedRose.revealOriginalMessage());
//                     readMessages.add(mutatedRose.revealMessage());
//                 });

//                 flowersToSell.addAll(harvestedRoseMutations);

//             } else {

//                 List<Rose> harvestedRoses = roseBox.harvestFlowers();
//                 harvestedRoses.forEach((rose) -> {
//                     System.out.println(rose.revealMessage());
//                     readMessages.add(rose.revealMessage());
//                 });

//                 flowersToSell.addAll(harvestedRoses);

//             }

//             break;

//         case "sunflower":
//             // don't need to call a specific method on mutated sunflowers...can treat just
//             // as a sunflower

//             // need to use wildcard
//             List<? extends Sunflower> harvestedSunflowers = args.length > 1 ? mutatedSunflowerBox.harvestFlowers()
//                     : sunflowerBox.harvestFlowers();
//             int harvested = harvestedSunflowers.stream().mapToInt((sunflower) -> sunflower.harvestSeeds()).sum();
//             this.numSunflowerSeeds += harvested;
//             System.out.println("Harvested: " + harvested + " Total: " + this.numSunflowerSeeds);

//             flowersToSell.addAll(harvestedSunflowers);

//             break;

//         default:
//             System.out.println("Parsing error");
//             break;
//         }
//     }

//     private boolean command(String[] args) {
//         boolean running = true;

//         switch (args[0]) {
//         case "plant":
//             routePlant(Arrays.copyOfRange(args, 1, args.length));
//             break;
//         case "harvest":
//             routeHarvest(Arrays.copyOfRange(args, 1, args.length));
//             break;
//         case "tick":
//             tick();
//             break;
//         case "log":
//             printMessageLog();
//             break;
//         case "package":
//             routePackage(Arrays.copyOfRange(args, 1, args.length));
//             break;
//         case "sell":
//             sellFlowers();
//             break;
//         case "mutate":
//             routeMutate(Arrays.copyOfRange(args, 1, args.length));
//             break;
//         case "s":
//             suppression = !suppression;
//             break;
//         case "d":
//             displayInfo();
//             break;
//         case "q":
//             running = false;
//             break;
//         default:
//             System.err.println("Parsing error");
//             break;
//         }

//         return running;
//     }

//     public boolean command(String commandString) {
//         String[] args = commandString.split(" ");
//         return command(args);
//     }

//     public void run() {
//         displayInfo();
//         System.out.println();

//         System.out.print("Enter command: ");
//         Scanner scanner = new Scanner(System.in);

//         while (command(scanner.nextLine())) {
//             if (!suppression) {
//                 System.out.println();
//                 displayInfo();
//                 System.out.println();
//             }

//             System.out.print("Enter command: ");
//         }

//         scanner.close();

//     }

//     public static void main(String[] args) {
//         FlowerSystem flowerSystem = new FlowerSystem();

//         flowerSystem.run();
//     }

// }
