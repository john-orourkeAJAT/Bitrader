import java.util.Date;
public class DataPoint {
double p1 = 0;
int t1 = 0; 
double p2 = 0;
int t2 = 0;
double p3 = 0;
int t3 = 0;
double p4 = 0;
int t4 = 0;
double p5 = 0;
int t5 = 0;

double sqError = 0;
//First 12 are months last 7 are days
boolean [] features = new boolean [19];
double [] fVals = new double[5];
int [] tVals = new int[5];
boolean isValid = false;
public DataPoint(double one, int to, double two, int tt, double three, int tth, double four, int tf, double five, int tfi )
{
	p1 = one;
	p2 = two;
	p3 = three;
	p4 = four;
	p5 = five;
	
	t1 = to;
	t2 = tt;
	t3 = tth;
	t4 = tf;
	t5 = tfi;
	//Fill the array with values to feed to perceptrons
	fVals[0] = p1;
	fVals[1] = p2;
	fVals[2] = p3;
	fVals[3] = p4;
	fVals[4] = p5;
	//Fill the array with times to feed to perceptrons
	tVals[0] = t1;
	tVals[1] = t2;
	tVals[2] = t3;
	tVals[3] = t4;
	tVals[4] = t5;
	
	if(p1/t1/p2/t2/p3/t3/p4/t4/p5/t5>0)
	{
		isValid = true;
	}
		
	//Generate boolean values from the date of the first point
	genFeatures();
}

@SuppressWarnings("deprecation")
private void genFeatures()
{
	Date d = new Date((long)t1*1000);
	int m = d.getMonth();
	int day = d.getDay();
	features[m] = true;
	features[12+day] = true;
}

public double getExpected()
{
	return p5;
}

public void setError(double e)
{
	sqError = e;
}

public double getError()
{
	return sqError;
}

public boolean validity()
{
	return isValid;
}
}
