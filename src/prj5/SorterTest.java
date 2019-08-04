// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who
// do.
// -- Sean Kenneth Rogers, Jackson Todd, Quan Nguyen
package prj5;

import student.TestCase;

/**
 * Test class for the Sorter class.
 * 
 * @author Jackson Todd (m0ri3)
 * @version 2018.11.29
 */
public class SorterTest extends TestCase {
    private LinkedList<Song> songs;
    private LinkedList<Song> sortedSongs;


    /**
     * Sets up the test vVariables.
     */
    public void setUp() {
        songs = new LinkedList<Song>();
        Song song1 = new Song("c", "c", "c", 3);
        Song song2 = new Song("d", "d", "d", 4);
        Song song3 = new Song("e", "e", "e", 5);
        Song song4 = new Song("a", "a", "a", 1);
        Song song5 = new Song("b", "b", "b", 2);
        songs.add(song1);
        songs.add(song2);
        songs.add(song3);
        songs.add(song4);
        songs.add(song5);

        sortedSongs = new LinkedList<Song>();
        sortedSongs.add(song3);
        sortedSongs.add(song2);
        sortedSongs.add(song1);
        sortedSongs.add(song5);
        sortedSongs.add(song4);
    }


    /**
     * Test the sortByTitle method.
     */
    public void testSortByTitle() {
        assertEquals(sortedSongs, Sorter.sortByTitle(songs));
    }


    /**
     * Test the sortByArtist method.
     */
    public void testSortByArtist() {
        assertEquals(sortedSongs, Sorter.sortByArtist(songs));
    }


    /**
     * Test the sortByGenre method.
     */
    public void testSortByGenre() {
        assertEquals(sortedSongs, Sorter.sortByGenre(songs));
    }


    /**
     * Test the sortByYear method.
     */
    public void testSortByYear() {
        assertEquals(sortedSongs, Sorter.sortByYear(songs));
    }
}
