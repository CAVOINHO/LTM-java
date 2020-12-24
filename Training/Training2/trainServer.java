import java.util.*;
import java.net.*;
import java.io.*;

public class trainServer {
    public static void main(String[] args) {
        int port = 8;
        try {
            // Tao UDP
            DatagramSocket ds = new DatagramSocket(port);
            System.out.println("UDP da khoi tao");
            // Nhan tu Client
            byte input[] = new byte[60000];
            DatagramPacket ip = new DatagramPacket(input, 60000);
            while (true) {
                ds.receive(ip);
                String matkhau = "kien123";
                DatagramPacket output2 = new DatagramPacket(matkhau.getBytes(), matkhau.length(), ip.getAddress(), ip.getPort());
                ds.send(output2);

                ServerSocket ss = new ServerSocket(9);
                System.out.println("Da khoi tao TCP Server");
                Socket s = ss.accept();
                InputStream is = s.getInputStream();
                OutputStream os = s.getOutputStream();
                Scanner sc = new Scanner(is);
                PrintWriter pw = new PrintWriter(os, true);
                // Nhan tu client
                String diachi = sc.nextLine();
                System.out.println("Dia chi la: " +  diachi);
                // Xu ly
                String daochuoi = sc.nextLine();
                System.out.println("Chuoi dao: " + daochuoi);    
                String sosanh = new StringBuilder(matkhau).reverse().toString();
                if (daochuoi.equals(sosanh)) {
                    pw.println("TRUE");
                } else {
                    pw.println("ERR");
                }
                s.close();
            }
        }

        catch (Exception e) {
            System.out.println("ERROR " + e);
        }
    }
}
