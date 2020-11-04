import java.net.*;
import java.io.*;
import java.util.*;

class PhucVuDocSo extends Thread {
   Socket s;

   public PhucVuDocSo(Socket s1) {
      s = s1;
   }

   public void run() {
      try {
         InputStream is = s.getInputStream();
         OutputStream os = s.getOutputStream();

         while(true) {
            int ch = is.read();
            if(ch == '@') break;
            String ketqua = "Khong biet";
            switch(ch) {
               case '0': ketqua = "Khong"; break;
               case '1': ketqua = "Mot"; break;
               case '2': ketqua = "Hai"; break;
               case '3': ketqua = "Ba"; break;
               case '4': ketqua = "Bon"; break;
               case '5': ketqua = "Nam"; break;
               case '6': ketqua = "Sau"; break;
               case '7': ketqua = "Bay"; break;
               case '8': ketqua = "Tam"; break;
               case '9': ketqua = "Chin"; break;
            }
            byte b[] = ketqua.getBytes();
            os.write(b);
         }
         s.close();
      }
      catch(IOException e) {
         System.out.println("Error: " + e);
      }
   }
}

class DocSoServerSS {
   public static void main(String[] args) {
      try {
         ServerSocket ss = new ServerSocket(50);
         System.out.println("Da khoi tao Server");
         while(true) {
            Socket s = ss.accept();
            PhucVuDocSo pv = new PhucVuDocSo(s);
            pv.start();
         }
      }
      catch(IOException e) {
         System.out.println("Error: " + e);
      }
   }
}