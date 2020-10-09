package ChuyenNhiPhanClien;
import java.io.*;
import java.net.*;
import java.util.*;

public class ChuyenNhiPhanClien {

	public static void main(String[] args) {
		
		try {
			Socket s = new Socket("127.0.0.1", 30);
			
			InputStream is = s.getInputStream();
			OutputStream os = s.getOutputStream();
			
			Scanner sc = new Scanner(is);
			PrintWriter pw =  new PrintWriter(os);
			
			while(true) {
				Scanner kb = new Scanner(System.in);
				System.out.print("Nhap chuoi: ");
				String str = kb.nextLine();
				
				pw.println(str);
				pw.flush();
				
				if(str.equals("EXIT")) {
					break;
				}
				
				String ketqua = sc.nextLine();
				System.out.println("Chuoi nhi phan : " + ketqua);
			}
			s.close();
		}
		
		catch(UnknownHostException e) {
			System.out.println("Khong the ket noi voi Server");
		}
		
		catch(IOException e) {
			System.out.println("Loi nhap xuat");
		}
		

	}

}
