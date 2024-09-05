## Design thinking
- To standardize the implementation of individual Satellite types, I developed an abstract class named Satellite. This class encompasses both implemented methods and abstract methods tailored to each Satellite type. This abstract class will have a 0..* to 1 composition relationship with the blackout controller

- Similarly, for individual Device types, I created an abstract class called Device. This class includes methods such as getter and setter, other operations and provides a common constructor for each Device type. This abstract class also has a 0..* to 1 compostition relationship with the blackout controlller.

- To establish some file operation, I decided to make a class file to contain all the methods and attributes that related to file operation. This class will have a 0..* to 1 composition relationship with the blackout controller as well as 1..* to 1..* with both satellite and device

- For individual Satellite types, I implemented three small subclasses: RelaySatellite, StandardSatellite, and TeleportSatellite. These subclasses inherit from the Satellite class and override the abstract moveToNextPos methods to accommodate their unique movement algorithms. 

- To handle different Device types, I created three subclasses: DesktopDevice, HandheldDevice, and LaptopDevice.

## Challenges that I have faced when do this assignment
- I feel like it is not just about coding but it is more about how i need to design for my solution, and to design the solution in an object oriented way makes me feel this is hard since this is my first time work on an assignment that requires a solution should follow an object oriented way

- Another thing I'm not familiar in this assignment is to think a solution that must follow the SOLID principle or Law of Demeter.


## Things that I have learned through this assignment
- Have a deeper understand what it looks like when design a solution that follows an object oriented design
- Understand the design principles such as SOLID, Law of Demeter