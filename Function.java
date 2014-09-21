

/*
Maintained By Walter Brown
*/
public abstract class Function {
  public abstract double evaluate(double x);
  public abstract String toString(String input);
  /* Tester */
  public static void main(String [] args) {
    ExponentialTerm e = new ExponentialTerm(2);
    PolynomialTerm p = new PolynomialTerm(2);
    Sigmoid s = new Sigmoid();
    testFunction(e);
    testFunction(p);
    testFunction(s);
  }
  public static void testFunction(Function subject) {
    for(double i = -1; i < 1; i+=0.1) {
      System.out.println(subject.toString(""+i)+"="+subject.evaluate(i));
    }
  }
}
