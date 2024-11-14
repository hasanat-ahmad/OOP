import java.util.*;

abstract class Convert {
    double val1;
    double val2;

    public Convert(double val1, double val2) {
        this.val1 = val1;
        this.val2 = val2;
    }

    public abstract double compute();
}

class LitresToGallons extends Convert{
    public LitresToGallons(double val1, double val2){
        super(val1, val2);
    }
    @Override
    public double compute(){
        val2 = val1*0.2;
        return val2;
    }
}

class FarenheitToCelcius extends Convert{
    public FarenheitToCelcius(double val1, double val2){
        super(val1, val2);
    }

    @Override
    public double compute(){
        val2 = (val1-32)*5/9;
        return val2;
    }
}

class FeetToMeters extends Convert{
    public FeetToMeters(double val1, double val2){
        super(val1, val2);
    }

    @Override
    public double compute(){
        val2 = val1 * 0.304;
        return val2;
    }
}

public class LabTask3 {
    public static void main(String[] args) {
        Convert convert = new FarenheitToCelcius(98, 0);
        System.out.println(convert.compute());
    }
}
