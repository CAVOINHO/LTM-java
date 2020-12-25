import java.io.*;
import java.util.*;
import java.net.*;

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
            int n = b.length;
            InetAddress dc = InetAddress.getByName(diachi);
            DatagramPacket local = new DatagramPacket(b, n, dc, congUDP);
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
            System.out.println("Cong cua TCP Socket: " + congTCP);
            System.out.println("Mat khau: " + matkhau);

            Socket s = new Socket(diachi, congTCP);
            InputStream is = s.getInputStream();
            OutputStream os = s.getOutputStream();
            Scanner sc = new Scanner(is);
            PrintWriter pw = new PrintWriter(os, true);

            pw.println(matkhau);

            String input3 = sc.nextLine();
            if( input3.equals("ERR")) {
                System.out.println("SAI MAT KHAU");
            }
            else {
                int lent = Integer.parseInt(input3);
                System.out.println("File co kich thuoc: " + lent);
                FileOutputStream f = new FileOutputStream("ketqua.txt");
                byte b1[] = new byte[lent];
                DataInputStream dis = new DataInputStream(is);
                dis.readFully(b1);
                f.write(b1);
                f.close();
                System.out.println("Da ghi file thanh cong");
            }
            s.close();
            ds.close();
            
        } catch (Exception e) {
            System.out.println("ERROR: " + e);
        }
    }
}






    

    
    
        
    



    

    
    
        
    



    

    
    
        
    

