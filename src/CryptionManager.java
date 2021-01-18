import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CryptionManager {
    
    public static ArrayList<String> getDecryptedList(ArrayList<String> sentenceList) {
        ArrayList<String> decryptedList = new ArrayList<>();
        for (String sentence : sentenceList) {
            String sentenceToDecrypt = sentence;
            sentenceToDecrypt = decryptAntd(sentenceToDecrypt);
            sentenceToDecrypt = decryptMor(sentenceToDecrypt);
            sentenceToDecrypt = decryptLar(sentenceToDecrypt);
            sentenceToDecrypt = decryptTrain(sentenceToDecrypt);
            sentenceToDecrypt = decryptAMTime(sentenceToDecrypt);
            sentenceToDecrypt = decryptPMTime(sentenceToDecrypt);
            sentenceToDecrypt = decryptOf(sentenceToDecrypt);
            sentenceToDecrypt = decryptHur(sentenceToDecrypt);
            
            
            decryptedList.add(sentenceToDecrypt);
        }
        return decryptedList;
    }
    
    private static String decryptPMTime(String input) {
        Pattern pattern = Pattern.compile("BC", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(input);
        input = matcher.replaceAll("P.M.");
        return input;
    }
    
    
    private static String decryptAMTime(String input) {
        Pattern pattern = Pattern.compile("WC", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(input);
        input = matcher.replaceAll("A.M.");
        return input;
    }
    
    private static String decryptTrain(String input) {
        Pattern pattern = Pattern.compile("train", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(input);
        input = matcher.replaceAll("tanks");
        return input;
    }
    
    
    private static String decryptHur(String input) {
        Pattern pattern = Pattern.compile("hur", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(input);
        input = matcher.replaceAll("have");
        return input;
    }
    
    private static String decryptOf(String input) {
        Pattern pattern = Pattern.compile("of", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(input);
        input = matcher.replaceAll("th");
        return input;
    }
    
    private static String decryptMor(String input) {
        Pattern pattern = Pattern.compile("mor", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(input);
        input = matcher.replaceAll("es");
        return input;
    }
    
    private static String decryptLar(String input) {
        Pattern pattern = Pattern.compile("lar", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(input);
        input = matcher.replaceAll("s");
        return input;
    }
    
    
    private static String decryptAntd(String input) {
        Pattern pattern = Pattern.compile("antd", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(input);
        input = matcher.replaceAll("tion");
        return input;
    }
    
    
}
