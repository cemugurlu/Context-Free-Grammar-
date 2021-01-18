import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * @author Cem Ugurlu
 * @author Uras Felamur
 * @date 17.01.2021
 * @brief Code that decrypts the given .txt file and with the implementation of CFG machine
 * it executes a new .txt file with the correct context free grammar.
 */
public class Main {
    
    public static void main(String[] args) throws IOException {
        ArrayList<String> sentenceList = new ArrayList<>();
        ArrayList<String> wordList = new ArrayList<>();
        ArrayList<String> finalList = new ArrayList<>();
        
        
        generateListsFromFile(sentenceList, wordList);
        
        ArrayList<String> decryptedList = CryptionManager.getDecryptedList(sentenceList);
        
        System.out.println("  *  * * * DECRYPTION HAS BEEN DONE * * *  *  ");
        //Created the intermediate file to send it CFG
        createIntermediateFile(decryptedList);
        
        System.out.println(decryptedList);
        
        /*for (String decryptedSentence : decryptedList)
            System.out.println(CFG.nonTerminalA(decryptedSentence));*/
        
        
        for (String sentence : decryptedList) {
            System.out.println(cfgControll(sentence));
            if (cfgControll(sentence))
                finalList.add(sentence);
            
        }
        
        createFinalTxtFile(finalList);
        
    }
    
    private static void createIntermediateFile(ArrayList<String> decryptedList) {
        try (PrintWriter writer = new PrintWriter("intermediateFile.txt", StandardCharsets.UTF_8)) {
            
            for (String decryptedSentences : decryptedList)
                writer.println(decryptedSentences);
            
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private static void createFinalTxtFile(ArrayList<String> decryptedList) {
        try (PrintWriter writer = new PrintWriter("final.txt", StandardCharsets.UTF_8)) {
            
            for (String decryptedSentences : decryptedList)
                writer.println(decryptedSentences);
            
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    
    private static void generateListsFromFile(ArrayList<String> sentenceList, ArrayList<String> wordList) {
        try {
            File inputFile = new File("encrypted.txt");
            Scanner scanner = new Scanner(inputFile);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] words = line.split(" ");
                wordList.addAll(Arrays.asList(words));
                sentenceList.add(line);
                //System.out.println(word);
            }
            
            
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Couldn't read the file.");
            e.printStackTrace();
        }
    }
    
    private static boolean cfgControll(String sentence) throws IOException {
        boolean eventBoolean = false;
        boolean locationBoolean = false;
        boolean timeBoolean = false;
        boolean suppliesBoolean = false;
        boolean codeBoolean = false;
        boolean actualBoolean = false;
        boolean pluralVerbBoolean = false;
        boolean singularVerbBoolean = false;
        boolean nonTerminalABoolean = false;
        
        
        eventBoolean = CFG.event(sentence);
        locationBoolean = CFG.location(sentence);
        timeBoolean = CFG.time(sentence);
        suppliesBoolean = CFG.supplies(sentence);
        codeBoolean = CFG.code(sentence);
        actualBoolean = CFG.actual(sentence);
        pluralVerbBoolean = CFG.pluralVerb(sentence);
        singularVerbBoolean = CFG.singularVerb(sentence);
        nonTerminalABoolean = CFG.nonTerminalA(sentence);
        
        
        
        boolean Y = CFG.terminalY(locationBoolean, eventBoolean);
        boolean T = CFG.terminalT(timeBoolean, suppliesBoolean);
        boolean X = CFG.terminalX(pluralVerbBoolean, singularVerbBoolean);
        boolean B = CFG.terminalB(actualBoolean, codeBoolean);
        boolean C = CFG.terminalC(nonTerminalABoolean, B);
        boolean K = CFG.terminalK(X, T, Y);
        boolean S = CFG.terminalS(C, K);
        
        return S;
        
        
    }
    
    
}
