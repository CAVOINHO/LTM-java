import java.io.*;
import java.net.*;
import java.util.*;

class DocSoClientSS {
   public static void main(String[] args) {
      try {
         Socket s = new Socket("127.0.0.1", 50);

         InputStream is = s.getInputStream();
         OutputStream os = s.getOutputStream();

         while(true) {
            System.out.println("Nhap 1 ky tu so: ");
            int ch = System.in.read();
            System.in.skip(2);
            os.write(ch);
            if(ch == '@') break;

            byte b[] = new byte[100];
            int n = is.read(b);

            String ketqua = new String(b, 0, n);
            System.out.println("Ket qua la: " + ketqua);
         }
         s.close();
      }
      catch(IOException e) {
         System.out.println("Error: " + e);
      }
   }
}