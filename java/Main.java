public class Main {
    public static void main(String[] args) {

        Exact.calculate();
        new SequentialSolving().calculate();
        new ParallelSolving().calculate();
        Error error = new Error();

        System.out.println("Computation error sequential: [ " + error.error(SequentialSolving.matrix)*100 + " % ] ");
        System.out.println("Computation error parallel : [ " + error.error(ParallelSolving.matrix)*100 + " % ]");
    }
}
