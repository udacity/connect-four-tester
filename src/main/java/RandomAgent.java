import java.util.Random;

public class RandomAgent extends Agent
{
    Random r;
    public RandomAgent(Connect4Game game, boolean iAmRed) {
        super(game, iAmRed);
        r=new Random();
    }
    
    public void move() {
        if(!myGame.boardFull()) {
            int i = r.nextInt(myGame.getColumnCount());
            while(getTopEmptySlot(myGame.getColumn(i))==null) {
                i = r.nextInt(myGame.getColumnCount());
            }
            
            moveOnColumn(myGame, i);
        }
    }
    
    public void moveOnColumn(Connect4Game game, int i) {
        Connect4Slot topEmptySlot = getTopEmptySlot(game.getColumn(i));
        if(!(topEmptySlot==null)) {
            if(iAmRed) {
                topEmptySlot.addRed();
            } else {
                topEmptySlot.addYellow();
            }
        }
    }
    public Connect4Slot getTopEmptySlot(Connect4Column column) {
        int topEmptySlot=-1;
        for(int i = 0; i<column.getRowCount(); i++) {
            if(!column.getSlot(i).getIsFilled()) {
                topEmptySlot=i;
            }
        }
        if(topEmptySlot<0) {
            return null;
        } else {
            return column.getSlot(topEmptySlot);
        }
    }
    
    public String getName() {
        return "Random Agent";
    }
}
