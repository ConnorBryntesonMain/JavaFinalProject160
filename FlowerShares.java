public class FlowerShares extends Stock {
    //This is the control if a stock has increased shares
    private int currentSeason;
    //This is the list of changes that can happen to a stock based on what season they are
    private final int[] seasonChanges = { 50000, 10000, -1000, -5000 };
    //This is where we control the season of when they increase and decrease
    public FlowerShares(String LLCTitle, int startingPrice, double randomnessMod, String scale, String season) {
        super(LLCTitle, startingPrice, randomnessMod, scale, season);
        this.currentSeason = 0;
    }
    //this is the change model
    Runnable changeSeasonStock() {
        return () -> {
            if (currentSeason == 4) {
                currentSeason = 0;
            }
            currentSeason++;
        };
    }
    /**
     * This is how we find out if they need change
     * @param s This is the stock we are testing
     */

    Runnable changeStockAmount(Stock s){
        return() -> {
            if (s.getSeason() == 3) {
                int change = seasonChanges[currentSeason];
                s.setStockAmount(change + s.getStockAmount());
            }
        };

    }

}