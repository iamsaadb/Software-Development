/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interpreter.ByteCode;
import interpreter.*;

/**
 *
 * @author Saad
 */
public class Label extends ByteCode {
    public String label;
    
    @Override
    public void init(String vars[]){
    
    label = vars[1];
    }
    
    @Override
    public void execute (VirtualMachine vm){
    
    }
    
    public String getLabel(){
    
    return label;
    }
    
    @Override
    public String toString(){
    return "LABEL  "+ label;
    
    }
}
