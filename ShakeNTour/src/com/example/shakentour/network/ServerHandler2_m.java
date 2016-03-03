package com.example.shakentour.network;
import java.io.IOException;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelHandler;
import com.example.shakentour.network.protobuf.PbmUser;
import com.example.shakentour.network.protobuf.PbmUser.N_MasterRes;

public class ServerHandler2_m extends SimpleChannelHandler {

	public static String master=null;
	@Override
	public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception {
		PbmUser.N_MasterRes res = (N_MasterRes) e.getMessage();
		master  = res.getNMResult().toString();
		System.out.println(master);
	}
	 
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) throws Exception {
		if (e.getCause() instanceof IOException) {
			
			return;
		}throw new Exception(e.getCause());
	}
}