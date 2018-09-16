package interpreter;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Stack;

public class RunTimeStack {

    private ArrayList runTimeStack;
    private Stack<Integer> framePointer;

    public RunTimeStack() 
    {
        runTimeStack = new ArrayList<>();
        framePointer = new Stack<>();
        //Add initial Frame Pointer, main is the entry
        // point of our language, so its frame pointer is 0.
        framePointer.add(0);
    }

    public int peek(){
    return (Integer)runTimeStack.indexOf(runTimeStack.size()-1);
    }
    
    public int pop(){
    return (Integer)runTimeStack.remove(runTimeStack.size()-1);
    }
    
    public int push(int i){
    runTimeStack.add(i);
    return i;
    }
    
    //create a new frame in the RTStack. 
    public void newFrameAt(int offset){
        if (runTimeStack.size()-offset != framePointer.lastElement()){
        framePointer.add(runTimeStack.size()-offset);
        }
    }
    
    //pop the frame
    public void popFrame(){
    int val = (Integer)runTimeStack.get(runTimeStack.size()-1);
    
    runTimeStack.subList((Integer)framePointer.lastElement(), runTimeStack.size()).clear();
   
        if ((Integer)framePointer.peek()!=0){
        framePointer.pop();
        }
    runTimeStack.add(val);
    }
    
    //store values into variables, pop the top of the stack, replace the value at the offset  and return
    public int store(int offset){
    runTimeStack.set((Integer)runTimeStack.get(runTimeStack.size()-1),(Integer)framePointer.peek()+offset);
    return (Integer)runTimeStack.get(runTimeStack.size()-1);
    }
    
    
    //load variables onto RTStack without removing anything
    public int load (int offset){
    runTimeStack.add((Integer)runTimeStack.get((Integer)framePointer.peek()+offset));
    return (Integer)runTimeStack.get((Integer)framePointer.peek()+offset);
    }
    
    //load liters onto RTStack
    public Integer push (Integer val){
    runTimeStack.add(new Integer (val));
    return val;
    }
    

    public void dump(){
    Iterator it = framePointer.iterator();
    int currentFrame= (Integer) it.next();
    int nextFrame= (Integer) it.next();
    for (int i =0; i<framePointer.size(); i++) {
        if (it.hasNext()){
            nextFrame = (Integer) it.next();
        }
        else {
            nextFrame = runTimeStack.size();
        }     
        
    System.out.print("[");
    
    for (int j = currentFrame; j < nextFrame ; j++){
    System.out.print(runTimeStack.get(j));
        if (j!=nextFrame-1){
        System.out.print(",");
        }
    }
    
    System.out.print("]");
    currentFrame = nextFrame;
    }
    System.out.println(" ");
    }



}   

