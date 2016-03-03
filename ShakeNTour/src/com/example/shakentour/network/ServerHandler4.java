package com.example.shakentour.network;

import java.io.IOException;

import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelHandler;

import com.example.shakentour.network.protobuf.PbmUser;
import com.example.shakentour.network.protobuf.PbmUser.VoteMakeRes;


public class ServerHandler4 extends SimpleChannelHandler {

	public static String voteMake=null;
	public static int ans1=0;
	public static int ans2=0;
	public static int ans3=0;
	public static int ans4=0;
	
	@Override
	public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception {
	
	if(e.getMessage() instanceof PbmUser.VoteMakeRes) {
		PbmUser.VoteMakeRes res = (VoteMakeRes) e.getMessage();
		voteMake=res.getVMResult().toString();
		System.out.println(voteMake);
		 ans1 = res.getVCAns1();
		 ans2 = res.getVCAns2();
		 ans3 = res.getVCAns3();
		 ans4 = res.getVCAns4();
		
	
		System.out.print(ans1);     	
		System.out.print(ans2);
		System.out.print(ans3);     	
		System.out.print(ans4);

     	}

	}
	 
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) throws Exception {
		if (e.getCause() instanceof IOException) {
			
			return;
		}
		throw new Exception(e.getCause());
	}
}
