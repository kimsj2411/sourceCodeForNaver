package com.example.shakentour.network;
import java.io.IOException;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelHandler;
import com.example.shakentour.network.protobuf.PbmUser;
import com.example.shakentour.network.protobuf.PbmUser.N_ClientRes;

public class ServerHandler2_c extends SimpleChannelHandler {

	public static String st=null;
	
	@Override
	public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception {
		if(e.getMessage() instanceof PbmUser.N_ClientRes) {
			PbmUser.N_ClientRes res = (N_ClientRes) e.getMessage();
			st = res.getNoticeBroad().toString();
			System.out.println(st);
     	}
	}
	 
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) throws Exception {
		if (e.getCause() instanceof IOException) {
			
			return;
		}throw new Exception(e.getCause());
	}
}
