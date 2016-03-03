package com.example.shakentour.network;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.handler.codec.frame.LengthFieldBasedFrameDecoder;
import org.jboss.netty.handler.codec.frame.LengthFieldPrepender;
import org.jboss.netty.handler.codec.protobuf.ProtobufDecoder;
import org.jboss.netty.handler.codec.protobuf.ProtobufEncoder;
import com.example.shakentour.network.protobuf.PbmUser;

public class ClientPipeLineFactory6 implements ChannelPipelineFactory {

	@Override
	public ChannelPipeline getPipeline() throws Exception {
		ChannelPipeline pipeline6 = Channels.pipeline();
			
		pipeline6.addLast("frameDecoder", new LengthFieldBasedFrameDecoder(1048576, 0, 4, 0, 4));
		pipeline6.addLast("VoteResultDecoder", new ProtobufDecoder(PbmUser.VoteResultRes.getDefaultInstance()));
		pipeline6.addLast("frameEncoder", new LengthFieldPrepender(4));
		pipeline6.addLast("VoteResultEncoder", new ProtobufEncoder());
		pipeline6.addLast("server handler", new ServerHandler6());
		
		return pipeline6;
	}
}
