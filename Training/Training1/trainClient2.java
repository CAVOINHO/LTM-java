import java.util.*;

import javax.security.auth.kerberos.KeyTab;

import java.net.*;
import java.io.*;

public class trainClient {
    public static void main(String[] args) {
        try {
            // 1. Nhập địa chỉ 1 Server và cổng UDP Server từ bàn phím
            Scanner kb = new Scanner(System.in);
            System.out.print("Nhap dia chi cua 1 Server: ");
            String diachi = kb.nextLine();
            System.out.print("Nhap cong cua UDP Server: ");
            int congUDP = Integer.parseInt(kb.nextLine());

            // 2. Tao UDP Socket
            DatagramSocket ds = new DatagramSocket();

            // 3. Gui goi tin UDP den Server
            System.out.print("Nhap du lieu cho goi tin: ");
            String dataPacket = kb.nextLine();
            byte outputFile[] = dataPacket.getBytes();
            int len = outputFile.length;
            InetAddress dc = InetAddress.getByName(diachi);
            DatagramPacket output = new DatagramPacket(outputFile, len, dc, congUDP);
            ds.send(output);

            // 4, 5 Nhan tu UDP Server
            // Nhan cong
            byte portByte[] = new byte[60000];
            DatagramPacket portPacket = new DatagramPacket(portByte, 60000);
            ds.receive(portPacket);
            String portStr = new String(portPacket.getData(), 0, portPacket.getLength());
            int congTCP = Integer.parseInt(portStr);

            // Nhan mat khau
            byte passwordByte[] = new byte[60000];
            DatagramPacket passwordPacket = new DatagramPacket(passwordByte, 60000);
            ds.receive(passwordPacket);
            String passwordStr = new String(passwordPacket.getData(), 0, passwordPacket.getLength());

            // 6. Noi ket TCP
            Socket s = new Socket(diachi, congTCP);
            InputStream is = s.getInputStream();
            OutputStream os = s.getOutputStream();
            // Scanner sc = new Scanner(is);
            PrintWriter pw = new PrintWriter(os, true);

            // 7.Gui mat khau qua Server
            pw.println(passwordStr);

            // Nhan du lieu tu Server
            byte[] bb = new byte[60000];
            int n = is.read(bb);
            String kq1 = new String(bb, 0, n);

            if (kq1.replaceAll("[\\n\\r]", "").equals("ERR")) {
                System.out.println("Mat khau sai !");
            }

            else {
                // Ghi vao file
                FileOutputStream f = new FileOutputStream("ketqua1.txt");
                f.write(bb, 0, n);
                System.out.println("Da ghi file thanh cong");
                f.close();
            }
            s.close();
        }

        catch (Exception e) {
            System.out.println("ERROR: " + e);
        }
    }
}
