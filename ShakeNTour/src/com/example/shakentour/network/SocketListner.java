package com.example.shakentour.network;

public class SocketListner extends Thread {
	private SampleClient client = new SampleClient();
	public SocketListner(String _name) {
		super(_name);
	}
	
	public void run(){
		// ���ο� �����带 �����ؼ� �̸� �����״� Netty Client�� ����
		client.initialize();
		client.start();
	}
	
	public void shutDown() {
		client.shutDown();
	}
}
