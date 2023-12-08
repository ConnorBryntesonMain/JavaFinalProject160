        import java.util.*;
        import java.io.File;
        import java.io.FileNotFoundException;
        import java.io.PrintStream;

        public class player<X, Y> extends Stock 
        {
            private int count;
            private final FlowerShares flowerShares;
            public static double cash = 100000;

            /**
             * @param LLCTitle      String this is the name of the stock
             * @param startingPrice This is the starting price of the stock this number will
             *                      change after the frist round of buying
             * @param randomnessMod I dont know if we have enough time to fully impelement
             *                      this
             * @param scale         This is the starting amount of stocks
             * @param season        This is when it is increasing or decreaing stock amounts
             */

            public player(String LLCTitle, int startingPrice, double randomnessMod, String scale, String season) 
            {
                super(LLCTitle, startingPrice, randomnessMod, scale, season);
                this.count = 0;
                this.flowerShares = new FlowerShares(LLCTitle, startingPrice, randomnessMod, scale, season);

            }

            /**
             * This method sets the count (setter method)
             * @param n int 
             */

            public void setCount(int n) 
            {
                this.count = n;
            }

            
            int totalTurns = 5;
            String test = "";
            /**
             * This method is the "turn" method. It functions as the turn mechanic in our game
             */
            void turn() throws FileNotFoundException, InterruptedException
            {
                PrintStream output = new PrintStream(outputFile);
                Scanner input = new Scanner(GuiFlower.bufferGrab());
                test = input.next();
                System.out.println(totalTurns);
                if (test.equals("")) 
                {

                    if (!GuiFlower.buttonPress) 
                    {
                        System.out.println("You are in the second if");
                    }
                } else 
                {
                    System.out.println("in the else");
                    namePicker(test);
                }

                output.println(name);
                while (totalTurns > 0) 
                {
                    action();
                    flowerShares.changeSeasonStock();
                    for (Stock stock : stockList) 
                    {
                        flowerShares.changeStockAmount(stock);
                    }
                    GuiFlower.updatestockListLabel(Arrays.toString(stockList.toArray()));
                    GuiFlower.updateStockOwnLabel(Arrays.toString(boughtStocks.toArray()));
                    setCount(0);
                    turnByturnPrint(output);
                    totalTurns--;
                }

            }

            String name;

            /** Gets the name of the user
             * @param input This is the user inputs            
             */

            public void namePicker(String input) 
            {

                // Call the updateQuestionLabel method
                GuiFlower.updateQuestionLabel("What would you like your name to be?");
                name = input;
                System.out.println("Your name is " + name);

            }

            File outputFile = new File("GameResults.txt");
            /** This method prints to the file
             * ~File IO 
             * @param output this is how I am printing to the file
             */
            public void turnByturnPrint(PrintStream output) 
            {
                output.println("Stocks picked: " + arrayIndex);
                output.println("The total bought stocks up and till this point: " + boughtStocks);
                output.println("This is the final cash the player has at the end of the turn: " + cash);
                output.println(cash);
            }

            
            String nextInput;

            /**
             * This is method is all moving parts of the code. 
             * It holds all the actions a player can take in there
             * ~User input 
             */

            void action() throws InterruptedException 
            {
                GuiFlower.updateQuestionLabel("Would you like to buy or sell?");

                Scanner input = new Scanner(GuiFlower.bufferGrab());
                while (input.hasNext()) 
                {
                    nextInput = input.next();
                }
                System.out.println("Out side of while loop");
                if (nextInput.equalsIgnoreCase("buy")) 
                {
                    if (justPicked) 
                    {
                        arrayIndex.clear();
                        justPicked = false;
                    }
                    pickStock();
                    buyAction();
                    Stock.updatePrices();
                } else if (nextInput.equalsIgnoreCase("sell") && !boughtStocks.isEmpty()) 
                {
                    sellAction();
                    Stock.updatePrices();
                } else 
                {
                    GuiFlower.updateQuestionLabel("You can not sell when you have no stock.");
                    action();
                }

            

            }

            /**
             * ArrayIndex is the stocks picked
             * JustPicked is meant to ensure that you can buy new stocks after just buying
             * ~ArrayList here
             */
            public List<Integer> arrayIndex = new ArrayList<>();
            public boolean justPicked = false;
            /* pickStock is how the user is able to pick the stocks they want to use        
            * @param input this is the user input          
            */

        

            public void pickStock() throws InterruptedException 
            {
                int arrayCount = 0;
                while (count < 3 && arrayIndex.size() < 3) 
                {
                    for (Stock stocks : Stock.stockList) 
                    {
                        if (count < 3 && arrayIndex.size() < 3) 
                        {
                            System.out.println(stocks);
                            GuiFlower.updateQuestionLabel("Would you like to pick this stock yes or no? (" + stocks + ")");
                            Scanner input = new Scanner(GuiFlower.bufferGrab());
                            String nextInput = input.next().trim();
                            if (nextInput.equalsIgnoreCase("yes")) 
                            {
                                arrayIndex.add(arrayCount);
                                count++;
                            }
                            arrayCount++;
                        } 
                        else 
                        {
                            justPicked = true;
                            count = 0;
                            break;
                        }
                    }
                }
            }
            boolean isDouble(String text){
                try{
                    Double.parseDouble(text);
                    return true;
                }catch (NumberFormatException e){
                    return false;
                }

            }
    
                
            /**
             * Arraylist for owned stocks
             * ~ArrayList
             */
            public ArrayList<PurchaseTuple> boughtStocks = new ArrayList<>();

            /**
             * This is method allows the user to buy stocks that are avalible 
             */
            void buyAction() throws InterruptedException 
            {
                for (int i = 0; i < arrayIndex.size(); i++)
                {
                    int num = arrayIndex.get(i);
                    GuiFlower.updateQuestionLabel("How much would you like to spend on " + stockList.get(arrayIndex.get(i)));
                    Scanner input = new Scanner(GuiFlower.bufferGrab());
                    String userInput = input.next();
                        
                    while(!isDouble(userInput)){
                        input = new Scanner(GuiFlower.bufferGrab());
                        userInput = input.next();
                    }
                        
                    double invest = Double.parseDouble(userInput);
                    if (invest > cash) 
                    {
                        GuiFlower.updateQuestionLabel("Be aware you don't have enough cash on hand.\nYou have this much cash left " + cash);
                        GuiFlower.updateQuestionLabel("How much would you like to spend on " + stockList.get(arrayIndex.get(i)));
                        invest = Double.parseDouble(userInput);
                    } 
                    else 
                    {
                        cash -= invest;
                        GuiFlower.updateCashLabel("Your Wallet: $" + player.cash + "");
                        double position = i + 0.0;
                        Stock bought = stockList.get(arrayIndex.get(i));
                        int price = bought.getPrice();
                        double scale = bought.getStockAmount();
                        bought.setStockAmount(scale - (invest / price));
                        PurchaseTuple<X, Y> purchase = new PurchaseTuple<X, Y>(position, (invest) / (price * 1.0));
                        boughtStocks.add(purchase);
                    }
                }
                justPicked = true;
                System.out.println(boughtStocks.toString());
            }

            /**
             * This method allows the user to sell their owned stocks 
             */

            void sellAction() throws InterruptedException 
            {
                for (int i = 0; i < boughtStocks.size(); i++) 
                {
                    PurchaseTuple information = boughtStocks.get(i);
                    GuiFlower.updateQuestionLabel("You have " + information.toStringPartial() + " left and the price is "
                            + stockList.get(arrayIndex.get((int) information.getPosition())));

                    GuiFlower.updateQuestionLabel("How much do you want to sell");
                    Scanner input = new Scanner(GuiFlower.bufferGrab());
                    test = input.next();
                    while(!isDouble(test)){
                        input = new Scanner(GuiFlower.bufferGrab());
                        test = input.next();
                    }
                    double nextNum = Double.parseDouble(test);
                    ;
                    if (nextNum <= information.getValue()) 
                    {
                        Stock bought = stockList.get(arrayIndex.get(((int) information.getPosition())));
                        double scale = bought.getStockAmount();
                        bought.setStockAmount(scale + nextNum);
                        cash += (nextNum * bought.getPrice());
                        GuiFlower.updateCashLabel("Your Wallet: $" + player.cash + "");
                        if (nextNum == information.getValue()) 
                        {
                            boughtStocks.remove(i);
                        } else 
                        {
                            double newStockAmount = information.getValue() - nextNum;
                            PurchaseTuple<X, Y> purchase = new PurchaseTuple<X, Y>(i, newStockAmount);
                            boughtStocks.set(i, purchase);
                        }
                    }
                }
                int boughtItemsCount = 0;

                for (int i = 0; i <= boughtStocks.size() - 1; i++) 
                {
                    double stockValue = boughtStocks.get(i).getValue();
                    if (stockValue < 1) 
                    {
                        boughtStocks.remove(boughtItemsCount);
                    }
                }
            }

        }
