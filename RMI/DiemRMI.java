import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;
import java.util.Scanner;

class DiemRMI extends UnicastRemoteObject implements DiemRMIItf {
    // Thuoc tinh
    private int x;
    private int y;

    // Ham xay dung
    public DiemRMI() throws RemoteException {
        super();
        x = y = 0;
    }

    public DiemRMI(int h, int t) throws RemoteException {
        super();
        x = h;
        y = t;
    }

    // Phuong thuc cuc bo
    public void nhap() {
        Scanner kb = new Scanner(System.in);
        System.out.print("Nhap hoanh do: ");
        x = kb.nextInt();
        System.out.print("Nhap tung do: ");
        y = kb.nextInt();
    }

    public void hienThi() {
        System.out.println("(" + x + "," + y + ")");
    }

    // Cac ham tu xa
    public String layToaDo() {
        String kq = "(" + x + "," + y + ")";
        return kq;
    }

    public void gan(int h, int t) {
        x = h;
        y = t;
    }

    public void doiDiem(int dx, int dy) {
        x += dx;
        y += dy;
    }
}