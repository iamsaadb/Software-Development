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
public class Dump extends ByteCode{
 public String stats = "OFF";
 
 @Override
 public void init(String vars[]){
 stats = vars[0];
 }
 
 @Override
 public void execute (VirtualMachine vm){
 vm.dumpSwitch(stats);
 }
 
 @Override
 public String toString(){
 return "DUMP" + stats + "\n"; 
 }
}