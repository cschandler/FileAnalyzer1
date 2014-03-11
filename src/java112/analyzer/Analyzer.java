package java112.analyzer;

/**
 * Provides class methods to be implemented by all analyzers.
 *
 * @author Charles Chandler
 * class name
 *
 */
public interface Analyzer {

    /**
     * This method determines how each analyzer will process tokens.
     *
     * @param token A single character from a text file provided by the user.
     * Provided by loopThroughInputFile method in the AnalyzeFile class.
     */
    void processToken(String token);

    /**
     * This method determines the format for the output to the text file.
     *
     * @param inputFilePath The file path to the source text document.  Input by
     * the user.
     * @param outputFilePath The file path the output text document will be
     * written to.  Provided by the writeAllOutputFiles method in the
     * AnalyzeFile class.
     */
    void writeOutputFile(String inputFilePath, String outputFilePath);
}
