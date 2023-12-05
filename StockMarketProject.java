    import java.util.*;
    import java.io.*;

    /**
     * Things to do
     * 1.
     * Create a GUI that has a text box that displays arraylist of all the stocks
     * and the prices, the stocks that the player bough,
     * has a buy and sell, a text field that displays total cash amount,
     * and an editable text field that accepts player input for how much they want
     * to spend.
     * 2. Print out how they did to the output file
     * 3. Make an AI
     * 4. Run the caluclations in parelle
     * 
     */

    public class StockMarketProject {
        public static double inOrOut = 0.0;
        public static int playerChoice;

        public static void main(String[] args) throws FileNotFoundException {
            //player myPlayer = new player("LLCTitle", 100, 0.1, "small", "spring");
            //Scanner input = new Scanner(System.in);

            //GUI Startpoint
            //Opens the game.
            javax.swing.SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    GuiFlower guiFlower = new GuiFlower();
                    guiFlower.initialize();
                    MyThread threadTurn = new MyThread();
                    threadTurn.start();

                }
            });



            //Stock.buildList();


        }
        public static class MyThread extends Thread{
            public void run(){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                player myPlayer = new player("LLCTitle", 100, 0.1, "small", "spring");
                try {


                    GuiFlower.updateCashLabel("$" + player.cash + "");
                    myPlayer.turn();
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }