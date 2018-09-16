package interpreter;
import interpreter.*;
import interpreter.ByteCode.ByteCode;
import java.io.*;
import java.util.*;

    /**
     * This function should read one line of source code at a time.
     * For each line it should:
     *      Tokenize string to break it into parts.
     *      Grab correct class name for the given ByteCode from CodeTable
     *      Create an instance of the ByteCode class name returned from code table.
     *      Parse any additional arguments for the given ByteCode and send them to
     *      the newly created ByteCode instance via the init function.
     */

public class ByteCodeLoader extends Object {

    private BufferedReader byteSource;
    private Program program;
    public ArrayList byteCodeList;

    public ByteCodeLoader(String file) throws IOException {
        this.byteSource = new BufferedReader(new FileReader(file));
        byteCodeList = new ArrayList();
        String line = byteSource.readLine();
        while(line!=null){
        //tokenizer
        StringTokenizer tokenizer = new StringTokenizer(line);
        String token = tokenizer.nextToken();
        String byteCodeClass = CodeTable.getClassName(token);
        
        String[] vars = new String[2];
        for (int i = 0; tokenizer.hasMoreTokens(); i++){
        vars[i] = tokenizer.nextToken();
        }
        
        //Build an instance of the class corresponding to the bytecode
        try{
        ByteCode bytecode = (ByteCode)(Class.forName("interpreter.bytecode."+byteCodeClass).newInstance());
        bytecode.init(vars);
        //store bytecodes in the ArrayList
        byteCodeList.add(bytecode);
        }
        catch(Exception ex ){
        System.out.println("Exception:  " + ex);
        }
        }
        
    }

    
    //load codes from the file and let Prog resolve Addrs
    public Program loadCodes(){
        //Program Prog = new Program(byteCodeList);
        //Prog.resolveAddrs();
    return null; //Prog;}
}}
