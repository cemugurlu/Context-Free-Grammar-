import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 *  CFG class with method corresponding to the cfg
 */
public class CFG {
    /** Non-Terminal and Terminal methods each works with their corresponding
     * .txt files
     *
     */
    public static boolean nonTerminalA(String input) throws IOException {
        return doesFileInPathContainsWordFromInput("A.txt", input);
    }
    
    public static boolean nonterminalZ(String input) throws IOException {
        return doesFileInPathContainsWordFromInput("Z.txt", input);
    
    }
    public static boolean actual(String input) throws IOException {
        return doesFileInPathContainsWordFromInput("actual.txt", input);
    }
    
    public static boolean code(String input) throws IOException {
        return doesFileInPathContainsWordFromInput("code.txt", input);
        
    }
    
    public static boolean event(String input) throws IOException {
        return doesFileInPathContainsWordFromInput("event.txt", input);
        
    }
    
    public static boolean location(String input) throws IOException {
        return doesFileInPathContainsWordFromInput("location.txt", input);
        
    }
    
    public static boolean pluralVerb(String input) throws IOException {
        return doesFileInPathContainsWordFromInput("plural_verb.txt", input);
        
    }
    
    public static boolean singularVerb(String input) throws IOException {
        return doesFileInPathContainsWordFromInput("singular_verb.txt", input);
        
    }
    
    public static boolean supplies(String input) throws IOException {
        return doesFileInPathContainsWordFromInput("supplies.txt", input);
        
    }
    
    public static boolean time(String input) throws IOException {
        return doesFileInPathContainsWordFromInput("time.txt", input);
        
    }
    
    
    public static boolean terminalY(boolean location, boolean event) {
        return location || event;
    }
    
    public static boolean terminalT(boolean time, boolean supplies) {
        return time || supplies;
    }
    
    
    
    public static boolean terminalX(boolean pluralVerb, boolean singularVerb) {
        return pluralVerb || singularVerb;
    }
    
    public static boolean terminalB(boolean actual, boolean code) {
        return actual || code;
    }
    
    public static boolean terminalK(boolean terminalX, boolean terminalT, boolean terminalY, boolean nonterminalZ) throws IOException {
        return terminalX && terminalT || terminalX && nonterminalZ && terminalY || terminalX;
    }
    
    public static boolean terminalC(boolean nonTerminalA, boolean terminalB) throws  IOException {
        return nonTerminalA && terminalB || terminalB;
    }
    
    public static boolean terminalS(boolean terminalC, boolean terminalK) throws IOException {
        return terminalC && terminalK;
    }
    
    
    /** Method that checks the word from input whether exists or not
     *
     * @param path
     * @param input
     * @return
     * @throws IOException
     */
    private static boolean doesFileInPathContainsWordFromInput(String path, String input) throws IOException {
        return Files.lines(Paths.get(path)).anyMatch(word -> {
            for (String inputWord : input.split(" ")) {
                if (inputWord.equals(word))
                    return true;
            }
            return false;
            
        });
        
    }
    
    
}
