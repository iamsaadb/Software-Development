package interpreter;
import interpreter.ByteCode.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Program {
    //bytecode instances holder
    private ArrayList <ByteCode> program = new ArrayList<>() ;
    //to resolve target addresses of branch bytecodes, hashmap with key = label and value = Target Address
    private HashMap<String,Integer> labelTable = new HashMap<>();
    
    //Add method is used to add the bytecode instance to the program
    public void add(ByteCode byteCode){
    if (byteCode instanceof Label){
    Label label = (Label)byteCode;
    labelTable.put(label.getLabel(),program.size());
    }
    program.add(byteCode);
    }
    //getter Code
    protected ByteCode getCode(int pc) {
        return this.program.get(pc);
    }
    //getter Size
    public int getSize() {
        return this.program.size();
    }

    /**
     * This function should go through the program and resolve all addresses.
     * Currently all labels look like LABEL <<num>>>, these need to be converted into
     * correct addresses so the VirtualMachine knows what to set the Program Counter(PC)
     * HINT: make note what type of data-stucture bytecodes are stored in.
     *
     * @param program Program object that holds a list of ByteCodes
     */
    public void resolveAddrs() {
        for (int i = 0 ; i<program.size();i++){
        if (program.get(i) instanceof Label){
            Label code = (Label)program.get(i);
            labelTable.put(code.getLabel(), i);
            }
        }
    
        for(int i = 0; i<program.size();i++){
            if(program.get(i) instanceof GoTo){
            GoTo code = (GoTo)program.get(i);
            code.setAddress((Integer)labelTable.get(code.getLabel()));
            }
            else if (program.get(i) instanceof Call){
            Call code = (Call)program.get(i);
            code.setAddress((Integer)labelTable.get(code.getFuncName()));
            }

            else if (program.get(i) instanceof FalseBranch){
            FalseBranch code = (FalseBranch)program.get(i);
            code.setAddress((Integer)labelTable.get(code.getLabel()));
            }

        }

    }




}
