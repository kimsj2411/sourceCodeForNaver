package com.example.shakentour.network;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.handler.codec.frame.LengthFieldBasedFrameDecoder;
import org.jboss.netty.handler.codec.frame.LengthFieldPrepender;
import org.jboss.netty.handler.codec.protobuf.ProtobufDecoder;
import org.jboss.netty.handler.codec.protobuf.ProtobufEncoder;
import com.example.shakentour.network.protobuf.PbmUser;

public class ClientPipeLineFactory6_m implements ChannelPipelineFactory {

	@Override
	public ChannelPipeline getPipeline() throws Exception {
		ChannelPipeline pipeline6_m = Channels.pipeline();
	
		pipeline6_m.addLast("frameDecoder", new LengthFieldBasedFrameDecoder(1048576, 0, 4, 0, 4));
		pipeline6_m.addLast("VoteMResultDecoder", new ProtobufDecoder(PbmUser.VoteMResultRes.getDefaultInstance()));
		pipeline6_m.addLast("frameEncoder", new LengthFieldPrepender(4));
		pipeline6_m.addLast("VoteMResultEncoder", new ProtobufEncoder());
		pipeline6_m.addLast("server handler", new ServerHandler6_m());
		
		return pipeline6_m;
	}
}