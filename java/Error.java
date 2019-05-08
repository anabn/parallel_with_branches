public class Error {

    double error(double[][] matrix) {
        double maxError = 0;
        for (int t = 0; t < Const.timeIterations; t++) {
            for (int i = 0; i < Const.xIterations; i++) {
                double temp = Math.abs(Exact.matrix[t][i] - matrix[t][i]) / Exact.matrix[t][i];
                if (temp > maxError) {
                    maxError = temp;
                }
            }
        }
        return maxError;
    }
}
