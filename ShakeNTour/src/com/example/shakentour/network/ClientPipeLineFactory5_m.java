package com.example.shakentour.network;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.handler.codec.frame.LengthFieldBasedFrameDecoder;
import org.jboss.netty.handler.codec.frame.LengthFieldPrepender;
import org.jboss.netty.handler.codec.protobuf.ProtobufDecoder;
import org.jboss.netty.handler.codec.protobuf.ProtobufEncoder;
import com.example.shakentour.network.protobuf.PbmUser;

public class ClientPipeLineFactory5_m implements ChannelPipelineFactory {

	@Override
	public ChannelPipeline getPipeline() throws Exception {
		ChannelPipeline pipeline5_m = Channels.pipeline();
	
		pipeline5_m.addLast("frameDecoder", new LengthFieldBasedFrameDecoder(1048576, 0, 4, 0, 4));
		pipeline5_m.addLast("VoteMasterDecoder", new ProtobufDecoder(PbmUser.V_MasterRes.getDefaultInstance()));
		pipeline5_m.addLast("frameEncoder", new LengthFieldPrepender(4));
		pipeline5_m.addLast("VoteMasterEncoder", new ProtobufEncoder());
		pipeline5_m.addLast("server handler", new ServerHandler5_m());
		
		return pipeline5_m;
	}
}