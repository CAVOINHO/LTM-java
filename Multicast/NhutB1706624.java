//Luu Tran Anh Nhut
//Ma So sinh vien: B1706624
//So may: 30
//De: 02
import java.util.*;
import java.net.*;
import java.io.*;
public class NhutB1706624 {
    public static void main(String[] args) {
        Scanner kb = new Scanner(System.in);
        System.out.println("Nhap dia chi server: ");
        String diachi = kb.nextLine();
        System.out.println("Nhap cong UDP: ");
        int congUDP = kb.nextInt();
        System.out.println("Nhap cong TCP: ");
        int congTCP = kb.nextInt();
        try {
            DatagramSocket ds = new DatagramSocket();
            
            File f = new File("data.tk");                       
            int lenfile = (int)f.length();
            byte b[] = new byte[lenfile];
            FileInputStream f1 = new FileInputStream("data.tk");
            DataInputStream dis = new DataInputStream(f1);
            dis.readFully(b);
            f1.close();
            
            DatagramPacket goigui = new DatagramPacket(b,b.length,InetAddress.getByName(diachi),congUDP);
            ds.send(goigui);
            
            byte b1[] = new byte[60000];
            DatagramPacket goinhan = new DatagramPacket(b1,b1.length);
            ds.receive(goinhan);
            
            //System.out.println("Password " + new String(goinhan.getData(),0,goinhan.getLength()));
            
            Socket s = new Socket(diachi,congTCP);
            InputStream is = s.getInputStream();
            OutputStream os = s.getOutputStream();
            
            Scanner sc = new Scanner(is);
            PrintWriter pw = new PrintWriter(os);
            
            //Gui dia chi client
            String diachiclient =  InetAddress.getLocalHost().getHostAddress();
            pw.println(diachiclient);
            pw.flush();
            //System.out.println("Dia chi " + diachiclient);
            //mat khau
            String pass = new String(goinhan.getData(),0,goinhan.getLength());
            String passDaochuoi = new StringBuilder(pass).reverse().toString();
            //System.out.println("DAo chupoi"+passDaochuoi);
            
            pw.println(passDaochuoi);
            pw.flush();
            
            String ketqua = sc.nextLine();
            
            if(ketqua.equals("-ERR")) {
                System.out.println("Chung thuc that bai");
            } else {
                System.out.println("Chung thuc thanh cong");
            }
                   
            s.close();
            ds.close();
        } catch (SocketException s) {
            System.out.println("Khong tao duoc noi ket!");
        } catch (FileNotFoundException ss) {
            System.out.println("File khong ton tai!");
        } catch (IOException sss) {
            System.out.println("Loi nhap xuat!");
        }
    }
}