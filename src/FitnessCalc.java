public class FitnessCalc {

    // Calculate individuals fitness by aggregating each assigned employee's performance scores
    static int getFitness(Individual individual) {
        int fitness = 0;
        // Loop through the individuals genes and add each employee's performance score to the fitness.
        for (int i = 0; i < individual.getSize(); i++) {

            int currentEmployeeIndex = individual.getGene(i);
            int currentTaskIndex = i;

            int performanceScore = EmployeeDataSet.getEmployeePerformanceScore(currentEmployeeIndex, currentTaskIndex);

            fitness += performanceScore;
        }
        return fitness;
    }
}