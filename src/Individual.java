import java.util.Random;

public class Individual {
    static int defaultGeneLength = 10;

    private Random _r = new Random();
    private int[] _genes = new int[defaultGeneLength];
    private int _fitness = 0;

    // Create a random individual
    public void generateIndividual() {
        for (int i = 0; i < getSize(); i++) {
            int gene = generateNewGene();
            _genes[i] = gene;
        }
    }

    public int generateNewGene(){
        int minEmployeeIndex = 0;
        int maxEmployeeIndex = 12;
        // Generate a random number between the minEmployeeIndex and maxEmployeeIndex.
        return _r.nextInt((maxEmployeeIndex - minEmployeeIndex) + 1) + minEmployeeIndex;
    }

    /* Getters and setters */
    // Use this if you want to create individuals with different gene lengths
    public static void setDefaultGeneLength(int length) {
        defaultGeneLength = length;
    }
    
    public int getGene(int index) {
        return _genes[index];
    }

    public void setGene(int index, int value) {
        _genes[index] = value;

        // Reset fitness so it will be computed again when getFitness is called.
        _fitness = 0;
    }

    public int getSize() {
        return _genes.length;
    }

    public int getFitness() {
        // Check that the fitness has not already been computed.
        if (_fitness == 0) {
            _fitness = FitnessCalc.getFitness(this);
        }
        return _fitness;
    }

    @Override
    public String toString() {
        String geneString = "";
        for (int i = 0; i < getSize(); i++) {

            int employeeIndex = getGene(i);
            int employeeId = employeeIndex + 1;

            geneString += employeeId + " ";
        }
        return geneString;
    }
}