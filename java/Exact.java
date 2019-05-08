public class Exact {

    static double[][] matrix = new double[Const.timeIterations][Const.xIterations];
    static double functionF(int t, double tStep, double B, double a){
        return (B - 10 * a * t * tStep);
    }
    static double functionSolution(int i, double xStep, double F, double a, double b, double A){
        return Math.pow( Math.pow( i * xStep, 2)/F + A * Math.pow(F, -0.2) - b * F / (24 * a), 2);
    }

    static void calculate(){
        Graphic graphic = new Graphic();
        for (int t = 0; t <Const.timeIterations; t++){
            for (int i = 0; i < Const.xIterations; i++){
                double F = functionF(t, Const.tStep, Const.B, Const.a);
                matrix[t][i] = functionSolution(i, Const.xStep, F, Const.a, Const.b, Const.A);
            }
        }
//        graphic.printToPlot(matrix, Const.fileAnother);
    }
}
