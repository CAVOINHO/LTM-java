import java.rmi.RMISecurityManager;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.net.MalformedURLException;

class DiemServer {
    public static void main(String[] args) {
        try {
            // Khoi tao co che bao mat cua RMI
            if (System.getSecurityManager() == null)
                System.setSecurityManager(new RMISecurityManager());
            // Tao doi tuong cho phep goi tu xa
            DiemRMI a = new DiemRMI();
            DiemRMI b = new DiemRMI(10, 20);
            DiemRMI c = new DiemRMI();
            System.out.println("Nhap toa do moi cho C");
            c.nhap();
            System.out.println("Toa do hien tai cua cac diem");
            System.out.print("A");
            a.hienThi();
            System.out.print("B");
            b.hienThi();
            System.out.print("C");
            c.hienThi();
            // Dang ky doi tuong cho phep goi tu xa
            Naming.rebind("DiemA", a);
            Naming.rebind("DiemB", b);
            Naming.rebind("DiemC", c);
            System.out.println("Da dang ky thanh cong 3 diem doi tu xa");
        } catch (RemoteException e) {
            System.out.println("Tao hoac dang ky doi tuong tu xa bi loi");
        } catch (MalformedURLException e) {
            System.out.println("Sai dinh dang URL");
        }
    }
}