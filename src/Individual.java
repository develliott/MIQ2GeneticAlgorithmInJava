//package simpleGa;

import java.util.Random;

public class Individual {

    private Random _r = new Random();

    static int defaultGeneLength = 10;
    private int[] _genes = new int[defaultGeneLength];
    // Cache
    private int fitness = 0;

    // Create a random individual
    public void generateIndividual() {

        int minEmployeeId = 1;
        int maxEmployeeId = 13;

        for (int i = 0; i < size(); i++) {

            // Generate a random number between 1 and 13
            int gene = _r.nextInt((maxEmployeeId - minEmployeeId) + 1) + minEmployeeId;
            _genes[i] = gene;
        }
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
        fitness = 0;
    }

    /* Public methods */
    public int size() {
        return _genes.length;
    }

    public int getFitness() {
        if (fitness == 0) {
            fitness = FitnessCalc.getFitness(this);
        }
        return fitness;
    }

    @Override
    public String toString() {
        String geneString = "";
        for (int i = 0; i < size(); i++) {
            geneString += getGene(i);
        }
        return geneString;
    }
}