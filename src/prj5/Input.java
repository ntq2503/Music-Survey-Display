// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who
// do.
// -- Sean Kenneth Rogers 9060-29058

package prj5;

import java.io.FileNotFoundException;
import bsh.ParseException;

/**
 * main method
 * 
 * @author Sean Kenneth Rogers, 
 * @version 11.8.2018
 *
 */

public class Input {

    // ----------------------------------------------------------
    /**
     * Creates new Input object.
     * Intentionally left blank
     */
    public Input() {
        // intentionally left blank
    }


    // ----------------------------------------------------------
    /**
     * Main argument to run program
     * @throws FileNotFoundException 
     * @throws ParseException 
     */
    public static void main(String args[]) throws ParseException, FileNotFoundException {
        @SuppressWarnings("unused")
        FileReader reader = new FileReader("Input/SongList.csv", "Input/MusicSurveyData.csv");
    }


    // ----------------------------------------------------------
    /**
     * Method to read input files to run in main program.
     * Needs to read a csv file and output information.
     * 
     * @param survey
     *            survey file input
     */
    public static void FileIO(String survey) {
        
    }
}
