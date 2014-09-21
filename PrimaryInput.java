

/*
Maintained by Walter Brown
*/
import java.util.*;
public class PrimaryInput extends Function {
  private static Map<String, Double> inputTable = new HashMap<String, Double>();
  private String name;
  public double evaluate(double x) {
    return inputTable.get(name);
  }
  public String getName() {
    return name;
  }
  
  public PrimaryInput(String input) {
    name = input;
    inputTable.put(input, new Double(0.0));
  }
  public  double setInput(String input, double value) {
    return inputTable.put(input, value);
  }
  public double setInput(String input, boolean feat)
  {
	  if(feat)
	  {
		  return inputTable.put(input, 1.0);
	  }
	  else
	  {
		  return inputTable.put(input, 0.0);
	  }
  }
  
  public static String currentValues() {
	    String result = "";
	    Iterator it = inputTable.entrySet().iterator();
	    while(it.hasNext()) {
	      Map.Entry pair = (Map.Entry)it.next();
	      result += pair.getKey() + "=" + pair.getValue() + "\n";
	    }
	    return result;
	  }

  
  public String toString(String input) {
    return name;
  }
}
