import java.io.IOException;
import java.io.*;
import java.util.*;
import java.net.*;

public class testClientSS {
   public static void main(String[] args) {
      try {
         Socket s = new Socket("127.0.0.1", 24);
         InputStream is = s.getInputStream();
         OutputStream os = s.getOutputStream();
         Scanner sc = new Scanner(is);
         PrintWriter pw = new PrintWriter(os);

         while(true) {
            Scanner kb = new Scanner(System.in);
            System.out.println("Nhap so dien thoai: ");
            String numberPhone = kb.nextLine();
            System.out.print("Nhap mat khau: ");
            String password = kb.nextLine();
            
            pw.println(numberPhone);
            pw.println(password);
            pw.flush();

            if(numberPhone.equals("EXIT")) {
					break;
            }
            
            String ketqua = sc.nextLine();
            System.out.println(ketqua + "!");
         }

         s.close();
      }
      catch(IOException e) {
         System.out.println("Error " + e);
      }
   }
}
