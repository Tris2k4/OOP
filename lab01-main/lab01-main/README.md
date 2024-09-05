# Lab 01

## Due: Week 2, Monday, 10:00 am [Sydney Local Time](https://www.timeanddate.com/worldclock/australia/sydney)

- [Lab 01](#lab-01)
  - [Due: Week 2, Monday, 10:00 am Sydney Local Time](#due-week-2-monday-1000-am-sydney-local-time)
- [Aims](#aims)
  - [Structure of Repository](#structure-of-repository)
- [Setup](#setup)
- [Exercises](#exercises)
  - [Core Exercises/Blogging](#core-exercisesblogging)
  - [Choice Exercises](#choice-exercises)
- [Submission](#submission)
- [Marking](#marking)

# Aims

**What is this lab aiming to achieve?**

Lab 01 is a chance for you to get your feet wet in the course with setting up and some basic exercises - much of your learning in this course will be experiential - learning through doing, and it starts now!

**Setup and Familiarisation**

- Become familiar with course practices for labs
- Revise how to use GitLab effectively
- Gain familiarity with Java development using the VSCode IDE

**Java Programming**

- Learn the basic syntax of the Java Programming Language
- Implement a simple class

**Reflective Learning**

- Become familiar with the blogging process and set goals for the course

Lab authored by Robert Clifton-Everest, Braedon Wooding, Nick Patrikeos & Alvin Cherk.

<h1 style="color: red;">Warning: Differences with Previous Terms</h1>

The repository structure has changed from previous terms. Please make sure you are using **Gradle 8.5** and **Java JDK 17** as per [setup instructions](#setup).

## Structure of Repository

The repository is now structured as follows:

```
lab01
├── README.md
├── app
│   ├── build
│   ├── build.gradle
│   └── src
│       ├── main
│       └── test
├── blog.md
├── config
│   └── checkstyle.xml
├── gradle
│   └── wrapper
├── gradlew
├── gradlew.bat
├── images
├── settings.gradle
└── spec
    └── spec files...
```

- [`app/src`](/app/src/): Is where all of the source code and tests will exist. It is still important to open VSCode within the `lab01` folder and not `app` or `app/src`.
- `app/build`: Is where the compiled code will be placed. You may need to delete this folder if you are having issues with VSCode and Gradle.
- [`config`](/config/): Contains configuration files for the project. You should not need to modify these files.
- [`gradle`](/gradle/): Contains the Gradle wrapper and Gradle metadata. You should not need to modify these files.
- [`spec`](/spec/): Contains the specification files for the exercises. You should not need to modify these files.

# Setup

- [🕛 Git Setup & GitLab Crash Course](https://nw-syd-gitlab.cseunsw.tech/COMP2511/24T2/content/-/blob/main/setup/docs/Git.md): If you need a refresher on Git or need to setup Git again, complete this exercise. We will be using git for all lab and assignment work throughout the course - so make sure you're comfortable using it. If you're confident with git you can skip this exercise.
- Follow these instructions to set up your local development environment for Java.
  - [🍎 MacOS Local Setup - Java JDK 17 & Gradle 8.5](https://nw-syd-gitlab.cseunsw.tech/COMP2511/24T2/content/-/blob/main/setup/docs/MacOSSetup.md)
  - [🪟 Windows Local Setup - Java JDK 17 & Gradle 8.5](https://nw-syd-gitlab.cseunsw.tech/COMP2511/24T2/content/-/blob/main/setup/docs/WindowsSetup.md)
  - [🐧 Linux/WSL (Ubuntu) Local Setup - Java JDK 17 & Gradle 8.5](https://nw-syd-gitlab.cseunsw.tech/COMP2511/24T2/content/-/blob/main/setup/docs/LinuxWSLSetup.md)
- [VScode Setup](https://nw-syd-gitlab.cseunsw.tech/COMP2511/24T2/content/-/blob/main/setup/docs/VSCodeSetup.md): Follow these instructions to set up VSCode for both local and CSE development.
  - We use VSCode in this course.
  - You are welcome to use any IDE you like, but we will only be able to provide support for you in classes, help sessions on the forum for VSCode - so you will need to and research solutions to problems yourself if you want to use a different IDE
  - Don't use a text editor as we will be dealing with large projects with many files. Over the course we will explore how to use VSCode to make coding life easier.

# Exercises

## Core Exercises/Blogging

In order to achieve full marks for the lab, you will need to complete the following core exercises and blogging:

- [🔢 Core Exercise: Average](/spec/CoreAverage.md)
- [🍌 Core Exercise: Splitter](/spec/CoreSplitter.md)
- [🛰️ Core Exercise: Satellite](/spec/CoreSatellite.md)
- [💭 Core Blogging: Goal Setting & Looking Ahead](/spec/CoreGoals.md)

## Choice Exercises

The following exercises are optional but are good if you want to practice Java Programming and design thinking to warm up for the course!

- [🍕 Choice Exercise: Pineapple on Piazza](/spec/ChoicePineapple.md)
- [📚 Choice Exercise: Scrabble Subwords](/spec/ChoiceScrabble.md)
- [⛏️ Choice Exercise: Physical Design](/spec/ChoicePhysical.md)

# Submission

> **Make sure you have committed all of your changes before submitting.**

To submit, push your commits to the **main** branch.

# Marking

When marking your design and programming lab exercises, the following aspects are considered:

- Exercise is submitted on time;
- Code passes pipeline with provided JUnit tests / your tests;
- Code solves the problem and is not a hardcoded solution;
- Code is written cleanly and with good style;
- Solution is well-designed, using the design principles and patterns intended by the activity;
- Answers to theory questions address all aspects of the question;
- Any blog posts are consistent with the code/design being described;
- Reflections and rationale are comprehensive and thoughtful
- Any other requirements are outlined in the problem specification.

Many lab exercises in future weeks will consist of a practical design and programming exercise and a written blog post reflecting on the activity. During marking you should bring up your code and your blog post and talk through your solution, thought process and reflections using your blog post as a prompt.
