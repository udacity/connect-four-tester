# Grading the Intro to Java (cs046) Final Project

To make things easier with grading, here’s a grading program that can help us assess student’s connect four agent code. It's a modified version of the given project files. (GraderRunner.java and GamesStatistics.java are added to the project, and some access modifiers are changed within Connect4Game.)

## Usage instructions

1. Copy the student's `MyAgent.java` file to the `src/main/java` directory.
2. Run `./gradlew` on the command line. (Windows users, use `.\gradlew` instead)
3. The program outputs two main points:
	1. Game result statistics
    1. Lint output for Java naming conventions and javadoc, using the `checkstyle` program


### Game Result Statistics

The tool plays the Connect Four game between the student’s agent and the other agents a whole bunch of times (say, a thousand times) and spits out something like:

    Number of games played: 1000
    # times Random Agent won: 27 (2.70%)
    # times My Agent won: 967 (96.70%)
    # times tied: 3 (0.30%)
    # of invalid moves: 0 (0.00%)

3. Once all the agents have finished playing, the win/loss and other information about each MyAgent vs. *Agent bout is printed in the terminal. Here's an example output:

```text
Game Statistics for Yellow MyAgent:
----------------
Number of games played: 1000
# times My Agent won: 908 (90.80%)
# times Random Agent won: 86 (8.60%)
# times tied: 6 (0.60%)
# of invalid moves: 0 (0.00%)
----------------
Number of games played: 1000
# times My Agent won: 588 (58.80%)
# times Beginner Agent won: 386 (38.60%)
# times tied: 26 (2.60%)
# of invalid moves: 0 (0.00%)
----------------
Number of games played: 1000
# times My Agent won: 420 (42.00%)
# times Intermediate Agent won: 551 (55.10%)
# times tied: 29 (2.90%)
# of invalid moves: 0 (0.00%)
----------------
Number of games played: 1000
# times My Agent won: 193 (19.30%)
# times Brilliant Agent won: 764 (76.40%)
# times tied: 43 (4.30%)
# of invalid moves: 0 (0.00%)

Game Statistics for Red MyAgent:
----------------
Number of games played: 1000
# times My Agent won: 907 (90.70%)
# times Random Agent won: 91 (9.10%)
# times tied: 2 (0.20%)
# of invalid moves: 0 (0.00%)
----------------
Number of games played: 1000
# times My Agent won: 702 (70.20%)
# times Beginner Agent won: 283 (28.30%)
# times tied: 15 (1.50%)
# of invalid moves: 0 (0.00%)
----------------
Number of games played: 1000
# times My Agent won: 580 (58.00%)
# times Intermediate Agent won: 406 (40.60%)
# times tied: 14 (1.40%)
# of invalid moves: 0 (0.00%)
----------------
Number of games played: 1000
# times My Agent won: 222 (22.20%)
# times Brilliant Agent won: 763 (76.30%)
# times tied: 15 (1.50%)
# of invalid moves: 0 (0.00%)
```

As you can see, the MyAgent is played against each agent twice: first as the yellow player, and second as the red player. This checks that the student's `MyAgent` class functions correctly as both colored players.

---

## Ways this thing helps us grade

Playing the game 10 or 20 times manually can potentially have us miss a bug in the student's code that can only show up in certain games, depending on where each red/yellow token is and which slots are currently empty. Playing the game a thousand or so times tests the student's agent more games than we could sanely do by hand.

Also, getting statistics from a larger number of games can help us better judge whether or not the student's agent meets the requirements on the rubric.

It also automates checking for Java naming conventions and javadoc comments.

Automating these parts of the project helps us focus on the important code review aspect of the evaluation.

Finally, it lets us enjoy flashing colors on the screen. Have fun!

 # Archival Note 
 This repository is deprecated; therefore, we are going to archive it. However, learners will be able to fork it to their personal Github account but cannot submit PRs to this repository. If you have any issues or suggestions to make, feel free to: 
- Utilize the https://knowledge.udacity.com/ forum to seek help on content-specific issues. 
- Submit a support ticket along with the link to your forked repository if (learners are) blocked for other reasons. Here are the links for the [retail consumers](https://udacity.zendesk.com/hc/en-us/requests/new) and [enterprise learners](https://udacityenterprise.zendesk.com/hc/en-us/requests/new?ticket_form_id=360000279131).