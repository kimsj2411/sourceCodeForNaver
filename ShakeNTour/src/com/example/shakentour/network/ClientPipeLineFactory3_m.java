package com.example.shakentour.network;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.handler.codec.frame.LengthFieldBasedFrameDecoder;
import org.jboss.netty.handler.codec.frame.LengthFieldPrepender;
import org.jboss.netty.handler.codec.protobuf.ProtobufDecoder;
import org.jboss.netty.handler.codec.protobuf.ProtobufEncoder;
import com.example.shakentour.network.protobuf.PbmUser;

public class ClientPipeLineFactory3_m implements ChannelPipelineFactory {

	@Override
	public ChannelPipeline getPipeline() throws Exception {
		ChannelPipeline pipeline3_m = Channels.pipeline();
		
		pipeline3_m.addLast("frameDecoder", new LengthFieldBasedFrameDecoder(1048576, 0, 4, 0, 4));
		pipeline3_m.addLast("AttendMasterDecoder", new ProtobufDecoder(PbmUser.A_MasterRes.getDefaultInstance()));
		pipeline3_m.addLast("frameEncoder", new LengthFieldPrepender(4));
		pipeline3_m.addLast("AttendMasterEncoder", new ProtobufEncoder());
		pipeline3_m.addLast("server handler", new ServerHandler3_m());
		
		return pipeline3_m;
	}
}