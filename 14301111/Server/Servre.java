package Server;
import java.io.*;
import java.net.*;

public class Servre{
	
	public static void main(String[] args){
		try{
			ServerSocket serverSocket = new ServerSocket(3333);
			Socket socket = null;
		
			//循环等待客户端连接
			while(true){
				//调用accept方法开始舰艇，等待客户端的连接
				socket = serverSocket.accept();
				//创建一个新的线程
				ServerThread serverThread = new ServerThread(socket);
				//启动线程
				serverThread.start();
				
			}
		}catch(IOException e){
			e.printStackTrace();
		}
	}
}
