public class SequentialSolving {

    static double[][] matrix = new double[Const.timeIterations][Const.xIterations];
    private static double approximatedSolution(double oPrevious, double oCurrent, double oNext, double tStep, double xStep, double a, double b) {
        return (oCurrent + tStep * (1/2 * a * (Math.pow(oCurrent, 1/2)) * (Math.pow(((oNext-oPrevious)/(2 * xStep)), 2))
                + a * (Math.pow(oCurrent, 1/2)) * ((oPrevious - 2 * oCurrent + oNext)/(Math.pow(xStep, 2))) + b * oCurrent));
}

    void calculate(){

        Graphic graphic = new Graphic();
        // t = 0
        for (int i = 0; i < Const.xIterations; i++){
            double F = Const.B;
            matrix[0][i] =  Math.pow( (1/F) * Math.pow( i * Const.xStep, 2)
                            + Const.A * Math.pow(F, -0.2)
                            - (Const.b / (24 * Const.a)) * F, 2);
        }

        // x = 0; x =1;
        for (int t = 1; t < Const.timeIterations; t++){
            double F = Const.B - 10 * Const.a * t * Const.tStep;
            matrix[t][0] = Math.pow(Const.A * Math.pow(F, -0.2)- (Const.b / (24 * Const.a)) * F, 2);
            matrix[t][Const.xIterations - 1] = Math.pow((1/F) + Const.A * Math.pow(F, -0.2)
                            - (Const.b / (24 * Const.a)) * F, 2);
        }


        long beginTime = System.currentTimeMillis();
        for (int t = 1; t < Const.timeIterations ; t++){
            for (int i = 1; i < Const.xIterations - 1; i++){
               matrix[t][i] = approximatedSolution(matrix[t-1][i-1], matrix[t-1][i], matrix[t-1][i+1],
                       Const.tStep, Const.xStep, Const.a, Const.b);
            }
        }

        long endTime = System.currentTimeMillis();
        System.out.println( "Sequential solving: [ " + (endTime - beginTime) + " ms ]");
        graphic.printToPlot(matrix, Const.fileSequential);
    }
}