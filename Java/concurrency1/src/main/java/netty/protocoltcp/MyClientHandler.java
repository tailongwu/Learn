package netty.protocoltcp;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class MyClientHandler extends SimpleChannelInboundHandler<MessageProtocol> {
    private int count = 0;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        for (int i = 0; i < 100; i++) {
            String message = "Today is good day";
            byte[] bytes = message.getBytes(StandardCharsets.UTF_8);
            int length = bytes.length;

            MessageProtocol messageProtocol = new MessageProtocol(length, bytes);
            ctx.writeAndFlush(messageProtocol);
        }
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageProtocol msg) throws Exception {
        int length = msg.getLen();
        byte[] bytes = msg.getContent();
        System.out.println();
        System.out.println();
        System.out.println("Client received message info:");
        System.out.println("Len=" + length);
        System.out.println("Content=" + new String(bytes, Charset.forName("utf-8")));
        System.out.println("Client received count=" + (++this.count));
    }
}
