import java.util.*;
import java.net.*;
import java.io.*;

public class UDPTimeServer {
   public static void main(String[] args) {
      int port = 8;
      try {
         //Tao UDP Socket
         DatagramSocket ds = new DatagramSocket(port);
         System.out.println("UDP da khoi tao");
         //Nhan yeu cau tu Client
         byte input[] = new byte[60000];
         DatagramPacket ip = new DatagramPacket(input, 60000);
         while(true) {
            ds.receive(ip);
            //Xu ly yeu cau
            Date d = new Date();
            String request = d.toString();
            //Dong goi ket qua
            DatagramPacket op = new DatagramPacket(request.getBytes(), request.length(), ip.getAddress(), ip.getPort());
            //Gui goi tra loi cho Client
            ds.send(op);
         }
      }

      catch(UnknownHostException e) {
         System.out.println("Khong khoi dong duoc UDP cong so " + port);
      }
      
      catch(IOException e) {
         System.out.println("Loi nhap xuat");
      }
   }
}
