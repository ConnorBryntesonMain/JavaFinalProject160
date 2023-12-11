public class FlowerShares extends Stock {

    private int currentSeason;
  
    /* These are the modifiers for the season, based on the index.
     * ~Array
     */

    private final int[] seasonChanges = { 50000, 10000, -1000, -5000 };

    
    public FlowerShares(String LLCTitle, int startingPrice, double randomnessMod, String scale, String season) 
    {
        super(LLCTitle, startingPrice, randomnessMod, scale, season);
        this.currentSeason = 0;
    }

    /* This method changes the season, and rests when it hit's 4 (rests the year)
     * ~Runnable 
     */
    Runnable changeSeasonStock() 
    {
        return () -> 
        {
            if (currentSeason == 4) 
            {
                currentSeason = 0;
            }
            currentSeason++;
        };
    }

    /**
     * This is method determines if they need the stock needs to be modified 
     * @param s Stock, This is the stock we are changing
     * ~Runnable
     */

    void changeStockAmount(Stock s)
    {
        if (s.getSeason() == 3) {
                int change = seasonChanges[currentSeason];
                s.setStockAmount(change + s.getStockAmount());
        }
    }

}