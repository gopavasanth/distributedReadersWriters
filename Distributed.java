import java.rmi.*;
public interface Distributed extends Remote{

public void readingControl(int i,String val) throws RemoteException;
public void stopReading(int i) throws RemoteException;
public void writing(String input) throws RemoteException;
public void reading(int i) throws RemoteException;
public void lock() throws RemoteException;
public void release() throws RemoteException;
}
