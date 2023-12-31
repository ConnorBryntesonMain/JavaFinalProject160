import java.text.DecimalFormat;
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
    
    private static GuiFlower guiFlowerInstance;

    /**
     * This is setting guiFlowerInstance to guiFlower (Setter method)
     * @param guiFlower guiFlower
     */
    public static void setGuiFlowerInstance(GuiFlower guiFlower) 
    {
        guiFlowerInstance = guiFlower;
    }

    /**
     * This is the constructor for the flower stocks. It takes the flowerName, startingPrice, randomnessMod, scale, and season.
     * season is used to determine the stockAmount
     * @param flowerName    String This is the flower name
     * @param startingPrice int this is the starting price of the flower at the begaining of the game
     * @param randomnessMod double this is going to be impacted by the inOrOut value                      
     * @param scale         String this is how much is in stock of a certain flower
     * @param season String
     */
    public Stock(String flowerName, int startingPrice, double randomnessMod, String scale, String season) 
    {
        this.name = flowerName;
        price = startingPrice;
        this.modifier = randomnessMod;

        this.season = season;
        if (scale.equalsIgnoreCase("large")) 
        {
            StockAmount = 100000.0;
        } else if (scale.equalsIgnoreCase("med")) 
        {
            StockAmount = 10000.0;
        } else if (scale.equalsIgnoreCase("small")) 
        {
            StockAmount = 1000.0;
        }
    }

    /**
     * This is grabing share size (getter method)
     * @return stockAmount double
     */
    public double getStockAmount() 
    {
        return this.StockAmount;
    }

    /**
     * This is setting the stock amount (setter method)
     * @param n double this is the new stock amount
     */
    public void setStockAmount(double n) 
    {
        StockAmount = n;
    }

    /**
     * This gets the price of the stock (getter method)
     * @return price int 
     */
    int getPrice() 
    {
        return price;
    }

    static ArrayList<Stock> stockList = new ArrayList<>();

    /**
     * This is building the list that you see above by pulling information from Stocks.txt file
     * It will do this automaticly so you can add as many stocks in addition and the
     */
    public static void buildList() 
    {
        try 
        {
            Scanner stockScan = new Scanner(new File("Stocks.txt"));
            while (stockScan.hasNextLine()) 
            {
                String line = stockScan.nextLine();
                try (Scanner lineScan = new Scanner(line)) 
                {
                    while (lineScan.hasNext()) 
                    {
                        stockList.add(
                                new Stock(lineScan.next(), lineScan.nextInt(), lineScan.nextDouble(), lineScan.next(),
                                        lineScan.next()));
                    }
                }
            }
            System.out.println(stockList);
        } 
        catch (Exception FileNotFoundException) 
        {

        }

    }

    /**
     * This is printing the string, this is manly used for printing off basic stock
     * info when buying or selling stocks in the game.
     */
    DecimalFormat df = new DecimalFormat("#.##");

    public String toString() 
    {
        return (name + "," + df.format(price) + "," + df.format(StockAmount));
    }

    /**
     * This is how we get randomness in the price variation this also makes it so
     * that the price will not be stagnate.
     */
    public static void updatePrices() 
    {

        for (Stock stock : Stock.stockList) 
        {
            int randomChange = (int) ((Math.random() * 21) - 10);
            stock.price += randomChange;
        }
    }
    /**
     * This method gets the season (getter method)
     */
    public int getSeason() 
    {
        if (this.season == "Summer") 
        {
            return 3;
        } else {
            return 2;
        }

    }
    /**
     * This method gets the name (getter method)
     */
    public String getName() 
    {
        return name;
    }
}
