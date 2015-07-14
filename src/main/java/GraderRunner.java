import java.util.List;
import java.util.ArrayList;

public class GraderRunner {

    // play the game many times and get win/lose/tie/invalid statistics from them
    public static final int NUM_GAMES = 1000;

    public static void main(String[] args) {
        List<GamesStatistics> yellowStatistics = playMyAgentAsYellow();
        List<GamesStatistics> redStatistics = playMyAgentAsRed();


        // Once all games have been played, print out the statistics
        System.out.println("\033[33mGame Statistics for Yellow MyAgent:\033[0m");
        for (GamesStatistics gameStats : yellowStatistics) {
            gameStats.printStatistics();
        }

        System.out.println();

        System.out.println("\033[31mGame Statistics for Red MyAgent:\033[0m");
        for (GamesStatistics gameStats : redStatistics) {
            gameStats.printStatistics();
        }

        System.exit(0); // Stop the game; this closes any Java swing hullabaloo.
    }

    /**
     * Plays
     * @return
     */
    public static List<GamesStatistics> playMyAgentAsYellow() {
        Connect4Game game = new Connect4Game(7, 6); // create the game; these sizes can be altered for larger or smaller games
        Agent myAgent = new MyAgent(game, false); // create the yellow player, any subclass of Agent

        // the agents who are going to play against MyAgent
        List<Agent> redAgents = new ArrayList<Agent>();
        redAgents.add(new RandomAgent(game, true));
        redAgents.add(new BeginnerAgent(game, true));
        redAgents.add(new IntermediateAgent(game, true));
        redAgents.add(new BrilliantAgent(game, true));

        // Play all games
        List<GamesStatistics> games = new ArrayList<GamesStatistics>();
        for (Agent agent : redAgents) {
            games.add(playGame(NUM_GAMES, game, agent, myAgent));
        }
        return games;
    }

    public static List<GamesStatistics> playMyAgentAsRed() {
        Connect4Game game = new Connect4Game(7, 6); // create the game; these sizes can be altered for larger or smaller games
        Agent myAgent = new MyAgent(game, true); // create the yellow player, any subclass of Agent


        // the agents who are going to play against MyAgent
        List<Agent> redAgents = new ArrayList<Agent>();
        redAgents.add(new RandomAgent(game, false));
        redAgents.add(new BeginnerAgent(game, false));
        redAgents.add(new IntermediateAgent(game, false));
        redAgents.add(new BrilliantAgent(game, false));

        // Play all games
        List<GamesStatistics> games = new ArrayList<GamesStatistics>();
        for (Agent agent : redAgents) {
            games.add(playGame(NUM_GAMES, game, myAgent, agent));
        }
        return games;
    }

    /**
     * Plays the games NUM_GAMES times and tracks the game results (wins/ties)
     *
     * @param NUM_GAMES    the number of games to play
     * @param game         the connect 4 game to play with
     * @param redPlayer    the red player of the game.
     * @param yellowPlayer the yellow player of the game.
     * @return the GamesStatistics from playing these games
     */
    public static GamesStatistics playGame(final int NUM_GAMES, Connect4Game game, Agent redPlayer, Agent yellowPlayer) {
        GamesStatistics gameStatistics = new GamesStatistics(redPlayer, yellowPlayer);
        gameStatistics.numGames = NUM_GAMES;

        Connect4Frame gameFrame = new Connect4Frame(game, redPlayer, yellowPlayer); // create the game window

        // play the game
        for (int i = 0; i < NUM_GAMES; i++) {
            gameFrame.newGame();
            gameFrame.playToEnd();

            if (game.gameWon() == 'R') {
                gameStatistics.numRedWon++;
            } else if (game.gameWon() == 'Y') {
                gameStatistics.numYellowWon++;
            } else if (game.boardFull()) {
                gameStatistics.numTies++;
            }
        }
        gameFrame.dispose();
        return gameStatistics;
    }
}
