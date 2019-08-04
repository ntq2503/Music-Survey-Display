package prj5;

import CS2114.TextShape;
import java.awt.Color;
import CS2114.Window;
import CS2114.Button;
import CS2114.Shape;
import CS2114.WindowSide;

// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who
// do.
// -- Sean Kenneth Rogers, Jackson Todd (m0ri3), Quan Nguyen (ntq2503)
/**
 * GUI front end for assigned buttons window and list.
 * Creates window space and all functions for buttons.
 * 
 * 
 * @author Jackson Todd (m0ri3), Quan Nguyen (ntq2503)
 * @version 2018.11.11
 * @param <E>
 *            linked list is generic type
 */
public class GUIFrontEnd {
    // Fields----------------------------------------------------
    private Window window;
    private LinkedList<Song> songList;
    private static final double PADDING_MULTIPLIER = 1.5;

    // Buttons--------------------------------------------------
    private Button prevButton;
    private Button artistButton;
    private Button songButton;
    private Button yearButton;
    private Button genreButton;
    private Button nextButton;
    private Button hobbyButton;
    private Button majorButton;
    private Button regionButton;
    private Button quitButton;
    private Legend legend;
    private String sortBy = "Title";

    private int songsBeginingIndex = 0;
    private int songsEndingIndex = 9;

    private static int padding;
    private static int legendWidth;

    private SongGlyph[] currentGlyphs;
    private Song[] currentSongs;

    // ----------------------------------------------------------


    private class Legend {
        private Shape[] shapes;
        private OutLinedBox outline;
        private final int centerX;


        public Legend() {
            this("");
        }


        // ----------------------------------------------------------
        /**
         * Constructor for song Glyphs allows for placement
         * in window.
         * 
         * @param pos
         *            The position of glyph on the window,
         *            left to right, top to bottom, 1 through 9
         */
        public Legend(String str) {
            shapes = new Shape[9];
            int w = window.getGraphPanelWidth();
            int h = window.getGraphPanelHeight();

            int cellHeight = (int)(0.7 * (h - (2 * padding)));

            int x = (int)(w - (padding + legendWidth));
            int y = (h - cellHeight - padding);

            centerX = x + (legendWidth / 2);

            outline = new OutLinedBox(x, y, legendWidth, cellHeight);

            y += padding;

            shapes[0] = new TextShape(centerX, y, "Hobby Legend");
            shapes[0].setBackgroundColor(new Color(255, 255, 255));

            y += shapes[0].getHeight();
            // holder 1
            shapes[1] = new TextShape(centerX, y, "Read", Color.MAGENTA);

            y += shapes[1].getHeight();
            // holder 2
            shapes[2] = new TextShape(centerX, y, "Art", Color.BLUE);

            y += shapes[2].getHeight();
            // holder 3
            shapes[3] = new TextShape(centerX, y, "Sports", Color.ORANGE);

            y += shapes[3].getHeight();
            // holder 4
            shapes[4] = new TextShape(centerX, y, "Music", Color.GREEN);

            y += shapes[4].getHeight() + padding;
            // song title
            shapes[5] = new TextShape(centerX, y, "Song Title");
            shapes[5].setBackgroundColor(Color.WHITE);

            y += shapes[5].getHeight();
            // vertical black bar
            shapes[6] = new Shape(centerX, y, shapes[5].getHeight() / 3,
                shapes[5].getHeight() * 2, Color.BLACK);

            y += (shapes[6].getHeight() / 2) - (shapes[5].getHeight() / 2);
            // heard
            shapes[7] = new TextShape(0, y, "Heard");
            shapes[7].setBackgroundColor(Color.WHITE);
            shapes[7].setX(centerX - shapes[7].getWidth() - shapes[6]
                .getWidth());

            // likes
            shapes[8] = new TextShape(0, y, "Likes");
            shapes[8].setBackgroundColor(Color.WHITE);
            shapes[8].setX(centerX + shapes[6].getWidth());

            y += padding;

            for (int i = 0; i < shapes.length; i++) {
                Shape shape = shapes[i];
                if (shape != null) {
                    window.addShape(shape);
                    window.moveToFront(shape);
                }
            }

            setUpText(str);
        }


        private void setUpText(String criteria) {
            if (criteria.equals("Hobby")) {
                ((TextShape)shapes[0]).setText("Hobby Legend");
                ((TextShape)shapes[1]).setText("Read");
                ((TextShape)shapes[2]).setText("Art");
                ((TextShape)shapes[3]).setText("Sports");
                ((TextShape)shapes[4]).setText("Music");
            }
            else if (criteria.equals("Major")) {
                ((TextShape)shapes[0]).setText("Major Legend");
                ((TextShape)shapes[1]).setText("Comp Sci");
                ((TextShape)shapes[2]).setText("Math/CMDA");
                ((TextShape)shapes[3]).setText("Other Eng");
                ((TextShape)shapes[4]).setText("Other");
            }
            else if (criteria.equals("Region")) {
                ((TextShape)shapes[0]).setText("Region Legend");
                ((TextShape)shapes[1]).setText("Southeast");
                ((TextShape)shapes[2]).setText("Northeast");
                ((TextShape)shapes[3]).setText("US(other)");
                ((TextShape)shapes[4]).setText("Foreign");
            }

            for (int i = 0; i < 6; i++) {
                Shape shape = shapes[i];
                if (shape != null) {
                    int w = shape.getWidth();
                    shape.setBackgroundColor(Color.WHITE);
                    shape.setX(centerX - w / 2);
                }
            }
        }


        public void remove() {
            outline.remove();
            for (int i = 0; i < shapes.length; i++) {
                Shape shape = shapes[i];
                if (shape != null) {
                    shape.remove();
                }
            }
        }
    }


    private class OutLinedBox {
        private Shape shape;
        private Shape shape2;


        public OutLinedBox(int x, int y, int w, int h) {
            this(x, y, w, h, 4, Color.BLACK);
        }


        public OutLinedBox(
            int x,
            int y,
            int w,
            int h,
            int thickness,
            Color color) {
            shape = new Shape(x, y, w, h, color);
            shape2 = new Shape(x + thickness / 2, y + thickness / 2, w
                - thickness, h - thickness, new Color(255, 255, 255));

            window.addShape(shape2);
            window.addShape(shape);
            window.moveToBack(shape2);
            window.moveToBack(shape);
        }


        public void remove() {
            shape.remove();
            shape2.remove();
        }
    }


    private class SongGlyph {

        TextShape title;
        TextShape artist;
        Shape vertical;
        Shape redLikes;
        Shape redHeard;
        Shape cyanLikes;
        Shape cyanHeard;
        Shape greenLikes;
        Shape greenHeard;
        Shape orangeLikes;
        Shape orangeHeard;
        OutLinedBox outline;
        String criteria;


        public SongGlyph(Song song, int pos, String criteria) {
            this.criteria = criteria;

            float[] likedArray = song.getPercentLiked(criteria);

            float percentLikedCategory1 = likedArray[0];
            float percentLikedCategory2 = likedArray[1];
            float percentLikedCategory3 = likedArray[2];
            float percentLikedCategory4 = likedArray[3];

            float[] heardArray = song.getPercentHeard(criteria);

            float percentHeardCategory1 = heardArray[0];
            float percentHeardCategory2 = heardArray[1];
            float percentHeardCategory3 = heardArray[2];
            float percentHeardCategory4 = heardArray[3];

            pos++;
            int h = window.getGraphPanelHeight();
            int w = window.getGraphPanelWidth();

            int cellHeight = (h - (padding * 4)) / 3;
            int cellWidth = (w - (padding * 5) - legendWidth) / 3;

            int xCellPos = (pos - 1) % 3;
            int yCellPos = (int)Math.floor((pos - 1) / 3.0);

            int x = padding + ((padding + cellWidth) * (xCellPos));
            int y = padding + ((padding + cellHeight) * (yCellPos));

            outline = new OutLinedBox(x, y, cellWidth, cellHeight);

            y += padding;
            // song title
            title = new TextShape(x, y, song.getTitle());
            title.setBackgroundColor(Color.WHITE);
            title.setX(x + (cellWidth - title.getWidth()) / 2);
            window.addShape(title);
            window.moveToFront(title);

            String text = "";
            if (sortBy.equals("Title") || sortBy.equals("Artist")) {
                text = "by " + song.getArtist();
            }
            else if (sortBy.equals("Genre")) {
                text = "genre: " + song.getGenre();
            }
            else if (sortBy.equals("Year")) {
                text = "year: " + song.getYear();
            }

            // artist name
            y += title.getHeight();
            artist = new TextShape(x, y, text);
            artist.setBackgroundColor(Color.WHITE);
            artist.setX(x + (cellWidth - artist.getWidth()) / 2);
            window.addShape(artist);
            window.moveToFront(artist);

            // black line
            y += artist.getHeight();
            vertical = new Shape(x + cellWidth / 2, y, artist.getHeight() / 3,
                artist.getHeight() * 2, Color.BLACK);
            window.addShape(vertical);

            int verticalWidth = artist.getHeight() / 2;
            int fullLength = (int)((cellWidth - (2 * verticalWidth)) / 2);
            int localY = vertical.getY();

            // red representation likes
            int length = (int)(fullLength * percentLikedCategory1);
            redLikes = new Shape((int)(x + cellWidth / 2), localY, length,
                verticalWidth, Color.MAGENTA);
            window.addShape(redLikes);
            window.moveToFront(redLikes);

            // red representation heard
            length = (int)(fullLength * percentHeardCategory1);
            redHeard = new Shape((int)(x - length + cellWidth / 2), localY,
                length, verticalWidth, Color.MAGENTA);
            window.addShape(redHeard);
            window.moveToFront(redHeard);

            localY += verticalWidth;
            // cyan representation likes
            length = (int)(fullLength * percentLikedCategory2);
            cyanLikes = new Shape((int)(x + cellWidth / 2), localY, length,
                verticalWidth, Color.BLUE);
            window.addShape(cyanLikes);
            window.moveToFront(cyanLikes);

            // cyan representation heard
            length = (int)(fullLength * percentHeardCategory2);
            cyanHeard = new Shape((int)(x - length + cellWidth / 2), localY,
                length, verticalWidth, Color.BLUE);
            window.addShape(cyanHeard);
            window.moveToFront(cyanHeard);

            localY += verticalWidth;
            // green representation likes
            length = (int)(fullLength * percentLikedCategory3);
            greenLikes = new Shape((int)(x + cellWidth / 2), localY, length,
                verticalWidth, Color.ORANGE);
            window.addShape(greenLikes);
            window.moveToFront(greenLikes);

            // green representation heard
            length = (int)(fullLength * percentHeardCategory3);
            greenHeard = new Shape((int)(x - length + cellWidth / 2), localY,
                length, verticalWidth, Color.ORANGE);
            window.addShape(greenHeard);
            window.moveToFront(greenHeard);

            localY += verticalWidth;
            // orange representation likes
            length = (int)(fullLength * percentLikedCategory4);
            orangeLikes = new Shape((int)(x + cellWidth / 2), localY, length,
                verticalWidth, Color.GREEN);
            window.addShape(orangeLikes);
            window.moveToFront(orangeLikes);

            // orange representation heard
            length = (int)(fullLength * percentHeardCategory4);
            orangeHeard = new Shape((int)(x - length + cellWidth / 2), localY,
                length, verticalWidth, Color.GREEN);
            window.addShape(orangeHeard);
            window.moveToFront(orangeHeard);

            window.moveToFront(vertical);
        }


        public void remove() {
            title.remove();
            artist.remove();
            vertical.remove();
            redLikes.remove();
            redHeard.remove();
            cyanLikes.remove();
            cyanHeard.remove();
            greenLikes.remove();
            greenHeard.remove();
            orangeLikes.remove();
            orangeHeard.remove();
            outline.remove();
        }
    }


    // Methods----------------------------------------------------
    /**
     * Default window constructor.
     * Constructor window object names, and places buttons.
     */
    public GUIFrontEnd(LinkedList<Song> songs) {
        this.songList = Sorter.sortByArtist(songs);
        // Window creation
        window = new Window();
        window.setTitle("m0ri3, seankr, ntq2503");

        currentGlyphs = new SongGlyph[9];
        currentSongs = new Song[9];

        setCurrentSongs(0);

        legendWidth = (int)((window.getGraphPanelWidth() - (5 * padding))
            * 0.20);
        padding = (int)(window.getGraphPanelWidth() * PADDING_MULTIPLIER
            / 100.0);

        addButtons();

        window.setSize((int)(window.getWidth() * 1.25), (int)(window
            .getHeight()));

        updateSongGlyphs("Hobby");

        legend = new Legend();
    }


    private void setCurrentSongs(int num) {
        if (num > 0) {
            songsBeginingIndex += 9;
            songsEndingIndex += 9;
        }
        else if (num < 0) {
            songsBeginingIndex -= 9;
            songsEndingIndex -= 9;
        }

        if (songsBeginingIndex < 0 || songsBeginingIndex > songList.size()
            || songsEndingIndex > songList.size()) {

            songsEndingIndex = Math.min(9, songList.size());
            songsBeginingIndex = Math.max(0, songsEndingIndex - 9);
        }
        
        int curSongsIndex = 0;
        for (int i = songsBeginingIndex; i < songsEndingIndex; i++) {
            if (songList.get(i) != null) {
                currentSongs[curSongsIndex] = songList.get(i);
                curSongsIndex++;
            }
        }
    }


    private void addButtons() {
        prevButton = new Button("\u2190 Prev");
        prevButton.onClick(this, "clickedPrev");
        window.addButton(prevButton, WindowSide.NORTH);

        artistButton = new Button("Sort by Artist Name");
        artistButton.onClick(this, "clickedArtist");
        window.addButton(artistButton, WindowSide.NORTH);

        songButton = new Button("Sort by Song Title");
        songButton.onClick(this, "clickedSong");
        window.addButton(songButton, WindowSide.NORTH);

        yearButton = new Button("Sort by Release Year");
        yearButton.onClick(this, "clickedYear");
        window.addButton(yearButton, WindowSide.NORTH);

        genreButton = new Button("Sort by Genre");
        genreButton.onClick(this, "clickedGenre");
        window.addButton(genreButton, WindowSide.NORTH);

        nextButton = new Button("Next \u2192");
        nextButton.onClick(this, "clickedNext");
        window.addButton(nextButton, WindowSide.NORTH);

        // Added for Readability -------------------------------------

        hobbyButton = new Button("Represent Hobby");
        hobbyButton.onClick(this, "clickedHobby");
        window.addButton(hobbyButton, WindowSide.SOUTH);

        majorButton = new Button("Represent Major");
        majorButton.onClick(this, "clickedMajor");
        window.addButton(majorButton, WindowSide.SOUTH);

        regionButton = new Button("Represent Region");
        regionButton.onClick(this, "clickedRegion");
        window.addButton(regionButton, WindowSide.SOUTH);

        quitButton = new Button("Quit");
        quitButton.onClick(this, "clickedQuit");
        window.addButton(quitButton, WindowSide.SOUTH);
    }


    // ----------------------------------------------------------
    /**
     * Constructor for song Glyphs allows for placement
     * in window.
     * 
     * @param pos
     *            The position of glyph on the window,
     *            left to right, top to bottom, 1 through 9
     */
    private void updateSongGlyphs(String criteria) {
        removeSongGlyphs();
        for (int i = 0; i < 9; i++) {
            currentGlyphs[i] = new SongGlyph(currentSongs[i], i, criteria);
        }
    }


    private void updateSongGlyphs() {
        String criteria = currentGlyphs[0].criteria;
        removeSongGlyphs();
        for (int i = 0; i < 9; i++) {
            currentGlyphs[i] = new SongGlyph(currentSongs[i], i, criteria);
        }
    }


    private void removeSongGlyphs() {
        for (int i = 0; i < 9; i++) {
            SongGlyph glyph = currentGlyphs[i];
            if (glyph != null) {
                glyph.remove();
            }
        }
    }

    // =============================Top Buttons=============================


    // ----------------------------------------------------------
    /**
     * Action for previous button.
     * Brings us to previous window of sort on front GUI
     * 
     * @param button
     *            button that is pressed (prevButton)
     */
    public void clickedPrev(Button button) {
        setCurrentSongs(-1);
        updateSongGlyphs();
    }


    // ----------------------------------------------------------
    /**
     * Action for artist button
     * Sorts by artist alphabetically
     * 
     * @param button
     *            button that is pressed (artistButton)
     */
    public void clickedArtist(Button button) {
        sortBy = "Artist";
        songsBeginingIndex = 0;
        songsEndingIndex = 9;
        songList = Sorter.sortByArtist(songList);
        setCurrentSongs(0);
        updateSongGlyphs();
    }


    // ----------------------------------------------------------
    /**
     * Action for song button
     * Sorts by songs alphabetically
     * 
     * @param button
     *            button that is pressed (songButton)
     */
    public void clickedSong(Button button) {
        sortBy = "Title";
        songsBeginingIndex = 0;
        songsEndingIndex = 9;
        songList = Sorter.sortByTitle(songList);
        setCurrentSongs(0);
        updateSongGlyphs();
    }


    // ----------------------------------------------------------
    /**
     * Action for year button
     * Sorts by year chornologically.
     * 
     * @param button
     *            button that is pressed (yearButton)
     */
    public void clickedYear(Button button) {
        sortBy = "Year";
        songsBeginingIndex = 0;
        songsEndingIndex = 9;
        songList = Sorter.sortByYear(songList);
        setCurrentSongs(0);
        updateSongGlyphs();
    }


    // ----------------------------------------------------------
    /**
     * Action for genre button
     * Sorts by genre alphabetically
     * 
     * @param button
     *            button that is pressed (genreButton)
     */
    public void clickedGenre(Button button) {
        sortBy = "Genre";
        songsBeginingIndex = 0;
        songsEndingIndex = 9;
        songList = Sorter.sortByGenre(songList);
        setCurrentSongs(0);
        updateSongGlyphs();
    }


    // ----------------------------------------------------------
    /**
     * Action for next button
     * Brings us to next window of sort on front GUI
     * 
     * @param button
     *            button that is pressed (nextButton)
     */
    public void clickedNext(Button button) {
        setCurrentSongs(1);
        updateSongGlyphs();
    }


    // =============================Bottom Buttons=============================

    private void updateLegend(String str) {
        legend.remove();
        legend = new Legend(str);
    }


    // ----------------------------------------------------------
    /**
     * Action for hobby button
     * Sorts by hobby alphabetically
     * 
     * @param button
     *            button that is pressed (hobbyButton)
     */
    public void clickedHobby(Button button) {
        updateSongGlyphs("Hobby");
        updateLegend("Hobby");
    }


    // ----------------------------------------------------------
    /**
     * Action for major button
     * Sorts by major alphabetically
     * 
     * @param button
     *            button that is pressed (majorButton)
     */
    public void clickedMajor(Button button) {
        updateSongGlyphs("Major");

        updateLegend("Major");
    }


    // ----------------------------------------------------------
    /**
     * Action for region button
     * Sorts by region alphabetically
     * 
     * @param button
     *            button that is pressed (regionButton)
     */
    public void clickedRegion(Button button) {
        updateSongGlyphs("Region");

        updateLegend("Region");
    }


    // ----------------------------------------------------------
    /**
     * Exits program through system.exit.
     * 
     * @param button
     *            the Quit button
     * 
     *            Gracefully exits the program
     */
    public void clickedQuit(Button button) {
        System.exit(0);
    }

}
