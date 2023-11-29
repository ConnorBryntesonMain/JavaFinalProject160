import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

public class player<X, Y> extends Stock {
    private int count;
    private final FlowerShares flowerShares;
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

    void turn(Scanner input) throws FileNotFoundException {
        PrintStream output = new PrintStream(outputFile);

        namePicker(input);
        output.println(name);
        action(input);
        while (totalTurns > 0) {
            action(input);
            turnCounter(input);
            totalTurns--;
            outputFile(output);
            turnByturnPrint(output);
        }

    }

    int turnCount = 5;

    void turnCounter(Scanner input) {
        flowerShares.changeSeasonStock();
        turnCount--;
        setCount(0);
    }

    String name;

    public void namePicker(Scanner input) {
        System.out.print("What would you like your name to be?");
        name = input.nextLine();
        System.out.println("Your name is " + name);
    }


    File outputFile = new File("GameResults.txt");

    public void outputFile(PrintStream output) throws FileNotFoundException {
        output.println(cash);

    }

    public void turnByturnPrint(PrintStream output) {
        output.println("Stocks picked: " + arrayIndex);
        output.println("The total bought stocks up and till this point: " + boughtStocks);
        output.println("This is the final cash the player has at the end of the turn: " + cash);
    }

    public List<Integer> arrayIndex = new ArrayList<>();
    public boolean justPicked = false;

    public void pickStock(Scanner input) {
        //System.out.println("You are in pick stock");
        int arrayCount = 0;

        while (count < 3 && arrayIndex.size() < 3) {
            //System.out.println("Debug: Stock.stockList size = " + Stock.stockList.size());
            //System.out.println(
            //        "Debug: count = " + count + ", arrayCount = " + arrayCount + ", arrayIndex = " + arrayIndex);

            for (Stock stocks : Stock.stockList) {

                //System.out.println("You are in the for loop");
                if (count < 3 && arrayIndex.size() < 3) {
                    System.out.println(stocks);
                    System.out.println("Would you like to pick this stock yes or no?");
                    String nextInput = input.nextLine().trim();
                    if (nextInput.equalsIgnoreCase("yes")) {
                        System.out.println("Good pick");
                        arrayIndex.add(arrayCount);
                        count++;
                    }
                    arrayCount++;
                } else {
                    justPicked = true;
                    count = 0;
                    break;
                }
            }
        }
    }

    public ArrayList<PurchaseTuple> boughtStocks = new ArrayList<>();

    String nextInput;

    void action(Scanner input) {
        System.out.println("Would you like to buy or sell?");
        nextInput = input.nextLine();
        //System.out.println("Debug: nextInput = " + nextInput);
        //input.nextLine();
        if (nextInput.equalsIgnoreCase("buy")) {
            if (justPicked) {
                arrayIndex.clear();
                justPicked = false;
            }
            pickStock(input);
            buyAction(input);
            Stock.updatePrices();
        } else if (nextInput.equalsIgnoreCase("sell") && !boughtStocks.isEmpty()) {
            sellAction(input);
            Stock.updatePrices();
        } else {
            System.out.println("You can not sell when you have no stock.");
            action(input);
        }
    }

    void buyAction(Scanner input) {
        System.out.println("You enter buy");
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
            Stock bought = stockList.get(arrayIndex.get(i));
            int price = bought.getPrice();
            double scale = bought.getStockAmount();
            bought.setStockAmount(scale - (invest / price));
            PurchaseTuple<X, Y> purchase = new PurchaseTuple<X, Y>(position, (invest) / (price * 1.0));
            boughtStocks.add(purchase);
        }
        justPicked = true;
        input.nextLine();
        System.out.println(boughtStocks.toString());
    }

    void sellAction(Scanner input) {
        for (int i = 0; i < boughtStocks.size(); i++) {
            PurchaseTuple information = boughtStocks.get(i);
            System.out.println("You have " + information.toStringPartial() + " left and the price is "
                    + stockList.get(arrayIndex.get((int) information.getPosition())));
            System.out.println("How much do you want to sell");
            double nextNum = input.nextDouble();
            if (nextNum <= information.getValue()) {
                Stock bought = stockList.get(arrayIndex.get(((int) information.getPosition())));
                double scale = bought.getStockAmount();
                bought.setStockAmount(scale + nextNum);
                cash += (nextNum * bought.getPrice());
                if (nextNum == information.getValue()) {
                    boughtStocks.remove(i);
                } else {
                    double newStockAmount = information.getValue() - nextNum;
                    PurchaseTuple<X, Y> purchase = new PurchaseTuple<X, Y>(i, newStockAmount);
                    boughtStocks.set(i, purchase);
                }
            }
        }
        int boughtItemsCount = 0;
        for (int i = 0; i <= boughtStocks.size(); i++) {
            if (boughtStocks.get(i).getValue() < 1) {
                boughtStocks.remove(boughtItemsCount);
            }
        }
    }

}
