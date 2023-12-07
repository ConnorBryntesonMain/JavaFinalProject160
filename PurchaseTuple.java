import java.text.DecimalFormat;

public class PurchaseTuple<X, Y> 
{
    public final double x;
    public final double y;

    DecimalFormat df = new DecimalFormat("#.##");

    public PurchaseTuple(double position, double d) 
    {
        this.x = position;
        this.y = d;
    }
    /* gets the value (getter method)
     * @return  y double 
     */
    public double getValue() 
    {
        return y;
    }
     /* gets the position (getter method)
     * @return x double 
     */
    public double getPosition() 
    {
        return x;
    }
    /* formats and returns the stocks in as a String (toString method)
     * @return String 
     */
    public String toString() 
    {
        return (Stock.stockList.get((int) x).getName() + "," + df.format(y));
    }
    /* formats the decimal numbers to be more readable (setter method)
     * @return String
     */

    public String toStringPartial() 
    {
        return "" + df.format(y);
    }
}
