import java.util.*;
import java.net.*;
import java.io.*;

public class trainClient {
    public static void main(String[] args) {
        Scanner kb = new Scanner(System.in);
        System.out.print("Nhap dia chi cua 1 Server: ");
        String diachi = kb.nextLine();
        System.out.print("Nhap cong cua UDP Server: ");
        int congUDP = Integer.parseInt(kb.nextLine());
        System.out.print("Nhap cong cua TCP Server: ");
        int congTCP = Integer.parseInt(kb.nextLine());

        try {
            DatagramSocket ds = new DatagramSocket();

            System.out.print("Nhap ho va ten: ");
            String hoten = kb.nextLine();
            byte outputFile[] = hoten.getBytes();
            int len = outputFile.length;
            InetAddress dc = InetAddress.getByName(diachi);
            DatagramPacket output = new DatagramPacket(outputFile, len, dc, congUDP);
            ds.send(output);

            byte intputFile[] = new byte[60000];
            DatagramPacket matkhau = new DatagramPacket(intputFile, 60000);
            ds.receive(matkhau);

            Socket s = new Socket(diachi, congTCP);
            InputStream is = s.getInputStream();
            OutputStream os = s.getOutputStream();
            Scanner sc = new Scanner(is);
            PrintWriter pw = new PrintWriter(os, true);

            String localClient = InetAddress.getLocalHost().getHostAddress();
            pw.println(localClient);

            String pass = new String(matkhau.getData(),0,matkhau.getLength());
            String passReverse = new StringBuilder(pass).reverse().toString();

            pw.println(passReverse);

            String kq = sc.nextLine();

            if(kq.equals("ERR")) {
                System.out.println("Chuoi sai!");
            }
            else {
                System.out.println("Chuoi dung!");
            }
            s.close();
        }

        catch(Exception e) {
            System.out.println("ERROR: " + e);
        }

    }
}
