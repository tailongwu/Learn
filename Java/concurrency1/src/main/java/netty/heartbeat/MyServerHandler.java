package netty.heartbeat;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleStateEvent;

public class MyServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
//        super.userEventTriggered(ctx, evt);
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent event = (IdleStateEvent) evt;
            String eventType = null;
            switch (event.state()) {
                case READER_IDLE:
                    eventType = "Reader idle";
                    break;
                case WRITER_IDLE:
                    eventType = "Writer idle";
                    break;
                case ALL_IDLE:
                    eventType = "Reader and Writer idle";
                    break;
            }

            System.out.println(ctx.channel().remoteAddress() + "--timeout--" + eventType);
        }
    }
}
