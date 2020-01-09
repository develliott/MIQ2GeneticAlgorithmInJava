//package simpleGa;

public class Population {

    private Individual[] _individuals;

    /*
     * Constructors
     */
    // Create a population
    public Population(int populationSize, boolean initialise) {
        _individuals = new Individual[populationSize];
        // Initialise population
        if (initialise) {
            // Loop and create individuals
            for (int i = 0; i < getSize(); i++) {
                Individual newIndividual = new Individual();
                newIndividual.generateIndividual();
                saveIndividual(i, newIndividual);
            }
        }
    }

    /* Getters */
    public Individual getIndividual(int index) {
        return _individuals[index];
    }

    public Individual getFittest() {
        Individual fittest = _individuals[0];
        // Offset the index because we already have index 0 stored in the 'fittest' variable.
        int indexOffset = 1;

        // Loop through individuals to find fittest
        for (int i = indexOffset; i < getSize(); i++) {
            // If the current element has a higher fitness score than the current 'fittest' ->
            if (fittest.getFitness() <= getIndividual(i).getFitness()) {
                // -> assign it as the the current 'fittest'.
                fittest = getIndividual(i);
            }
        }
        return fittest;
    }

    /* Public methods */
    // Get population size
    public int getSize() {
        return _individuals.length;
    }

    // Save individual
    public void saveIndividual(int index, Individual individual) {
        _individuals[index] = individual;
    }
}