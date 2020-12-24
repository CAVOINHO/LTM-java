import java.util.Scanner;
import java.net.*;
import java.io.*;

class MulticastFileServer {
	public static void main(String[] args) {
		try {
			// Nhap tu ban phim ten file can gui cho group
			Scanner kb = new Scanner(System.in);
			System.out.print("Nhap ten file: ");
			String tenfile = kb.nextLine();
			// Tao UDP Socket
			DatagramSocket ds = new DatagramSocket();
			// Doc noi dung file
			FileInputStream f = new FileInputStream(tenfile);
			byte b[] = new byte[60000];
			int len = f.read(b); // len la so byte doc duoc
			f.close();
			// Dong goi
			InetAddress dc = InetAddress.getByName("230.10.10.10");
			int p = 1010;
			DatagramPacket goigui = new DatagramPacket(b, len, dc, p);
			while (true) {
				// Gui goi cho nhom dia chi
				ds.send(goigui);
				Thread.sleep(10000);
			}

		} catch (SocketException e) {
			System.out.println("Khong khoi tao duoc UDP Socket");
		} catch (UnknownHostException e) {
			System.out.println("Sai dia chi");
		} catch (FileNotFoundException e) {
			System.out.println("Khong tim duoc file");
		} catch (IOException e) {
			System.out.println("Loi nhap xuat");
		} catch (InterruptedException e) {
			System.out.println("Loi tam dung");
		}
	}
}