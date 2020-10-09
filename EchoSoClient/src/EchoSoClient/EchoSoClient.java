package EchoSoClient;
import java.io.*;
import java.net.*;

public class EchoSoClient {

	public static void main(String[] args) {
		try {
			Socket s = new Socket("127.0.0.1", 7000);
			//Lay 2 stream in-out;
			InputStream is = s.getInputStream();
			OutputStream os = s.getOutputStream();
			while(true) {
				System.out.println("Nhap vao mot ky tu so: ");
				int ch = System.in.read();
				System.in.skip(2);
				//Gui ky tu qua server
				os.write(ch);
				//Kiem tra dieu kien de thoat
				if(ch == '@') break;
				//Nhan ket qua tra ve tu Server
				byte b[] = new byte[100];
				int n = is.read(b);
				//hien thi ket qua
				String ketqua = new String(b, 0, n);
				System.out.println("Nhan ve: " + ketqua);
			}
			s.close();
		}
		
		catch(UnknownHostException e) {
			System.out.println("Khong the ket noi Server");
		}
		
		catch(IOException e) {
			System.out.println("Loi nhap xuat");
		}
	}

}
