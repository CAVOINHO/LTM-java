import java.io.*;
import java.net.*;
import java.util.*;

public class trainClient {
    public static void main(String[] args) {
        Scanner kb = new Scanner(System.in);
        System.out.print("Nhap dia chi Server: ");
        String diachi = kb.nextLine();
        System.out.print("Nhap cong TCP: ");
        int congTCP = Integer.parseInt(kb.nextLine());
        try {
            Socket s = new Socket(diachi, congTCP);
            InputStream is = s.getInputStream();
            OutputStream os = s.getOutputStream();
            Scanner sc = new Scanner(is);
            PrintWriter pw = new PrintWriter(os, true);

            String daichiClient = InetAddress.getLocalHost().getHostAddress();
            pw.println(daichiClient);

            String input1 = sc.nextLine();
            String matkhau = sc.nextLine();
            int congUDP = Integer.parseInt(input1);

            System.out.println("Cong UDP: " + congUDP);
            System.out.println("Mat khau: " + matkhau);

            DatagramSocket ds = new DatagramSocket();
            byte b1[] = matkhau.getBytes();
            int len = b1.length;
            InetAddress dc = InetAddress.getByName(diachi);
            DatagramPacket op = new DatagramPacket(b1, len, dc, congUDP);
            ds.send(op);

            byte b2[] = new byte[60000];
            DatagramPacket ip = new DatagramPacket(b2, 60000);
            ds.receive(ip);

            byte b3[] = ip.getData();
            int n = ip.getLength();
            FileOutputStream f = new FileOutputStream("ketqua.txt");
            f.write(b3, 0, n);
            f.close();
            System.out.println("Da ghi file thanh cong!");

            s.close();
            ds.close();

        } catch (Exception e) {
            System.out.println("ERROR: " + e);
        }
    }
}
