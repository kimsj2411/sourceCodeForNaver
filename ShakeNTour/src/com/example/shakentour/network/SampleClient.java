package com.example.shakentour.network;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;
import org.jboss.netty.bootstrap.ClientBootstrap;
import org.jboss.netty.channel.ChannelFuture;
import org.jboss.netty.channel.socket.nio.NioClientSocketChannelFactory;
import com.example.shakentour.network.ClientPipeLineFactory2_c;
import com.example.shakentour.network.ClientPipeLineFactory;
import com.example.shakentour.network.ClientPipeLineFactory2_m;
import com.example.shakentour.network.ClientPipeLineFactory2;
import com.example.shakentour.network.ClientPipeLineFactory3;
import com.example.shakentour.network.ClientPipeLineFactory3_m;
import com.example.shakentour.network.ClientPipeLineFactory4;
import com.example.shakentour.network.ClientPipeLineFactory5;
import com.example.shakentour.network.ClientPipeLineFactory6;
import com.example.shakentour.network.ClientPipeLineFactory6_m;

public class SampleClient implements IClient {

	private final ClientBootstrap clientBootStrap = new ClientBootstrap();
	private final ClientBootstrap clientBootStrap2 = new ClientBootstrap();
	private final ClientBootstrap clientBootStrap2_m = new ClientBootstrap();
	private final ClientBootstrap clientBootStrap2_c = new ClientBootstrap();
	private final ClientBootstrap clientBootStrap3 = new ClientBootstrap();
	private final ClientBootstrap clientBootStrap3_m = new ClientBootstrap();
	private final ClientBootstrap clientBootStrap4 = new ClientBootstrap();
	private final ClientBootstrap clientBootStrap5 = new ClientBootstrap();
	private final ClientBootstrap clientBootStrap5_m = new ClientBootstrap();
	private final ClientBootstrap clientBootStrap6 = new ClientBootstrap();
	private final ClientBootstrap clientBootStrap6_m = new ClientBootstrap();
	public static ChannelFuture channelFuture;
	public static ChannelFuture channelFuture2;
	public static ChannelFuture channelFuture2_m;
	public static ChannelFuture channelFuture2_c;
	public static ChannelFuture channelFuture3;
	public static ChannelFuture channelFuture3_m;
	public static ChannelFuture channelFuture4;
	public static ChannelFuture channelFuture5;
	public static ChannelFuture channelFuture5_m;
	public static ChannelFuture channelFuture6;
	public static ChannelFuture channelFuture6_m;

	@Override
	public void initialize() {
		clientBootStrap.setFactory(new NioClientSocketChannelFactory(Executors.newCachedThreadPool()
				, Executors.newCachedThreadPool()
				, Runtime.getRuntime().availableProcessors() * 2 + 1));
		clientBootStrap.setPipelineFactory(new ClientPipeLineFactory());
		
		clientBootStrap2.setFactory(new NioClientSocketChannelFactory(Executors.newCachedThreadPool()
				, Executors.newCachedThreadPool()
				, Runtime.getRuntime().availableProcessors() * 2 + 1));
		clientBootStrap2.setPipelineFactory(new ClientPipeLineFactory2());
		
		clientBootStrap2_m.setFactory(new NioClientSocketChannelFactory(Executors.newCachedThreadPool()
				, Executors.newCachedThreadPool()
				, Runtime.getRuntime().availableProcessors() * 2 + 1));
		clientBootStrap2_m.setPipelineFactory(new ClientPipeLineFactory2_m());
		
		clientBootStrap2_c.setFactory(new NioClientSocketChannelFactory(Executors.newCachedThreadPool()
				, Executors.newCachedThreadPool()
				, Runtime.getRuntime().availableProcessors() * 2 + 1));
		clientBootStrap2_c.setPipelineFactory(new ClientPipeLineFactory2_c());
		
		clientBootStrap3.setFactory(new NioClientSocketChannelFactory(Executors.newCachedThreadPool()
				, Executors.newCachedThreadPool()
				, Runtime.getRuntime().availableProcessors() * 2 + 1));
		clientBootStrap3.setPipelineFactory(new ClientPipeLineFactory3());
		
		clientBootStrap3_m.setFactory(new NioClientSocketChannelFactory(Executors.newCachedThreadPool()
				, Executors.newCachedThreadPool()
				, Runtime.getRuntime().availableProcessors() * 2 + 1));
		clientBootStrap3_m.setPipelineFactory(new ClientPipeLineFactory3_m());

		clientBootStrap4.setFactory(new NioClientSocketChannelFactory(Executors.newCachedThreadPool()
				, Executors.newCachedThreadPool()
				, Runtime.getRuntime().availableProcessors() * 2 + 1));
		clientBootStrap4.setPipelineFactory(new ClientPipeLineFactory4());

		clientBootStrap5.setFactory(new NioClientSocketChannelFactory(Executors.newCachedThreadPool()
				, Executors.newCachedThreadPool()
				, Runtime.getRuntime().availableProcessors() * 2 + 1));
		clientBootStrap5.setPipelineFactory(new ClientPipeLineFactory5());
		
		clientBootStrap5_m.setFactory(new NioClientSocketChannelFactory(Executors.newCachedThreadPool()
				, Executors.newCachedThreadPool()
				, Runtime.getRuntime().availableProcessors() * 2 + 1));
		clientBootStrap5_m.setPipelineFactory(new ClientPipeLineFactory5_m());
		
		clientBootStrap6.setFactory(new NioClientSocketChannelFactory(Executors.newCachedThreadPool()
				, Executors.newCachedThreadPool()
				, Runtime.getRuntime().availableProcessors() * 2 + 1));
		clientBootStrap6.setPipelineFactory(new ClientPipeLineFactory6());
		
		clientBootStrap6_m.setFactory(new NioClientSocketChannelFactory(Executors.newCachedThreadPool()
				, Executors.newCachedThreadPool()
				, Runtime.getRuntime().availableProcessors() * 2 + 1));
		clientBootStrap6_m.setPipelineFactory(new ClientPipeLineFactory6_m());
	}

	@Override
	public void start() {
		System.out.println("client start");		
		System.out.println("connect to server");

		channelFuture=clientBootStrap.connect(new InetSocketAddress("192.168.43.211", 1010));
		channelFuture2=clientBootStrap2.connect(new InetSocketAddress("192.168.43.211", 1020));
		channelFuture2_m=clientBootStrap2_m.connect(new InetSocketAddress("192.168.43.211", 1025));
		channelFuture2_c=clientBootStrap2_c.connect(new InetSocketAddress("192.168.43.211", 1026));
		channelFuture3=clientBootStrap3.connect(new InetSocketAddress("192.168.43.211", 1030));
		channelFuture3_m=clientBootStrap3_m.connect(new InetSocketAddress("192.168.43.211", 1035));//port번호 조절
		channelFuture4=clientBootStrap4.connect(new InetSocketAddress("192.168.43.211", 1040));
		channelFuture5=clientBootStrap5.connect(new InetSocketAddress("192.168.43.211", 1050));
		channelFuture5_m=clientBootStrap5_m.connect(new InetSocketAddress("192.168.43.211", 1055));
		channelFuture6=clientBootStrap6.connect(new InetSocketAddress("192.168.43.211", 1060));
		channelFuture6_m=clientBootStrap6_m.connect(new InetSocketAddress("192.168.43.211", 1065));
	}
	
	public void shutDown() {
		if (channelFuture != null) {
			channelFuture.getChannel().close();
		}
	}
}