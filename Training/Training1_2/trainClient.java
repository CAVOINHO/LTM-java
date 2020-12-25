import java.io.*;
import java.net.*;
import java.util.*;

public class trainClient {
    public static void main(String[] args) {
        Scanner kb = new Scanner(System.in);
        System.out.print("Nhap dia chi Server: ");
        String diachi = kb.nextLine();
        System.out.print("Nhap cong UDP: ");
        int congUDP = Integer.parseInt(kb.nextLine());
        try {
            DatagramSocket ds = new DatagramSocket();

            String diachiClient = InetAddress.getLocalHost().getHostAddress();
            byte b[] = diachiClient.getBytes();
            int len = b.length;
            InetAddress dc = InetAddress.getByName(diachi);
            DatagramPacket local = new DatagramPacket(b, len, dc, congUDP);
            ds.send(local);

            byte input1[] = new byte[60000];
            byte input2[] = new byte[60000];
            DatagramPacket ip1 = new DatagramPacket(input1, 60000);
            DatagramPacket ip2 = new DatagramPacket(input2, 60000);
            ds.receive(ip1);
            ds.receive(ip2);
            String ketqua1 = new String(ip1.getData(), 0, ip1.getLength());
            String ketqua2 = new String(ip2.getData(), 0, ip2.getLength());
            int congTCP = Integer.parseInt(ketqua1);
            String matkhau = ketqua2.toString();

            System.out.println("Cong TCP: " + congTCP);
            System.out.println("Mat khau: " + matkhau);

            Socket s = new Socket(diachi, congTCP);
            InputStream is = s.getInputStream();
            OutputStream os = s.getOutputStream();
            Scanner sc = new Scanner(is);
            PrintWriter pw = new PrintWriter(os, true);

            pw.println(matkhau);

            String input3 = sc.nextLine();
            if (input3.equals("ERR")) {
                System.out.println("Mat khau sai");
            } else {
                int n = Integer.parseInt(input3);
                System.out.println("Kich thuoc file: " + n);
                byte b1[] = new byte[n];
                FileOutputStream f = new FileOutputStream("ketqua.pdf");
                DataInputStream dis = new DataInputStream(is);
                dis.readFully(b1);
                f.write(b1);
                f.close();
            }
            s.close();
            ds.close();

        } catch (Exception e) {
            System.out.println("ERROR: " + e);
        }
    }
}


