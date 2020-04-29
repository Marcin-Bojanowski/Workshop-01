package pl.githubcom;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.file.*;
import java.util.Arrays;

public class Search {

    public static void main(String[] args) {
        String url = "https://www.onet.pl/";
        String filename = "popular_words.txt";
        String filteredFilename = "filtered_popular_words.txt";
        String popularwords = getElements(url);
        String[] splittedWords = popularWords(popularwords);
        writeToFile(filename, splittedWords);
        String[] readedWords = new String[splittedWords.length];
        try {
            readedWords = readFile(filename);
        } catch (IOException ex) {
            System.out.println(ex);
        }
        String[] censoredWords = {"oraz","ponieważ","koronawirus","epidemia","pandemia","covid"};
        String[] filteredWords = filteredWords(readedWords, censoredWords);
        System.out.println("Liczna słów odczytanych z pliku");
        System.out.println(readedWords.length);
        System.out.println("Liczba słów ocenzurowanych");
        System.out.println(readedWords.length - filteredWords.length);
        writeToFile(filteredFilename, filteredWords);
    }

    public static String[] popularWords(String elements) {
        String[] wordsArray = elements.split("[ &!?.,'_+=:;/()\n\\-\"0123456789]");
        String[] splittedWords = new String[0];
        for (int i = 0; i < wordsArray.length; i++) {
            if (wordsArray[i].length() >= 3) {
                splittedWords = Arrays.copyOf(splittedWords, splittedWords.length + 1);
                splittedWords[splittedWords.length - 1] = wordsArray[i];
            }
        }
        return splittedWords;
    }


    public static String getElements(String url) {
        Connection connect = Jsoup.connect(url);
        String words = "";
        try {
            Document document = connect.get();
            Elements links = document.select("span.title");
            words = links.text().toLowerCase();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return words;
    }

    public static void writeToFile(String filename, String[] splittedWords) {
        Path path = Paths.get(filename);
        try {
            Files.write(path, Arrays.asList(splittedWords));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static String[] readFile(String filename) throws IOException {
        String[] readedWords = new String[0];
        Path path = Paths.get(filename);
        if (Files.exists(path)) {
            for (String line : Files.readAllLines(path)) {
                readedWords = Arrays.copyOf(readedWords, readedWords.length + 1);
                readedWords[readedWords.length - 1] = line;
            }
        }
        return readedWords;
    }

    public static String[] filteredWords(String[] readedWords, String[] censoredWords) {
        String[] filteredWords = new String[0];
        for (String record : readedWords) {
            if (!Arrays.asList(censoredWords).contains(record)) {
                filteredWords = Arrays.copyOf(filteredWords, filteredWords.length + 1);
                filteredWords[filteredWords.length - 1] = record;
            }
        }
        return filteredWords;
    }
}
