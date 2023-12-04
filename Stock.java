import java.util.*;
import java.io.*;

public class Stock {

    private final String name;
    private int price;
    public double modifier;
    public double StockAmount;
    public boolean stockClosed = false;
    public boolean gameGoing = true;
    public String season;
    //private double value;
    private static GuiFlower guiFlowerInstance;

    // Setter method to set the GuiFlower instance
    public static void setGuiFlowerInstance(GuiFlower guiFlower) {
        guiFlowerInstance = guiFlower;
    }

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
        price = startingPrice;
        this.modifier = randomnessMod;

        this.season = season;
        if (scale.equalsIgnoreCase("large")) {
            StockAmount = 100000.0;
        } else if (scale.equalsIgnoreCase("med")) {
            StockAmount = 10000.0;
        } else if (scale.equalsIgnoreCase("small")) {
            StockAmount = 1000.0;
        }
    }

    /**
     * This is grabing share size
     * 
     * @return just returns share size...
     */
    public double getStockAmount() {
        return this.StockAmount;
    }
    /**
     * This is setting the stock amount
     * @param n double this is the new stock amount
     */
    public void setStockAmount(double n) {
        StockAmount = n;
    }

    /**
     * @return price
     */
    int getPrice() {
        return price;
    }
    //This is the stock list used through the whole program
    static ArrayList<Stock> stockList = new ArrayList<>();
    /**
     * This is building the list that you see above by pulling information from Stocks.txt
     * It will do this automaticly so you can add as many stocks in addition and the program should still work
     */
    public static void buildList() {
        try {
            Scanner stockScan = new Scanner(new File("Stocks.txt"));
            while (stockScan.hasNextLine()) {
                String line = stockScan.nextLine();
                try (Scanner lineScan = new Scanner(line)) {
                    while (lineScan.hasNext()) {
                        stockList.add(
                                new Stock(lineScan.next(), lineScan.nextInt(), lineScan.nextDouble(), lineScan.next(),
                                        lineScan.next()));
                    }
                }
            }
    
            // Move the updatestockListLabel call outside the loop
            guiFlowerInstance.updatestockListLabel(Arrays.toString(stockList.toArray()));
            System.out.println(stockList);
        } catch (Exception FileNotFoundException) {

        }

    }
    /**
     * This is printing the string, this is manly used for printing off basic stock info when buying or selling stocks
     * in the game.
     */

    public String toString() {
        return (name + "," + price + "," + StockAmount);
    }
    /**
     * This is how we get randomness in the price variation this also makes it so that the price can not be stanagte.
     */
    public static void updatePrices() {

        for (Stock stock : Stock.stockList) {
            int randomChange = (int) (Math.random() * 21) - 10;
            stock.price += randomChange;
        }
    }

    public int getSeason(){
        if(this.season == "Summer"){
            return 3;
        }else {
            return 2;
        }

    }
}
