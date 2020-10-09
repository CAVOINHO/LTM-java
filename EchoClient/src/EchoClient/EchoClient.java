package EchoClient;
import java.io.*;
import java.net.*;

public class EchoClient {

	public static void main(String[] args) {
		try {
			//Noi ket voi server cong 7
			Socket s = new Socket("127.0.0.1", 7);
			//Lay ra 2 stream in-out
			InputStream is = s.getInputStream();
			OutputStream os = s.getOutputStream();
			while(true) {
				//Nhap 1 ky tu nhieu ban phim
				System.out.println("Nhap 1 ky tu: ");
				int ch = System.in.read();
				System.in.skip(2); // Bo qua 2 ki tu \r\n
				//Gui ky tu qua server
				os.write(ch);
				//Kiem tra dieu kien de thoat
				if(ch == '@') break;
				//Nhan ket qua tra ve tu Server
				int ch1 = is.read();
				//Hien thi ket qua
				System.out.println("Nhan duoc: " + (char)ch1);
			}
			//Dong noi ket
			s.close();
		}
		
		catch(UnknownHostException e) {
			System.out.println("Khong noi ket duoc voi Server");
		}
		
		catch(IOException e) {
			System.out.println("Co loi nhap xuat");
		}
	}

}
