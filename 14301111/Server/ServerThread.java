package Server;

import java.io.*;
import java.net.*;

public class ServerThread extends Thread{
	
	Socket socket = null;
	public ServerThread(Socket socket){
		this.socket = socket;
	}
	
	//响应客户端的请求
	public void run(){
		InputStream is = null;
		InputStreamReader isr = null;
		BufferedReader br = null;
		OutputStream os = null;
		PrintWriter pw = null;
		
		try{
			//读取客户端的信息
			is = socket.getInputStream();
			//将字节流转换为字符流
			isr = new InputStreamReader(is);
			//添加缓冲
			br = new BufferedReader(isr);
			//用来读取的字符串
			String info = null;
			String buff = "";
			//循环读取数据
			while((info = br.readLine()) != null){
				buff += info;
				System.out.println(info);
			}
			
			String back = "";
			 // 字符串下标从0开始，长度-1结束。倒序所以从长度-1开始，0结束。
			for (int i = buff.length()-1; i >= 0; i--) {
				back += buff.charAt(i);
			}
			//关闭输入流
			socket.shutdownInput();
			
			//获取输出流，响应客户端的请求
			os = socket.getOutputStream();
			//包装为打印流
			pw = new PrintWriter(os);
			//将缓存输出
			pw.write(back);
			pw.flush();
			
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














