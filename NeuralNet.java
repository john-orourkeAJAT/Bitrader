

/*
Maintained by Walter Brown
Up for modification by anyone else.
*/
import java.util.*;
public class NeuralNet {

  public void addInputNeuron(Neuron i) {
    inputNeurons.add(i);
  }

  private static final double alpha = 0.0000001;

  public NeuralNet() {
    inputNeurons = new LinkedList<Neuron>();

    /* Create a dud output neuron for safety sake. */
    outputNeuron = new Neuron(new PolynomialTerm(0));
  }

  public Iterator<Neuron> getInputNeuronIterator() {
    return inputNeurons.iterator();
  }

  public Neuron getOutputNeuron() {
    return outputNeuron;
  }

  private LinkedList<Neuron> inputNeurons;

  public void learn(DataPoint dp) {
    /* Properly set all neurons. */
    

    /* Calculate the constant part of the derivative. */
    double precomputed = 2*(dp.getExpected() - outputNeuron.evaluate());

    /* Modify weights. */
    outputNeuron.setBias(outputNeuron.getBias()+
                           alpha*precomputed);
    
    for(Neuron i : inputNeurons) {
      outputNeuron.setWeight(i, outputNeuron.getWeight(i)+
                             alpha*precomputed*i.evaluate());
    }
  }

  private Neuron outputNeuron;

  public void setOutputNeuron(Neuron n) {
    outputNeuron = n;
  }
  
  public double getSquaredError(DataPoint dp) {
	  
	  double holder =  (getOutputNeuron().evaluate()-dp.getExpected())/dp.getExpected();
	  dp.setError(holder);
	  return holder;
  }
  
  public double getAverageLoss(ArrayList<DataPoint> dataPoints) {
	  double result = 0;
	  for(DataPoint dp:dataPoints) {
		  result += dp.sqError/dp.getExpected();
	  }
	  return result/dataPoints.size();
  }

  /* Testing */
  public static void main(String [] args) {
    //NeuralNet n = new NeuralNet();

    /* First demonstrate how the layout looks with just one neuron. */
    /* Output neuron uses a linear function, f=x^(1). */
    //n.setOutputNeuron(new Neuron(new PolynomialTerm(1)));

    /* Add inputs. */
    /*for(int i = 0; i < 3; i++) {
      n.addInputNeuron(new Neuron(new PrimaryInput("Input"+i)));
    }

    int counter = 0;
    while(true) {
      if(counter%1000==0)
        System.out.println(n.getOutputNeuron());
      DataPoint dp = new DataPoint();
      double i = Math.random();
      double j = Math.random();
      dp.addFeature("Input0", i);
      dp.addFeature("Input1", j);
      dp.addFeature("Input2", 0);
      dp.setExpected(i*2+3+j);
      n.learn(dp);
      counter+=1;
    }
  */}
}
