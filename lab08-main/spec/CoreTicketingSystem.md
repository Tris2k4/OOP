# 🎟️ Core Exercise: Ticketing System

In this lab, you will be utilising the singleton and decorator patterns to create a ticketing system and send the user their receipt in different ways.

> The tasks are independent of one another, so if you get stuck on one you can move to the next without it impacting your ability to complete it.

# Task 1) Singleton Pattern

Inside of [`Application`](/app/src/main/java/ticketingsystem/Application.java), there is a main function simulating 7 users all trying to buy tickets at the same time below is the current output of the program (It may be slightly different on your computer).

```
There is now 2 left!
There is now 2 left!
There is now 2 left!
Yay you bought a ticket!
Yay you bought a ticket!
Yay you bought a ticket!
Sending an email to user5@email.com
Sending an email to user6@email.com
Sending an email to user1@email.com
Sending an sms to 6666666666
Sending your mail to Apocalypse Peaks, Antarctica
Sending your mail to UNSW
The total cost is: 50.6
------------------------------------------
The total cost is: 50.5
------------------------------------------
The total cost is: 50.0
------------------------------------------
There is now 0 left!
Yay you bought a ticket!
Sending an sms to 2222222222
Sending your mail to The Moon
There is now 0 left!
Yay you bought a ticket!
Unfortunately there are no tickets left :(
Sending an email to user3@email.com
Sending an sms to 3333333333
The total cost is: 50.6
------------------------------------------
The total cost is: 50.1
------------------------------------------
There is now 4 left!
Yay you bought a ticket!
Sending your mail to No Name, Colorado
The total cost is: 50.5
------------------------------------------
```

> Looking at the output do you think the following are desired behaviours?
>
> - 3 people bought the 3rd ticket
> - 2 people bought the last ticket
> - 6 people managed to get tickets
> - The output is all messed up

Fix the code so that the program produces the desired output without changing `Application` (except for the initialisation of [`TicketingSystem`](/app/src/main/java/ticketingsystem/TicketingSystem.java)) or [`User`](/app/src/main/java/ticketingsystem/User.java).

> The users are buying the tickets in their `run()` function. `run()` is what is called by `Thread` when `.start()` is called

# Task 2) Decorator Pattern

```java
ReceiptSender receipt = new Receipt();
double cost = receipt.getCost();
receipt.send();
if (email != null) {
    ReceiptSender emailReceipt = new EmailReceipt(email);
    cost += emailReceipt.getCost();
    emailReceipt.send();
}
if (sms != null) {
    ReceiptSender SMSReceipt = new SMSReceipt(sms);
    cost += SMSReceipt.getCost();
    SMSReceipt.send();
}
if (address != null) {
    ReceiptSender postReceipt = new PostReceipt(address);
    cost += postReceipt.getCost();
    postReceipt.send();
}
System.out.println("The total cost is: " + cost);
```

What design problems can you see in the above code?

Consider having to store the receipts for multiple ticket purchases. This would need to include all the selected receipt options and the total price. Do you think the current design easily allows for this extension?

Looking at the code inside of `buyTicket()` in [`TicketingSystem`](/app/src/main/java/ticketingsystem/TicketingSystem.java) what can be done to improve its design? Implement your solution. After your refactoring, the regression test should remain passing!

## Final Expected output

```
There is now 4 left!
Yay you bought a ticket!
Sending an email to user1@email.com
Sending your mail to UNSW
The total cost is: 50.5
------------------------------------------
There is now 3 left!
Yay you bought a ticket!
Sending an email to user5@email.com
The total cost is: 50.0
------------------------------------------
There is now 2 left!
Yay you bought a ticket!
Sending an email to user6@email.com
Sending an sms to 6666666666
Sending your mail to Apocalypse Peaks, Antarctica
The total cost is: 50.6
------------------------------------------
There is now 1 left!
Yay you bought a ticket!
Sending an sms to 2222222222
Sending your mail to The Moon
The total cost is: 50.6
------------------------------------------
There is now 0 left!
Yay you bought a ticket!
The total cost is: 50.0
------------------------------------------
Unfortunately there are no tickets left :(
Unfortunately there are no tickets left :(
```

If the output is slightly different don’t worry as long as it is working as expected and you have refactored the code to fix the design problems in task 2
