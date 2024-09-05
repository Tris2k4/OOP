# 🛡️ Core Exercise: Enrolments

Task 1
## Part A
- Inapproriate string comparision using to check grade. Refering to the snippet code, the design should use equal method to check the content of the string not the != since this can lead to an unexpected result if they are different objects in memory

- Additionally, the design also encounter with some issue such as encapsulation. Refering to the code, the enrolment class doesn't need to know the specific grade string value. To fix this, we can make a method in a grade class and invoke that method in enrolment class just to encapsulate the data.

- To solve the issue, we just need to delete all the condition of the return statement, and fix that to return grade.isPass() && !grade.isFail();

- In the grade class add 2 methods which are grade.isPass() and grade.isFail(). Inside grade.isPass() add return this.getMark() >= 50 && !isFail();

- For isFail method add return this.getGrade().equals("FL") || this.getGrade().equals("UF"); [using equals method instead of using !=]

## Part B
```java
    if (enrolment.getGrade().getMark() >= 50 && enrolment.getGrade().getGrade() != "FL"
            && enrolment.getGrade().getGrade() != "UF") {
        valid = true;
        break;
    }
```
- change to enrolment.hasPassedCourse()

## Part C
- In the enrolments codebase, the class courseoffering violates the LSP principle since this class did not substitute the super class


## Reflection on challenges 
- Some challenges that I have faced during this lab is to spot out the code smells by applying different software principles and design a solution to fix the problem
- In this lab, I also faced some issue with using some of the methods in functional paradigm in Java since I'm not familiar with this language yet as well as has some difficulty on understanding what does a comparator do


## Bank Contract
- The code is consistent with the preconditions and postconditions because it ensures that the methods is consistent, maintaining the correctness and expected behaviours in all scenarios. By having preconditions, it makes sure some sort of conditions must be satisfy before a method can execute properly. If the preconditions are not satisfy the code block with not be executed. By having post conditions, it also ensures that the conditions must be true after the methods executed
- Balance >= 0 is a class variant for both classes because whenever we make a deposit or withdrawn, it will always ensures the balance will >= 0 and this is precondition, as for postcondition, it will always guarantee that the balance when we make a withdrawn will always >= 0 and not becomes negative
- Yes since LoggedBankAccount maintains the same as the super class while adding some additional operation such as logging