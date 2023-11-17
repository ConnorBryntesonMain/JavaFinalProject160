import java.util.*;
import java.io.*;

/**
 * Things to do
 * 1.
 * Create a GUI that has a text box that displays arraylist of all the stocks
 * and the prices, the stocks that the player bough,
 * has a buy and sell, a text feild that displays total cash amount,
 * and an editable text feild that accepts player input for how much they want
 * to spend.
 * 2. Print out how they did to the output file
 * 3. Make an AI
 * 4. Run the caluclations in parelle
 * 
 */

public class StockMarketProject {
    public static double inOrOut = 0.0;
    public static int playerChoice;

    public static void main(String[] args) throws FileNotFoundException {
        player myPlayer = new player("LLCTitle", 100, 0.1, "small", "spring");
        Scanner input = new Scanner(System.in);

        Stock.buildList();
        myPlayer.turn(input);

    }
}