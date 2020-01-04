//package simpleGa;

public class FitnessCalc {

//    static byte[] solution = new byte[64];

//    /* Public methods */
//    // Set a candidate solution as a byte array
//    public static void setSolution(byte[] newSolution) {
//        solution = newSolution;
//    }
//
//    // To make it easier we can use this method to set our candidate solution
//    // with string of 0s and 1s
//    static void setSolution(String newSolution) {
//        solution = new byte[newSolution.length()];
//        // Loop through each character of our string and save it in our byte
//        // array
//        for (int i = 0; i < newSolution.length(); i++) {
//            String character = newSolution.substring(i, i + 1);
//            if (character.contains("0") || character.contains("1")) {
//                solution[i] = Byte.parseByte(character);
//            } else {
//                solution[i] = 0;
//            }
//        }
//    }

    // Calculate individuals fittness by aggregating each employee's performance scores
    static int getFitness(Individual individual) {
        int fitness = 0;
        // Loop through our individuals genes and add each employee's performance score to the fitness.
        for (int i = 0; i < individual.getSize(); i++) {
            fitness += individual.getGene(i);
        }
        return fitness;
    }
    
//    // Get optimum fitness
//    static int getMaxFitness() {
//        int maxFitness = solution.length;
//        return maxFitness;
//    }
}