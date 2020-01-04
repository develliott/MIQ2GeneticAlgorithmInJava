import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class CSVReader {

    private String _filePath;

    public CSVReader(String filePath) {
        _filePath = filePath;
    }

    public EmployeeDataSet parseDataIntoEmployeeDataSet() {

        // Todo: Refactor the hardcoded amounts.
        EmployeeDataSet employeeDataSet = new EmployeeDataSet(13, 10);

        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";

        try {

            int employeeIndex = 0;

            br = new BufferedReader(new FileReader(_filePath));
            while ((line = br.readLine()) != null) {

                // use comma as separator
                String[] employeeData = line.split(cvsSplitBy);

                for (int taskIndex = 0; taskIndex < employeeData.length; taskIndex++) {
                    employeeDataSet.setEmployeeTaskPerformance(employeeIndex, taskIndex, Integer.parseInt(employeeData[taskIndex]));
                }

                employeeIndex++;
            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return employeeDataSet;
    }
}