import java.net.*;
import java.util.*;
import java.io.*;

public class trainClient {
    public static void main(String[] args) {

        try {
            Scanner kb = new Scanner(System.in);
            System.out.print("Nhap dia chi Server: ");
            String diachi = kb.nextLine();
            System.out.print("Nhap cong TCP: ");
            int congTCP = Integer.parseInt(kb.nextLine());

            Socket s = new Socket(diachi, congTCP);
            InputStream is = s.getInputStream();
            OutputStream os = s.getOutputStream();
            Scanner sc = new Scanner(is);
            PrintWriter pw = new PrintWriter(os, true);
            System.out.print("Nhap ho va ten: ");
            String hoten = kb.nextLine();
            System.out.print("Nhap dia chi: ");
            pw.println(hoten);
            pw.println(diachi);

            String ten = sc.nextLine();
            String congUDP = sc.nextLine();
            System.out.println("Ten: " + ten);
            System.out.println("Cong: " + congUDP);
            int cong = Integer.parseInt(congUDP);

            DatagramSocket ds = new DatagramSocket();
            String matkhau = "123";
            byte op[] = matkhau.getBytes();
            int len = op.length;
            InetAddress x = InetAddress.getByName(diachi);
            DatagramPacket oip = new DatagramPacket(op, len, x, cong);
            ds.send(oip);

            byte b[] = new byte[60000];
            DatagramPacket ip = new DatagramPacket(b, 60000);
            ds.receive(ip);
            System.out.println("file: " + ip);

            byte input[] = ip.getData();
            int n = ip.getLength();
            FileOutputStream f = new FileOutputStream("ketqua.txt");
            f.write(input, 0, n);
            f.close();
            System.out.println("Da ghi file thanh cong!");
            s.close();
            ds.close();
        }

        catch (Exception e) {
            System.out.println("ERROR: " + e);
        }
    }
}
