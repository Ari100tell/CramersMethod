
/**
 * Created by Ari100tell on 06.10.13.
 */
public class Determinant {
    private final int MATRIX_SIZE = 3;
    private final int BEFORE_MATRIX_SIZE = MATRIX_SIZE - 1;
    private final double EPSELON = 1E-8;
    private double[][] matrix = new double[MATRIX_SIZE][MATRIX_SIZE];


    public double calculateDeterminant(double[][] matrix) {

        double determinant;

        for (int i = 0; i < BEFORE_MATRIX_SIZE; i++) {
            if (Math.abs(matrix[i][i]) < EPSELON) {
                return determinant = 0;
            }
            for (int j = i + 1; j < MATRIX_SIZE; j++) {
                determinant = matrix[j][i] / matrix[i][i];
                for (int k = i; k < MATRIX_SIZE; k++) {
                    matrix[j][k] = matrix[j][k] - determinant * matrix[i][k];
                }
            }
        }
        determinant = 1.0;
        for (int i = 0; i < MATRIX_SIZE; i++) {
            determinant = determinant * matrix[i][i];
        }
        return Math.round(determinant);
    }

    public static void main(String[] args) {
        Determinant determinant = new Determinant();
        double[][] m = {
                {0, 3, 2, 1},
                {3, 0, 4, 2},
                {2, 4, 0, 3},
                {1, 2, 3, 0}
        };
        double det;
        det = determinant.calculateDeterminant(m);
        System.out.println(det);
    }
}

