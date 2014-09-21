


public class ExponentialTerm extends Function {

  private double _base;

  public double evaluate(double x) {
    return Math.pow(_base, x);
  }

  public ExponentialTerm(double base) {
    _base = base;
  }

  public String toString(String input) {
    return _base+"^("+input+")";
  }
}
