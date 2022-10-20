package netty.codec;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelPipeline;
import io.netty.util.CharsetUtil;

import java.util.concurrent.TimeUnit;

public class NettyServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//        super.channelRead(ctx, msg);
        System.out.println("CurrentThread: " + Thread.currentThread().getName());
        System.out.println("Server context is " + ctx);
        Channel channel = ctx.channel();
        ChannelPipeline pipeline = ctx.pipeline();

        ByteBuf buf = (ByteBuf) msg;
        System.out.println("Message from client: " + buf.toString(CharsetUtil.UTF_8));
        System.out.println("Client address" + channel.remoteAddress());


        ctx.channel().eventLoop().execute(() -> {
            try {
                System.out.println("喵1：" + Thread.currentThread().getName());
                Thread.sleep(10 * 1000);
                ctx.writeAndFlush(Unpooled.copiedBuffer("Hello client: 喵1", CharsetUtil.UTF_8));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        ctx.channel().eventLoop().execute(() -> {
            try {
                System.out.println("喵2：" + Thread.currentThread().getName());
                Thread.sleep(20 * 1000);
                ctx.writeAndFlush(Unpooled.copiedBuffer("Hello client: 喵2", CharsetUtil.UTF_8));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        ctx.channel().eventLoop().schedule(() -> {}, 10, TimeUnit.SECONDS);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
//        super.channelReadComplete(ctx);
        ctx.writeAndFlush(Unpooled.copiedBuffer("Hello client: 喵喵喵", CharsetUtil.UTF_8));
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
//        super.exceptionCaught(ctx, cause);
        ctx.channel().close();
    }
}
