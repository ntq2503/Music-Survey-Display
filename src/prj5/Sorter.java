// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who
// do.
// -- Sean Kenneth Rogers, Jackson Todd, Quan Nguyen
package prj5;

import java.util.Iterator;

/**
 * Test class for the Sorter class.
 * 
 * @author Jackson Todd (m0ri3)
 * @version 2018.11.29
 */
public class Sorter {
    /**
     * constructor
     */
    public Sorter() {
        // intentionally left blank
    }


    /**
     * sort song list by title
     * 
     * @param unorderedSongs
     *            unsorted list
     * @return the sorted list
     */
    public static LinkedList<Song> sortByTitle(
        LinkedList<Song> unorderedSongs) {
        return sortBy(1, unorderedSongs);
    }


    /**
     * sort song list by artist name
     * 
     * @param unorderedSongs
     *            unsorted list
     * @return the sorted list
     */
    public static LinkedList<Song> sortByArtist(
        LinkedList<Song> unorderedSongs) {
        return sortBy(2, unorderedSongs);
    }


    /**
     * sort song list by genre
     * 
     * @param unorderedSongs
     *            unsorted list
     * @return the sorted list
     */
    public static LinkedList<Song> sortByGenre(
        LinkedList<Song> unorderedSongs) {
        return sortBy(3, unorderedSongs);
    }


    /**
     * sort song list by year
     * 
     * @param unorderedSongs
     *            unsorted list
     * @return the sorted list
     */
    public static LinkedList<Song> sortByYear(LinkedList<Song> unorderedSongs) {
        return sortBy(4, unorderedSongs);
    }


    /**
     * helper method to sort
     * 
     * @param whatToSortBy
     *            indicate what order to sort
     * @param songs
     *            song list
     * @return the sorted list
     */
    private static LinkedList<Song> sortBy(
        int whatToSortBy,
        LinkedList<Song> songs) {
        LinkedList<Song> newList = new LinkedList<Song>();
        while (!songs.isEmpty()) {
            Song smallest = getSmallestSong(whatToSortBy, songs);
            songs.remove(smallest);
            newList.addLast(smallest);
        }
        return newList;
    }


    /**
     * find smallest song
     * 
     * @param whatToSortBy
     *            indicate what order to sort
     * @param songs
     *            song list
     * @return the smallest song
     */
    private static Song getSmallestSong(
        int whatToSortBy,
        LinkedList<Song> songs) {
        Iterator<Song> it = songs.iterator();

        Song smallest = it.next();
        Song current;
        while (it.hasNext()) {
            current = it.next();
            if (songIsSmallerByWhatToSortBy(whatToSortBy, current, smallest)) {
                smallest = current;
            }
        }
        return smallest;
    }


    /**
     * check if current song is smaller than smallest
     * 
     * @param whatToSortBy
     *            indicate what order to sort
     * @param current
     *            current song
     * @param smallest
     *            smallest song
     * @return
     */
    private static boolean songIsSmallerByWhatToSortBy(
        int whatToSortBy,
        Song current,
        Song smallest) {

        switch (whatToSortBy) {
            case 1:
                return current.getTitle().compareTo(smallest.getTitle()) < 0;
            case 3:
                return current.getGenre().compareTo(smallest.getGenre()) < 0;
            case 2:
                return current.getArtist().compareTo(smallest.getArtist()) < 0;
            case 4:
                return current.getYear() < smallest.getYear();
            default:
                return false;
        }
    }
}
