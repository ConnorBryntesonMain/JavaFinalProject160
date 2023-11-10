public class FlowerShares extends Stock {

    private int currentSeason;
    private int[] seasonChanges = { 50000, 10000, -10000, -50000 };

    public FlowerShares(String LLCTitle, int startingPrice, double randomnessMod, String scale, String season) {
        super(LLCTitle, startingPrice, randomnessMod, scale, season);
        this.currentSeason = 0;
    }

    void changeSeasonStock() {

        if (currentSeason == 4) {
            currentSeason = 0;
        }

        double shares = getStockAmount();
        int change = seasonChanges[currentSeason];

        setStockAmount(shares + change);

        currentSeason++;
    }

}