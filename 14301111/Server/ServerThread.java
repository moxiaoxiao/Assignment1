package Server;

import java.io.*;
import java.net.*;

public class ServerThread extends Thread{
	
	//和本线程相关的socket
	Socket socket = null;
	public ServerThread(Socket socket){
		this.socket = socket;
	}
	
	//线程执行的操作，相应客户端的请求
	public void run(){
		InputStream is = null;
		InputStreamReader isr = null;
		BufferedReader br = null;
		
		OutputStream os = null;
		PrintWriter pw = null;
		try{
			//获取一个输入流，去读取客户端的信息
			is = socket.getInputStream();
			isr = new InputStreamReader(is);//将字节流转换为字符流
			br = new BufferedReader(isr);//添加缓冲
			String info = null;
			String buff = "";
			//循环读取数据
			while((info = br.readLine()) != null){
				buff += info;
				System.out.println("收到客户端发送数据 "+ info);
			}
			String back = "";
			for (int i = buff.length()-1; i >= 0; i--) { // 字符串下标从0开始，长度-1结束。倒序所以从长度-1开始，0结束。
				back += buff.charAt(i);
			}
			
			socket.shutdownInput();//关闭输入流
			
			//获取输出流，响应客户端的请求
			os = socket.getOutputStream();
			pw = new PrintWriter(os);//包装为打印流
			pw.write(back);
			pw.flush();//将缓存输出
			
		}catch(IOException e){
			e.printStackTrace();
		}finally{
			
			try{
				//关闭资源
				if(pw != null)
					pw.close();
				if(os != null)
					os.close();
				if(is != null)
					is.close();
				if(isr != null)
					isr.close();
				if(br != null)
					br.close();
				if(socket != null)
					socket.close();
			}catch(IOException e){
				e.printStackTrace();
			}
		}
	}

}














