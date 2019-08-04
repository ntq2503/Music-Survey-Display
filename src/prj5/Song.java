package prj5;
// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who
// do.
// -- Jackson Todd, Quan Nguyen (ntq2503)

/**
 * Song class for project 5
 * 
 * @author Jackson Todd (m0ri3), Quan Nguyen (ntq2503)
 * @version 2018.11.08
 */
public class Song {
    // Fields--------------------------------------------------------------
    private String title;
    private String artist;
    private String genre;
    private Integer year;
    private int[] liked;
    private int[] heard;
    private int[] totalResp1;
    private int[] totalResp2;


    // Methods------------------------------------------------------------
    /**
     * Song default constructor.
     * 
     * @param title
     *            Song title
     * @param artist
     *            Song artist
     * @param genre
     *            Song genre
     * @param year
     *            Song year
     */
    public Song(String title, String artist, String genre, int year) {
        this.title = title;
        this.artist = artist;
        this.genre = genre;
        this.year = year;
        liked = new int[13];
        heard = new int[13];
        totalResp1 = new int[13];
        totalResp2 = new int[13];
    }


    // ----------------------------------------------------------
    /**
     * Returns song title
     * 
     * @return
     *         return song title
     */
    public String getTitle() {
        return title;
    }


    // ----------------------------------------------------------
    /**
     * Sets song title
     * 
     * @param title
     *            new title of song
     */
    public void setTitle(String title) {
        this.title = title;
    }


    // ----------------------------------------------------------
    /**
     * Returns artist
     * 
     * @return
     *         return artist name
     */
    public String getArtist() {
        return artist;
    }


    // ----------------------------------------------------------
    /**
     * Sets artist
     * 
     * @param artist
     *            new artist of song
     */
    public void setArtist(String artist) {
        this.artist = artist;
    }


    // ----------------------------------------------------------
    /**
     * Return song genre
     * 
     * @return
     *         return genre
     */
    public String getGenre() {
        return genre;
    }


    // ----------------------------------------------------------
    /**
     * Sets aGenre
     * 
     * @param genre
     *            new genre of song
     */
    public void setGenre(String genre) {
        this.genre = genre;
    }


    // ----------------------------------------------------------
    /**
     * Return year
     * 
     * @return
     *         return year of song
     */
    public int getYear() {
        return year;
    }


    // ----------------------------------------------------------
    /**
     * Sets year
     * 
     * @param year
     *            new year of song
     */
    public void setYear(int year) {
        this.year = year;
    }


    /**
     * check if 2 songs are equal
     * 
     * @param obj
     *            the other song
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (obj == this) {
            return true;
        }

        if (obj.getClass() != this.getClass()) {
            return false;
        }

        Song comp = (Song)obj;
        return this.getTitle().equals(comp.getTitle());
    }


    /**
     * get the array liked
     * 
     * @return the array
     */
    public int[] getLiked() {
        return liked;
    }


    /**
     * get the array heard
     * 
     * @return the array
     */
    public int[] getHeard() {
        return heard;
    }


    /**
     * get the array of responses for heard
     * 
     * @return the array
     */
    public int[] getTotal1() {
        return totalResp1;
    }


    /**
     * get the array of responses for liked
     * 
     * @return the array
     */
    public int[] getTotal2() {
        return totalResp2;
    }


    /**
     * increments liked index
     * 
     * @param i
     *            the index
     */
    public void incLiked(int i) {
        liked[i]++;
    }


    /**
     * increments heard index
     * 
     * @param i
     *            the index
     */
    public void incHeard(int i) {
        heard[i]++;
    }


    /**
     * increments totalResp1 index
     * 
     * @param i
     *            the index
     */
    public void incTotal1(int i) {
        totalResp1[i]++;
    }


    /**
     * increments totalResp2 index
     * 
     * @param i
     *            the index
     */
    public void incTotal2(int i) {
        totalResp2[i]++;
    }


    /**
     * calculate heard percentage of songs
     * 
     * @return an array of heard percentage
     */
    public float[] getAllPercentHeard() {
        float[] per = new float[13];
        for (int i = 0; i < heard.length; i++) {
            if (totalResp1[i] == 0) {
                per[i] = 0;
            }

            else {
                per[i] = (float)heard[i] / (float)totalResp1[i];
            }
        }
        return per;
    }


    /**
     * calculate liked percentage of songs
     * 
     * @return an array of liked percentage
     */
    public float[] getAllPercentLiked() {
        float[] per = new float[13];
        for (int i = 0; i < liked.length; i++) {
            if (totalResp2[i] == 0) {
                per[i] = 0;
            }

            else {
                per[i] = (float)liked[i] / (float)totalResp2[i];
            }
        }
        return per;
    }


    /**
     * get an array of liked percentage of songs for a certain criteria
     * 
     * @param criteria
     *            the criteria
     * @return the array
     */
    public float[] getPercentLiked(String criteria) {
        float[] per = new float[4];
        float[] all = getAllPercentLiked();

        if (criteria.equals("Region")) {
            per[0] = all[8];
            per[1] = all[9];
            per[2] = all[10];
            per[3] = all[11];
        }
        else if (criteria.equals("Major")) {
            per[0] = all[4];
            per[1] = all[5];
            per[2] = all[6];
            per[3] = all[7];
        }
        else if (criteria.equals("Hobby")) {
            per[0] = all[0];
            per[1] = all[1];
            per[2] = all[2];
            per[3] = all[3];
        }

        return per;
    }


    /**
     * get an array of heard percentage of songs for a certain criteria
     * 
     * @param criteria
     *            the criteria
     * @return the array
     */
    public float[] getPercentHeard(String criteria) {
        float[] per = new float[4];
        float[] all = getAllPercentHeard();

        if (criteria.equals("Region")) {
            per[0] = all[8];
            per[1] = all[9];
            per[2] = all[10];
            per[3] = all[11];
        }
        else if (criteria.equals("Major")) {
            per[0] = all[4];
            per[1] = all[5];
            per[2] = all[6];
            per[3] = all[7];
        }
        else if (criteria.equals("Hobby")) {
            per[0] = all[0];
            per[1] = all[1];
            per[2] = all[2];
            per[3] = all[3];
        }

        return per;
    }


    /**
     * convert a song to String
     * 
     * @return the string of a song
     */
    public String toString() {
        StringBuilder output = new StringBuilder("Song Title: ");
        output.append(title + "\n");
        output.append("Song Artist: " + artist + "\n");
        output.append("Song Genre: " + genre + "\n");
        output.append("Song Year: " + year + "\n");
        float[] heards = getAllPercentHeard();
        float[] likes = getAllPercentLiked();
        output.append("Heard" + "\n");
        output.append("reading:" + heards[0] + " art:" + heards[1] + " sports:"
            + heards[2] + " music:" + heards[3] + "\n");
        output.append("Likes" + "\n");
        output.append("reading:" + likes[0] + " art:" + likes[1] + " sports:"
            + likes[2] + " music:" + likes[3] + "\n");

        return output.toString();
    }
}
