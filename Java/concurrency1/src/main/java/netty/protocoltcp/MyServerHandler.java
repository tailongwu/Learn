package netty.protocoltcp;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

public class MyServerHandler extends SimpleChannelInboundHandler<MessageProtocol> {
    private int count = 0;

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageProtocol msg) throws Exception {
        int length = msg.getLen();
        byte[] bytes = msg.getContent();
        System.out.println();
        System.out.println();
        System.out.println("Server received message info: ");
        System.out.println("Len=" + length);
        System.out.println("Content=" + new String(bytes, Charset.forName("utf-8")));
        System.out.println("Server received count=" + (++this.count));

        String resp = UUID.randomUUID().toString();
        byte[] contentBytes = resp.getBytes(StandardCharsets.UTF_8);
        MessageProtocol messageProtocol = new MessageProtocol(contentBytes.length, contentBytes);
        ctx.writeAndFlush(messageProtocol);
    }
}
