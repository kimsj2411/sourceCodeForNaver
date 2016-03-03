
package com.example.shakentour.network;

import java.io.IOException;

import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelHandler;
import com.example.shakentour.VoteResultActivity;
import com.example.shakentour.network.protobuf.PbmUser;
import com.example.shakentour.network.protobuf.PbmUser.VoteMResultRes;


public class ServerHandler6_m extends SimpleChannelHandler {

public static int vc_ans1=0;
public static int vc_ans2=0;	
public static int vc_ans3=0;
public static int vc_ans4=0;

@Override

	public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception {

		if(e.getMessage() instanceof PbmUser.VoteMResultRes) {
			PbmUser.VoteMResultRes res = (VoteMResultRes) e.getMessage();
			
			String v_que = res.getVQue();
			String v_ans1 = res.getVAns1();
			String v_ans2 = res.getVAns2();
			String v_ans3 = res.getVAns3();
			String v_ans4 = res.getVAns4();
		 vc_ans1 = res.getVCAns1();
		vc_ans2 = res.getVCAns2();
		vc_ans3 = res.getVCAns3();
		vc_ans4 = res.getVCAns4();
						
			System.out.println("[투표 용지] ");
			System.out.println(v_que);
			System.out.println(v_ans1);
			System.out.println(v_ans2);
			System.out.println(v_ans3);
			System.out.println(v_ans4);
			//투표결과
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
