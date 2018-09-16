/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interpreter.ByteCode;
import interpreter.*;
import java.util.Scanner;
/**
 *
 * @author Saad
 */
public class Read extends ByteCode{
    
      @Override
      public void init(String arguments[]){}
      
      @Override
      public String toString(){
        return "READ";
    }
       @Override
        public void execute(VirtualMachine vm){
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter an int: ");
        vm.pushRunStack(sc.nextInt());
    }
}
