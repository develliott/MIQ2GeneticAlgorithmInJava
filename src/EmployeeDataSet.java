public class EmployeeDataSet {

    private static int[][] _dataset;

    public EmployeeDataSet(int amountOfEmployees, int amountOfTasks) {
        _dataset = new int[amountOfEmployees][amountOfTasks];
    }

    public static void setEmployeeTaskPerformance(int employeeIndex, int taskIndex, int performanceScore) {

        _dataset[employeeIndex][taskIndex] = performanceScore;
    }

    public static int getEmployeePerformanceScore(int employeeIndex, int taskIndex) {
        return _dataset[employeeIndex][taskIndex];
    }
}