import java.io.*;
import java.net.*;
import java.util.*;

public class ListClientSS {
   public static void main(String[] args) {
      try {
         Socket s = new Socket("127.0.0.1", 18);

         InputStream is = s.getInputStream();
         OutputStream os = s.getOutputStream();
         Scanner sc = new Scanner(is);
         PrintWriter pw = new PrintWriter(os);
         while(true) {
            Scanner kb = new Scanner(System.in);
            System.out.println("Nhap dia chi: ");
            String thumuc = kb.nextLine();
            String caulenh = "LIST " + thumuc;
            pw.println(caulenh);
            pw.flush();
            if(thumuc.equals("EXIT")) break;

            String str = sc.nextLine();
            int n = Integer.parseInt(str);
            if( n == 0 ) {
               System.out.println("Khong tim thay thu muc " + thumuc);
            }
            else {
               if( n == 1 ) {
                  System.out.println("Thu muc " + thumuc + " rong");
               }
               else {
                  System.out.println("Ket qua: ");
                  System.out.println("Thu muc " + thumuc + " co " + n + " thu muc & tep tin");
                  for( int i = 0; i < n; i++) {
                     String ketqua = sc.nextLine();
                     System.out.println(ketqua);
                  }
               }
            }
         }
         s.close();
      }
      catch(UnknownHostException e) {
         System.out.println("Khong the ket noi voi Server!");
      }
      catch(IOException e) {
         System.out.println("Loi nhap xuat!");
      }
   }
}
