package com.example.shakentour.network;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.handler.codec.frame.LengthFieldBasedFrameDecoder;
import org.jboss.netty.handler.codec.frame.LengthFieldPrepender;
import org.jboss.netty.handler.codec.protobuf.ProtobufDecoder;
import org.jboss.netty.handler.codec.protobuf.ProtobufEncoder;
import com.example.shakentour.network.protobuf.PbmUser;

public class ClientPipeLineFactory5 implements ChannelPipelineFactory {

	@Override
	public ChannelPipeline getPipeline() throws Exception {
		ChannelPipeline pipeline5 = Channels.pipeline();
			
		pipeline5.addLast("frameDecoder", new LengthFieldBasedFrameDecoder(1048576, 0, 4, 0, 4));
		pipeline5.addLast("VoteDecoder", new ProtobufDecoder(PbmUser.VoteRes.getDefaultInstance()));
		pipeline5.addLast("frameEncoder", new LengthFieldPrepender(4));
		pipeline5.addLast("VoteEncoder", new ProtobufEncoder());
		pipeline5.addLast("server handler", new ServerHandler5());
		
		return pipeline5;
	}
}