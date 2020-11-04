import java.io.*;
import java.net.*;
import java.util.*;
import java.lang.ClassNotFoundException;

class PhucVuDiem extends Thread {
   Socket s;

   public PhucVuDiem(Socket s1) {
      s = s1;
   }

   public void run() {
      try {
         InputStream is = s.getInputStream();
         OutputStream os = s.getOutputStream();
         Scanner sc = new Scanner(is);
         PrintWriter pw = new PrintWriter(os);

         while(true) {
            String str = sc.nextLine();

            String hp = "CT221";
            String ms = "B1706597";
            String diem = "9,9";

            if( str.equals("EXIT")) {
               break;
            }

            String ketqua = new String();

            String ch = str.trim();
            int n = ch.lastIndexOf(" ");
            String amssv = str.substring(0, n);
            String bmahp = str.substring(n + 1);

            if( amssv.equals(ms) && bmahp.equals(hp) ) {
               ketqua = amssv + " | " + diem;
            }
            else {
               System.out.println("MSSV hoac ma hoc phan khong dung");
            }
            pw.println(ketqua);
            pw.flush();
         }
         s.close();
      }
      catch(IOException e) {
         System.out.println("Error: " + e);
      }
   }
}

class DiemServer {
   public static void main(String[] args) {
      try {
         ServerSocket ss = new ServerSocket(4);
         System.out.println("Da khoi tao Server");
         while(true) {
            Socket s = ss.accept();
            PhucVuDiem pv = new PhucVuDiem(s);
            pv.start();
         }
      }
      catch(IOException e) {
         System.out.println("Error: " + e);
      }
   }
}