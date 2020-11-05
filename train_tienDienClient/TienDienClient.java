import java.io.*;
import java.net.*;
import java.util.*;

class TienDienClient {
   public static void main(String[] args) {
      try {
         Socket s = new Socket("127.0.0.1", 28);

         InputStream is = s.getInputStream();
         OutputStream os = s.getOutputStream();
         Scanner sc = new Scanner(is);
         PrintWriter pw = new PrintWriter(os);

         while(true) {
            Scanner kb = new Scanner(System.in);
            System.out.println("Vui long nhap thang: ");
            String month = kb.nextLine();
            pw.println(month);
            pw.flush();

            if(month.equals("EXIT")) {
               break;
            }

            String ketqua = sc.nextLine();
            System.out.println(ketqua);
         }
         s.close();
      }
      catch(IOException e) {
         System.out.println("Error :" + e);
      }
   }
}