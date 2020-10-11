import java.io.*;
import java.util.*;
import java.net.*;

public class ListServer {
   public static void main(String[] args) {
      try {
         ServerSocket ss = new ServerSocket(50);
         System.out.println("Da khoi tao Server");

         Socket s = ss.accept();
         InputStream is = s.getInputStream();
         OutputStream os = s.getOutputStream();
         Scanner sc = new Scanner(is);
         PrintWriter pw = new PrintWriter(os);

         String caulenh = sc.nextLine();
         String thumuc = caulenh.substring(5);
         File f = new File(thumuc);
         if( f.isDirectory() && f.exists() ) {
            String kq[] = f.list();
            int n = kq.length;
            pw.println(n);
            pw.flush();
            for( int i = 0; i < n; i++) {
               File f1 = new File(thumuc + "/" + kq[i]);
               if( f1.isFile() ) {
                  pw.println(kq[i]);
               } 
               else{
                  pw.println("[ " + kq[i] + " ]");
               }
               pw.flush();
            }
         }
         else {
            pw.println("0");
            pw.flush();
         }
      }

      catch(IOException e) {
         System.out.println("Loi nhap xuat");
      }
   }
}
