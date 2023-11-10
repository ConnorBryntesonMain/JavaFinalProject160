import java.util.*;
import java.io.*;

/**
 * Things to do
 * 1. Debug the array list to hold total bought stocks
 * 2. Print out how they did to the output file
 * 3. Make an AI
 * 4. Run the caluclations in parelle
 * 
 */

public class StockMarketProject {
    public static double inOrOut = 0.0;
    public static int playerChoice;
    public static double cash = 100000;

    public static void main(String[] args) throws FileNotFoundException {
        player myPlayer = new player("LLCTitle", 100, 0.1, "small", "spring");
        Scanner input = new Scanner(System.in);

        Stock.buildList();
        myPlayer.turn(input);

    }
}