import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
//������ //Ŭ���̾�Ʈ
public class ClientSocket extends Thread{
	private Socket socket;
	private InputStream is;
	private OutputStream os;
	private Scanner nscan;
	private PrintWriter nout;
	
	private ReceiveListener receiveListener;
	private boolean isRun = true;
	
	public ClientSocket(String ip, int port) throws UnknownHostException, IOException{//����
		socket = new Socket(ip, port);
		System.out.println("Connected... to "+socket.getRemoteSocketAddress().toString());
		
		init(socket);
	}
	
	
	
	public void setReceiveListener(ReceiveListener receiveListener) {
		this.receiveListener = receiveListener;
	}



	private void init(Socket socket) throws IOException {
		this.socket = socket;
		is = socket.getInputStream();
		nscan = new Scanner(is);
		os = socket.getOutputStream();
		nout = new PrintWriter(os,true);
		
		this.start(); // ��ŸƮ�ϴ� ���� �� ����!
		
	}

	public ClientSocket(Socket socket) throws IOException {
		init(socket);
	}
	@Override
	public void run() {
		
		//�̺κ��� ���� ������ �� ���� ���� �� ���� ĸ���� �̿��ϴ� �ְ� ���⸦ ä����
		/*Scanner scan = new Scanner(System.in);
		System.out.print("Msg : ");
		String msg = scan.nextLine();
		nout.println(msg);
		
		System.out.println("echo data : " + echo);*/
		
		
		while(isRun){
			String echo = nscan.nextLine();
			if(receiveListener != null)
				receiveListener.OnReceive(echo);
			
		}
		
		//�����Ͱ� �������� close
		nout.close();
		nscan.close();
		try {
			os.close();
			is.close();
			socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



	public void send(String msg) {
		// TODO Auto-generated method stub
		nout.println(msg);
	}



	public void close() {
		// TODO Auto-generated method stub
		isRun = false;		
	}

}
