public class PurchaseTuple<X, Y> {
    public final double x;
    public final double y;

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
        return (x + "," + y);
    }

    public String toStringPartial() {
        return "" + y;
    }
}
