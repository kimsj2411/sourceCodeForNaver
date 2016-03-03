
package com.example.shakentour.network;
import java.io.IOException;

import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelHandler;

import com.example.shakentour.network.protobuf.PbmUser;
import com.example.shakentour.network.protobuf.PbmUser.VoteResultRes;
import com.example.shakentour.network.protobuf.PbmUser.VoteResultRes.vote;


public class ServerHandler6 extends SimpleChannelHandler {

	public static String voteResult=null;
		
	@Override
	public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception {
	
	if(e.getMessage() instanceof PbmUser.VoteResultRes) {
		PbmUser.VoteResultRes res = (VoteResultRes) e.getMessage();
		voteResult = res.getVResult().toString();
		
		System.out.println(voteResult);
		
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
