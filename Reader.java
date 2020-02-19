import java.rmi.*;
import java.util.*;

public class Reader{
	public static void main(String args[]){
		Scanner sc = new Scanner(System.in);
		int n;
		System.out.println("Enter your id (1/2):");
		n = sc.nextInt();
		try{
				Distributed stub=(Distributed)Naming.lookup("rmi://localhost:5000/run");
				Thread newThread = new Thread(() -> {
					try{
						stub.readingControl(n,"start");
					} catch(Exception e){
						System.out.println(e);
					}
				});
			newThread.start();
			while(true) {
				System.out.println("Do you want to (pause) or type anything to (stop) ?");
				String s = sc.next();
				if(s.equals("pause")) {
					stub.readingControl(n,"pause");
					System.out.println("Do you want to (resume) or type anything to (stop)");
					s = sc.next();
					if(s.equals("resume"))	
						stub.readingControl(n,"resume");
					else
					{
						stub.readingControl(n,"stop");
						break;
					}
				}
				else
				{
					stub.readingControl(n,"stop");
					break;
				}
			}
		} catch(Exception e){
			System.out.println(e);
		}
	}
}
