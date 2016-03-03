package com.example.shakentour.network;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.handler.codec.frame.LengthFieldBasedFrameDecoder;
import org.jboss.netty.handler.codec.frame.LengthFieldPrepender;
import org.jboss.netty.handler.codec.protobuf.ProtobufDecoder;
import org.jboss.netty.handler.codec.protobuf.ProtobufEncoder;
import com.example.shakentour.network.protobuf.PbmUser;

public class ClientPipeLineFactory4 implements ChannelPipelineFactory {

	@Override
	public ChannelPipeline getPipeline() throws Exception {
		ChannelPipeline pipeline4 = Channels.pipeline();
	
		pipeline4.addLast("frameDecoder", new LengthFieldBasedFrameDecoder(1048576, 0, 4, 0, 4));
		pipeline4.addLast("VoteMakeDecoder", new ProtobufDecoder(PbmUser.VoteMakeRes.getDefaultInstance()));
		pipeline4.addLast("frameEncoder", new LengthFieldPrepender(4));
		pipeline4.addLast("VoteMakeEncoder", new ProtobufEncoder());
		pipeline4.addLast("server handler", new ServerHandler4());
		
		return pipeline4;
	}
}