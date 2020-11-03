import java.io.*;
import java.net.*;
import java.util.*;

class PhucVuList extends Thread {
   Socket s;
   
   public PhucVuList(Socket s1) {
      s = s1;
   }

   public void run() {
      try {
         InputStream is = s.getInputStream();
         OutputStream os = s.getOutputStream();
         Scanner sc = new Scanner(is);
         PrintWriter pw = new PrintWriter(os);

         while(true) {
            String caulenh = sc.nextLine();
            String thumuc = caulenh.substring(5);

            if(thumuc.equals("EXIT")) break;
            
            File f = new File(thumuc);
            if( f.isDirectory() && f.exists() ) {
               String kq[] = f.list();
               int n = kq.length;
               pw.println(n);
               pw.flush();

               for( int i = 0; i < n; i++ ) {
                  File f1 = new File(thumuc + "/" + kq[i]);
                  if( f1.isFile() ) {
                     pw.println(kq[i]);
                  }
                  else {
                     pw.println("[" + kq[i] + "]");
                  }
                  pw.flush();
               }

            }
            else {
               pw.println("0");
               pw.flush();
            }
         }
         s.close();
      }
      catch(IOException e) {
         System.out.println("Loi nhap xuat khi phuc vu 1 Client!");
      }
   }
}

public class ListServerSS {
   public static void main(String[] args) {
      try {
         ServerSocket ss = new ServerSocket(18);
         System.out.println("Server da khoi dong!");
         while(true) {
            Socket s1 = ss.accept();
            PhucVuList pv = new PhucVuList(s1);
            pv.start();
         }
      }
      catch(IOException e) {
         System.out.println("Loi nhap xuat");
      }
   }
}
