import java.rmi.Naming;
import java.rmi.RemoteException;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;

class DiemClient {
    public static void main(String[] args) {
        try {
            // Tim cac doi tuong cho phep goi tu xa
            DiemRMIItf ra = (DiemRMIItf) Naming.lookup("rmi://127.0.0.1/DiemA");
            DiemRMIItf rb = (DiemRMIItf) Naming.lookup("rmi://127.0.0.1/DiemB");
            DiemRMIItf rc = (DiemRMIItf) Naming.lookup("rmi://127.0.0.1/DiemC");
            // Goi ham
            System.out.println("Diem A: " + ra.layToaDo());
            System.out.println("Diem B: " + rb.layToaDo());
            System.out.println("Diem C: " + rc.layToaDo());
        } catch (NotBoundException e) {
            System.out.println("Khong tim thay doi tuong");
        } catch (RemoteException e) {
            System.out.println("Tim doi tuong tu xa bi loi");
        } catch (MalformedURLException e) {
            System.out.println("Sai dinh dang URL");
        }
    }
}