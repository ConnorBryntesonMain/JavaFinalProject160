import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class player extends Stock {
    private int count;
    private FlowerShares flowerShares;
    private double value;
    public static double cash = 100000;

    public player(String LLCTitle, int startingPrice, double randomnessMod, String scale, String season) {
        super(LLCTitle, startingPrice, randomnessMod, scale, season);
        this.count = 0;
        this.flowerShares = new FlowerShares(LLCTitle, startingPrice, randomnessMod, scale, season);

    }

    Stock test = new Stock("LLCTitle", 100, 0.1, "small", "spring");

    public int getCount() {
        return this.count;
    }

    public void setCount(int n) {
        this.count = n;
    }

    int totalTurns = 5;

    void turn(Scanner input) {
        action(input);
        while (totalTurns > 0) {
            action(input);
            turnCounter(input);
            Stock.updatePrices();
            totalTurns--;
        }
    }

    int turnCount = 5;

    void turnCounter(Scanner input) {
        flowerShares.changeSeasonStock();
        turnCount--;
        setCount(0);
    }

    public ArrayList<Integer> arrayIndex = new ArrayList<>();

    public void pickStock(Scanner input) {
        input.nextLine();
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
            pickStock(input);
        }
    }

    public ArrayList<Double> boughtStocks = new ArrayList<>();
    String nextInput;

    void action(Scanner input) {
        System.out.println("Would you like to buy or sell?");
        nextInput = input.next();
        if (nextInput.equalsIgnoreCase("buy")) {
            pickStock(input);
            buyAction(input);
        } else if (nextInput.equalsIgnoreCase("sell") && !boughtStocks.isEmpty()) {
            sellAction(input);
        } else {
            System.out.println("You can not sell when you have no stock.");
            action(input);
        }
    }

    void buyAction(Scanner input) {
        for (int i = 0; i < arrayIndex.size(); i++) {
            int num = arrayIndex.get(i);
            System.out.println("How much would you like to spend on " + stockList.get(arrayIndex.get(i)));
            double invest = input.nextInt();
            if (invest > cash) {
                System.out.println(
                        "Be aware you don't have enough cash on hand.\nYou have this much cash left "
                                + cash);
                System.out.println("How much would you like to spend on " + stockList.get(arrayIndex.get(i)));
                invest = input.nextInt();
            }
            cash -= invest;
            double position = i + 0.0;
            boughtStocks.add(position);
            Stock bought = stockList.get(arrayIndex.get(i));
            int price = bought.getPrice();
            double scale = bought.getStockAmount();
            boughtStocks.add((invest * 1.0) / (price * 1.0));
            bought.setStockAmount(scale - (invest / price));
        }
        System.out.println(boughtStocks.toString());
    }

    void sellAction(Scanner input) {
        int stockCount = 1;
        for (int i = 0; i < arrayIndex.size(); i++) {
            System.out.println("You have " + boughtStocks.get(stockCount) + " left and the price is "
                    + stockList.get(arrayIndex.get(i)));
            System.out.println("How much do you want to sell");
            double nextNum = input.nextDouble();

            if (nextNum <= boughtStocks.get(stockCount)) {
                Stock bought = stockList.get(arrayIndex.get(i));
                double scale = bought.getStockAmount();
                bought.setStockAmount(scale + nextNum);
                cash += (nextNum * bought.getPrice());
                if (nextNum == boughtStocks.get(stockCount)) {
                    boughtStocks.remove(stockCount);
                } else {
                    double newStockAmount = boughtStocks.get(stockCount) - nextNum;
                    boughtStocks.add(stockCount, newStockAmount);
                }
            }
            stockCount += 2;
        }
    }
}
