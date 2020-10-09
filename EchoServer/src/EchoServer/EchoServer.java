package EchoServer;
import java.io.*;
import java.net.*;

public class EchoServer {

	public static void main(String[] args) {
		try {
			// Tao server socket lang nghe tren cong 7
			ServerSocket ss =  new ServerSocket(7);
			System.out.println("Da khoi tao xong Server Socket");
			while(true) {
				//Phuc vu cho nhieu client
				//Chap nhan cho client ket noi
				Socket s = ss.accept();
				System.out.println("Co client " + s.getInetAddress() + " - cong: " + s.getPort() + " noi ket");
				//Lay ra 2 stream in-out
				InputStream is = s.getInputStream();
				OutputStream os = s.getOutputStream();
				
				while(true) {
					//Phuc vu cho 1 client nhieu lan
					//Nhan yeu cau tu client (1 ki tu ch) 
					int ch = is.read();
					System.out.println("Nhan tu client: " + ch);
					//Kiem tra dieu kien de thoat
					if(ch == '@') break;
					//Xu ly yeu cau
					int ch1 = ch;
					//Gui ket qua cho client (1 ky tu ch1)
					os.write(ch1);
				}
				//Dong noi ket
				System.out.println("-----Dong noi ket voi client " + s.getInetAddress() + " - cong: " + s.getPort());
				s.close();
			}
		}
		catch(IOException e) {
			System.out.println("Co loi nhap xuat");
		}
 	}

}
