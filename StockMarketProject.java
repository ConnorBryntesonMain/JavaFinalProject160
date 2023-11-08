import java.util.*;
import java.io.*;

/**
 * Things to do
 * 
 * 1. Set up ability to invest
 * 2. Put choices into the game like shorts and timed sells
 * 3. Set up price change over time
 * 4. Set up turns for computer and player
 * 5. Print out how they did to the output file
 * 6. Make an AI
 * 7. Run the caluclations in parelle
 * 
 */

public class StockMarketProject {
    public static double inOrOut = 0.0; // This is the invest or sell amount dont have any use right now
    public static int playerChoice; // This is the player input
    public static double cash = 100000;

    public static void main(String[] args) throws FileNotFoundException {
        Scanner input = new Scanner(System.in);

        Stock.buildList();
        playersTurn(input);
        Stock.playerAction(input);

    }

    static int whatSeason = 0;

    /**
     * This is going through the players turn by going up to 3 moves
     * 
     * @param input Scanner this is how we grab user input
     */
    public static void playersTurn(Scanner input) {

        Stock.playerTurn(input);

        FlowerShares.checkSeason(whatSeason);
        FlowerShares.changeSeasonStock();

    }

    /**
     * Stock is the super class for all of my other classes
     */
    public static class Stock {
        private String name;
        private static int price;
        public double modifier;
        public static double StockAmount;
        public boolean stockClosed = false;
        public boolean gameGoing = true;
        public String season;

        /**
         * This is the constructor for the flower stocks
         * 
         * @param flowerName    This is the flower name
         * @param startingPrice this is the starting price of the flower at the
         *                      begaining of the game
         * @param randomnessMod this is going to be impacted by the inOrOut value but
         *                      has yet to be impelemented
         * @param scale         this is how much is in stock of a certain flower
         * @param optionsNum    this displays for user input
         */
        public Stock(String flowerName, int startingPrice, double randomnessMod, String scale, String season) {
            this.name = flowerName;
            this.price = startingPrice;
            this.modifier = randomnessMod;
            if (scale.toLowerCase() == "small") {
                StockAmount = 10000;
            } else if (scale.toLowerCase() == "med") {
                StockAmount = 100000;
            } else if (scale.toLowerCase() == "large") {
                StockAmount = 1000000;
            } else {
                StockAmount = 1;
            }
            this.season = season;
        }

        /**
         * This is grabing share size
         * 
         * @return just returns share size...
         * 
         *         I dont know if this is needed anymore
         */
        public static double getStockAmount() {
            return StockAmount;
        }

        public static void setStockAmount(double n) {
            StockAmount = n;
        }

        /**
         * This is the mod rate but I dont have this tied to anything yet
         * 
         * @param moneyInOut this is the value but we dont have it being updated just
         *                   yet that is next step
         */
        public void modifierRate(double moneyInOut) {
            modifier = (price * StockAmount) / inOrOut;
            if (modifier > 0.5) {
                price -= 10;
            } else {
                price += 10;
            }
        }

        /**
         * This is just checking if a flower cost 0 and it does it closes the abiblity
         * to buy that stock
         */
        public void priceChecker() {
            if (price <= 0) {
                stockClosed = true;
            } else {
                stockClosed = false;
            }
        }

        /**
         * this is just returning price but I dont know if it has a point
         * 
         * @return price
         */
        int getPrice() {
            return price;
        }

        static ArrayList<Stock> stockList = new ArrayList<>();

        public static void buildList() throws FileNotFoundException {
            Scanner stockSacn = new Scanner(new File("Stocks.txt"));

            while (stockSacn.hasNextLine()) {
                String line = stockSacn.nextLine();
                Scanner lineScan = new Scanner(line);
                while (lineScan.hasNext()) {
                    stockList.add(new Stock(lineScan.next(), lineScan.nextInt(), lineScan.nextDouble(), lineScan.next(),
                            lineScan.next()));
                }
            }
            System.out.println(stockList);
        }

        public String toString() {
            return (name + "," + price + "," + StockAmount);
        }

        public static ArrayList<Integer> arrayIndex = new ArrayList<>();
        public static int count = 0;

        public static void playerTurn(Scanner input) {
            int arrayCount = 0;

            for (Stock stocks : Stock.stockList) {
                if (count < 3) {
                    System.out.println(stocks);
                    System.out.println("Would you like to pick this stock yes or no?");
                    String nextInput = input.nextLine();
                    if (nextInput.equalsIgnoreCase("yes")) {
                        System.out.println("Good pick");
                        arrayIndex.add(arrayCount);
                        count++;
                    }
                    arrayCount++;
                } else {
                    break;
                }
            }
            if (arrayIndex.size() < 3) {
                playerTurn(input);
            }
        }

        public static ArrayList<Double> boughtStocks = new ArrayList<>();

        static void playerAction(Scanner input) {
            System.out.println("Would you like to buy or sell?");
            String nextInput = input.next();
            if (nextInput.equalsIgnoreCase("buy")) {
                for (int i = 0; i < arrayIndex.size(); i++) {
                    int num = arrayIndex.get(i);
                    System.out.println("How much would you like to spend on " + stockList.get(arrayIndex.get(i)));
                    double invest = input.nextInt();
                    if (invest > cash) {
                        System.out.println(
                                "Be aware you don't have enough cash on hand.\nYou have this much cash left " + cash);
                        System.out.println("How much would you like to spend on " + stockList.get(arrayIndex.get(i)));
                        invest = input.nextInt();
                    }
                    cash -= invest;
                    List<Double> holder = Arrays.asList(num + 0.0, (invest / price));
                    boughtStocks.addAll(holder);
                    System.out.println(cash);
                }
                System.out.println(boughtStocks.toString());
            } else if (nextInput.equalsIgnoreCase("sell") && !boughtStocks.isEmpty()) {
                for (int i = 0; i < arrayIndex.size(); i += 2) {
                    System.out.println("Would you like to sell" + arrayIndex.get(i - 1));
                    String yesorno = input.next();
                    if (yesorno.equalsIgnoreCase("yes")) {
                        System.out.println("You have " + arrayIndex.get(i) + "left and the price is"
                                + stockList.get(arrayIndex.get((5 * i) + 1)));
                        System.out.println("How much do you want to sell");
                        int nextNum = input.nextInt();
                    }

                }
            }
        }
    }

    /**
     * This is the main class for all the stocks I just wanted to seperate out from
     * stocks that way
     * it is easier to read and look at
     */
    public static class FlowerShares extends Stock {
        public FlowerShares(String LLCTitle, int startingPrice, double randomnessMod, String scale, String season) {
            super(LLCTitle, startingPrice, randomnessMod, scale, season);
        }

        public static int checkSeason(int whatSeason) {
            if (whatSeason == 4) {
                whatSeason = 0;
            } else {
                whatSeason++;
            }
            return whatSeason;
        }

        static void changeSeasonStock() {

            double shares = getStockAmount();
            if (whatSeason == 0) {
                setStockAmount(shares + (shares / 2));
            } else if (whatSeason == 1) {
                setStockAmount(shares + (shares * .1));
            } else if (whatSeason == 2) {
                setStockAmount(shares - (shares * .1));
            } else if (whatSeason == 3) {
                setStockAmount(shares - (shares / 2));
            }
        }

    }

    /**
     * this is what houses all the flowers and where I can call all the meathods for
     * the players turn...
     */
    public static class AllFlower {

    }

}