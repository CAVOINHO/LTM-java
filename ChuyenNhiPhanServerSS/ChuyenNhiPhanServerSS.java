import java.io.*;
import java.net.*;
import java.util.*;

class PhucVuNhiPhan extends Thread {
   Socket s;

   public PhucVuNhiPhan(Socket s1) {
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
            
            if(str.equals("EXIT")) break;
            
            String ketqua = new String();
            
            try {
               int n = Integer.parseInt(str);
               ketqua = Integer.toBinaryString(n);
            }
            
            catch(NumberFormatException e) {
               ketqua = "Khong phai so nguyen";
            }
            
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

class ChuyenNhiPhanServer {
	public static void main(String[] args) {
		
		try {
			ServerSocket ss = new ServerSocket(30);
			System.out.println("Da khoi tao Server");
			
			while(true) {
				Socket s1 = ss.accept();
            PhucVuNhiPhan pv = new PhucVuNhiPhan(s1);
            pv.start();
			}
	}
		
		catch(IOException e) {
			System.out.println("Loi nhap xuat");
		}
	}

}
