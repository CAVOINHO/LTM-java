import java.io.*;
import java.net.*;
import java.util.*;

class PhucVuTienDien extends Thread {
   Socket s;

   public PhucVuTienDien(Socket s1) {
      s = s1;
   }

   public void run() {
      try {
         InputStream is = s.getInputStream();
         OutputStream os = s.getOutputStream();
         Scanner sc = new Scanner(is);
         PrintWriter pw = new PrintWriter(os);

         while(true) {
            String month = sc.nextLine();

            if(month.equals("EXIT")) {
               break;
            }

            String thang1 = "100.000";
            String thang2 = "200.000";
            String thang3 = "300.000";
            String thang4 = "400.000";
            String thang5 = "500.000";
            String thang6 = "600.000";
            String thang7 = "700.000";
            String thang8 = "800.000";
            String thang9 = "900.000";
            String thang10 = "1.000.000";
            String thang11 = "1.100.000";
            String thang12 = "1.200.000";

            String ketqua = new String();

            switch(month) {
               case "1" : ketqua = "Tien dien thang " + month + ": " + thang1; break;
               case "2" : ketqua = "Tien dien thang " + month + ": " + thang2; break;
               case "3" : ketqua = "Tien dien thang " + month + ": " + thang3; break;
               case "4" : ketqua = "Tien dien thang " + month + ": " + thang4; break;
               case "5" : ketqua = "Tien dien thang " + month + ": " + thang5; break;
               case "6" : ketqua = "Tien dien thang " + month + ": " + thang6; break;
               case "7" : ketqua = "Tien dien thang " + month + ": " + thang7; break;
               case "8" : ketqua = "Tien dien thang " + month + ": " + thang8; break;
               case "9" : ketqua = "Tien dien thang " + month + ": " + thang9; break;
               case "10" : ketqua = "Tien dien thang " + month + ": " + thang10; break;
               case "11" : ketqua = "Tien dien thang " + month + ": " + thang11; break;
               case "12" : ketqua = "Tien dien thang " + month + ": " + thang12; break;
            }

            pw.println(ketqua);
            pw.flush();
         }
         s.close();
      }
      catch(IOException e) {
         System.out.println("Error :" + e);
      }
   }
}

class TienDienServer {
   public static void main(String[] args) {
      try {
         ServerSocket ss = new ServerSocket(28);
         System.out.println("Da khoi tao Server");
         while(true) {
            Socket s = ss.accept();
            PhucVuTienDien pv = new PhucVuTienDien(s);
            pv.start();
         }
      }
      catch(IOException e) {
         System.out.println("Error: " + e);
      }
   }
}