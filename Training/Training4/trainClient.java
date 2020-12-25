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

            String diachiClient = InetAddress.getLocalHost().getHostAddress();
            pw.println(diachiClient);

            String input1 = sc.nextLine();
            String input2 = sc.nextLine();
            int congUDP = Integer.parseInt(input1);
            String matkhau = input2.toString();

            System.out.println("Cong: " + congUDP);
            System.out.println("Mat khau: " + matkhau);

            DatagramSocket ds = new DatagramSocket();
            byte b[] = matkhau.getBytes();
            int len = b.length;
            InetAddress dc = InetAddress.getByName(diachi);
            DatagramPacket op = new DatagramPacket(b, len, dc, congUDP);
            ds.send(op);

            byte b1[] = new byte[60000];
            DatagramPacket ip = new DatagramPacket(b1, 60000);
            ds.receive(ip);

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
