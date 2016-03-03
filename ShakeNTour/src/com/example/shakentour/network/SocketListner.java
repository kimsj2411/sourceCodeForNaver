package com.example.shakentour.network;

public class SocketListner extends Thread {
	private SampleClient client = new SampleClient();
	public SocketListner(String _name) {
		super(_name);
	}
	
	public void run(){
		// 새로운 쓰레드를 생성해서 미리 만들어뒀던 Netty Client를 구동
		client.initialize();
		client.start();
	}
	
	public void shutDown() {
		client.shutDown();
	}
}
