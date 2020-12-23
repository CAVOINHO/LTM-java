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
                String congTCP = "9";
                String matkhau = "kien123";
                // Gui cho Client
                DatagramPacket output1 = new DatagramPacket(congTCP.getBytes(), congTCP.length(), ip.getAddress(),
                        ip.getPort());
                DatagramPacket output2 = new DatagramPacket(matkhau.getBytes(), matkhau.length(), ip.getAddress(),
                        ip.getPort());
                ds.send(output1);
                ds.send(output2);

                ServerSocket ss = new ServerSocket(9);
                System.out.println("Da khoi tao TCP Server");
                Socket s = ss.accept();
                InputStream is = s.getInputStream();
                OutputStream os = s.getOutputStream();
                Scanner sc = new Scanner(is);
                PrintWriter pw = new PrintWriter(os);
                // Nhan tu client
                String pass = sc.nextLine();
                System.out.println("Mat khau la: " + pass);
                // Xu ly
                if (pass.equals(matkhau)) {
                    File f = new File("testing.txt");
                    int n = (int) f.length();
                    System.out.println("kich thuoc: " + n);
                    pw.println("" + n);
                    pw.flush();
                    byte b[] = new byte[n];
                    FileInputStream f1 = new FileInputStream("testing.txt");
                    DataInputStream dis = new DataInputStream(f1);
                    DataOutputStream dos = new DataOutputStream(os);
                    dis.readFully(b);
                    System.out.println("Da doc file thanh cong");
                    f1.close();
                    dos.write(b);
                    System.out.println("Da gui file thanh cong");
                } else {
                    pw.println("ERR");
                    pw.flush();
                }
                s.close();
            }
        }

        catch (Exception e) {
            System.out.println("ERROR " + e);
        }
    }
}
