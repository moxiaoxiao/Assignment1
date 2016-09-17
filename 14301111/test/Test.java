package test;
import Client.Client;
public class Test {
	public static void main(String [] args){
		Client[] clients = new Client[5];
		String[] msgs = {"123","abc","ASD","QWE","ZXC"};
		String[] names = {"1","2","3","4","5"};
		for(int i = 0 ; i < 5; i++){
			clients[i] = new Client(names[i]);
			clients[i].sentMessage(msgs[i]);
		}
	}
}
