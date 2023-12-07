    import java.util.*;
    import java.io.*;
    public class StockMarketProject 
    {
        public static double inOrOut = 0.0;
        public static int playerChoice;

        public static void main(String[] args) throws FileNotFoundException 
        {
            /* This method starts the game by running guiFlower.initialize();
             * 
             */
            javax.swing.SwingUtilities.invokeLater(new Runnable() 
            {
                public void run() 
                {
                    
                    GuiFlower guiFlower = new GuiFlower();
                    guiFlower.initialize();
                    MyThread threadTurn = new MyThread();
                    threadTurn.start();

                }
            });
            //Stock.buildList();
        }
        public static class MyThread extends Thread
        {
            public void run()
            {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) 
                {
                    throw new RuntimeException(e);
                }
                player myPlayer = new player("LLCTitle", 100, 0.1, "small", "spring");
                try 
                {


                    GuiFlower.updateCashLabel("Your Wallet: $" + player.cash + "");
                    myPlayer.turn();
                } catch (FileNotFoundException e) 
                {
                    throw new RuntimeException(e);
                } catch (InterruptedException e) 
                {
                    throw new RuntimeException(e);
                }
            }
        }
    }