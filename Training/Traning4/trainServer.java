import java.util.*;
import java.net.*;
import java.io.*;

public class trainServer {
    public static void main(String[] args) {
        int port = 8;
        try {
            ServerSocket ss = new ServerSocket(9);
            System.out.println("Da khoi tao TCP Server");
            Socket s = ss.accept();
            InputStream is = s.getInputStream();
            OutputStream os = s.getOutputStream();
            Scanner sc = new Scanner(is);
            PrintWriter pw = new PrintWriter(os, true);

            String hoten = sc.nextLine();
            String local = sc.nextLine();
            System.out.println("hoten: " + hoten);
            String ten = hoten.substring(hoten.lastIndexOf(" ") + 1);
            pw.println(ten);
            pw.println(port);

            DatagramSocket ds = new DatagramSocket(port);
            System.out.println("UDP da khoi tao");

            byte rc[] = new byte[60000];
            DatagramPacket ki = new DatagramPacket(rc, 60000);
            ds.receive(ki);

            File f = new File("testing.txt");
            int n = (int) f.length();
            byte b[] = new byte[60000];
            InetAddress dc = ki.getAddress();
            int p = ki.getPort();

            FileInputStream f1 = new FileInputStream("testing.txt");

            f1.read(b);
            System.out.println("Da doc file thanh cong");
            f1.close();
            DatagramPacket output = new DatagramPacket(b, n, dc, p);
            ds.send(output);
            System.out.println("Da gui file thanh cong");

        }

        catch (Exception e) {
            System.out.println("ERROR " + e);
        }
    }
}
