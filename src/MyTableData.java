
/**
 * Created by Ari100tell on 03.10.13.
 */
public class MyTableData {


    public double getCellValue(int i, int j) {
        return data[i][j];
    }

    public int getDataSize() {
        return data.length;
    }

    public double getFreeVariables(int i) {
        return freeVariables[i];
    }

    public double[][] getData() {
        return data;
    }

    private double[][] data = {               //Данные
            {0.15, 2.11, 30.75},
            {0.64, 1.21, 2.05},
            {3.21, 1.53, 1.04},
    };

    private double[] freeVariables = {-26.38, 1.01, 5.23};
}
