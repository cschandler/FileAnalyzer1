package java112.analyzer;

import java.util.*;
import java.io.*;

/**
 * Outputs each unique token from the text file input by the user.
 * NOTE: This class is coded in Java 4, as per project specifications.
 *
 * @author Charles Chandler
 * class UniqueTokenAnalyzer
 *
 */
public class UniqueTokenAnalyzer implements Analyzer {

    private Set uniqueTokenList;

    /**
     * Constructor for UniqueTokenAnalyzer
     */
    public UniqueTokenAnalyzer() {
        uniqueTokenList = new TreeSet();
    }

    /**
     * Returns uniqueTokenList instance variable.
     *
     * @return A set of all the unique tokens in the input text file.
     */
    public Set getUniqueTokensList() {
        return uniqueTokenList;
    }

    /**
     * Recieves tokens from the AnalyzeFile class and puts them into a TreeSet.
     *
     * @param token A single token from the input file.
     */
    public void processToken(String token) {
        uniqueTokenList.add(token);
    }

    /**
     * Uses PrintWriter to output a file of all of the unique tokens in the
     * input text file.
     *
     * @param inputFilePath The input file path originally provided by the user.
     * @param outputFilePath The destination path for the output text file.
     */
    public void writeOutputFile(String inputFilePath, String outputFilePath) {

        PrintWriter out = null;

        try {
            out = new PrintWriter(new BufferedWriter(
                    new FileWriter(outputFilePath)));

            loopThroughTokens(out);

            System.out.println("Unique Token List output to " +
                    outputFilePath + ".");

        } catch (IOException ioexception) {
            ioexception.printStackTrace();
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }

    /**
     * Writes each token in the TreeSet uniqueTokenList to the output file.
     *
     * @param out The PrintWriter created in the writeOutputFile method.
     */
    private void loopThroughTokens(PrintWriter out) {

        for (Iterator iterator = uniqueTokenList.iterator();
                iterator.hasNext();) {
            out.println(iterator.next());
        }
    }
}
