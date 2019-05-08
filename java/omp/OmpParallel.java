package omp;

public class OmpParallel {
    public static final double a=1.5;
    public static final double b=0.7;
    public static final double A=65.;
    public static final double B=16.;

    static int xIterations = 10;
    static int timeIterations = 300;
    static double xStep = 1. / (xIterations - 1);
    static double tStep = 1. / (timeIterations - 1);
    static double[][] matrix = new double[timeIterations][xIterations];


    public static void main(String[] args) {
        for (int i = 0; i < xIterations; i++){
            double F = B;
            matrix[0][i] =  Math.pow( (1/F) * Math.pow( i * xStep, 2)
                    + A * Math.pow(F, -0.2)
                    - (b / (24 * a)) * F, 2);
        }

        // x = 0; x =1;
        for (int t = 1; t < timeIterations; t++){
            double F = B - 10 * a * t * tStep;
            matrix[t][0] = Math.pow(A * Math.pow(F, -0.2)- (b / (24 * a)) * F, 2);
            matrix[t][xIterations - 1] = Math.pow((1/F) + A * Math.pow(F, -0.2)
                    - (b / (24 * a)) * F, 2);
        }
        long beginTime = System.currentTimeMillis();
        for (int t = 1; t < timeIterations ; t++){
            // omp parallel for threadNum(3) 
            for (int i = 1; i < xIterations - 1; i++) {
            }
        }
//            oCurrent + tStep * (1/2 * a * (Math.pow(oCurrent, 1/2)) * (Math.pow(((oNext-oPrevious)/(2 * xStep)), 2))
//                + a * (Math.pow(oCurrent, 1/2)) * ((oPrevious - 2 * oCurrent + oNext)/(Math.pow(xStep, 2))) + b * oCurrent)
//                matrix[t][i] = matrix[t-1][i-1] + tStep*(1/2 * a * (Math.pow()))
        long endTime = System.currentTimeMillis();

        double maxError = 0;
        //omp parallel public(maxError)
        for (int t = 0; t < timeIterations; t++) {
            for (int i = 0; i < xIterations; i++) {
                double temp = Math.abs(matrix[t][i] - matrix[t][i]) / matrix[t][i];
                if (temp > maxError) {
                    maxError = temp;
                }
            }
        }
        System.out.println("Computation error: " + maxError*100 + "%\n" + "Computed in " + (endTime - beginTime) + "ms");
    }
}
