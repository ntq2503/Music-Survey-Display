package prj5;

import student.TestCase;

// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who
// do.
// -- Sean Kenneth Rogers, Quan Nguyen (ntq2503)
/**
 * Song test class for project 5.
 * 
 * @author Sean Kenneth Rogers
 * @version 11.8.2018
 *
 */
public class SongTest extends TestCase {

    private Song song;


    /**
     * Default constructor before each test
     */
    public void setUp() {
        song = new Song("TDog", "T-Money", "house", 1982);
    }


    /**
     * test getters and setters
     */
    public void testGettersSetters() {
        // test original set up
        assertEquals(song.getTitle(), "TDog");
        assertEquals(song.getArtist(), "T-Money");
        assertEquals(song.getGenre(), "house");
        assertEquals(song.getYear(), 1982);

        // set new fields
        song.setTitle("not TDog");
        song.setArtist("not T-Money");
        song.setGenre("not house");
        song.setYear(1999);

        // test new sets
        assertEquals(song.getTitle(), "not TDog");
        assertEquals(song.getArtist(), "not T-Money");
        assertEquals(song.getGenre(), "not house");
        assertEquals(song.getYear(), 1999);
        assertEquals(13, song.getLiked().length);
        assertEquals(13, song.getHeard().length);
        assertEquals(13, song.getTotal1().length);
        assertEquals(13, song.getTotal2().length);
    }


    /**
     * test methods to increment arrays
     */
    public void testIncrement() {
        song.incHeard(0);
        song.incLiked(1);
        song.incTotal1(2);
        song.incTotal2(3);
        assertEquals(1, song.getHeard()[0]);
        assertEquals(1, song.getLiked()[1]);
        assertEquals(1, song.getTotal1()[2]);
        assertEquals(1, song.getTotal2()[3]);
    }


    /**
     * test getting arrays of percentage of heard and liked
     */
    public void testGetPercentHeardLiked() {
        song.incHeard(0);
        song.incLiked(1);
        song.incTotal1(0);
        song.incTotal2(1);
        song.incTotal1(0);
        song.incTotal2(1);
        song.incHeard(4);
        song.incLiked(5);
        song.incTotal1(4);
        song.incTotal2(5);
        song.incTotal1(4);
        song.incTotal2(5);
        song.incHeard(8);
        song.incLiked(9);
        song.incTotal1(8);
        song.incTotal2(9);
        song.incTotal1(8);
        song.incTotal2(9);
        assertEquals(0.5, song.getPercentHeard("Hobby")[0], 0.000001);
        assertEquals(0.5, song.getPercentLiked("Hobby")[1], 0.000001);
        assertEquals(0.5, song.getPercentHeard("Major")[0], 0.000001);
        assertEquals(0.5, song.getPercentLiked("Major")[1], 0.000001);
        assertEquals(0.5, song.getPercentHeard("Region")[0], 0.000001);
        assertEquals(0.5, song.getPercentLiked("Region")[1], 0.000001);
    }


    /**
     * test converting song to String
     */
    public void testToString() {
        StringBuilder string = new StringBuilder();
        string.append("Song Title: TDog" + "\n");
        string.append("Song Artist: T-Money" + "\n");
        string.append("Song Genre: house" + "\n");
        string.append("Song Year: 1982" + "\n");
        string.append("Heard" + "\n");
        string.append("reading:0.0 art:0.0 sports:0.0 music:0.0" + "\n");
        string.append("Likes" + "\n");
        string.append("reading:0.0 art:0.0 sports:0.0 music:0.0" + "\n");
        assertEquals(string.toString(), song.toString());
    }
}
