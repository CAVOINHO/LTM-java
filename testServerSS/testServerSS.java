import java.io.*;
import java.util.*;
import java.net.*;

class PhucVu extends Thread {
   Socket s;

   public PhucVu(Socket s1) {
      s = s1;
   }

   public static boolean isNumeric(String strNum) {
      if (strNum == null) {
         return false;
      }
      try {
         double d = Double.parseDouble(strNum);
      } catch (NumberFormatException nfe) {
         return false;
      }
      return true;
   }

   public boolean check(String str) {
      try {
         if(isNumeric(numberPhone) && isNumeric(password)) {
            return true;
         }
         else {
            return false;
         }
      }
      return true;
   }

   public void run() {
      try {
         InputStream is = s.getInputStream();
         OutputStream os = s.getOutputStream();
         Scanner sc = new Scanner(is);
         PrintWriter pw = new PrintWriter(os, true);

         while(true) {
            String numberPhone = sc.nextLine();
            String password = sc.nextLine();

            if(numberPhone.equals("EXIT") || password.equals("EXIT")) break;

            String ketqua = new String();
            if(ketqua.check(numberPhone, password)){
               System.out.println("Ket noi thanh cong");
            }
            else {
               System.out.println("Ket noi that bai");
            }
            pw.println(ketqua);
         }
         s.close();
      }
      catch(IOException e){
         System.out.println("Loi nhap xuat khi phuc vu mot client");
      }
   }
}

public class testServerSS {
   public static void main(String[] args) {
      try {
         ServerSocket ss = new ServerSocket(24);
         System.out.println("Da khoi tao Server");

         while(true) {
            Socket s1 = ss.accept();
            PhucVu pv = new PhucVu(s1);
            pv.start();
         }
      }
      catch(IOException e) {
         System.out.println("Loi nhap xuat");
      }
   }
}
