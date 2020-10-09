import java.io.*;
import java.net.*;
import java.util.*;

class PhucVuTachTen extends Thread {
   Socket s;
   
   public PhucVuTachTen(Socket s1) {
      s = s1;
   }

   private String TachTen(String hoTen) {
      String HT = hoTen.trim();
      int i = HT.lastIndexOf(" ");
      String ten = HT.substring(i + 1);
      return ten;
   }
   
   public void run() {
      try {
         InputStream is = s.getInputStream();
         OutputStream os = s.getOutputStream();
         
         Scanner sc = new Scanner(is);
         PrintWriter pw = new PrintWriter(os);
         
         while(true) {
            String str = sc.nextLine();
            
            if(str.equals("EXIT")) break;
            
            String ketqua = new String();
            ketqua = TachTen(str);

            pw.println(ketqua);
            pw.flush();
         }
         s.close(); 
      }
      catch(IOException e) {
         System.out.println("Loi nhap xuat khi phuc vu 1 Client");
      }
   }
}

class TachTenServer {
	public static void main(String[] args) {
		
		try {
			ServerSocket ss = new ServerSocket(40);
			System.out.println("Da khoi tao Server");
			
			while(true) {
				Socket s1 = ss.accept();
            PhucVuTachTen pv = new PhucVuTachTen(s1);
            pv.start();
			}
	   }
		
		catch(IOException e) {
			System.out.println("Loi nhap xuat");
		}
	}

}
