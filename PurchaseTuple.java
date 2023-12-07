import java.text.DecimalFormat;

public class PurchaseTuple<X, Y> {
    public final double x;
    public final double y;

    DecimalFormat df = new DecimalFormat("#.##");

    public PurchaseTuple(double position, double d) {
        this.x = position;
        this.y = d;
    }

    public double getValue() {
        return y;
    }

    public double getPosition() {
        return x;
    }

    public String toString() {

        return (Stock.stockList.get((int) x).getName() + "," + df.format(y));
    }

    public String toStringPartial() {
        return "" + df.format(y);
    }
}
