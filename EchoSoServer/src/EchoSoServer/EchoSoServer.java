package EchoSoServer;
import java.io.*;
import java.net.*;

public class EchoSoServer {

	public static void main(String[] args) {
		try {
			ServerSocket ss = new ServerSocket(7000);
			while(true) {
				//chap nhan ket noi tu client
				Socket s = ss.accept();
				// Tao 2 stream in-out
				InputStream is = s.getInputStream();
				OutputStream os = s.getOutputStream();
				while(true) {
					//Nhan 1 ki tu tu client
					int ch = is.read();
					System.in.skip(2);
					//Kiem tra dieu kien de thoat
					if(ch == '@') break;
					//Xu ly yeu cau
					String ketqua = "Khong biet";
					switch(ch) {
						case '0': ketqua = "Khong"; break;
						case '1': ketqua = "Mot"; break;
						case '2': ketqua = "Hai"; break;
						case '3': ketqua = "Ba"; break;
						case '4': ketqua = "Bon"; break;
						case '5': ketqua = "Nam"; break;
						case '6': ketqua = "Sau"; break;
						case '7': ketqua = "Bay"; break;
						case '8': ketqua = "Tam"; break;
						case '9': ketqua = "Chin"; break;
					}
					//Gui ket qua cho client
					byte b[] = ketqua.getBytes();
					os.write(b);
				}
				s.close();
			}
		}
		
		catch(IOException e) {
			System.out.println("Loi nhap xuat");
		}

	}

}
