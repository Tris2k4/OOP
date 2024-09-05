## Task 1: Metric
- Observer pattern: Grapher and Panel

## Task 2: Traffic light
- What are the different states? Green Light, Red Light, Yellow Light

- What are the actions/transitions between each state? Red to Green, Green to Yellow and Yellow to Red. After added pedestrian light, there will be extra transition such as Red to Pedestrian if there is Pedestrian and Pedestrian to Green
![alt text](<Copy of Board-5.jpg>)

- How does the initial code break the open/closed principle, and how does the state pattern fix it?
The initial code breaks this principle since it requires modification to add new functionality. The State Pattern fixes this by allowing the behavior of an object to change based on its internal state, without modifying its class.

What is the difference between the Strategy Pattern and the state pattern? What makes this an example of the State Pattern?
The State pattern deals with what (state or type) an object is whereas the Strategy pattern deals with how an object performs a certain task. An example of the State pattern in this case is the transition of the light can be understand as the change of state of an object.

Core Exercise: Check-in
How do you think you are going in the course?
Everything is so so.
What has been the most difficult/challenging part of the course so far?
Apply design principles and design patterns to solve a problem
Look back at the goals you set in Week 1. How are they tracking?
They are maybe on the track.
What is one thing you want to improve for the second half of the term?
I will read leture sides and lecture code after lecture live stream.