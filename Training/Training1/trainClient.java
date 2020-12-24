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
            System.out.print("Nhap ho va ten: ");
            String hoten = kb.nextLine();
            byte outputFile[] = hoten.getBytes();
            int len = outputFile.length;
            InetAddress dc = InetAddress.getByName(diachi);
            DatagramPacket output = new DatagramPacket(outputFile, len, dc, congUDP);
            ds.send(output);

            // 4, 5. Nhan tu UDP Server
            byte inputFile1[] = new byte[60000];
            byte inputFile2[] = new byte[60000];
            DatagramPacket input1 = new DatagramPacket(inputFile1, 60000);
            DatagramPacket input2 = new DatagramPacket(inputFile2, 60000);
            ds.receive(input1);
            ds.receive(input2);
            String ketqua1 = new String(input1.getData(), 0, input1.getLength());
            String ketqua2 = new String(input2.getData(), 0, input2.getLength());
            int congTCP = Integer.parseInt(ketqua1);
            String matkhau = ketqua2.toString();
            System.out.println("Cong cua TCP Socket: " + congTCP);
            System.out.println("Mat khau: " + matkhau);

            // 6. Noi ket TCP
            Socket s = new Socket(diachi, congTCP);
            InputStream is = s.getInputStream();
            OutputStream os = s.getOutputStream();
            Scanner sc = new Scanner(is);
            PrintWriter pw = new PrintWriter(os, true);

            // 7.Gui mat khau qua Server
            String a = "123";
            pw.println(a);

            // 8.9.10. Nhan ket qua tu Server
            String ketquaTCP = sc.nextLine();
            if (ketquaTCP.equals("ERR")) {
                System.out.println("Mat khau sai!");
            } else {
                int n = Integer.parseInt(ketquaTCP);
                FileOutputStream f = new FileOutputStream("ketqua.txt");
                byte b[] = new byte[n];
                System.out.println("Kich thuoc file " + n);
                DataInputStream dis = new DataInputStream(is);
                dis.readFully(b);
                f.write(b);
                f.close();
                System.out.println("Da ghi file thanh cong");
            }
            s.close();
        }

        catch (Exception e) {
            System.out.println("ERROR: " + e);
        }
    }
}
