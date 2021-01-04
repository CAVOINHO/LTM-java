import java.rmi.Remote;
import java.rmi.RemoteException;
interface  DiemRMIItf extends Remote {
	// Khai bao cac ham goi tu xa
	public String layToaDo() throws RemoteException;
	public void gan(int h, int t) throws RemoteException;
	public void doiDiem(int dx, int dy) throws RemoteException;
}