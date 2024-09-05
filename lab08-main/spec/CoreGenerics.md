# 🌻 Core Exercise: Generics

As a bit of a hobby, I’ve recently taken up a bit of magical gardening and have been growing roses and sunflowers in my spare time. I grow them in magical flower boxes which are able to hold an infinite number of flowers, however I don’t want different flowers in the same box. As a result, I have created two flower boxes, one just for roses and one just for flowers. These flower boxes allow them to grow, and once fully grown, they can be harvested. The different flowers also have different abilities. With the sunflowers, I am able to harvest their seeds while each rose has a secret message that can be revealed. The seeds and messages can’t be obtained unless the flower has been grown in a flower box, however flowers can still be used without having been grown in a flower box.

I’m so obsessed with this that I’ve written a little CLI interactive simulation that I can play around with when I’m away from my plants, however there are some issues with the code that I’d like your help with improving. I have a bug in the code that took me ages to find. I’m accidentally planting roses in my sunflower box and sunflowers in my rose box. I haven’t fixed the bug yet, so have a look at my code to see how easy this mistake is to make. Additionally, when I’m harvesting the flowers from the flower boxes, I have to do some ugly looking casts so I can call the unique method to the specific flowers. I’ve heard that generics can help with both of these things.

You can view the documentation for the CLI here: [CLI Documentation](/spec/GenericsCLI.md)

You can view some examples of how the system works here: [CLI Examples](/spec/GenericsCLIExample.md)

# Task 1 (core)

> This exercise uses the [`gardening.v1`](/app/src/main/java/gardening/v1/) package.

Don’t fix the bug in the code just yet. The next few questions require the bug to be present. You will fix the bug as a part of **question 4**. Create a blog post to answer the following questions:

There is a bug in the routePlant method:

```java
public void routePlant(String[] args) {
    switch (args[0]) {
    case "rose":

        if (roses.size() > 0) {
            roseBox.addFlower(sunflowers.get(0)); // whoops
            roses.remove(0);
        } else {
            System.out.println("You have no roses to be planted");
        }

        break;

    case "sunflower":

        if (sunflowers.size() > 0) {
            sunflowerBox.addFlower(roses.get(0)); // whoops
            sunflowers.remove(0);
        } else {
            System.out.println("You have no sunflowers to be planted");
        }

        break;

    default:
        System.out.println("Parsing error");
        break;
    }
}
```

**Q1) Why doesn’t the compiler warn me that I have this bug in my code?**

In the `routeHarvest` method, I had to do some gross casting in order to get the code to work.

```java
public void routeHarvest(String[] args) {
    switch (args[0]) {
    case "rose":
        List<Rose> harvestedRoses = (List<Rose>) (List<?>) roseBox.harvestFlowers(); // gross casting
        harvestedRoses.forEach((rose) -> {
            System.out.println(rose.revealMessage());
            readMessages.add(rose.revealMessage());
        });

        flowersToSell.addAll(harvestedRoses);

        break;

    case "sunflower":
        List<Sunflower> harvestedSunflowers = (List<Sunflower>) (List<?>) sunflowerBox.harvestFlowers(); // gross casting
        int harvested = harvestedSunflowers.stream().mapToInt((sunflower) -> sunflower.harvestSeeds()).sum();
        this.numSunflowerSeeds += harvested;
        System.out.println("Harvested: " + harvested + " Total: " + this.numSunflowerSeeds);

        flowersToSell.addAll(harvestedSunflowers);

        break;

    default:
        System.out.println("Parsing error");
        break;
    }
}
```

**Q2) Why do I have to cast the list of flowers that are returned from their respective boxes?**

## Implement Generics

- Modify the `FlowerBox` class to use a generic parameter. We want to be able to create flower boxes that only work with roses and flower boxes that only work with sunflowers. We also don’t want to have to cast the list that is returned when we harvest the flowers from that box.

<details>
  <summary>Hint</summary>
  <ul>
    <li>
      Everywhere the type <code>Flower</code> is used in the
      <code>FlowerBox</code> class, should be replaced with your generic
      parameter.
    </li>
  </ul>
</details>

- Bound the generic parameter so `FlowerBox` can only be parameterized by the type `Flower` or its subtypes. We don't want to be able to create a flower box of strings for example.
- Modify the code in the `FlowerSystem` class where the flower boxes are created, to use the generic parameter. Put in an appropriate type for each box.

Q3) Does the compiler warn or give an error about the bug in the code, now that the flower box is using generics? Why?

- Fix the bug. This part of the code should have no warnings or errors now.

**Q4) Explain how the information provided by the compiler helped you fix the bug? Why is the type system happy now that the bug is fixed?**

**Q5) Remove all of the casts from the `routeHarvest` method. This part of the code should have no warnings or errors now. Why does this work without us having to cast anything?**

**Q6) What do you think is the advantage of using Generics here over creating subclasses for the Sunflower Box and the Rose Box?**

The code should all be working now. Play around with the CLI for a bit and continue onto the next exercise to see how generics can be leveraged even further.

# Task 2 (Core)

> This exercise continues from Task 1 and uses the [`gardening.v2`](/app/src/main/java/gardening/v2/) package. You will need to reintroduce generic parameters for the `FlowerBox` but this time it will require more than one generic parameter.

The design in task 1 could potentially be improved a bit. We probably don’t want to be able to call methods on the flower related to harvesting if the flower hasn’t gone through a flower box yet. In this task, we’re going to divide up the responsibilities, having one group of objects be responsible for growing the flower, and another group of objects handling the responsibilities of a harvested flower.

Files for a `FlowerSeedling` super type with the sub types `RoseSeedling` and `SunflowerSeedling` have been provided. At the moment, `FlowerSeedling` is an abstract class, though you may use interfaces or abstract classes as you see fit but justify your choices. Implement them as described by the following.

The seedlings are going to act like your flowers when they were planted. Seedlings will grow in the `FlowerBox` until they are ready in which they can be harvested. However this time, harvesting won't be returning the same thing we put into our `FlowerBox`. Rather than returning a seedling, you will return a flower. For example, a `RoseSeedling` is able to create a new Rose by the seedling's `harvestFlower()` method.

Move all of the growth-related methods into the seedling. Some parts of the code have been moved for you, to give you an indication of these classes' responsibilities. The `getPrice()` method still belongs to all flowers, the `revealMessage()` method belongs to the `Rose`, and the `harvestSeeds()` method belongs to the `Sunflower`, though you may have to change the way these are implemented now that we’re performing this structural change.

As we have seedlings now instead of flowers before a flower box is used, the `package` command from Task 1 no longer makes sense. Instead, we will allow seedlings to be packaged in the same way flowers were packaged in Task 1. This means that seedlings will also need a `getPrice()` method and both flowers and seedlings can be put into the same package to be sold. A `Sellable` interface that defines `getPrice()` and is implemented by both seedlings and flowers has been provided.

You will also need to add a generic parameter in some capacity to your seedling types. This is so that when we create a `FlowerBox` that takes in `RoseSeedling`'s, we force it to return `Rose` flowers, i.e., through the type system, the compiler enforces that our seedling and flower are a pair. Your `FlowerBox` will then need two generic parameters, one that represents the type of objects we add into our flower box i.e., seedlings, and one that represents the type of objects we get from our flower box i.e., flowers. Both parameters need appropriate upper bounds.

> The compiler errors will be fixed by adding in your generic parameters at the various TODOs.

> You’ll notice that some methods are protected instead of public. This is so that the method can be used in the flowers package i.e., can be called by the `FlowerBox`, but can’t be called in the `FlowerSystem`. You may find this useful in coming up with a tidy design.

# Task 3 (choice)

> This task uses the [`gardening.v3`](/app/src/main/java/gardening/v3/) package.

For this exercise, our Flowers' have returned to how they were like in Task 1. Almost all of the code has been implemented for you here. Have a look at the `FlowerSystem` file to see that we have two different types of flower boxes now:

- `StandardFlowerBox` - This is the same as the flower box from the previous examples, but it is now inheriting from and abstract parent class `FlowerBox`.
- `MutatedFlowerBox` - With this flower box, we are able to plant flowers like with the `StandardFlowerBox`, however, when they are harvested, they will have had a `Mutation` applied to them. When instantiating a `MutatedFlowerBox`, we need to equip it with a `Mutator` that works with the given type of flowers. For example, if we want a `MutatedFlowerBox` that takes in Roses then we need to equip the `MutatedFlowerBox` with some form of RoseMutator. By using generics, we can enforce that the type of Mutator matches the type of Flower, that way a `SunflowerMutator` can’t be applied to Roses.

Both of these classes extend from an abstract parent class `FlowerBox` which defines the commonality between them and the abstractions to be overridden.

These classes have been provided and the logic has been implemented, however the code currently does not compile as the definitions for the generic parameters used in the `StandardFlowerBox` and `MutatedFlowerBox` are missing. An abstract class `FlowerBox` has been created with generic parameters put in. Without changing any other files, define the generic parameters for the subclasses `StandardFlowerBox` and `MutatedFlowerBox` such that it conforms with the parent class and the rest of the code. Have a look through the code base to see exactly how these objects are used.

Criteria:

- `MutatedFlowerBox` should have a generic parameter for the flowers that can be added to the box. This should have an upper bound of `Flower` similar to task 1.
- `MutatedFlowerBox` should also have a generic parameter for the type of `Mutation` that gets applied. Although `Mutation` can also be treated as a `Flower` (it’s a `Flower` decorator), we don’t want the mutation used to be something that is not a mutation, i.e., a normal flower. This parameter should have an upper bound of `Mutation`
- Observe that the `Mutation` type also has a generic parameter to link it with the type of `Flower` being mutated. You will need to make sure the `Mutation` upper bound mentioned in your `MutatedFlowerBox` is consistent with the parameter that represents the input flower type.

<details>
  <summary>Hint</summary>
  <ul>
    <li>
      Although the parent class <code>FlowerBox</code> refers to two generic
      parameters, the subclass <code>StandardFlowerBox</code> only needs one
      parameter. How can we define only a single parameter in
      <code>StandardFlowerBox</code>
      while satisfying the two required by the <code>FlowerBox</code>?
    </li>
    <li>
      <code>MutatedFlowerBox</code> does require two different generic
      parameters, however.
    </li>
  </ul>
</details>
