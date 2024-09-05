# Assignment II Pair Blog Template

## Task 1) Code Analysis and Refactoring ⛏️

### a) From DRY to Design Patterns

[Links to your merge requests](https://nw-syd-gitlab.cseunsw.tech/COMP2511/24T2/teams/H18A_JALAPENO/assignment-ii/-/merge_requests/5)

> i. Look inside src/main/java/dungeonmania/entities/enemies. Where can you notice an instance of repeated code? Note down the particular offending lines/methods/fields.

- The repeated code is in the method move of Mercenary and Zombie toast. Specifically, in file Mercenary.java at line 102 and file ZombieToast.java at line 26 since it is repeating checking for whether the player get effective potion is InvincibilityPotion.

> ii. What Design Pattern could be used to improve the quality of the code and avoid repetition? Justify your choice by relating the scenario to the key characteristics of your chosen Design Pattern.

- I will use Strategy Design Pattern since this pattern will allow us to avoid code repetition, long method as well as make the code be less coupling and more cohesion.

> iii. Using your chosen Design Pattern, refactor the code to remove the repetition.

- I create a MovementStrategy interface with different movement strategies and implement it. In the conditional statement across each file such as mercenery, zombie, spider, I replace it with the strategy that I have implemented which makes the code become less coupling and able to avoid code repetition.


### b) Observer Pattern

> Identify one place where the Observer Pattern is present in the codebase, and outline how the implementation relates to the key characteristics of the Observer Pattern.

[Answer]

#### Identification of the Observer Pattern in the codebase

- Location: The interation between Switch class in the folder entities and Bomb class in the folder collectables is the Observer pattern.
- Roles: Switch acts as the Subject, and Bomb acts as the Observer.

#### Outline how the implementation relates to the key characteristics of the Observer Pattern.

##### Subjects Switch:

- The Switch class contains a list of subcribed bombs, has method to add and remove Bomb(function subcribe and unsubcribe).
- When a Boulders land on the Switch, it will trigger the Switch. It will make the Switch activated and notifies all subcribed bombs by calling their notify function. This behavior is executed in the onOverlap function when a switch overlaps with a boulder, setting activated to true and notifying bombs of the change.

##### Observers Bomb

- The Bomb class has a subcribe function to register with a Switch.
- When receive a notificaction through the notify function, the bomb responds by executing its explode method. This method simulate the bomb explosion by affecting the game entites within its blast radius, show the Bomb bahavior react to state changes in the Switch.

### c) Inheritance Design

[Links to your merge requests](/put/links/here)

> i. Name the code smell present in the above code. Identify all subclasses of Entity which have similar code smells that point towards the same root cause.

- The code smell present in the above code is unused methods such as onOverlap, onMovedAway, onDestroy. The similar code smell also present in door.java, boulder.java, player.java, portal.java, switch.java and wall.java


> ii. Redesign the inheritance structure to solve the problem, in doing so remove the smells.

- Delete all the unused method in the subclasses mentioned above.


### d) More Code Smells

[Links to your merge requests](https://nw-syd-gitlab.cseunsw.tech/COMP2511/24T2/teams/H18A_JALAPENO/assignment-ii/-/merge_requests/2)

> i. What design smell is present in the above description?

- The code smell present in the above code is these methods such as onOverlap, onMovedAway, onDestroy have been inherited from the superclass which is entity so we don't need to rewrite the method and override causing code repetition unless if we want to modify the behaviour of the method in a different way. The similar code smell also present in key.java, potion.java, wood.java, treasure.java, zombietoastspawner.java, buildable.java, bomb.java, sword.java, door.java, boulder.java, player.java, portal.java, switch.java and wall.java

#### Code smell in the above description: Shotgun Surgery

- Shotgun Surgery: In the above description, the evidence is when changing how item pick-up is handled, we will require numerous small change in many different parts of the system.
  > ii. Refactor the code to resolve the smell and underlying problem causing it.

- Delete all the unnecessary method in the subclasses mentioned above.


#### Refactor the code

- To resolve the issue of the Player class being too tightly coupled with the pick up items function for various entities, we can introduce an interaction model that centralize the pick-up logic within the Player class while leveraging polymorphism through the Pickupable interface. This will make it eaiser to manage and extend how various items are pick up and intergrated into the game.
- Step 1: Define the Pickupable Interface.
  public interface Pickupable {
  boolean pickUp(Player player);
  }
- Step 2: Implement Pickupable in Collectable Classes
  Each collectable item class will implement the Pickupable interface. This encapsulate the logic for what happens when the item is picked up directly within each item class.
  For regular items:
  @Override
  public boolean pickUp(Player player) {
  return player.getInventory().add(this);
  }
  For the Treasure class, additionally update the treasure count:
  @Override
  public boolean pickUp(Player player) {
  player.setCollectedTreasureCount(1);
  return player.getInventory().add(this);
  }

- Step 3: Modify pickUp function in Player Class and onOverlap function in collectables item class.
  In the Player class, replace inventory.add((InventoryItem) item) with ((Pickupable) item).pickUp(this) to utilize the new interface.
  In collectable item classes, change ((Player) entity).pickUp(this) to pickUp((Player) entity) in the onOverlap method to correctly invoke the pickUp method.
- These changes effectively centralize and simplify the item pickup process, reducing the amount of errors and effort needed for future modifications related to item collection behaviors.

### e) Open-Closed Goals

[Links to your merge requests](/put/links/here)

> i. Do you think the design is of good quality here? Do you think it complies with the open-closed principle? Do you think the design should be changed?

- The design is not in a good quality since it violates the open-closed principle since there is a switch statement, hence this design should be changed

> ii. If you think the design is sufficient as it is, justify your decision. If you think the answer is no, pick a suitable Design Pattern that would improve the quality of the code and refactor the code accordingly.

- I have picked strategy pattern to solve the issue. What I have done is I change the goal to an interface with many strategy such as And, Or, etc with the method toString and achieved.

### f) Open Refactoring

[Merge Request 1](https://nw-syd-gitlab.cseunsw.tech/COMP2511/24T2/teams/H18A_JALAPENO/assignment-ii/-/merge_requests/3)

#### The effects of potions (invisibility and invincibility) has been implemented using a State Pattern. However, the State Pattern hasn't been used particularly effectively here and as a result, there is poor design.

[Briefly explain what you did]

- 1. Define the applyBuff method in the PlayerState class. The state pattern is used to encapsulate the behavior associated with a particular state. So that's why I try define the applyBuff method in playerState class to ensure that each state can handle its specific logic.
- 2. Implement the applyBuff Method in each Concrete State class. Each state (BaseState, InvicibleState and InvisibleState) has its own unique behavior. Implementing applyBuff in each state class ensures this behavior is encapsulated within the state. Also help simplify the Player class, making it easier to manage and understand.
- 3. Refactor the Player class to use state pattern for buff application. By delegating the buff logic to the player states, I use state pattern to be more effectively. This make the code more maintainable and extensible, as adding new states in the future or modifying the existing one without impacting the Player class.

[Merge Request 2](/put/links/here)

#### The current implementation of buildable entities contains a significant amount of hard coding. Think about how you can improve this.

[Briefly explain what you did]

- 1. Added a private durability field to Buildable class. This will encapsulate the common attribute method for all buildable items, allowing reuse and management of of this property.
- 2. Added getDurability method and decreaseDurability method in in the Buildable class. The first method provide a common method for all buildable items to access their durability. The second method reduce the durability of an item and remove if from inventory of the player if durability reaches zero, centralizing the durabilitiy management logic.
- 3. Implement use method with decreaseDurability in Bow and Shield classes. Utilized the shared decreaseDurability method from the Buildable class to manage durability reduction each time these items are used.

## Task 2) Evolution of Requirements 👽

### a) Microevolution - Enemy Goal

[Links to your merge requests](https://nw-syd-gitlab.cseunsw.tech/COMP2511/24T2/teams/H18A_JALAPENO/assignment-ii/-/merge_requests/8)

**Assumptions**

- None

**Design**

- Add a new file name EnemyGoalStrategy.java
- Create a new test file for enemy goal

**Changes after review**

[Design review/Changes made]

**Test list**

- Test basic enemies goal
- Test enemies goal with spawner

**Other notes**

[Any other notes]

### Choice 1 (Insert choice)

[Links to your merge requests](https://nw-syd-gitlab.cseunsw.tech/COMP2511/24T2/teams/H18A_JALAPENO/assignment-ii/-/merge_requests/11)

**Assumptions**

- None

**Design**

- Create a SunStone class as a subclass of entity, Sceptre class and MidnightArmour as a subclass of buildable
- Add logic for build sceptre and midnight armour

**Changes after review**

[Design review/Changes made]

**Test list**

- Creating a sceptre test
- Mind cont
- Testing treasure goal by pick up sun stone
- Test building midnight_armour
- Test building midnight_armour with a zombie

**Other notes**

[Any other notes]

### Choice 2 (Logic Switches)

[Links to your merge requests](https://nw-syd-gitlab.cseunsw.tech/COMP2511/24T2/teams/H18A_JALAPENO/assignment-ii/-/merge_requests/9)

**Assumptions**

- Assumed that logical entities (such as light bulbs and switch doors) need to interact with conductors (wires, switches) based on logical conditions specified (and, or, xor, co_and).
- Assumed that all entities react correctly based on their logical states and propagate state changes throughout the game map.

**Design**



- Entities and Logic: Introduced logical entities (LightBulb, SwitchDoor) and conductors (Wire, Switch) with methods to check and update their state based on the surrounding entities' states and the logical conditions specified.

- Interaction Mechanics: Enhanced the Switch and Wire classes to support activating/deactivating based on the proximity of other active conductors, implementing a propagation method to ensure that all logical state changes trigger updates throughout connected systems.

**Changes after review**

[Design review/Changes made]
- After initial review, updated the propagation logic to prevent infinite loops and ensure that activation states are updated before propagation.
- Enhanced the Bomb class to handle logical activation and deactivation, ensuring that it interacts with the environment according to its configured logic.

**Test list**

[Test List]
- Logical Entity Activation: Tested the activation and deactivation of Light Bulbs and Switch Doors under various logical conditions.
- Conductor Propagation: Ensured that changes in the state of a Switch or a Wire correctly propagate through the connected system, affecting adjacent logical entities.
- lightBulb
- lightBulbAnd
- lightBulbXor
- lightBulbCoAnd
- orSwitchDoorTest
- andSwitchDoorTest
- xorSwitchDoorTest
- coandSwitchDoorTest
- orAndxorBombTest
- andAndcoadBombTest
**Other notes**

[Any other notes]

### Choice 3 (Insert choice) (If you have a 3rd member)

[Links to your merge requests](/put/links/here)

**Assumptions**

[Any assumptions made]

**Design**

[Design]

**Changes after review**

[Design review/Changes made]

**Test list**

[Test List]

**Other notes**

[Any other notes]

### f) Microevolution - Enemy Goal

## Task 3) Investigation Task ⁉️

[Merge Request 1](https://nw-syd-gitlab.cseunsw.tech/COMP2511/24T2/teams/H18A_JALAPENO/assignment-ii/-/merge_requests/8)

[Briefly explain what you did]
- Code Modification in ZombieToastSpawner.java: Enhanced interaction logic to allow the destruction of the ZombieToastSpawner if the player is adjacent and has a weapon.
- Test Adjustments in ZombieTest.java: Updated tests to reflect the new behavior of the ZombieToastSpawner, ensuring it was destroyed

[Merge Request 2](https://nw-syd-gitlab.cseunsw.tech/COMP2511/24T2/teams/H18A_JALAPENO/assignment-ii/-/merge_requests/13)

[Briefly explain what you did]
- Code Modification in Key.java: Altered the pickUp method within the Key class to restrict the player from picking up more than one key at a time.
- Test Adjustments for Key Handling: change the logic test for cannotPickupTwoKeys test case in DoorsKeysTest.java.