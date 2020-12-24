import java.util.Scanner;
import java.net.*;
import java.io.*;
class MulticastFileClient {
	public static void main(String[] args) {
		try {
			// Nhap ten file can luu
			Scanner kb = new Scanner(System.in);
			System.out.print("Nhap ten file can luu: ");
			String tenfile = kb.nextLine();
			// Tao Multicast Socet cong 1010
			MulticastSocket ms = new MulticastSocket(1010);
			// Tham gia vao nhom dia chi 230.10.10.10
			InetAddress dc = InetAddress.getByName("230.10.10.10");
			ms.joinGroup(dc);
			// Nhan goi
			byte b[] = new byte[60000];
			DatagramPacket goinhan = new DatagramPacket(b,60000);
			ms.receive(goinhan);
			// Luu file
			byte b1[] = goinhan.getData();
			int len1 = goinhan.getLength();
			FileOutputStream f = new FileOutputStream(tenfile);
			f.write(b1,0,len1);
			f.close();
			System.out.println("Da ghi file thanh cong");
		}
		catch(UnknownHostException e) {
			System.out.println("Sai dia chi");
		}
		catch(FileNotFoundException e) {
			System.out.println("Sai duong dan file");
		}
		catch(IOException e) {
			System.out.println("Loi nhap xuat");
		}
	}
}