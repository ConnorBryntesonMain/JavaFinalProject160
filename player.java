import java.util.ArrayList;
import java.util.Scanner;

public class player extends Stock {
    private int count;
    private FlowerShares flowerShares;
    private double value;

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

    int totalTurns = 2;

    void turn(Scanner input) {
        while (totalTurns > 0) {
            turnCounter(input);
            action(input);
            FlowerShares.updatePrices();
            totalTurns--;
        }
    }

    int turnCount = 5;

    void turnCounter(Scanner input) {
        turnCount = 5;
        while (turnCount > 0) {

            pickStock(input);

            flowerShares.changeSeasonStock();
            turnCount--;
        }
        setCount(0);
    }

    public ArrayList<Integer> arrayIndex = new ArrayList<>();

    public void pickStock(Scanner input) {
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
            for (int i = 0; i < arrayIndex.size(); i++) {
                int num = arrayIndex.get(i);
                System.out.println("How much would you like to spend on " + stockList.get(arrayIndex.get(i)));
                double invest = input.nextInt();
                if (invest > StockMarketProject.cash) {
                    System.out.println(
                            "Be aware you don't have enough cash on hand.\nYou have this much cash left "
                                    + StockMarketProject.cash);
                    System.out.println("How much would you like to spend on " + stockList.get(arrayIndex.get(i)));
                    invest = input.nextInt();
                }
                StockMarketProject.cash -= invest;
                double position = i + 0.0;
                boughtStocks.add(position);
                Stock bought = stockList.get(arrayIndex.get(i));
                int price = bought.getPrice();
                double scale = bought.getStockAmount();
                boughtStocks.add(invest / price);
                bought.setStockAmount(scale - (invest / price));

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
        } else {
            System.out.println("You can not sell when you have no stock.");
            action(input);
        }
    }
}
