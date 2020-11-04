import java.io.*;
import java.net.*;
import java.util.*;

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
            String ht = sc.nextLine();
            String mssv = sc.nextLine();
            String ten = "Dang Trung Kien";
            String ms = "B1706597";
            String diem = "9,9";

            if( (ht.equals("EXIT")) || (mssv.equals("EXIT")) ) {
               break;
            }

            String ketqua = new String();

            if(ht.equals(ten) && mssv.equals(ms)) {
               ketqua = diem;
            }
            else {
               pw.println("Ho ten hoac MSSV khong ton tai");
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

class DiemServerSS {
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