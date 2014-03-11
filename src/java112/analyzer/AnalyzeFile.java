package java112.analyzer;

import java.io.*;
import java.util.*;

/**
 * This is the primary controlling class for the project. The class will recieve
 * the input file path from the main method in the AnalyzerDriver class. It will
 * create instances of all analyzers. It will parse through the text file at the
 * file path provided, seperating it into discrete tokens. Those tokens are then
 * passed to the analyzers which will process them in various ways.  Finally,
 * the output file path is provided for each analyzer, determining where the
 * file will be written to and what name it will have.
 *
 * @author Charles Chandler
 * class AnalyzeFile
 *
 */
public class AnalyzeFile {

    private static final int VALID_ARGUMENTS_COUNT = 1;

    private String inputFilePath;
    private SummaryReport summaryReport;
    private UniqueTokenAnalyzer uniqueTokenAnalyzer;

    /**
     * Recieves the file path input by the user, and calls methods
     * to process the text file.
     *
     * @param arguments The input file path provided by the main method in the
     * AnalyzerDriver class.
     */
    public void runAnalysis(String[] arguments) {

        if (arguments.length != VALID_ARGUMENTS_COUNT) {
            System.out.println("Please enter file path of analysis file.");
            return;
        }

        inputFilePath = arguments[0];

        createAnalyzer();
        openInputFile();
        writeAllOutputFiles();
    }

    /**
     * Instantiates each analyzer.
     */
    private void createAnalyzer() {

        summaryReport = new SummaryReport();
        uniqueTokenAnalyzer = new UniqueTokenAnalyzer();
    }

    /**
     * Opens the text file at the inputFilePath location, and calls
     * loopThroughInputFile.
     */
    private void openInputFile() {

        BufferedReader input = null;

        try {
            input = new BufferedReader(new FileReader(inputFilePath));

            parseTokensFromInputFile(input);

        } catch (java.io.FileNotFoundException fnfe) {
            System.out.println("Failed to read input file");
            fnfe.printStackTrace();
        } catch (Exception exception) {
            System.out.println("General Error");
            exception.printStackTrace();
        } finally {

            try {
                if (input != null) {
                    input.close();
                }
            } catch (java.io.IOException ioe) {
                System.out.println("Failed to close input file");
                ioe.printStackTrace();
            }
        }
    }

    /**
     * Parses through the text file opened by openInputFile, seperating it into
     * tokens and sending each token to the processToken method in each analyzer.
     *
     * @param input The BufferedReader created in the openInputFile class.
     * @throws IOException
     */
    private void parseTokensFromInputFile(BufferedReader input)
            throws IOException {

        String inputLine = "";
        String[] tokenArray = null;
        String token = "";

        while (input.ready()) {

            inputLine = input.readLine();
            tokenArray = inputLine.split("\\W");

            sendTokensToAnalyzers(tokenArray);
        }
    }

    private void sendTokensToAnalyzers (String[] tokenArray) {

        for (int i = 0; i < tokenArray.length; i++) {

            if (!tokenArray[i].isEmpty()) {
                summaryReport.processToken(tokenArray[i]);
                uniqueTokenAnalyzer.processToken(tokenArray[i]);
            }
        }
    }

    /**
     * Calls each analyzer's writeOutputFile method, and provides it with the
     * input file path and output file path.
     */
    private void writeAllOutputFiles() {

        summaryReport.writeOutputFile(inputFilePath,
                "output/summary_report.txt");
        uniqueTokenAnalyzer.writeOutputFile(inputFilePath,
                "output/unique_tokens.txt");
    }
}
