

/*
Maintained by Walter Brown
*/
public class Sigmoid extends Function {

  public double evaluate(double x) {
    return 1.0/(1.0+Math.exp(-x));
  }

  public String toString(String input) {
    return "S("+input+")";
  }
}
