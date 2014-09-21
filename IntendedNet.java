import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.MatchResult;



public class IntendedNet {
	
public static void main(String [] args) throws FileNotFoundException
{
	//Read in all the possible data points into the arrayList that will hold them
	ArrayList<DataPoint> d = new ArrayList<DataPoint>();
	Scanner s = new Scanner(new File("bitvals.txt"));
	//int i = 0;
	//p vals are bitvals all of which are collated into a single point
	double p1 = 0;
	double p2 = 0;
	double p3 = 0;
	double p4 = 0;
	double p5 = 0;
	//tvals are for timestamps
	int t1 =0;
	int t2 = 0;
	int t3 = 0;
	int t4 = 0;
	int t5 = 0;
	while(s.hasNextLine()/*&& i < 3500000*/)
	{
		
		
		//This pattern cuts out the commas from the file and combines the two values separated by decimal to a single double
		//If clauses prevent overrunning end of file and if we don't have all 5 necessary values then we throw them out and close the file
		
		s.findInLine("(\\d+)([0-9]*\\.[0-9]*)");
		MatchResult m = s.match();
		if(s.hasNextLine())
		{
		t1 = Integer.parseInt(m.group(1));
		p1 = Double.parseDouble(m.group(0));
		s.nextLine();
		//Create second value in a data point
		if(s.hasNextLine())
		{
		s.findInLine("(\\d+)([0-9]*\\.[0-9]*)");
		m = s.match();
		
		t2 = Integer.parseInt(m.group(1));
		p2 = Double.parseDouble(m.group(0));
		
		s.nextLine();
		
		//Create third value in a data point
		if(s.hasNextLine())
		{
		s.findInLine("(\\d+)([0-9]*\\.[0-9]*)");
		m = s.match();
		
		t3 = Integer.parseInt(m.group(1));
		p3 = Double.parseDouble(m.group(0));
		s.nextLine();
		
		//Create fourth value
		if(s.hasNextLine())
		{
		s.findInLine("(\\d+)([0-9]*\\.[0-9]*)");
		m = s.match();
		
		t4 = Integer.parseInt(m.group(1));
		p4 = Double.parseDouble(m.group(0));
		s.nextLine();
		//Create last value this is the one that we test against. I.E the first four values are read into the neural net as features and the output is the fifth
		//Thus in practice we will use four closest values for which we have data as features
		//Future implementations might compute the four values if data is unavailable before spitting out a final prediction
		if(s.hasNextLine())
		{
		s.findInLine("(\\d+)([0-9]*\\.[0-9]*)");
		m = s.match();
		
		t5 = Integer.parseInt(m.group(1));
		p5 = Double.parseDouble(m.group(0));
	
		}
		
		else {
			t5 = 0;
			p5 = 0;
			break;
		}
		}
		else
		{
			t4 = 0;
			p4 = 0;
			break;
		}
		}
		else
		{
			t3 = 0;
			p3 = 0;
			break;
		}
		}
		else
		{
			t2 = 0; 
			p2 = 0;
		}
		}
		
		else
		{
			t1=0;
			p1=0;
	}
		//Add our full data point to the array
		d.add(new DataPoint(p1,t1,p2,t2,p3,t3,p4,t4,p5,t5));
	}
	s.close();
	//tRM is the neural net used
	NeuralNet theRealMccoy = new NeuralNet();
	//We define tRM's output function to be a linear combination of first degree poly's
	theRealMccoy.setOutputNeuron(new Neuron(new ExponentialTerm(1)));
	//We begin adding features. Here they are days of the week and month and then the first four values
	//The p values here add each successive feature to a list that the final function is based off of. Adding a feature to the neural net only creates a spot for it in the computed function p stores values that are assigned for each data point read in
	theRealMccoy.addInputNeuron(new Neuron(new PrimaryInput("Monday")));
	PrimaryInput p = new PrimaryInput("Monday");
	theRealMccoy.addInputNeuron(new Neuron(new PrimaryInput("Tuesday")));
	p = new PrimaryInput("Tuesday");
	theRealMccoy.addInputNeuron(new Neuron(new PrimaryInput("Wednesday")));
	p = new PrimaryInput("Wednesday");
	theRealMccoy.addInputNeuron(new Neuron(new PrimaryInput("Thursday")));
	p = new PrimaryInput("Thursday");
	theRealMccoy.addInputNeuron(new Neuron(new PrimaryInput("Friday")));
	p = new PrimaryInput("Friday");
	theRealMccoy.addInputNeuron(new Neuron(new PrimaryInput("Saturday")));
	p = new PrimaryInput("Saturday");
	theRealMccoy.addInputNeuron(new Neuron(new PrimaryInput("Sunday")));
	p = new PrimaryInput("Sunday");
	theRealMccoy.addInputNeuron(new Neuron(new PrimaryInput("January")));
	p = new PrimaryInput("January");
	theRealMccoy.addInputNeuron(new Neuron(new PrimaryInput("February")));
	p = new PrimaryInput("February");
	theRealMccoy.addInputNeuron(new Neuron(new PrimaryInput("March")));
	p = new PrimaryInput("March");
	theRealMccoy.addInputNeuron(new Neuron(new PrimaryInput("April")));
	p = new PrimaryInput("April");
	theRealMccoy.addInputNeuron(new Neuron(new PrimaryInput("May")));
	p = new PrimaryInput("May");
	theRealMccoy.addInputNeuron(new Neuron(new PrimaryInput("June")));
	p = new PrimaryInput("June");
	theRealMccoy.addInputNeuron(new Neuron(new PrimaryInput("July")));
	p = new PrimaryInput("July");
	theRealMccoy.addInputNeuron(new Neuron(new PrimaryInput("August")));
	p = new PrimaryInput("August");
	theRealMccoy.addInputNeuron(new Neuron(new PrimaryInput("September")));
	p = new PrimaryInput("September");
	theRealMccoy.addInputNeuron(new Neuron(new PrimaryInput("October")));
	p = new PrimaryInput("October");
	theRealMccoy.addInputNeuron(new Neuron(new PrimaryInput("November")));
	p = new PrimaryInput("November");
	theRealMccoy.addInputNeuron(new Neuron(new PrimaryInput("December")));
	p = new PrimaryInput("December");
	theRealMccoy.addInputNeuron(new Neuron(new PrimaryInput("p1")));
	p = new PrimaryInput("p1");
	theRealMccoy.addInputNeuron(new Neuron(new PrimaryInput("p2")));
	p = new PrimaryInput("p2");
	theRealMccoy.addInputNeuron(new Neuron(new PrimaryInput("p3")));
	p = new PrimaryInput("p3");
	theRealMccoy.addInputNeuron(new Neuron(new PrimaryInput("p4")));
	p = new PrimaryInput("p4");
	
	for(int j = 0; j<(int)((4.4*d.size())/5); j++ )
	{
		//We assign values to the features for each data point
		DataPoint dp = d.get(j);
		p.setInput("Monday", dp.features[12]);
		p.setInput("Tuesday", dp.features[13]);
		p.setInput("Wednesday", dp.features[14]);
		p.setInput("Thursday", dp.features[15]);
		p.setInput("Friday", dp.features[16]);
		p.setInput("Saturday", dp.features[17]);
		p.setInput("Sunday", dp.features[18]);
		p.setInput("January", dp.features[0]);
		p.setInput("February", dp.features[1]);
		p.setInput("March", dp.features[2]);
		p.setInput("April", dp.features[3]);
		p.setInput("May", dp.features[4]);
		p.setInput("June", dp.features[5]);
		p.setInput("July", dp.features[6]);
		p.setInput("August", dp.features[7]);
		p.setInput("September", dp.features[8]);
		p.setInput("October", dp.features[9]);
		p.setInput("November", dp.features[10]);
		p.setInput("December", dp.features[11]);
		p.setInput("p1", dp.fVals[0]);
		p.setInput("p2", dp.fVals[1]);
		p.setInput("p3", dp.fVals[2]);
		p.setInput("p4", dp.fVals[3]);
	
		//Learn updates the weights for each feature based on how close the current model is to the prediction for each successive data point in the training set. The alpha value makes sure that each incremental change is not too large but the amount changed is based off the gradient of the error between expected and returned
		theRealMccoy.learn(dp);
		
		
		}
	System.out.println("Final Equation with variables plugged in: " + theRealMccoy.getOutputNeuron());
	
	for(int k =(int) (4.4*d.size()/5); k<d.size();k++)
	{
		//Repeat same proccess for testing
		DataPoint dp = d.get(k);
		p.setInput("Monday", dp.features[12]);
		p.setInput("Tuesday", dp.features[13]);
		p.setInput("Wednesday", dp.features[14]);
		p.setInput("Thursday", dp.features[15]);
		p.setInput("Friday", dp.features[16]);
		p.setInput("Saturday", dp.features[17]);
		p.setInput("Sunday", dp.features[18]);
		p.setInput("January", dp.features[0]);
		p.setInput("February", dp.features[1]);
		p.setInput("March", dp.features[2]);
		p.setInput("April", dp.features[3]);
		p.setInput("May", dp.features[4]);
		p.setInput("June", dp.features[5]);
		p.setInput("July", dp.features[6]);
		p.setInput("August", dp.features[7]);
		p.setInput("September", dp.features[8]);
		p.setInput("October", dp.features[9]);
		p.setInput("November", dp.features[10]);
		p.setInput("December", dp.features[11]);
		p.setInput("p1", dp.fVals[0]);
		p.setInput("p2", dp.fVals[1]);
		p.setInput("p3", dp.fVals[2]);
		p.setInput("p4", dp.fVals[3]);
		//The only real difference is here we do not learn. Rather we are interested in how close our prediction for each data point is to the actual value recorded as the fifth value in each data point in the testing set.
		//Squared error gets +- error of expected versus actual squares it and adds it in accordance with the error model for neural nets
		theRealMccoy.getSquaredError(dp);
		
		
	}
	//Math things into place by dividing for number of points and taking square root
	System.out.println("Average error of our net:" + theRealMccoy.getAverageLoss(d));
	
	//This is largely unneccessary its just a comparison to what the prediction would be for each point if our algorithm was just to take the average of the four closest values and use that as our guess. Note that a shitty neural net beats the tar out of it by more than plus or minus 100 in many cases.
	double sMEV = 0;
	for(DataPoint dp : d) {
	  sMEV += Math.pow(dp.getExpected()-(dp.fVals[0]+dp.fVals[1]+dp.fVals[2]+dp.fVals[3]+dp.fVals[4])/5, 2)/dp.getExpected();
	}
	sMEV /= d.size();
	sMEV = Math.pow(sMEV, 0.5);
	System.out.println("Error of the naive expectation: "+ sMEV);
	}
}
	

