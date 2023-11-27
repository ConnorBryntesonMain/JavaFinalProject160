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
    private double value;

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
     * 
     *         I dont know if this is needed anymore
     */
    public double getStockAmount() {
        return this.StockAmount;
    }

    public void setStockAmount(double n) {
        StockAmount = n;
    }

    /**
     * this is just returning price but I dont know if it has a point
     * 
     * @return price
     */
    int getPrice() {
        return price;
    }

    void setPrice(int price) {
        this.price = price;
    }

    static ArrayList<Stock> stockList = new ArrayList<>();

    public static void buildList() throws FileNotFoundException {
        Scanner stockSacn = new Scanner(new File("Stocks.txt"));

        while (stockSacn.hasNextLine()) {
            String line = stockSacn.nextLine();
            try (Scanner lineScan = new Scanner(line)) {
                while (lineScan.hasNext()) {
                    stockList.add(
                            new Stock(lineScan.next(), lineScan.nextInt(), lineScan.nextDouble(), lineScan.next(),
                                    lineScan.next()));
                }
            }
        }
        System.out.println(stockList);
    }

    public String toString() {
        return (name + "," + price + "," + StockAmount);
    }

    public static void updatePrices() {

        for (Stock stock : Stock.stockList) {
            int randomChange = (int) (Math.random() * 21) - 10;
            stock.price += randomChange;
        }
    }

    /**
     * This is the mod rate but I dont have this tied to anything yet
     * 
     * @param moneyInOut this is the value but we dont have it being updated just
     * 
     * 
     *                   modifier = (price * StockAmount) /
     *                   StockMarketProject.inOrOut;
     *                   if (modifier > 0.5) {
     *                   price -= 10;
     *                   } else {
     *                   price += 10;
     *                   }
     *                   }
     *                   ng if a flower cost 0 and it does it
     *                   to buy that stock
     */
    public void priceChecker() {
        stockClosed = price <= 0;
    }

}
