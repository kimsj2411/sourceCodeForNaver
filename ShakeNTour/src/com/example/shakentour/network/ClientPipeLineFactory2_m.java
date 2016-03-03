package com.example.shakentour.network;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.handler.codec.frame.LengthFieldBasedFrameDecoder;
import org.jboss.netty.handler.codec.frame.LengthFieldPrepender;
import org.jboss.netty.handler.codec.protobuf.ProtobufDecoder;
import org.jboss.netty.handler.codec.protobuf.ProtobufEncoder;

import com.example.shakentour.network.protobuf.PbmUser;

public class ClientPipeLineFactory2_m implements ChannelPipelineFactory {

	@Override
	public ChannelPipeline getPipeline() throws Exception {
		ChannelPipeline pipeline2_m = Channels.pipeline();
		
		pipeline2_m.addLast("frameDecoder", new LengthFieldBasedFrameDecoder(1048576, 0, 4, 0, 4));
		pipeline2_m.addLast("AttendMasterDecoder", new ProtobufDecoder(PbmUser.N_MasterRes.getDefaultInstance()));
		pipeline2_m.addLast("frameEncoder", new LengthFieldPrepender(4));
		pipeline2_m.addLast("AttendMasterEncoder", new ProtobufEncoder());
		pipeline2_m.addLast("server handler", new ServerHandler2_m());
		
		return pipeline2_m;
	}
}