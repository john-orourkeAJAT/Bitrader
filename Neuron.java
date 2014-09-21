

/*
Maintained by Walter Brown.
*/

/*
-Neuron:
--Has a constant bias (w0)
--Stores a set of all "dendrite connections"(possibly use a sub-object to couple weights with other perceptrons?)
--Can form a synapse with another perceptron.
--Has one function.
--Can calculate the value of its "axon ending."  It's up to the receiving perceptron to apply a weight to the axon.
--Appropriate setters and getters for weights
A perceptron can simulate an input value if it has no dendrite connections by having a changeable constant bias.
*/

import java.util.*;

public class Neuron {

  private final double _bias = 1;
  private Map<Neuron, Double> dendrites =
      new HashMap<Neuron, Double>();
  private Function _function = new Sigmoid();

  /* Returns the value at the end of the axon. */
  public double evaluate() {
    /* Sum together all inputs. */
    double sum = 0;
    for(Neuron key : dendrites.keySet()) {
      if(key == this) sum += dendrites.get(key)*_bias;
      else sum += dendrites.get(key);
    }
    return _function.evaluate(sum);
  }

  public double getBias() {
    if(dendrites.containsKey(this))
      return this._bias*dendrites.get(this);
    else
      return 0;
  }
  public Function getFunction() {
    return _function;
  }
  /* Gets the weight associated with a given perceptron input. */
  public double getWeight(Neuron perceptron) {
    if(dendrites.containsKey(perceptron))
      return dendrites.get(perceptron);
    else
      return 0;
  }

  public Neuron(Function function) {
    this._function =  function;
  }

  public void setBias(double bias) {
    setWeight(this, bias/_bias);
  }
  public void setFunction(Function function) {
    this._function = function;
  }
  /* Sets the weight associated with a given perceptron. */
  public void setWeight(Neuron perceptron, double weight) {
	    if(weight != Double.NaN)
	      dendrites.put(perceptron, weight);
	  }

  public String toString() {
    String input = ""+getBias();
    for(Neuron key : dendrites.keySet()) {
      if(key != this) {
        if(dendrites.get(key) >= 0) input += "+";
        input += dendrites.get(key) + "*" + key.toString();
      }
    }
    return _function.toString(input);
  }

  /* Tester */
  public static void main(String [] args){
    /*
    Here is a little neural net to decide if you'll walk the dog today.
    */
    /*Neuron walkDog = new Neuron(new StepFunction());
    Neuron rain    = new Neuron(new PrimaryInput("RAIN"));
    Neuron snow    = new Neuron(new PrimaryInput("SNOW"));
    Neuron monday  = new Neuron(new PrimaryInput("MONDAY"));
    Neuron warm    = new Neuron(new PrimaryInput("WARM"));

    /* Hook factors up to output perceptron. */
   /* walkDog.setBias(0.5);
    walkDog.setWeight(rain, -0.1);
    walkDog.setWeight(snow, -0.6);
    walkDog.setWeight(monday, -0.6);
    walkDog.setWeight(warm, 0.7);

    /* Make sure setup is correct. */
   /* System.out.println(walkDog);

    System.out.println("All PrimaryInputs initialized to 0.");
    System.out.println(walkDog.evaluate());

    System.out.println("If it's monday, I'm not keen on getting up.");
    PrimaryInput.setInput("MONDAY", 1);
    System.out.println(walkDog.evaluate());

    System.out.println("But a warm monday is okay.");
    PrimaryInput.setInput("WARM", 1);
    System.out.println(walkDog.evaluate());

    System.out.println("I'd even be okay if it was raining on a warm monday.");
    PrimaryInput.setInput("RAIN", 1);
    System.out.println(walkDog.evaluate());

    System.out.println("Snow on the other hand...");
    PrimaryInput.setInput("SNOW", 1);
    System.out.println(walkDog.evaluate());

    System.out.println("Actually, I'm beginning to like snow.");
    walkDog.setWeight(snow, 0.1);
    System.out.println(walkDog);
    System.out.println(walkDog.evaluate());*/

  }
}
