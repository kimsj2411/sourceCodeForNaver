package com.example.shakentour.network;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.handler.codec.frame.LengthFieldBasedFrameDecoder;
import org.jboss.netty.handler.codec.frame.LengthFieldPrepender;
import org.jboss.netty.handler.codec.protobuf.ProtobufDecoder;
import org.jboss.netty.handler.codec.protobuf.ProtobufEncoder;
import com.example.shakentour.network.protobuf.PbmUser;

public class ClientPipeLineFactory3 implements ChannelPipelineFactory {

	@Override
	public ChannelPipeline getPipeline() throws Exception {
		ChannelPipeline pipeline3 = Channels.pipeline();
			
		pipeline3.addLast("frameDecoder", new LengthFieldBasedFrameDecoder(1048576, 0, 4, 0, 4));
		pipeline3.addLast("AttendDecoder", new ProtobufDecoder(PbmUser.AttendRes.getDefaultInstance()));
		pipeline3.addLast("frameEncoder", new LengthFieldPrepender(4));
		pipeline3.addLast("AttendEncoder", new ProtobufEncoder());
		pipeline3.addLast("server handler", new ServerHandler3());
		
		return pipeline3;
	}
}