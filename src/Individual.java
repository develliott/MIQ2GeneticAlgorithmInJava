import java.util.Random;
import java.util.stream.IntStream;

public class Individual {
    static int defaultGeneLength = 10;

    private Random _r = new Random();
    private int[] _genes = new int[defaultGeneLength];
    private int _fitness = 0;

    // Create a random individual
    public void generateIndividual() {
        initialiseGenes();
        for (int i = 0; i < getSize(); i++) {
            int gene = generateNewGene();
            _genes[i] = gene;
        }
    }

    // Return an employeeIndex that does not currently exist in the _genes array.
    public int generateNewGene(){
        int minEmployeeIndex = 0;
        int maxEmployeeIndex = 12;

        // Generate a random number between the minEmployeeIndex and maxEmployeeIndex.
        int employeeIndex = _r.nextInt((maxEmployeeIndex - minEmployeeIndex) + 1) + minEmployeeIndex;

        // Check if the employee has already been assigned,
        // to ensure that each employee can only be assigned 1 task.
        if (employeeIsAlreadyAssigned(employeeIndex)){
            // recursively loop until one is found.
            employeeIndex = generateNewGene();
        }
        return employeeIndex;
    }

    // Return a boolean indicating if the employee has already been assigned.
    public boolean employeeIsAlreadyAssigned(int employeeIndex){
        return IntStream.of(_genes).anyMatch(x -> x == employeeIndex);
    }

    // Set genes to a value that isn't a valid employee index.
    // This is because the _genes array is initialised to 0's by default.
    // This method prevents the employeeIsAlreadyAssigned() check returning true when trying to assign employeeIndex 0.
    public void initialiseGenes(){
        for (int i = 0; i < getSize(); i++) {
            _genes[i] = 9999;
        }
    }

    // Return the value of the gene at the given index
    public int getGene(int index) {
        return _genes[index];
    }

    // Set the value of the gene at the given index
    public void setGene(int index, int value) {
        _genes[index] = value;

        // Reset fitness so it will be computed again when getFitness() is called.
        _fitness = 0;
    }

    // Return the size of the chromosome.
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

        // Return the string as Employee IDs.
        return geneString;
    }
}