import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

public class player<X, Y> extends Stock {
    private int count;
    private final FlowerShares flowerShares;
    public static double cash = 100000;








    /**
     * @param LLCTitle String this is the name of the stock
     * @param startingPrice This is the starting price of the stock this number will change after the frist round of buying
     * @param randomnessMod I dont know if we have enough time to fully impelement this
     * @param scale This is the starting amount of stocks
     * @param season This is when it is increasing or decreaing stock amounts
     */

    public player(String LLCTitle, int startingPrice, double randomnessMod, String scale, String season) {
        super(LLCTitle, startingPrice, randomnessMod, scale, season);
        this.count = 0;
        this.flowerShares = new FlowerShares(LLCTitle, startingPrice, randomnessMod, scale, season);

    }
    /**
     * This is seting the count for the whole problem
     */

    public void setCount(int n) {
        this.count = n;
    }
    //This is setting the turn limit as without it the game would just run forever
    int totalTurns = 5;
    /**
     * We are using this as a way to make sure that we can keep teh turns orgnized in a way that makes sense for the
     * project as it would get hard to read/understand with out it
     */
    String test = "";
    void turn() throws FileNotFoundException, InterruptedException {
        PrintStream output = new PrintStream(outputFile);
        Scanner input = new Scanner(GuiFlower.bufferGrab());
        test = input.next();
        System.out.println(totalTurns);
        if(test.equals("")){

            if(!GuiFlower.buttonPress) {
                System.out.println("You are in the second if");
            }
        }else {
            System.out.println("in the else");
            namePicker(test);
        }

        output.println(name);
        action();
        while (totalTurns > 0) {
            action();
            flowerShares.changeSeasonStock();
            for(Stock stock : stockList){
                flowerShares.changeStockAmount(stock);
            }
            setCount(0);
            turnByturnPrint(output);
            totalTurns--;
        }

    }


    String name;
    /**
     * @param input This is the user inputs
     * This is meant to grab and display your name that way it feels more personal
     */

    public void namePicker(String input) {



        // Call the updateQuestionLabel method
        GuiFlower.updateQuestionLabel("What would you like your name to be?");
        name = input;
        System.out.println("Your name is " + name);


    }

    /**
     * Outputfile is the file I am dumbing the turn by turn game data in.
     * @param output this is how I am printing to the file
     */

    File outputFile = new File("GameResults.txt");
    public void turnByturnPrint(PrintStream output) {
        output.println("Stocks picked: " + arrayIndex);
        output.println("The total bought stocks up and till this point: " + boughtStocks);
        output.println("This is the final cash the player has at the end of the turn: " + cash);
        output.println(cash);
    }
    //nextInput is really important as it is holder varible for all information inputed by the user
    String nextInput;
    /**
     * This is action, also known as all the moving parts of the code. It holds all the actions a player can take in there
     * turn as such it is also where most things went wrong. And it just allows a player to act in the game.
     */

    void action() throws InterruptedException {
        GuiFlower.updateQuestionLabel("Would you like to buy or sell?");
        Scanner input = new Scanner(GuiFlower.bufferGrab());
        while(input.hasNext()){
            nextInput = input.next();
        }
        System.out.println("Out side of while loop");
            if (nextInput.equalsIgnoreCase("buy")) {
                if (justPicked) {
                    arrayIndex.clear();
                    justPicked = false;
                }
                input = new Scanner(GuiFlower.bufferGrab());
                pickStock();
                input = new Scanner(GuiFlower.bufferGrab());
                buyAction(input.next());
                Stock.updatePrices();
            } else if (nextInput.equalsIgnoreCase("sell") && !boughtStocks.isEmpty()) {
                input = new Scanner(GuiFlower.bufferGrab());
                sellAction(input.next());
                Stock.updatePrices();
            } else {
                GuiFlower.updateQuestionLabel("You can not sell when you have no stock.");
                action();
            }

        //System.out.println("Debug: nextInput = " + nextInput);
        //input.nextLine();

    }


    /**
     * ArrayIndex is the stocks picked
     * JustPicked is meant to ensure that you can buy new stocks after just buying meaning buy then buy
     * @param input this is the user input
     *
     * pickStock is how the user is able to pick the stocks they want to use
     */

    public List<Integer> arrayIndex = new ArrayList<>();
    public boolean justPicked = false;

    public void pickStock() throws InterruptedException {
        
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
                    GuiFlower.updateQuestionLabel("Would you like to pick this stock yes or no?");
                    Scanner input = new Scanner(GuiFlower.bufferGrab());
                    String nextInput = input.next().trim();
                    if (nextInput.equalsIgnoreCase("yes")) {
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
    //This is the stock list that holds information of stocks that were picked and how much was put into it
    public ArrayList<PurchaseTuple> boughtStocks = new ArrayList<>();
    /**
     * This is users buy action, it allows them to spend up to how much cash they have on a stock
     * @param input this is the user input
     */


    void buyAction(String input) throws InterruptedException {

        for (int i = 0; i < arrayIndex.size(); i++) {
            int num = arrayIndex.get(i);
            GuiFlower.updateQuestionLabel("How much would you like to spend on " + stockList.get(arrayIndex.get(i)));
            
            double invest = Double.parseDouble(Objects.requireNonNull(test));
            if (invest > cash) {
                GuiFlower.updateQuestionLabel(
                        "Be aware you don't have enough cash on hand.\nYou have this much cash left "
                                + cash);
                
                GuiFlower.updateQuestionLabel("How much would you like to spend on " + stockList.get(arrayIndex.get(i)));
                
                invest = Double.parseDouble(Objects.requireNonNull(test));;
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
        System.out.println(boughtStocks.toString());
    }
    /**
     * This is the sell turn allowing them to sell any stock that they own in the order that they bought the item
     * @param input this is the user input for the turn
     */

    void sellAction(String input) throws InterruptedException {
        for (int i = 0; i < boughtStocks.size(); i++) {
            PurchaseTuple information = boughtStocks.get(i);
            GuiFlower.updateQuestionLabel("You have " + information.toStringPartial() + " left and the price is "
                    + stockList.get(arrayIndex.get((int) information.getPosition())));
            
            GuiFlower.updateQuestionLabel("How much do you want to sell");
            
            double nextNum = Double.parseDouble(Objects.requireNonNull(test));;
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

        for (int i = 0; i <= boughtStocks.size() -1; i++) {
            double stockValue = boughtStocks.get(i).getValue();
            if (stockValue < 1) {
                boughtStocks.remove(boughtItemsCount);
            }
        }
    }



}