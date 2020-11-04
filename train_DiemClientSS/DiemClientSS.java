import java.io.*;
import java.net.*;
import java.util.*;

class DiemClientSS {
   public static void main(String[] args) {
      try {
         Socket s = new Socket("127.0.0.1", 4);

         InputStream is = s.getInputStream();
         OutputStream os = s.getOutputStream();
         Scanner sc = new Scanner(is);
         PrintWriter pw = new PrintWriter(os);

         while(true) {
            Scanner kb = new Scanner(System.in);
            System.out.println("Nhap ho va ten: ");
            String ht = kb.nextLine();
            System.out.println("Nhap MSSV: ");
            String mssv = kb.nextLine();
            pw.println(ht);
            pw.println(mssv);
            pw.flush();

            if(ht.equals("EXIT") || mssv.equals("EXIT")) {
               break;
            }

            String ketqua= sc.nextLine();
            System.out.println("Ket qua la: " + ketqua);
         }
         s.close();
      }

      catch(UnknownHostException e) {
         System.out.println("Error: " + e);
      }

      catch(IOException e) {
         System.out.println("Error: " + e);
      }
   }
}