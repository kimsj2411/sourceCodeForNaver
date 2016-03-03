
package com.example.shakentour.network;

import java.io.IOException;

import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.ExceptionEvent;
import com.example.shakentour.AttendCheckActivity;
import com.example.shakentour.AttendActivity;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelHandler;
import com.example.shakentour.network.protobuf.PbmUser;
import com.example.shakentour.network.protobuf.PbmUser.AttendRes;


public class ServerHandler3 extends SimpleChannelHandler {

	public static int nowNum=0;
	public static int totalNum=0;
	
	@Override
	public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception {
	
	if(e.getMessage() instanceof PbmUser.AttendRes) {
		PbmUser.AttendRes res = (AttendRes) e.getMessage();
	
		System.out.println("출석 결과 : "+res.getAResult().toString());
		nowNum=res.getAttendNum();
		totalNum=res.getAttendTotNum();
		System.out.println("현재 출석 인원 : " + nowNum);
		System.out.println("전체 출석 인원 : " + totalNum);
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
