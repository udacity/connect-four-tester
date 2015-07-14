import java.util.Random;

public class BeginnerAgent extends Agent
{
    Random r;
    public BeginnerAgent(Connect4Game game, boolean iAmRed) {
        super(game, iAmRed);
        r=new Random();
    }
    
    public void move() {
        int move = 0;
        //////System.out.println(toString() + "Moving...");
        int iCanWin = canWin(iAmRed);
        int theyCanWin = canWin(!iAmRed);
        if(iCanWin >= 0) {
            move=iCanWin;
            //System.out.println(toString() + ": I can win! Moving at " + move + ".");
        } else if(theyCanWin >= 0) {
            move=theyCanWin;
            //System.out.println(toString() + ": They can win! Moving at " + move + ".");
        } else {
            move=randomMove();
            //System.out.println(toString() + ": Move randomly! Moving at " + move + ".");
        }
        
        moveOnColumn(move);
    }
    
    public int randomMove() {
        int i = r.nextInt(myGame.getColumnCount());
        while(getTopEmptySlot(myGame.getColumn(i))==null) {
            i = r.nextInt(myGame.getColumnCount());
        }
        return i;
    }
    public void moveOnColumn(int i) {
        Connect4Slot topEmptySlot = getTopEmptySlot(myGame.getColumn(i));
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
    
    public int canWin(boolean red) {
        ////System.out.println("  Checking can win...");
        for(int i = 0; i < myGame.getColumnCount(); i++) {
            ////System.out.println("    Checking column " + i + "...");
            int tei = getTopEmptyIndex(myGame.getColumn(i));
            if(tei > -1) {
                ////System.out.println("    Column " + i + " has an empty slot at " + tei + ".");
                if(tei < myGame.getRowCount() - 3) { // if more than 3 tokens might be below this slot
                    ////System.out.println("      Checking if there's three tokens below...");
                    if(myGame.getColumn(i).getSlot(tei+1).getIsRed()==red && myGame.getColumn(i).getSlot(tei+2).getIsRed()==red && myGame.getColumn(i).getSlot(tei+3).getIsRed()==red) { // if a column win is available here
                        return i;
                    }
                }
                if(i < myGame.getColumnCount() - 3) { // if three tokens might be to the right
                    ////System.out.println("      Checking if there's three tokens to the right...");
                    if(checkIfEqual(red, myGame.getColumn(i+1).getSlot(tei), myGame.getColumn(i+2).getSlot(tei), myGame.getColumn(i+3).getSlot(tei))) {
                        return i;
                    }
                }
                if(i > 2) { // if three tokens might be to the left
                    ////System.out.println("      Checking if there's three tokens to the left...");
                    if(checkIfEqual(red, myGame.getColumn(i-1).getSlot(tei), myGame.getColumn(i-3).getSlot(tei), myGame.getColumn(i-2).getSlot(tei))) {
                        return i;
                    }
                }
            }
        }
        return -1;
    }
    public boolean checkIfEqual(boolean isRed, Connect4Slot slot1, Connect4Slot slot2, Connect4Slot slot3) {
        if(slot1.getIsFilled() && slot2.getIsFilled() && slot3.getIsFilled()) {
            if(slot1.getIsRed()==isRed && slot2.getIsRed()==isRed && slot3.getIsRed()==isRed) {
                return true;
            }
        }
        return false;
    }
    public int getTopEmptyIndex(Connect4Column column) {
        int topEmptySlot=-1;
        for(int i = 0; i<column.getRowCount(); i++) {
            if(!column.getSlot(i).getIsFilled()) {
                topEmptySlot=i;
            }
        }
        return topEmptySlot;
    }
    
    public String getName() {
        return "Beginner Agent";
    }
}
