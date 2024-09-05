Task 1
Q1: Why is this approach considered good design? What principles does it makes use of?
[This approach is considered as a good design since it used UML Class diagrams to help clients can understand easily, therefore this design had used KISS principle to change things from complex to simple. Refering to abstraction, this diagram helps us can understand the complex system without seeing the complex implementation details. As for encapsulation, it provides us which classes such as hotel, room or booking encapsulate the data such as room detail, booking dates, etc. Lastly, this approach is considered as a good design since they proivded us modularisation. For example, the system is divided into distinct modules such as hotel, room, booking and booking system controller. Each module has a specific responsibility which makes the system easier to understand and easy to develop and maintain the system.]
Q2: What is meant by the term "black-box"? How are the tests inside BookingSystemTest black-box?
The term "black-box" meant that we write tests in the function 
[The term black-box means that examines software functionality but without peering into its structures or working. The tests inside BookingSystemTest are black-box since they verify the functionality of the booking system from the external perspective. For example, the tests provide the inputs and expected outputs to test whether that methods is on the right track or not.]
Task 2
Q3: What does this method do? What does it return, and what side effects does it have?
[This method is trying to make a booking by iterating through a list of Room objects in the rooms list, if there is room desired for customer and the booking date is not overlap then it will return true otherwise return false. The side effects it have is if a booking is successful, the book method on a Room object will modify the state of that room by adding a new Booking to its list of bookings. Lastly, The availability of the room will change because it now has an additional booking for the specified dates.]

Q4: In your opinion, which is better quality code, Code A or B? Justify your answer.
[Code B is better since code B is since code B is more readable and as for code A, it has redundant variable room which unnecessary. Code B has applied KISS principle whereas code A doesn't]

Task 3
Q5: What are some code smells (features of the code that make it poor quality) present in this method?
[The feature of the code that make it poor quality is it repeats too many times the if (room instanceof StandardRoom) and the return true or false, unnecessary of conditional check. In general, it violates the DRY principle]

Task 4
Q6: Note down all of the code smells you can see.
[Repeating book and toJSON methods accross all the rooms. The add room method (code A) can be also identify as code smells since they involve redundant and unnecessary variable such as room = null]
Q7: Reflect on your thought process, the steps you took to refactor and how you have improved the design of the system.
[I have improved the design system by changing the interface to abstract class and deleted some of the repeating codes in the method booking and toJson across all the rooms and move these 2 methods into the superclass which is the abstract room class. For the add room method, I was uncommented code B and delete code A which improve the readability and avoid redundant and unnecessary variable]