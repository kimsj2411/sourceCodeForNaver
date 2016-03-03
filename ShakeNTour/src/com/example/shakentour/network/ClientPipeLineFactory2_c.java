package com.example.shakentour.network;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.handler.codec.frame.LengthFieldBasedFrameDecoder;
import org.jboss.netty.handler.codec.frame.LengthFieldPrepender;
import org.jboss.netty.handler.codec.protobuf.ProtobufDecoder;
import org.jboss.netty.handler.codec.protobuf.ProtobufEncoder;
import com.example.shakentour.network.protobuf.PbmUser;

public class ClientPipeLineFactory2_c implements ChannelPipelineFactory {

	@Override
	public ChannelPipeline getPipeline() throws Exception {
		ChannelPipeline pipeline2_c = Channels.pipeline();
		
		pipeline2_c.addLast("frameDecoder", new LengthFieldBasedFrameDecoder(1048576, 0, 4, 0, 4));
		pipeline2_c.addLast("NoticeClientDecoder", new ProtobufDecoder(PbmUser.N_ClientRes.getDefaultInstance()));
		pipeline2_c.addLast("frameEncoder", new LengthFieldPrepender(4));
		pipeline2_c.addLast("NoticeClientEncoder", new ProtobufEncoder());
		pipeline2_c.addLast("server handler", new ServerHandler2_c());
		
		return pipeline2_c;
	}
}