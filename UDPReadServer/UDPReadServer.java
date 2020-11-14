import java.util.*;
import java.net.*;
import java.io.*;

public class UDPReadServer {
   public static void main(String[] args) {
      try {
         //Tao UDP Socket
         DatagramSocket ds = new DatagramSocket(9);
         System.out.print("Da khoi tao UDP");
         //Nhan goi tu Client
         byte input[] = new byte[60000];
         DatagramPacket ip = new DatagramPacket(input, 60000);
         ds.receive(ip);
         //Xu ly yeu cau
         byte input2[] = ip.getData();
         int len = ip.getLength();
         String command = new String(input2, 0, len);
         String fileName = command.substring(8);
         //Doc file
         File f = new File(fileName);
         int len2 = (int)f.length();
         InetAddress dc = ip.getAddress();
         int port = ip.getPort();
         if( f.exists() && f.isFile() && len2 > 0 ) {
            byte output[] = new byte[len2];
            FileInputStream f1 = new FileInputStream(fileName);
            f1.read(output);
            f1.close();
            DatagramPacket op = new DatagramPacket(output, len2, dc, port);
            ds.send(op);
         }
         else {
            byte output[] = new byte[len2];
            DatagramPacket op = new DatagramPacket(output, 0, dc, port);
            ds.send(op);
         }
      }
   
      catch(SocketException e) {
         System.out.println("Khong khoi duoc UDP Server cong 7");
      }
   
      catch(FileNotFoundException e) {
         System.out.println("Khong tim thay file yeu cau");
      }
   
      catch(IOException e) {
         System.out.println(e);
      }
   }
}
