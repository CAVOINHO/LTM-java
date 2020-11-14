import java.io.*;
import java.net.*;
import java.util.*;

public class UDPTimeClient {
   public static void main(String[] args) {
      try {
         //Tao UDP Socket
         DatagramSocket ds = new DatagramSocket();
         Scanner kb = new Scanner(System.in);
         while(true) {
            //Nhap mot chuoi
            System.out.print("Nhap mot chuoi: ");
            String str = kb.nextLine();
            //dieu kien thoat
            if(str.equals("EXIT")) break;
            //Dong goi chuoi
            byte outputFile[] = str.getBytes();
            int len = outputFile.length;
            InetAddress dc = InetAddress.getByName("127.0.0.1");
            int port = 8;
            // Dong goi 
            DatagramPacket output = new DatagramPacket(outputFile, len, dc, port);
            //Gui goi qua Server
            ds.send(output);
            //Nhan goi tra loi tu Server
            byte inputFile[] = new byte[60000];
            DatagramPacket input = new DatagramPacket(inputFile, 60000);
            //Nhan goi tra loi
            ds.receive(input);
            //Lay du lieu tu goi va hien thi
            String ketqua = new String(input.getData(), 0, input.getLength());
            System.out.println("Thoi gian hien tai: " + ketqua);
         }
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
