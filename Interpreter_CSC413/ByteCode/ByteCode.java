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
public abstract class ByteCode {
    
    public void init(String vars[]){
    }
    public abstract void execute(VirtualMachine vm);
    public String toString(){
    return " ";
    }
}
