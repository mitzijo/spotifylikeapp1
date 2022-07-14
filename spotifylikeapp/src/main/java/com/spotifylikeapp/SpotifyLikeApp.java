package com.spotifylikeapp;
import java.io.File; 
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner; 
  
import javax.sound.sampled.AudioInputStream; 
import javax.sound.sampled.AudioSystem; 
import javax.sound.sampled.Clip; 
import javax.sound.sampled.LineUnavailableException; 
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.DataLine.Info;

import static javax.sound.sampled.AudioSystem.getAudioInputStream;
import static javax.sound.sampled.AudioFormat.Encoding.PCM_SIGNED;

/*
    To compile: javac SpotifyLikeApp.java
    To run: java SpotifyLikeApp
 */

// declares a class for the app
public class SpotifyLikeApp {

    // global variables for the app
    String status;
    Long position;
    static Clip audioClip;
static HashMap<String, Song> songs = new HashMap<>();



    // "main" makes this class a java app that can be executed
    public static void main(String[] args) {
        Song s = new Song (); 
            s.setArtist("The Dubbstyle");
            s.setTitle("Zumbido");
            s.setYear("2002");
            s.setGenre("technopop");
            s.setFilePath("/Users/mitzichavez/Documents/GitHub/spotifylikeapp/spotifylikeapp/src/main/java/com/spotifylikeapp/wav/The-Dubbstyle-Zumbido.wav");
            songs.put(s.getTtile(), s); 

            s = new Song();
            s.setArtist("Scott Holmes");
            s.setTitle("Storybook");
            s.setYear("2003");
            s.setGenre("Rock");
            s.setFilePath("/Users/mitzichavez/Documents/GitHub/spotifylikeapp/spotifylikeapp/src/main/java/com/spotifylikeapp/wav/Scott-Holmes-Storybook.wav");
            songs.put(s.getTtile(), s);

            s = new Song();
            s.setArtist("Mid Air Machine");
            s.setTitle("Burn It Down");
            s.setYear("2001");
            s.setGenre("Heavy Metal");
            s.setFilePath("/Users/mitzichavez/Documents/GitHub/spotifylikeapp/spotifylikeapp/src/main/java/com/spotifylikeapp/wav/Mid-Air-Machine-Burn-It-Down.wav");
            songs.put(s.getTtile(), s);

            s = new Song();
            s.setArtist("Kitkat Club");
            s.setTitle("Welcome");
            s.setYear("2005");
            s.setGenre("Pop Funk");
            s.setFilePath("/Users/mitzichavez/Documents/GitHub/spotifylikeapp/spotifylikeapp/src/main/java/com/spotifylikeapp/wav/Kitkat-Club-Welcome.wav");
            songs.put(s.getTtile(), s);

            s = new Song();
            s.setArtist("Kathleen Martin");
            s.setTitle("El Preso Numbero Nueve");
            s.setYear("2003");
            s.setGenre("Spanish Rock");
            s.setFilePath("/Users/mitzichavez/Documents/GitHub/spotifylikeapp/spotifylikeapp/src/main/java/com/spotifylikeapp/wav/Kathleen-Martin-El-Preso-Numero-Nueve.wav");
            songs.put(s.getTtile(), s);

            s = new Song();
            s.setArtist("Dee Yan Key");
            s.setTitle("Vacaciones Salsa");
            s.setYear("2001");
            s.setGenre("Salsa");
            s.setFilePath("/Users/mitzichavez/Documents/GitHub/spotifylikeapp/spotifylikeapp/src/main/java/com/spotifylikeapp/wav/Dee-Yan-Key-Vacaciones-Salsa.wav");
            songs.put(s.getTtile(), s);

            s = new Song();
            s.setArtist("Checkie Brown");
            s.setTitle("Wirklich Wichtig");
            s.setYear("2001");
            s.setGenre("Folk");
            s.setFilePath("/Users/mitzichavez/Documents/GitHub/spotifylikeapp/spotifylikeapp/src/main/java/com/spotifylikeapp/wav/Checkie-Brown-Wirklich-Wichtig.wav");
            songs.put(s.getTtile(), s);

            s = new Song();
            s.setArtist("Checkie Brown");
            s.setTitle("Tanzen");
            s.setYear("2003");
            s.setGenre("Folk");
            s.setFilePath("/Users/mitzichavez/Documents/GitHub/spotifylikeapp/spotifylikeapp/src/main/java/com/spotifylikeapp/wav/Checkie-Brown-Tanzen.wav");
            songs.put(s.getTtile(), s);

            s = new Song();
            s.setArtist("Bisou");
            s.setTitle("Journey of King");
            s.setYear("2002");
            s.setGenre("Pop");
            s.setFilePath("/Users/mitzichavez/Documents/GitHub/spotifylikeapp/spotifylikeapp/src/main/java/com/spotifylikeapp/wav/Bisou-Journey-of-King.wav");
            songs.put(s.getTtile(), s);

            s = new Song();
            s.setArtist("Ava Luna");
            s.setTitle("Cement Lunch");
            s.setYear("2008");
            s.setGenre("Emo Rock");
            s.setFilePath("/Users/mitzichavez/Documents/GitHub/spotifylikeapp/spotifylikeapp/src/main/java/com/spotifylikeapp/wav/Ava-Luna-Cement-Lunch.wav");
            songs.put(s.getTtile(), s);


        // create a scanner for user input
        Scanner input = new Scanner(System.in);

        String userInput = "";
        while (!userInput.equals("q")) {

            menu();

            // get input
            userInput = input.nextLine();

            // accept upper or lower case commands
            userInput.toLowerCase();

            // do something
            handleMenu(userInput);

        }

        // close the scanner
        input.close();

    }


    /*
     * displays the menu for the app
     */
    public static void menu() {

        System.out.println("---- SpotifyLikeApp ----");
        System.out.println("[H]ome");
        System.out.println("[S]earch by title");
        System.out.println("[L]ibrary");
        System.out.println("[P]lay");
        System.out.println("[Q]uit");

        System.out.println("");
        System.out.print("Enter q to Quit:");

    }

    /*
     * handles the user input for the app
     */
    public static void handleMenu(String userInput) {

        switch (userInput) {

            case "h":
                System.out.println("-->Home<--");
                break;

            case "s":
                System.out.println("-->Search by title<--");
                Scanner input = new Scanner(System.in);
                System.out.println("Enter Title:");
                String title = input.nextLine();
                Song song = songs.get(title);

            /*created if or else statement */
                if (song !=null)
                {
                    System.out.println("Current selection is playing");
                    play(song.getFilePath());

                } else {
                    System.out.println("Please search again");
            }
                break;

                case "l":
                System.out.println("-->Library<--");
                for(Map.Entry<String, Song> s : songs.entrySet()) {
                    System.out.println("Title: " + s.getValue().getTitle());
                    System.out.println("Artist: " + s.getValue().getArtist());
                    System.out.println("Genre: " + s.getValue().getGenre());
                    System.out.println("Year: " + s.getValue().getYear());
                    System.out.println("");

                }

                break;
                
            case "p":
                System.out.println("-->Play<--");
             
                break;

            case "q":
                System.out.println("-->Quit<--");
                break;

            default:
                break;
        }

    }

    /*
     * plays an audio file
     */
    public static void play(String filePath) {

        // open the audio file
        final File file = new File(filePath);

        try {
        
            // create clip 
            audioClip = AudioSystem.getClip();

            // get input stream
            final AudioInputStream in = getAudioInputStream(file);

            audioClip.open(in);
            audioClip.setMicrosecondPosition(0);
            audioClip.loop(Clip.LOOP_CONTINUOUSLY);

        } catch(Exception e) {
            e.printStackTrace(); 
        }

    }


}

