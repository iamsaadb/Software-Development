package interpreter;
import interpreter.ByteCode.ByteCode;
import interpreter.Program;
import java.util.List;
import java.util.Stack;

public class VirtualMachine {

    private RunTimeStack runStack;
    private Stack<Integer> returnAddrs;
    private Program program;
    private int pc;
    private boolean isRunning;
    int dumpVal;
    String dumpFlag;
    public boolean isHalt;

    protected VirtualMachine(Program program) {
        this.program = program;
    }

    
    public void execute(){
    runStack = new RunTimeStack();
    returnAddrs = new Stack();
    pc = 0;
    isRunning = true;
    dumpFlag = "OFF";
    
        while(isRunning){
            if(dumpFlag.equals("ON")){
                dumpVal = 1;
            }
        ByteCode code = program.getCode(pc);
        code.execute(this);
            if(dumpFlag.equals("OFF")){
                dumpVal = 0; 
            }
            if (dumpVal == 1){
                runStack.dump();
            }
        pc++;
        }  
    }
    
      
    //getters and setters
    
     public void setPc(int n){
    this.pc = n;
    }
     
    public int getPc( ){
    return pc;
    }
    //incrementor
    public void incrementePc(){
    pc++;
    }
     public void newFrame(int i){
        runStack.newFrameAt(i);
    }
    
    public void popFrame(){
        runStack.popFrame();
    }
    
    public int popRunStack(){
        return runStack.pop();
    }
    
    public int peekRunStack(){
        return runStack.peek();
    }
    
    public void pushRunStack(int i){
        runStack.push(i);
    }
    
    public void loadStack(int i){
        runStack.load(i);
    }
    
    public void storeStack(int i){
        runStack.store(i);
    }
   
    public void pushReturnAd(int i){
        returnAddrs.push(i);
    }
    
    public int popReturnAd(){
        return (Integer)returnAddrs.pop();
    }
    
    public void dumpSwitch(String flag){
        dumpFlag = flag;
    }

    public void halt(){
        isRunning = false;
    }

   
  
    
    
}
