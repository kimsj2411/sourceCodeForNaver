package com.example.shakentour.network;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.handler.codec.frame.LengthFieldBasedFrameDecoder;
import org.jboss.netty.handler.codec.frame.LengthFieldPrepender;
import org.jboss.netty.handler.codec.protobuf.ProtobufDecoder;
import org.jboss.netty.handler.codec.protobuf.ProtobufEncoder;
import com.example.shakentour.network.protobuf.PbmUser;

public class ClientPipeLineFactory2 implements ChannelPipelineFactory {

	@Override
	public ChannelPipeline getPipeline() throws Exception {
		ChannelPipeline pipeline2 = Channels.pipeline();
	
		pipeline2.addLast("frameDecoder", new LengthFieldBasedFrameDecoder(1048576, 0, 4, 0, 4));
		pipeline2.addLast("NoticeDecoder", new ProtobufDecoder(PbmUser.NoticeRes.getDefaultInstance()));
		pipeline2.addLast("frameEncoder", new LengthFieldPrepender(4));
		pipeline2.addLast("NoticeEncoder", new ProtobufEncoder());
		pipeline2.addLast("server handler", new ServerHandler2());
		
		return pipeline2;
	}
}
