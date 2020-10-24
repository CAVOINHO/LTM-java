import java.io.*;
import java.util.*;
import java.net.*;

public class ReadFileClient {
	public static void main(String[] args) {
		try {
			Socket s = new Socket("127.0.0.1", 10);
			
			InputStream is = s.getInputStream();
			OutputStream os = s.getOutputStream();
			Scanner sc = new Scanner(is);
			PrintWriter pw = new PrintWriter(os);
			
			Scanner kb = new Scanner(System.in);
			System.out.println("Nhap ten file can doc: ");
			String tenfile = kb.nextLine();
			
			System.out.println("Nhap ten file de luu: ");
			String fileghi = kb.nextLine();
			
			String caulenh = "READ " + tenfile;
			
			pw.println(caulenh);
			pw.flush();
			
			String ketqua = sc.nextLine();
			int n = Integer.parseInt(ketqua);
			if( n == -1 ) {
				System.out.println("File khong ton tai!");
			}
			else {
				if( n == 0 ) {
					System.out.println("File rong!");
				}
				else {
					FileOutputStream f = new FileOutputStream(fileghi);
					byte b[] = new byte[n];
					System.out.println("Kich thuoc file " + n);
					DataInputStream dis = new DataInputStream(is);
					dis.readFully(b);
					f.write(b);
					f.close();
					System.out.println("Da ghi file thanh cong");
				}
			}
			s.close();
			
		}
		catch(IOException e) {
			System.out.println("Loi nhap xuat!");
		}
	}
}