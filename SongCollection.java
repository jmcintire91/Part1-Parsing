package student;

/**
 ***********************************************************************
 * REVISION HISTORY (newest first)
 * *********************************************************************
 * 9/03/2018 - codyssnow - went over JM's code, identified error with parsing
 * file and finding quotation mark. Pending JM's proofreading
 * 9/03/2018 - jacobmcintire - cont. work on parsing file 
 * 9/02/2018 - jacobmcintire - cont. work on parsing file 
 * 9/01/2018 - jacobmcintire - cont. work on parsing file 
 * 8/31/2018 - jacobmcintire - cont. work on parsing file
 * 8/29/2018 - jacobmcintire - cont. work on parsing file 
 * 8/27/2018 - jacobmcintire - work on parsing file 
 * 8/25/2018 - codyssnow - more work on reading in file, setting up variables, 
 * arrayList object created. 
 * 8/24/2018 - codyssnow - got try/catch block working for reading in file 
 * 8/22/2018 - codyssnow - initial work building SongCollection method 
 * 2018 - Cody Snow and Jacob McIntire completed this class for CSCI 290 
 * 2016 - Anne Applin - formatting and JavaDoc skeletons added 
 * 2015 - Prof. Bob Boothe - Starting code and main for testing
 * ***********************************************************************
 * SongCollection.java Read the specified data file and build an array of songs.
 */

import java.util.*;
import java.io.*;

/**
 * The driver for the song database application.
 *
 * @author boothe
 */
public class SongCollection {

    private Song[] songs;

    /**
     *
     * Note: in any other language, reading input inside a class is simply not
     * done!! No I/O inside classes because you would normally provide
     * precompiled classes and I/O is OS and Machine dependent and therefore not
     * portable. Java runs on a virtual machine that IS portable. So this is
     * permissable because we are programming in Java.
     *
     * @author codyssnow
     * @author jakemcintire
     *
     * @param filename The path and filename to the datafile that we are using
     * must be set in the Project Properties as an argument.
     */
    public SongCollection(String filename) {

        // use a try catch block
        // read in the song file and build the songs array
        // you must use a StringBuilder to read in the lyrics!
        // you must add the line feed at the end of each lyric line.
        // sort the songs array using Array.sort (see the Java API)
        // create/initialize songList object
        ArrayList<Song> songList = new ArrayList<>();
        String artist;
        String title;
        String lyrics;
        StringBuilder lyricBuilder = new StringBuilder();
        Scanner inputFile = null;
        try {
            inputFile = new Scanner(new File(filename));

            // use Strings to read in file
            // read stuff one line at a time
            // will need to use a logical test for the presence of " marks
            // - JM
            // the logical test is missing the " and continuing through the 
            // file until it reaches the end, storing the rest of it in the 
            // lyrics for the first song object. -CS
            while (inputFile.hasNextLine()) {
                artist = inputFile.nextLine();
                artist = artist.substring(8, artist.length() - 1);
                title = inputFile.nextLine();
                title = title.substring(7, title.length() - 1);
                lyrics = inputFile.nextLine().substring(8);

                // CS - JM used .equals instead of .nextLine().equals()
                while (!inputFile.nextLine().equals("\"") 
                        && inputFile.hasNextLine()) {
                    lyricBuilder.append(String.format(" "
                            + inputFile.nextLine())).toString();
                }
                
                lyrics += lyricBuilder;
                // build new Song object with song information
                Song s = new Song(artist, title, lyrics);
                // add song object to arrayList
                songList.add(s);

                // CS 9/3 -
                // changed the println test to see what's going on with 
                // object s
                System.out.println("\n" + s);
                System.out.println(lyrics);
            }

            inputFile.close();
        } catch (InputMismatchException e) {
            System.out.println("Probably using nextInt or nextDouble"
                    + " when the file input is not of that type.");
            System.out.println(e);
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Probably some problem with the input"
                    + " data file or the keyboard input.");
            System.out.println(e);
            e.printStackTrace();
        }

        // JM - populate songs with Song objects from songArray using toArray 
        songs = new Song[songList.size()];
        // CS - employed toArray, which returns an already sorted array with 
        // the objects collected in songList 
        songs = songList.toArray(songs);
    }

    /**
     * this is used as the data source for building other data structures
     *
     * @return the songs array
     */
    public Song[] getAllSongs() {

        return songs;
    }

    /**
     * unit testing method
     *
     * @param args
     */
    public static void main(String[] args) {
        if (args.length == 0) {
            System.err.println("usage: prog songfile");
            return;
        }

        SongCollection sc = new SongCollection(args[0]);

        // todo: show song count and up to the first 10 songs 
        // (name & title only, 1 per line) 
    }
}