package Server;
import java.io.*;
import java.net.*;

public class Servre{
	
	public static void main(String[] args){
		try{
			ServerSocket serverSocket = new ServerSocket(3333);
			Socket socket = null;
			//记录客户端的数量
			int count = 0;
			System.out.println("服务器即将启动，等待客户机连接");
			//循环舰艇等待客户端连接
			while(true){
				//调用accept方法开始舰艇，等待客户端的连接
				socket = serverSocket.accept();
				//创建一个新的线程
				ServerThread serverThread = new ServerThread(socket);
				//启动线程
				serverThread.start();
				
				count++;//统计客户机的数量
				System.out.println("客户端的数量："+count);
				InetAddress address = socket.getInetAddress();
				System.out.println("当前客户机的IP ： " + address.getHostAddress());
			}
		}catch(IOException e){
			e.printStackTrace();
		}
	}
}