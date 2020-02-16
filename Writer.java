import java.rmi.*;
import java.util.*;
import java.io.*;
public class Writer{

public static void main(String args[]){
Scanner sc = new Scanner(System.in);
try{

Distributed stub=(Distributed)Naming.lookup("rmi://localhost:5000/run");
String ch = "yes";
String input = "";
stub.lock();
do {
System.out.println("Write your text");
input = sc.nextLine();
stub.writing(input);
System.out.println("Do you want to write more (yes/no)");
ch = sc.nextLine(); 

}while(ch.equals("yes"));
stub.release();
}catch(Exception e){System.out.println(e);}
}

}
