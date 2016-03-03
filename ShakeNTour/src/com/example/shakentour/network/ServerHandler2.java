package com.example.shakentour.network;

import java.io.IOException;

import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelHandler;

import com.example.shakentour.network.protobuf.PbmUser;
import com.example.shakentour.network.protobuf.PbmUser.NoticeRes;
import com.example.shakentour.network.protobuf.PbmUser.NoticeRes.master;
import com.example.shakentour.network.protobuf.PbmUser.NoticeRes.notice;

public class ServerHandler2 extends SimpleChannelHandler {
	public static String master=null;
	public static String notice=null;
	public static String result=null;
	
	@Override
	public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception {
	
	if(e.getMessage() instanceof PbmUser.NoticeRes) {
		PbmUser.NoticeRes res = (NoticeRes) e.getMessage();
		
		master = res.getNAResult().toString();
		System.out.println(master);
		notice = res.getNoticeBroad().toString();
		result = res.getNResult().toString();
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
