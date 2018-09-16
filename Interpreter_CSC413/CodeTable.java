/**
 * Code table of byte codes in language X
 * @key name of a specific byte code
 * @value name of the class that the key belongs to.
 * returns Class name as a string.
 */
package interpreter;

import java.util.HashMap;

public class CodeTable {
    
    protected static HashMap<String,String> codeTable ;
    
    private CodeTable(){}
    
    public static void init(){
        codeTable =  new HashMap<>();
        
        codeTable.put("HALT", "Halt");
        codeTable.put("POP", "Pop");
        codeTable.put("FALSEBRANCH", "FalseBranch");
        codeTable.put("GOTO", "GoTo");
        codeTable.put("STORE", "Store");
        codeTable.put("LOAD", "Load");
        codeTable.put("LIT", "Lit");
        codeTable.put("ARGS", "Args");
        codeTable.put("CALL", "Call");
        codeTable.put("RETURN", "Return");
        codeTable.put("BOP", "BOP");
        codeTable.put("READ", "Read");
        codeTable.put("WRITE", "Write");
        codeTable.put("LABEL", "Label");
        codeTable.put("DUMP", "Dump");
    }

    /**
     * A method to facilitate the retrieval of the names
     * of a specific byte code class.
     * @param key for byte code.
     * @return class name of desired byte code.
     */
    public static String getClassName(String key){
        
        return codeTable.get(key);
        
    }
}
