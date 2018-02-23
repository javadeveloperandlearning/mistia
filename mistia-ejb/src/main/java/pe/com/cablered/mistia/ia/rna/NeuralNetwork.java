package pe.com.cablered.mistia.ia.rna;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;

/**
 *
 * @author QOSMIO
 */
public class NeuralNetwork {
    static {
        Locale.setDefault(Locale.ENGLISH);
    }
 
     boolean isTrained = false;
    final DecimalFormat df;
    final Random rand = new Random();
    final ArrayList<Neuron> inputLayer = new ArrayList<Neuron>();
    final ArrayList<Neuron> hiddenLayer = new ArrayList<Neuron>();
    final ArrayList<Neuron> outputLayer = new ArrayList<Neuron>();
    final Neuron bias = new Neuron();
    final int[] layers;
    final int randomWeightMultiplier = 1;
 
    final double epsilon = 0.00000000001;
 
    final double learningRate = 0.9f;
    final double momentum = 0.7f;
 /*
    // Inputs for xor problem
    final double inputs[][] = { { 1, 1 }, { 1, 0 }, { 0, 1 }, { 0, 0 } };
 
    // Corresponding outputs, xor training data
    final double expectedOutputs[][] = { { 0 }, { 1 }, { 1 }, { 0 } };
    double resultOutputs[][] = { { -1 }, { -1 }, { -1 }, { -1 } }; // dummy init
    double output[];*/
    
    
      // Inputs for xor problem
   /* final double inputs[][] = { { 10, 9, 10 },{ 9, 10, 9 },{ 8, 9, 10 },{ 10, 9, 8 },
                                { 7, 9, 8  },{ 9.5,  8.5 , 9.1 },{ 10, 10, 10 },{ 8, 9.75, 8.5 },
                                { 9.5, 8.0, 9.1 },{ 7.8, 8.5, 10 },{ 8.0, 8.25, 9.8 },{ 7.86, 8.29, 9.28 },
                                { 9.6, 8.55, 9.8 },{ 7.9, 8.58, 8 },{ 8.9, 8.75, 9.75 },{ 7.89, 8.75, 9.0 },
                                };*/
    
    
  public double inputs[][] = { 
		    {10,5,2,2,10,3,3,1,10,5,15,10,0.8,0.9,0.95,0.8},
			{10,4,2,2,10,1,1,1,2,5,15,5,0.8,0.85,0.89,0.85},
			{0,2,2,2,0,2,15,15,2,5,15,1,0.8,0.85,0.95,0.89},
			{2,4,1,2,5,2,1,1,10,5,15,3,0.7,0.75,0.79,0.85},
			{1,5,1,10,10,2,10,0,0,0,0,0,0.75,0.74,0.85,0.83}
            };
    
    // Corresponding outputs, xor training data
   public   double expectedOutputs[][] = { { 1 },{ 1 }, { 1 },{ 1 }, { 1 },{ 1 }, { 1 },{ 1 }, 
                                        { 1 },{ 1 }, { 1 },{ 1 }, { 1 },{ 1 }, { 1 },{ 1 }
                                        };
   public   double resultOutputs[][] = { { -1 }, { -1 }, { -1 }, { -1 }, { -1 }, { -1 }, { -1 }, { -1 },
                                 { -1 }, { -1 }, { -1 }, { -1 }, { -1 }, { -1 }, { -1 }, { -1 }}; // dummy init
    
    
 /*  public double inputs[][] = { };
    
    // Corresponding outputs, xor training data
   public   double expectedOutputs[][] = { };
   public   double resultOutputs[][] = { }; // dummy init*/
   
  double output[];
 
    // for weight update all
     HashMap<String, Double> weightUpdate = new HashMap<String, Double>();
     HashMap<String, Map<String, Double>> weightUpdateOut = new HashMap<String, Map<String, Double>>();
 
    public static void main(String[] args) {
        //NeuralNetwork nn = new NeuralNetwork(2, 4, 1);
        NeuralNetwork nn = new NeuralNetwork(16, 4, 1, false,null);
        int maxRuns = 50000;
        double minErrorCondition = 0.001;
        nn.run(maxRuns, minErrorCondition);
        //nn.testNet();
    }
    
    
    
    public boolean isTrained() {
    	
    	
    	
    	
    	
		return isTrained;
	}



	public  double[][] getOutPuts( double[][] inputs){
    	
    	double _resultOutputs[][] = new double[inputs.length][1];
    	
    	for (int p = 0; p < inputs.length; p++) {
            
            setInput(inputs[p]);
            activate();

            double _output[] = getOutput();
            _resultOutputs[p] = _output;
    	}    	
    	
    	return _resultOutputs;
    } 
    
    
    public  void init(){
     	NeuralNetwork nn = new NeuralNetwork(3, 4, 1, false, null);
    }
    
    
    private void testNet1(){
    	
    	
    	
    	
    }
    
    
    
    
    
    private void testNet(){
    
    	double _inputs[][] = { {0, 0, 0 },{ 0.72, 0.8, 0.7 }};
    	
        double[][] _outputs =  getOutPuts(_inputs);
    	
        System.out.println(" restult output : "+_outputs[0][0] );
        System.out.println(" restult output : "+_outputs[1][0] );
        //{ 10, 9, 10 },{ 9, 10, 9 },{ 8, 9, 10 },
       /* double _inputs[][] = { {0, 0, 0 }};
                setInput(_inputs[0]);
                activate();
         double[] _output = getOutput();
                
                
         System.out.println(" restult output : "+_output[0]);
         
          double _inputs2[][] = { { 10, 9, 10 }};
          setInput(_inputs2[0]);
          activate();
         _output = getOutput();
           System.out.println(" restult output : "+_output[0]);
                
          double _inputs3[][] = { { 9.5, 8.0, 9.1  }};
          setInput(_inputs3[0]);
          activate();
         _output = getOutput();
           System.out.println(" restult output : "+_output[0]);
           
           
          double _inputs4[][] = { { 5, 7.5, 6}};
          setInput(_inputs4[0]);
          activate();
         _output = getOutput();
           System.out.println(" restult output : "+_output[0]);*/
    
    }
    

    
    
    public NeuralNetwork(int input, int hidden, int output,  boolean isTrained, HashMap<String, Double> weightUpdate) {
    	
    	this.isTrained =  isTrained;
        this.layers = new int[] { input, hidden, output };
        df = new DecimalFormat("#.0#");
 
        /**
         * Create all neurons and connections Connections are created in the
         * neuron class
         */
        for (int i = 0; i < layers.length; i++) {
            if (i == 0) { // input layer
                for (int j = 0; j < layers[i]; j++) {
                    Neuron neuron = new Neuron();
                    inputLayer.add(neuron);
                }
            } else if (i == 1) { // hidden layer
                for (int j = 0; j < layers[i]; j++) {
                    Neuron neuron = new Neuron();
                    neuron.addInConnectionsS(inputLayer);
                    neuron.addBiasConnection(bias);
                    hiddenLayer.add(neuron);
                }
            }
 
            else if (i == 2) { // output layer
                for (int j = 0; j < layers[i]; j++) {
                    Neuron neuron = new Neuron();
                    neuron.addInConnectionsS(hiddenLayer);
                    neuron.addBiasConnection(bias);
                    outputLayer.add(neuron);
                }
            } else {
                System.out.println("!Error NeuralNetwork init");
            }
        }
 
        // initialize random weights
        for (Neuron neuron : hiddenLayer) {
            ArrayList<Connection> connections = neuron.getAllInConnections();
            for (Connection conn : connections) {
                ///double newWeight = getRandom();
                double newWeight = 0.0;
                conn.setWeight(newWeight);
            }
        }
        for (Neuron neuron : outputLayer) {
            ArrayList<Connection> connections = neuron.getAllInConnections();
            for (Connection conn : connections) {
                //double newWeight = getRandom();
                double newWeight = 0.0;
                conn.setWeight(newWeight);
            }
        }
 
        // reset id counters
        Neuron.counter = 0;
        Connection.counter = 0;
 
        if (isTrained) {
            //trainedWeights();
        	
        	trainedWeights(weightUpdate);
        	
            updateAllWeights();
        }
    }
 
    // random
    double getRandom() {
        return randomWeightMultiplier * (rand.nextDouble() * 2 - 1); // [-1;1[
    }
 
    /**
     * 
     * @param inputs
     *            There is equally many neurons in the input layer as there are
     *            in input variables
     */
    public void setInput(double inputs[]) {
        for (int i = 0; i < inputLayer.size(); i++) {
            inputLayer.get(i).setOutput(inputs[i]);
        }
    }
 
    public double[] getOutput() {
        double[] outputs = new double[outputLayer.size()];
        for (int i = 0; i < outputLayer.size(); i++)
            outputs[i] = outputLayer.get(i).getOutput();
        return outputs;
    }
 
    /**
     * Calculate the output of the neural network based on the input The forward
     * operation
     */
    public void activate() {
        for (Neuron n : hiddenLayer)
            n.calculateOutput();
        for (Neuron n : outputLayer)
            n.calculateOutput();
    }
 
    /**
     * all output propagate back
     * 
     * @param expectedOutput
     *            first calculate the partial derivative of the error with
     *            respect to each of the weight leading into the output neurons
     *            bias is also updated here
     */
    public void applyBackpropagation(double expectedOutput[]) {
 
        // error check, normalize value ]0;1[
        for (int i = 0; i < expectedOutput.length; i++) {
            double d = expectedOutput[i];
            if (d < 0 || d > 1) {
                if (d < 0)
                    expectedOutput[i] = 0 + epsilon;
                else
                    expectedOutput[i] = 1 - epsilon;
            }
        }
 
        int i = 0;
        for (Neuron n : outputLayer) {
            ArrayList<Connection> connections = n.getAllInConnections();
            for (Connection con : connections) {
                double ak = n.getOutput();
                double ai = con.leftNeuron.getOutput();
                double desiredOutput = expectedOutput[i];
 
                double partialDerivative = -ak * (1 - ak) * ai
                        * (desiredOutput - ak);
                double deltaWeight = -learningRate * partialDerivative;
                double newWeight = con.getWeight() + deltaWeight;
                con.setDeltaWeight(deltaWeight);
                con.setWeight(newWeight + momentum * con.getPrevDeltaWeight());
            }
            i++;
        }
 
        // update weights for the hidden layer
        for (Neuron n : hiddenLayer) {
            ArrayList<Connection> connections = n.getAllInConnections();
            for (Connection con : connections) {
                double aj = n.getOutput();
                double ai = con.leftNeuron.getOutput();
                double sumKoutputs = 0;
                int j = 0;
                for (Neuron out_neu : outputLayer) {
                    double wjk = out_neu.getConnection(n.id).getWeight();
                    double desiredOutput = (double) expectedOutput[j];
                    double ak = out_neu.getOutput();
                    j++;
                    sumKoutputs = sumKoutputs
                            + (-(desiredOutput - ak) * ak * (1 - ak) * wjk);
                }
 
                double partialDerivative = aj * (1 - aj) * ai * sumKoutputs;
                double deltaWeight = -learningRate * partialDerivative;
                double newWeight = con.getWeight() + deltaWeight;
                con.setDeltaWeight(deltaWeight);
                con.setWeight(newWeight + momentum * con.getPrevDeltaWeight());
            }
        }
    }
 
    public void run(int maxSteps, double minError) {
        int i;
        // Train neural network until minError reached or maxSteps exceeded
        double error = 1;
        for (i = 0; i < maxSteps && error > minError; i++) {
            error = 0;
            for (int p = 0; p < inputs.length; p++) {
                
                //System.out.println(" input : "+inputs[p][0]+","+inputs[p][1]+", "+inputs[p][2]);
                setInput(inputs[p]);
                activate();
 
                output = getOutput();
                resultOutputs[p] = output;
                
                //System.out.println(" output : "+output[0]);
 
                for (int j = 0; j < expectedOutputs[p].length; j++) {
                    double err = Math.pow(output[j] - expectedOutputs[p][j], 2);
                    error += err;
                }
 
                applyBackpropagation(expectedOutputs[p]);
            }
        }
 
        printResult();
         
        System.out.println("Sum of squared errors = " + error);
        System.out.println("##### EPOCH " + i+"\n");
        if (i == maxSteps) {
            System.out.println("!Error training try again");
        } else {
            printAllWeights();
            printWeightUpdate();
        }
    }
     
    void printResult()
    {
        System.out.println("NN example with xor training");
        for (int p = 0; p < inputs.length; p++) {
            System.out.print("INPUTS: ");
            for (int x = 0; x < layers[0]; x++) {
                System.out.print(inputs[p][x] + " ");
            }
 
            System.out.print("EXPECTED: ");
            for (int x = 0; x < layers[2]; x++) {
                System.out.print(expectedOutputs[p][x] + " ");
            }
 
            System.out.print("ACTUAL: ");
            for (int x = 0; x < layers[2]; x++) {
                System.out.print(resultOutputs[p][x] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
 
    String weightKey(int neuronId, int conId) {
        return "N" + neuronId + "_C" + conId;
    }
 
    /**
     * Take from hash table and put into all weights
     */
    public void updateAllWeights() {
        // update weights for the output layer
        for (Neuron n : outputLayer) {
            ArrayList<Connection> connections = n.getAllInConnections();
            for (Connection con : connections) {
                String key = weightKey(n.id, con.id);
                double newWeight = weightUpdate.get(key);
                con.setWeight(newWeight);
            }
        }
        // update weights for the hidden layer
        for (Neuron n : hiddenLayer) {
            ArrayList<Connection> connections = n.getAllInConnections();
            for (Connection con : connections) {
                String key = weightKey(n.id, con.id);
                double newWeight = weightUpdate.get(key);
                con.setWeight(newWeight);
            }
        }
    }
 
    
    public  void trainedWeights(HashMap<String, Double> weightUpdate) {
    	System.out.println(" actualizando pesos ");
    	this.weightUpdate.clear();
    	
    	Iterator<String> it = weightUpdate.keySet().iterator();
    	while(it.hasNext()){
    		String key =  it.next();
    		this.weightUpdate.put(key, weightUpdate.get(key));
    	}
    	
    }
    
    // trained data
    void trainedWeights() {
        weightUpdate.clear();
        weightUpdate.put(weightKey(17, 0), 0.09619492167261015);
        weightUpdate.put(weightKey(17, 1), 0.07354475086046393);
        weightUpdate.put(weightKey(17, 2), 0.030836985380242585);
        weightUpdate.put(weightKey(17, 3), 0.07621505338741799);
        weightUpdate.put(weightKey(17, 4), 0.14301147849710572);
        weightUpdate.put(weightKey(17, 5), 0.03302448304859026);
        weightUpdate.put(weightKey(17, 6), 0.11878104847398906);
        weightUpdate.put(weightKey(17, 7), 0.06367453748135177);
        weightUpdate.put(weightKey(17, 8), 0.0542904770799402);
        weightUpdate.put(weightKey(17, 9), 0.06648534569489142);
        weightUpdate.put(weightKey(17, 10), 0.19945603708467396);
        weightUpdate.put(weightKey(17, 11), 0.06436728420197542);
        weightUpdate.put(weightKey(17, 12), 0.014287299503526224);
        weightUpdate.put(weightKey(17, 13), 0.015039671942445048);
        weightUpdate.put(weightKey(17, 14), 0.016360716189377168);
        weightUpdate.put(weightKey(17, 15), 0.015427840448972784);
        weightUpdate.put(weightKey(17, 16), 0.0);
        weightUpdate.put(weightKey(18, 17), 0.09619492167261015);
        weightUpdate.put(weightKey(18, 18), 0.07354475086046393);
        weightUpdate.put(weightKey(18, 19), 0.030836985380242585);
        weightUpdate.put(weightKey(18, 20), 0.07621505338741799);
        weightUpdate.put(weightKey(18, 21), 0.14301147849710572);
        weightUpdate.put(weightKey(18, 22), 0.03302448304859026);
        weightUpdate.put(weightKey(18, 23), 0.11878104847398906);
        weightUpdate.put(weightKey(18, 24), 0.06367453748135177);
        weightUpdate.put(weightKey(18, 25), 0.0542904770799402);
        weightUpdate.put(weightKey(18, 26), 0.06648534569489142);
        weightUpdate.put(weightKey(18, 27), 0.19945603708467396);
        weightUpdate.put(weightKey(18, 28), 0.06436728420197542);
        weightUpdate.put(weightKey(18, 29), 0.014287299503526224);
        weightUpdate.put(weightKey(18, 30), 0.015039671942445048);
        weightUpdate.put(weightKey(18, 31), 0.016360716189377168);
        weightUpdate.put(weightKey(18, 32), 0.015427840448972784);
        weightUpdate.put(weightKey(18, 33), 0.0);
        weightUpdate.put(weightKey(19, 34), 0.09619492167261015);
        weightUpdate.put(weightKey(19, 35), 0.07354475086046393);
        weightUpdate.put(weightKey(19, 36), 0.030836985380242585);
        weightUpdate.put(weightKey(19, 37), 0.07621505338741799);
        weightUpdate.put(weightKey(19, 38), 0.14301147849710572);
        weightUpdate.put(weightKey(19, 39), 0.03302448304859026);
        weightUpdate.put(weightKey(19, 40), 0.11878104847398906);
        weightUpdate.put(weightKey(19, 41), 0.06367453748135177);
        weightUpdate.put(weightKey(19, 42), 0.0542904770799402);
        weightUpdate.put(weightKey(19, 43), 0.06648534569489142);
        weightUpdate.put(weightKey(19, 44), 0.19945603708467396);
        weightUpdate.put(weightKey(19, 45), 0.06436728420197542);
        weightUpdate.put(weightKey(19, 46), 0.014287299503526224);
        weightUpdate.put(weightKey(19, 47), 0.015039671942445048);
        weightUpdate.put(weightKey(19, 48), 0.016360716189377168);
        weightUpdate.put(weightKey(19, 49), 0.015427840448972784);
        weightUpdate.put(weightKey(19, 50), 0.0);
        weightUpdate.put(weightKey(20, 51), 0.09619492167261015);
        weightUpdate.put(weightKey(20, 52), 0.07354475086046393);
        weightUpdate.put(weightKey(20, 53), 0.030836985380242585);
        weightUpdate.put(weightKey(20, 54), 0.07621505338741799);
        weightUpdate.put(weightKey(20, 55), 0.14301147849710572);
        weightUpdate.put(weightKey(20, 56), 0.03302448304859026);
        weightUpdate.put(weightKey(20, 57), 0.11878104847398906);
        weightUpdate.put(weightKey(20, 58), 0.06367453748135177);
        weightUpdate.put(weightKey(20, 59), 0.0542904770799402);
        weightUpdate.put(weightKey(20, 60), 0.06648534569489142);
        weightUpdate.put(weightKey(20, 61), 0.19945603708467396);
        weightUpdate.put(weightKey(20, 62), 0.06436728420197542);
        weightUpdate.put(weightKey(20, 63), 0.014287299503526224);
        weightUpdate.put(weightKey(20, 64), 0.015039671942445048);
        weightUpdate.put(weightKey(20, 65), 0.016360716189377168);
        weightUpdate.put(weightKey(20, 66), 0.015427840448972784);
        weightUpdate.put(weightKey(20, 67), 0.0);
        
        weightUpdate.put(weightKey(21, 68), 1.0680613854256082);
        weightUpdate.put(weightKey(21, 69), 1.0680613854256082);
        weightUpdate.put(weightKey(21, 70), 1.0680613854256082);
        weightUpdate.put(weightKey(21, 71), 1.0680613854256082);
        weightUpdate.put(weightKey(21, 72), 0.0);
    }
    
 
    public void printWeightUpdate() {
        System.out.println("printWeightUpdate, put this i trainedWeights() and set isTrained to true");
        // weights for the hidden layer
        int np = 1;
        for (Neuron n : hiddenLayer) {
            ArrayList<Connection> connections = n.getAllInConnections();
            for (Connection con : connections) {
                String w = df.format(con.getWeight());
              //print test
                //System.out.println("weightUpdate.put(weightKey(" + n.id + ", "+ con.id + "), " + con.getWeight() + ");");
                
               // System.out.println( "1," +np+", "+ n.id + ", "+ con.id + ", " + con.getWeight());
                Map<String, Double> values =  new HashMap<String, Double>();
                values.put("idx",(double) np);
                values.put("n", (double)n.id);
                values.put("c",(double)con.id);
                values.put("peso",con.getWeight());
                weightUpdateOut.put(weightKey(n.id,con.id), values);
                
                np++;
            }
        }
       // System.out.println(" outputLayer, put this ");
        // weights for the output layer
        for (Neuron n : outputLayer) {
            ArrayList<Connection> connections = n.getAllInConnections();
            for (Connection con : connections) {
                String w = df.format(con.getWeight());
                //System.out.println("weightUpdate.put(weightKey(" + n.id + ", "+ con.id + "), " + con.getWeight() + ");");
                //print test
                //System.out.println(  "1," +np+", "+ n.id + ", "+ con.id + ", " + con.getWeight());
                
                Map<String, Double> values =  new HashMap<String, Double>();
                values.put("idx",(double) np);
                values.put("n", (double)n.id);
                values.put("c",(double)con.id);
                values.put("peso",con.getWeight());
                weightUpdateOut.put(weightKey(n.id,con.id), values);
                np++;
            }
        }
        
        System.out.println();
    }
 
    public void printAllWeights() {
        //System.out.println("printAllWeights");
        // weights for the hidden layer
        for (Neuron n : hiddenLayer) {
            ArrayList<Connection> connections = n.getAllInConnections();
            for (Connection con : connections) {
                double w = con.getWeight();
              //print test
                //System.out.println("n=" + n.id + " c=" + con.id + " w=" + w);
            }
        }
        // weights for the output layer
        for (Neuron n : outputLayer) {
            ArrayList<Connection> connections = n.getAllInConnections();
            for (Connection con : connections) {
                double w = con.getWeight();
              //print test
              //  System.out.println("n=" + n.id + " c=" + con.id + " w=" + w);
            }
        }
        System.out.println();
    }



	public HashMap<String, Map<String, Double>> getWeightUpdateOut() {
		return weightUpdateOut;
	}



	public void setWeightUpdateOut(HashMap<String, Map<String, Double>> weightUpdateOut) {
		this.weightUpdateOut = weightUpdateOut;
	}



    
    
    
    
    
    
    
    
}