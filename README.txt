24 April 2014

To run:
javac NeuralNet.java 
java NeuralNet 

Hi Everyone,
This is a neural net perceptron setup with learning.  Take a look at NeuralNet.java's main method to see a use example.
-> Instantiate a NeuralNet.
-> Create an output neuron.  In our case, we want a the perceptron to spit out a pure linear combination of the input values.  That behavior is captured by having the output neuron have a "new PolynomialTerm(1)" function (I gave neurons more manoeuvrability than we really needed.  Don't worry about the details here.)
-> Add an input neuron for each feature.  Features are called by string names.  There is no way nor need to specify that a 
new Neuron(new PrimaryInput("My Awesome Example Input"))
-> Package DataPoint objects as a collection of features and an expected value.
DataPoint dp = new DataPoint();
dp.addFeature("My Awesome Example Input", 1);
dp.addFeature("Stock Price", 3254);
dp.setExpected(3258);
-> Make the NeuralNet learn from the DataPoint.
n.learn(dp);
-> Report out the learned formula.
System.out.println(n.getOutputNeuron());

After learning is done:
-> Build a DataPoint for your testpoint.
-> Tell the DataPoint to set the inputs to the Perceptron.
dp.setPrimaryInputs();
-> Evaluate the final function.
myNeuralNet.getOutputNeuron().evaluate()

If any of these are confusing, let me know!

Best,
-Walter
webrown@umass.edu


Tyler you primarily want to look at intendedNet for how things work this gives the implementation of a neural net for the situation based off of the helper classes we have.
Next look at data point to see how we create a single point to be used in testing and training
Then look at the function methods polynomial term and what not these are largely approximations of math terms to be used in constructiong our final function.
Then look at primary input note the singleton property which allows us to simply instantiate new features to create a substantive list. We then read these features in for each data point and when we are done we have a learnable state for the perceptron.
Final look at Neural net which defines the perceptron itself and introduces the alpha value  and the learn function as a way to use the derivative of our current function to use in updating the weights of each feature.
Note that our final output is a linear combination of our features each multiplied by the learned weights plus a bias weight which prevents the functioning from zeroing during early learning and helps approximate cases where values begin at some initial boundary above zero.
