import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Cem Ugurlu
 * @author Uras Felamur
 */
public class Main {
    
    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
        ArrayList<String> sentenceList = new ArrayList<>();
        ArrayList<String> wordList = new ArrayList<>();
        
        try {
            File inputFile = new File("encrypted.txt");
            Scanner scanner = new Scanner(inputFile);
            while (scanner.hasNextLine()) {
                String word = scanner.nextLine();
                String[] words = word.split(" ");
                wordList.addAll(Arrays.asList(words));
                sentenceList.add(word);
                System.out.println(word);
            }
            
            
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Couldn't read the file.");
            e.printStackTrace();
        }
        System.out.println("  *  * * * DECRYPTION HAS BEEN DONE * * *  *  ");
        //System.out.println(wordList);
        
        ArrayList<String> decryptedList = new ArrayList<>();
        for (String sentence : sentenceList) {
            String sentenceToDecrypt = sentence;
            sentenceToDecrypt = decryptTank(sentenceToDecrypt);
            sentenceToDecrypt = decryptNumber(sentenceToDecrypt);
            sentenceToDecrypt = decryptAMTime(sentenceToDecrypt);
            sentenceToDecrypt = decryptPMTime(sentenceToDecrypt);
            sentenceToDecrypt = decryptTion(sentenceToDecrypt);
            sentenceToDecrypt = decryptTh(sentenceToDecrypt);
            
            decryptedList.add(sentenceToDecrypt);
        }
        //Created the intermediate file to send it CFG
        PrintWriter writer = new PrintWriter("intermediateFile.txt", "UTF-8");
        
        for (String decryptedSentences : decryptedList) {
            writer.println(decryptedSentences);
        }
        writer.close();
        
        
        
    }
    
    public static String encryptReverseTheString(String input) {
        Pattern pat = Pattern.compile(" [a-z&&[^aeiou]]", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pat.matcher(input);
        //TODO: Reverse the input
        //input = matcher.replaceAll(" $1ba ");
        
        return input;
        
        
    }
    
    public static String decryptAMTime(String input) {
        Pattern pattern = Pattern.compile("A.M.", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(input);
        input = matcher.replaceAll("P.M.");
        return input;
    }
    
    
    public static String decryptPMTime(String input) {
        Pattern pattern = Pattern.compile("P.M.", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(input);
        input = matcher.replaceAll("A.M.");
        return input;
    }
    
    public static String decryptTank(String input) {
        Pattern pattern = Pattern.compile("blommor", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(input);
        input = matcher.replaceAll("tanks");
        return input;
    }
    
    public static String decryptNumber(String input) {
        if (isInteger(input)) {
            int integer = Integer.parseInt(input);
            integer = integer / 1912;
            input = String.valueOf(integer);
            return input;
            
        }
        
        return input; //???
        
    }
    
    
    public static String decryptTh(String input) {
        Pattern pattern = Pattern.compile("of", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(input);
        input = matcher.replaceAll("th");
        return input;
    }
    
    
    public static String decryptTion(String input) {
        Pattern pattern = Pattern.compile("antd", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(input);
        input = matcher.replaceAll("tion");
        return input;
    }
    
    public static boolean isInteger(String input) {
        if (input == null) {
            return false;
        }
        int length = input.length();
        if (length == 0) {
            return false;
        }
        int i = 0;
        if (input.charAt(0) == '-') {
            if (length == 1) {
                return false;
            }
            i = 1;
        }
        for (; i < length; i++) {
            char c = input.charAt(i);
            if (c < '0' || c > '9') {
                return false;
            }
        }
        return true;
    }
    
    
}
