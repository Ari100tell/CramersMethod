
/**
 * Класс для вычисления детерминанта
 *
 * Created by Ari100tell on 06.10.13.
 */
public class Determinant {

    private  final double EPSELON = 1E-8;

    public double calculateDeterminant(double[][] matrix) {
        int matrixSize = matrix.length;
        int beforeMatrixSize = matrixSize -1;
        double determinant;
        for (int i = 0; i < beforeMatrixSize; i++) {
            if (Math.abs(matrix[i][i]) < EPSELON) {
                return 0;
            }
            for (int j = i + 1; j < matrixSize; j++) {
                determinant = matrix[j][i] / matrix[i][i];
                for (int k = i; k < matrixSize; k++) {
                    matrix[j][k] = matrix[j][k] - determinant * matrix[i][k];
                }
            }
        }
        determinant = 1.0;
        for (int i = 0; i < matrixSize; i++) {
            determinant = determinant * matrix[i][i];
        }
        return Math.round(determinant);
    }
}

