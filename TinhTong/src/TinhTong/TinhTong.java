package TinhTong;
import java.util.*;

public class TinhTong {
	public static void main(String[] args) {
		int a = 0, b = 0;
		while(true) {
			try {
				Scanner kb =  new Scanner(System.in);
				System.out.print("Nhap a: ");
				a = kb.nextInt();
				System.out.print("Nhap b: ");
				b = kb.nextInt();
				break;
			}
			catch(InputMismatchException e) {
				System.out.print("Nhap sai -  nhap lai");
			}
		}
		
		int tong = a + b;
		System.out.print("Tong = " + tong);

	}

}
