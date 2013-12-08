
/**
 * Класс в котором хранятся данные необходимые для решения
 * системы линейных уравнений
 *
 * Created by Ari100tell on 03.10.13.
 */
public class MyTableData {

    //Коэффициенты системы линейных уравнений
    private double[][] data = {
            {0.15, 2.11, 30.75},
            {0.64, 1.21, 2.05},
            {3.21, 1.53, 1.04},
    };

    //Свободные переменные
    private double[] freeVariables = {-26.38, 1.01, 5.23};

    public double getCellValue(int i, int j) {
        return data[i][j];
    }

    public double getFreeVariables(int i) {
        return freeVariables[i];
    }

    public int getDataSize() {
        return data.length;
    }
}
