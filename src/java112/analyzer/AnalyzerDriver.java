package java112.analyzer;

/**
 * Instantiate an instance of the project's primary processing
 * class.
 *
 * @author Charles Chandler
 * class AnalyzerDriver
 */
public class AnalyzerDriver {

    /**
     * The class will call the main processing method of the main class passing
     * the command line arguments array to the method.
     *
     * @param arguements The input file path for the text file to be analyed,
     * provided by the user.
     */
    public static void main(String[] arguments) {

        System.out.println("Beginning analyzation.");
        AnalyzeFile analyzer = new AnalyzeFile();
        analyzer.runAnalysis(arguments);
    }
}
