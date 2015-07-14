public class GamesStatistics {
    // declare these public because there's no need to create getters/setters.
    // I trust that you won't make this program explode.
    public int numGames;

    public int numRedWon;
    public int numYellowWon;
    public int numTies;
    public int numInvalidMoves;

    public Agent redPlayer;
    public Agent yellowPlayer;

    /**
     * Constructs a GamesStatistics object for these two agents
     * playing against each other.
     */
    public GamesStatistics(Agent redPlayer, Agent yellowPlayer) {
        this.redPlayer = redPlayer;
        this.yellowPlayer = yellowPlayer;
    }


    /**
     * Prints out the statistics of the game.
     */
    public void printStatistics() {
        System.out.println("----------------");
        System.out.println("Number of games played: " + this.numGames);
        System.out.printf("# times %s won: %d (%.2f%%)\n", redPlayer.getName(),
                this.numRedWon,
                this.percentTotal(this.numRedWon));
        System.out.printf("# times %s won: %d (%.2f%%)\n",
                yellowPlayer.getName(), this.numYellowWon,
                this.percentTotal(this.numYellowWon));
        System.out.printf("# times tied: %d (%.2f%%)\n",
                this.numTies,
                this.percentTotal(this.numTies));
        System.out.printf("# of invalid moves: %d (%.2f%%)\n",
                this.numInvalidMoves,
                this.percentTotal(this.numInvalidMoves));
    }

    /**
     * Calculates the percentage of count / total and
     * normalizes it to the percentage (e.g., xx.xx)
     *
     * @param count the count to consider
     *              return the count as a percentage of the total games
     */
    public double percentTotal(double count) {
        return count / numGames * 100;
    }
}
