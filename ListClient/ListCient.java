import java.io.*;
import java.util.*;
import javax.print.attribute.standard.PrinterInfo;
import java.net.*;

public class ListCient {
   public static void main(String[] args) {
      try {
         Socket s = new Socket("127.0.0.1", 50);

         InputStream is = s.getInputStream();
         OutputStream os = s.getOutputStream();
         Scanner sc = new Scanner(is);
         PrintWriter pw = new PrintWriter(os);

         Scanner kb = new Scanner(System.in);
         System.out.println("Nhap dia thu muc: ");
         String diachi = kb.nextLine();
         String caulenh = "LIST " + diachi;
         pw.println(caulenh);
         pw.flush();

         String str = sc.nextLine();
         int n = Integer.parseInt(str);
         if(n == 0) {
            System.out.println("Thu muc " + diachi + " khong ton tai!");
         }
         else {
            if(n == 1) {
               System.out.println("Thu muc " + diachi + " rong!");
            }
            else {
               System.out.println("Ket qua: ");
               System.out.println("Thu muc " + diachi + " co " + n + " thu muc va tap tin");
               for(int i = 0; i < n; i++) {
                  String ketqua = sc.nextLine();
                  System.out.println(ketqua);
               }
            }
         }
      }

      catch(UnknownHostException e) {
         System.out.println("Khong the ket noi voi Server");
      }

      catch(IOException e) {
         System.out.println("Loi nhap xuat");
      }
   }
}
