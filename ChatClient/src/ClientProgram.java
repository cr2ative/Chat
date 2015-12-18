import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
//Client
public class ClientProgram {
	public static void main(String[] args) throws UnknownHostException, IOException{
		ClientSocket client = new ClientSocket("211.238.142.112",10009);
		client.setReceiveListener(new ReceiveListener() {
			
			@Override
			public void OnReceive(String echo) {
				// TODO Auto-generated method stub
				System.out.println("echo data : "+echo);
			}
		});
		Scanner scan = new Scanner(System.in);
		
		while(true)
		{
			System.out.print("Msg : ");
			String msg = scan.nextLine();
			client.send(msg);
			
			if(msg.equals("bye"))
			{
				client.close();
				break;
			}
			
		}
		/*
		// ���� ����
		Socket sock = new Socket("211.238.142.116", 10009);
		System.out.println("Connected... to "+sock.getRemoteSocketAddress().toString());
		
		// ä�� ���� ����
		InputStream is = sock.getInputStream();
		Scanner nscan = new Scanner(is);
		OutputStream os = sock.getOutputStream();
		PrintWriter nout = new PrintWriter(os,true);// auto flush
		
		//Ŭ���̾�Ʈ ���� ����
		Scanner scan = new Scanner(System.in);
		System.out.print("Msg : ");
		String msg = scan.nextLine();
		nout.println(msg);
		String echo = nscan.nextLine();
		System.out.println("echo data : " + echo);
		
		
		//�����Ͱ� �������� close
		nout.close();
		nscan.close();
		os.close();
		is.close();
		sock.close();*/
	}
}
