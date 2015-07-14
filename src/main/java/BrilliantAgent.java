import java.util.Random;

public class BrilliantAgent extends Agent
{
    Random r;
    public BrilliantAgent(Connect4Game game, boolean iAmRed) {
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
                if(i < myGame.getColumnCount() - 2 && i > 0) { // if two tokens might be to the right and one to the left
                    ////System.out.println("      Checking if there's two tokens to the right and one to the left...");
                    if(checkIfEqual(red, myGame.getColumn(i-1).getSlot(tei), myGame.getColumn(i+1).getSlot(tei), myGame.getColumn(i+2).getSlot(tei))) {
                        return i;
                    }
                }
                if(i < myGame.getColumnCount() - 1 && i > 1) { // if one token might be to the right and two to the left
                    ////System.out.println("      Checking if there's one token to the right and two to the left...");
                    if(checkIfEqual(red, myGame.getColumn(i-1).getSlot(tei), myGame.getColumn(i+1).getSlot(tei), myGame.getColumn(i-2).getSlot(tei))) {
                        return i;
                    }
                }
                if(i > 2) { // if three tokens might be to the left
                    ////System.out.println("      Checking if there's three tokens to the left...");
                    if(checkIfEqual(red, myGame.getColumn(i-1).getSlot(tei), myGame.getColumn(i-3).getSlot(tei), myGame.getColumn(i-2).getSlot(tei))) {
                        return i;
                    }
                }
                if(i < myGame.getColumnCount() - 3 && tei < myGame.getRowCount() - 3) { // if three tokens might be down and to the right
                    ////System.out.println("      Checking if three tokens might be down and to the right...");
                    if(checkIfEqual(red, myGame.getColumn(i+1).getSlot(tei+1), myGame.getColumn(i+3).getSlot(tei+3), myGame.getColumn(i+2).getSlot(tei+2))) {
                        return i;
                    }
                }
                if(i < myGame.getColumnCount() - 2 && i > 0 && tei < myGame.getRowCount() - 2 && tei > 0) { // if two tokens might be down and to the right and one up and to the left
                    ////System.out.println("      Checking if two tokens might be down and to the right and one up and to the left...");
                    if(checkIfEqual(red, myGame.getColumn(i+1).getSlot(tei+1), myGame.getColumn(i-1).getSlot(tei-1), myGame.getColumn(i+2).getSlot(tei+2))) {
                        return i;
                    }
                }
                if(i < myGame.getColumnCount() - 1 && i > 1 && tei < myGame.getRowCount() - 1 && tei > 1) { // if one token might be down and to the right and two up and to the left
                    ////System.out.println("      Checking if one token might be down and to the right and two up and to the left...");
                    if(checkIfEqual(red, myGame.getColumn(i+1).getSlot(tei+1), myGame.getColumn(i-1).getSlot(tei-1), myGame.getColumn(i-2).getSlot(tei-2))) {
                        return i;
                    }
                }
                if(i < myGame.getColumnCount() && i > 2 && tei < myGame.getRowCount() && tei > 2) { // if three tokens might be up and to the left
                    ////System.out.println("      Checking if three tokens might be up and to the left...");
                    if(checkIfEqual(red, myGame.getColumn(i-1).getSlot(tei-1), myGame.getColumn(i-2).getSlot(tei-2), myGame.getColumn(i-3).getSlot(tei-3))) {
                        return i;
                    }
                }
                if(i > 2 && i < myGame.getColumnCount() && tei < myGame.getRowCount() - 3 && tei >= 0) { // if three tokens might be down and to the left
                    ////System.out.println("      Checking if three tokens might be down and to the left...");
                    if(checkIfEqual(red, myGame.getColumn(i-1).getSlot(tei+1), myGame.getColumn(i-2).getSlot(tei+2), myGame.getColumn(i-3).getSlot(tei+3))) {
                        return i;
                    }
                }
                if(i > 1 && i < myGame.getColumnCount() - 1 && tei < myGame.getRowCount() - 2 && tei > 0) { // if two tokens might be down and to the left and one up and to the right
                    ////System.out.println("      Checking if two tokens might be down and to the left and one up and to the right...");
                    if(checkIfEqual(red, myGame.getColumn(i-1).getSlot(tei+1), myGame.getColumn(i-2).getSlot(tei+2), myGame.getColumn(i+1).getSlot(tei-1))) {
                        return i;
                    }
                }
                if(i > 0 && i < myGame.getColumnCount() - 2 && tei < myGame.getRowCount() - 1 && tei > 1) { // if one token might be down and to the left and two up and to the right
                    ////System.out.println("      Checking if one token might be down and to the left and two up and to the right...");
                    if(checkIfEqual(red, myGame.getColumn(i-1).getSlot(tei+1), myGame.getColumn(i+2).getSlot(tei-2), myGame.getColumn(i+1).getSlot(tei-1))){
                        return i;
                    }
                }
                if(i >= 0 && i < myGame.getColumnCount() - 3 && tei < myGame.getRowCount() && tei > 2) { // if three tokens might be up and to the right
                    ////System.out.println("      Checking if three tokens might be up and to the right...");
                    if(checkIfEqual(red, myGame.getColumn(i+3).getSlot(tei-3), myGame.getColumn(i+2).getSlot(tei-2), myGame.getColumn(i+1).getSlot(tei-1))) {
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
        return "Brilliant Agent";
    }
}
