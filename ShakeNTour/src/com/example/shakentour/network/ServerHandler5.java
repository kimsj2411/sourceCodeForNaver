package com.example.shakentour.network;

import java.io.IOException;

import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelHandler;

import com.example.shakentour.network.protobuf.PbmUser;
import com.example.shakentour.network.protobuf.PbmUser.VoteRes;


public class ServerHandler5  extends SimpleChannelHandler {
	public static String vc_que=null;
	public static String vc_ans1=null;
	public static String vc_ans2=null;
	public static String vc_ans3=null;
	public static String vc_ans4=null;
		
	@Override
	public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception {
	
	if(e.getMessage() instanceof PbmUser.VoteRes) {
		PbmUser.VoteRes res = (VoteRes) e.getMessage();
		
		vc_que = res.getVQue();
		vc_ans1 = res.getVAns1();
		vc_ans2 = res.getVAns2();
		 vc_ans3 = res.getVAns3();
		 vc_ans4 = res.getVAns4();
		
		
		System.out.println("[투표 용지] ");
		System.out.println(vc_que);
		System.out.println(vc_ans1);
		System.out.println(vc_ans2);
		System.out.println(vc_ans3);
		System.out.println(vc_ans4);

		
		
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
