package Client;
import java.io.*;
import java.net.*;

public class Client {
	private String name= "";
	public Client(){
		this.name = "默认机";
	} //缺省构造
	public Client(String name){
		this.name = name;
	}
	public void sentMessage(String msg){
		try{
			//创建客户端socket
			System.out.println("客户端"+this.name+"开始工作");
			Socket socket = new Socket("127.0.0.1",3333);
			//获取输出流，向服务器发送信息
			OutputStream os = socket.getOutputStream();//字节输出流
			PrintWriter pw = new PrintWriter(socket.getOutputStream());
			pw.write(msg);
			System.out.println("向服务器发送字符串：' "+ msg + " '");
			pw.flush();
			socket.shutdownOutput();//关闭输出流
			
			InputStream is = socket.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
			
			
			String info = null;
			//循环读取
			while((info = br.readLine()) != null){
				System.out.println("收到服务器返回字符串：' " + info + " '");
			}
			System.out.println("客户端"+this.name+"工作完成");
			br.close();
			is.close();
			isr.close();
			
			pw.close();
			os.close();
			socket.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args){
		new Client().sentMessage("123");
		
	}

}
