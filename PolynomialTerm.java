

/*
Maintained by Walter Brown
*/
public class PolynomialTerm extends Function {

  private double _power;

  public double evaluate(double x) {
    return Math.pow(x, _power);
  }

  public PolynomialTerm(double power) {
    _power = power;
  }

  public String toString(String input) {
    return "("+input+")^"+_power;
  }
}
