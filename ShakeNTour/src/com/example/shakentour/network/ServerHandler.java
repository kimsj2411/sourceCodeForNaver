package com.example.shakentour.network;
import java.io.IOException;

import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelHandler;

import android.util.Log;

import com.example.shakentour.network.protobuf.PbmUser;
import com.example.shakentour.network.protobuf.PbmUser.LoginRes;

public class ServerHandler extends SimpleChannelHandler {
	public static String a="ibi";
	
	@Override
	public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception {
		if(e.getMessage() instanceof PbmUser.LoginRes) {
			PbmUser.LoginRes res = (LoginRes) e.getMessage();
			a = res.getPResult().toString();
			System.out.println("핸드폰 입력 여부 : "+a);
			System.out.println("방 입장 여부 : " +res.getRResult().toString());
		}
	}
	 
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) throws Exception {
		if (e.getCause() instanceof IOException) {
			
			return;
		}		throw new Exception(e.getCause());
	}
}
