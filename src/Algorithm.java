//package simpleGa;

public class Algorithm {

    /* GA parameters */
    private static final double uniformRate = 0.5;
    private static final double mutationRate = 0.15;
    private static final int tournamentSize = 5;
    private static final boolean elitism = true;

    /* Public methods */
    
    // Evolve a population
    public static Population evolvePopulation(Population pop) {
        Population newPopulation = new Population(pop.getSize(), false);

        int elitismOffset = 0;
        // Keep our best individual
        if (elitism) {
            newPopulation.saveIndividual(0, pop.getFittest());
            elitismOffset = 1;
        }

        // Loop over the population size and create new individuals with
        // crossover
        for (int i = elitismOffset; i < pop.getSize(); i++) {

            // indiv1 = The fittest individual from x random individuals in pop, where x = tournamentSize.
            Individual indiv1 = tournamentSelection(pop);

            // indiv2 = The fittest individual from x random individuals in pop, where x = tournamentSize.
            Individual indiv2 = tournamentSelection(pop);

            Individual newIndiv = crossover(indiv1, indiv2);
            newPopulation.saveIndividual(i, newIndiv);
        }

        // Mutate population
        for (int i = elitismOffset; i < newPopulation.getSize(); i++) {
            mutate(newPopulation.getIndividual(i));
        }

        return newPopulation;
    }

    // Crossover individuals
    private static Individual crossover(Individual indiv1, Individual indiv2) {
        Individual newSol = new Individual();
        newSol.initialiseGenes();

        // Loop through genes
        for (int i = 0; i < indiv1.getSize(); i++) {

            // Set the new gene to 9999 because newSol.initialiseGenes() sets all index's to 9999
            // so the while condition below will activate. - See Individual.initialiseGenes() for more details.
            int newGene = 9999;

            // If the new employee is already assigned, find a new employee
            while (newSol.employeeIsAlreadyAssigned(newGene)){

                // Randomly choose to take a gene from indiv1 or indiv2
                if (Math.random() <= uniformRate) {
                    newGene = indiv1.getGene(i);
                } else {
                    newGene = indiv2.getGene(i);
                }


                // If both individual's allele at the current index already exist in the new solution,
                // then a conflict will always occur and the while loop will never terminate.
                if (newSol.employeeIsAlreadyAssigned(indiv1.getGene(i)) && newSol.employeeIsAlreadyAssigned(indiv2.getGene(i))){
                    // So generate a new gene
                    newGene = newSol.generateNewGene();
                }
            }
            newSol.setGene(i, newGene);
        }
        return newSol;
    }

    // Mutate an individual
    private static void mutate(Individual indiv) {
        // Loop through genes
        for (int i = 0; i < indiv.getSize(); i++) {

            // If the random number is lower or equal to the mutationRate, mutate the gene
            if (Math.random() <= mutationRate) {

                // Set newGene to the current gene to activate the while loop below.
                int newGene = indiv.getGene(i);
                // If the new employee is already assigned, find a new employee
                while(indiv.employeeIsAlreadyAssigned(newGene)) {
                    // Create new gene
                    newGene = indiv.generateNewGene();
                }
                indiv.setGene(i, newGene);
            }

        }
    }

    // Select individuals for crossover
    private static Individual tournamentSelection(Population pop) {
        // Create a tournament population
        Population tournament = new Population(tournamentSize, false);
        // For each place in the tournament get a random individual
        for (int i = 0; i < tournamentSize; i++) {
            int randomId = (int) (Math.random() * pop.getSize());
            tournament.saveIndividual(i, pop.getIndividual(randomId));
        }
        // Get the fittest from the tournament
        Individual fittest = tournament.getFittest();
        return fittest;
    }
}