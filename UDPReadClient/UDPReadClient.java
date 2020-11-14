import java.util.*;
import java.net.*;
import java.io.*;

public class UDPReadClient {
   public static void main(String[] args) {
      try {
         //Tao UDP Socket
         DatagramSocket ds = new DatagramSocket();
         //Nhap ten file can lay ten Server
         Scanner kb = new Scanner(System.in);
         System.out.print("Nhap ten file can thao tac: ");
         String fileName = kb.nextLine();
         //Nhap ten file can ghi
         System.out.print("Nhap ten file muon luu: ");
         String fileNameRead = kb.nextLine();
         //Dong goi
         String command = "READUDP " + fileName;
         byte output[] = command.getBytes();
         int len = output.length;
         InetAddress dc = InetAddress.getByName("127.0.0.1");
         int port = 9;
         DatagramPacket op = new DatagramPacket(output, len, dc, port);
         //gui goi cho Server
         ds.send(op);
         //Nhan goi tra ve tu Server
         byte input[] = new byte[60000];
         DatagramPacket ip = new DatagramPacket(input, 60000);
         ds.receive(ip);
         //Lay du lieu tu goi tra ve
         int length = ip.getLength();
         if( length == 0 ) {
            System.out.println("File khong ton tai hoac file rong");
         }
         else {
            byte inputF[] = ip.getData();
            FileOutputStream f = new FileOutputStream(fileNameRead);
            f.write(inputF, 0, length);
            f.close();
            System.out.println("Da ghi file thanh cong!");
         }
         ds.close();
      }

      catch(SocketException e) {
         System.out.println("Khong khoi tao duoc Socket");
      }

      catch(UnknownHostException e) {
         System.out.println("Sai dia chi");
      }

      catch(IOException e) {
         System.out.println("Loi nhap xuat");
      }
   }
}
