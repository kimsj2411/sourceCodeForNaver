
package com.example.shakentour.network;

import java.io.IOException;

import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelHandler;

import com.example.shakentour.network.protobuf.PbmUser;
import com.example.shakentour.network.protobuf.PbmUser.V_MasterRes;
import com.example.shakentour.network.protobuf.PbmUser.V_MasterRes.master;
import com.example.shakentour.MainActivity;

public class ServerHandler5_m extends SimpleChannelHandler {

	public static String MasterCheck=null;
	

	@Override
	public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception {
	
	
		PbmUser.V_MasterRes res = (V_MasterRes) e.getMessage();
		MasterCheck= res.getVMResult().toString();
		System.out.println(MasterCheck);

	}

	 
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) throws Exception {
		if (e.getCause() instanceof IOException) {
			
			return;
		}
		throw new Exception(e.getCause());
	}
}
