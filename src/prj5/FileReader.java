// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who
// do.
// -- Jackson Todd (m0ri3), Quan Nguyen (ntq2503)
package prj5;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import bsh.ParseException;

/**
 * A helper class to read the data from csv files and parse it into
 * data structures for use by other classes in the program.
 * 
 * @author Jackson Todd (m0ri3), Quan Nguyen (ntq2503)
 * @version 2018.11.27
 */
public class FileReader {

    private LinkedList<Song> songs;


    // -----------------------------------------------------------
    /**
     * Initializes the fields and calls the reader methods.
     * 
     * @throws FileNotFoundException
     * @throws ParseException
     */
    public FileReader(String songFile, String surveyFile)
        throws ParseException,
        FileNotFoundException {
        songFileReader(songFile);
        surveyFileReader(surveyFile);
        new GUIFrontEnd(songs);
    }


    // -----------------------------------------------------------
    /**
     * Takes a file of song information and parses it for use
     * elsewhere in the program.
     * Format of the file: Song Title,Artist,Year,Genre
     * 
     * @param filename
     *            The filename of the data to read.
     * @return The LinkedList of parsed data.
     * @throws ParseException
     * @throws FileNotFoundException
     */
    private void songFileReader(String fileName)
        throws ParseException,
        FileNotFoundException {
        songs = new LinkedList<Song>();
        Scanner scanner = null;
        try {
            scanner = new Scanner(new File(fileName));
            scanner.nextLine();
            while (scanner.hasNextLine()) {
                String[] line = scanner.nextLine().split(",");

                if (line.length != 4) {
                    throw new ParseException();
                }

                String title = line[0];
                String artist = line[1];
                String genre = line[3];
                int year = Integer.valueOf(line[2]);

                songs.add(new Song(title, artist, genre, year));
            }
        }
        finally {
            scanner.close();
        }
    }


    // -----------------------------------------------------------
    /**
     * Takes a file of student survey information and parses it for use
     * elsewhere in the program.
     * 
     * @param filename
     *            The filename of the data to read.
     * @return The LinkedList of parsed data.
     */
    private void surveyFileReader(String fileName)
        throws ParseException,
        FileNotFoundException {
        Scanner scanner = null;
        try {
            scanner = new Scanner(new File(fileName));
            scanner.nextLine();

            while (scanner.hasNextLine()) {
                String[] line = scanner.nextLine().toLowerCase().split(",");
                System.out.print(line.toString());
                int major = 12;
                int region = 12;
                int hobby = 12;
                if (line.length < 5) {
                    continue;
                }
                switch (line[4]) {
                    case "reading":
                        hobby = 0;
                        break;
                    case "art":
                        hobby = 1;
                        break;
                    case "sports":
                        hobby = 2;
                        break;
                    case "music":
                        hobby = 3;
                        break;
                }
                switch (line[2]) {
                    case "computer science":
                        major = 4;
                        break;
                    case "other engineering":
                        major = 5;
                        break;
                    case "math or cmda":
                        major = 6;
                        break;
                    case "other":
                        major = 7;
                        break;
                }
                switch (line[3]) {
                    case "northeast":
                        region = 8;
                        break;
                    case "southeast":
                        region = 9;
                        break;
                    case "united states (other than southeast or northwest)":
                        region = 10;
                        break;
                    case "outside of united states":
                        region = 11;
                        break;
                }
                int songIndex = 0;
                for (int i = 5; i < line.length - 1; i += 2) {
                    if (line[i].equals("yes")) {
                        songs.get(songIndex).incHeard(major);
                        songs.get(songIndex).incHeard(hobby);
                        songs.get(songIndex).incHeard(region);
                        songs.get(songIndex).incTotal1(major);
                        songs.get(songIndex).incTotal1(hobby);
                        songs.get(songIndex).incTotal1(region);
                    }
                    if (line[i].equals("no")) {
                        songs.get(songIndex).incTotal1(major);
                        songs.get(songIndex).incTotal1(hobby);
                        songs.get(songIndex).incTotal1(region);
                    }
                    if (line[i + 1].equals("yes")) {
                        songs.get(songIndex).incLiked(major);
                        songs.get(songIndex).incLiked(hobby);
                        songs.get(songIndex).incLiked(region);
                        songs.get(songIndex).incTotal2(major);
                        songs.get(songIndex).incTotal2(hobby);
                        songs.get(songIndex).incTotal2(region);
                    }
                    if (line[i + 1].equals("no")) {
                        songs.get(songIndex).incTotal2(major);
                        songs.get(songIndex).incTotal2(hobby);
                        songs.get(songIndex).incTotal2(region);
                    }
                    songIndex++;
                }
            }

        }
        finally {
            scanner.close();
        }
    }


    // -----------------------------------------------------------
    /**
     * 
     * @return The songs field.
     */
    public LinkedList<Song> getSongs() {
        return songs;
    }

}
