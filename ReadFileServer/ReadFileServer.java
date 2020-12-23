import java.io.*;
import java.net.*;
import java.util.*;

public class ReadFileServer {
	public static void main(String[] avgs) {
		try {
			ServerSocket ss = new ServerSocket(10);
			System.out.println("Server dang khoi dong");
			while (true) {
				Socket s = ss.accept();

				InputStream is = s.getInputStream();
				OutputStream os = s.getOutputStream();
				Scanner sc = new Scanner(is);
				PrintWriter pw = new PrintWriter(os);

				String caulenh = sc.nextLine();
				String tenfile = caulenh.substring(5);

				File f = new File(tenfile);
				int n = (int) f.length();
				if (f.isFile() && f.exists()) {
					pw.println("" + n);
					pw.flush();
					if (n != 0) {
						byte b[] = new byte[n];
						FileInputStream f1 = new FileInputStream(tenfile);
						DataInputStream dis = new DataInputStream(f1);
						DataOutputStream dos = new DataOutputStream(os);
						dis.readFully(b);
						System.out.println("Da doc file thanh cong");
						f1.close();
						dos.write(b);
						System.out.println("Da gui file thanh cong");
					}
				} else {
					pw.println("-1");
					pw.flush();
				}
				s.close();
			}
		} catch (IOException e) {
			System.out.println("Loi nhap xuat");
		}
	}
}