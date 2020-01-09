public class GA {

    public static void main(String[] args) {

        // Initialise a mechanism for looking up performance scores.
        CSVReader csvReader = new CSVReader(System.getProperty("user.dir") + "/src/data.csv");
        EmployeeDataSet employeeDataSet = csvReader.parseDataIntoEmployeeDataSet();

        // Create an initial population of 50 individuals
        Population myPop = new Population(50, true);
//
        // Evolve our population until we reach an near-optimal solution
        int generationCount = 0;
        int maxGenerations = 100000;

        int globalHighestScore = 0;
        int generationsWithNoChangeToScore = 0;
        int maxGenerationsWithNoChangeToScore = 500;

        // Simulate 'maxGenerations' amount of generations, unless the score hasn't changed for 'maxGenerationsWithNoChangeToScore'.
        while (generationCount < maxGenerations && generationsWithNoChangeToScore < maxGenerationsWithNoChangeToScore) {
            generationCount++;

            // Find the fittest individual in the current population.
            int fittestScoreInPop = myPop.getFittest().getFitness();

            // If the score hasn't improved since the last generation ->
            if (fittestScoreInPop == globalHighestScore){
                // -> Increment 'generationsWithNoChangeToScore' by 1.
                generationsWithNoChangeToScore++;
            }
            else{
                // Reset 'generationsWithNoChangeToScore' to 0.
                generationsWithNoChangeToScore = 0;
            }

            // If the current fittest score is more than the global highest score ->
            if (fittestScoreInPop > globalHighestScore){
                // -> update the global highest score with the current fittest score.
                globalHighestScore = fittestScoreInPop;
            }

            System.out.println("Generation: " + generationCount + " Fittest: " + fittestScoreInPop );
            myPop = Algorithm.evolvePopulation(myPop);
        }
        System.out.println("\nSolution found!");

        if(generationsWithNoChangeToScore == maxGenerationsWithNoChangeToScore){
            String message = String.format("The score hasn't changed in %d generations, so it has been considered an optimal solution.", maxGenerationsWithNoChangeToScore);
            System.out.println(message);
        }

        System.out.println("\nGeneration: "+ generationCount);
        System.out.println("Genes (Employee IDs):");
        System.out.println(myPop.getFittest());
        System.out.println("Fitness: " + myPop.getFittest().getFitness());

    }
}