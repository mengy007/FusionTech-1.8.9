package com.techmafia.mcmods.fusiontech.net.messages.base;

import com.techmafia.mcmods.fusiontech.utility.LogHelper;
import net.minecraft.util.BlockPos;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * Created by Meng on 7/30/2015.
 */
public class WorldMessage implements IMessage {
    protected int x, y, z;

    protected WorldMessage() {}
    protected WorldMessage(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        x = buf.readInt();
        y = buf.readInt();
        z = buf.readInt();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(x);
        buf.writeInt(y);
        buf.writeInt(z);
    }

    public abstract static class Handler<M extends WorldMessage> implements IMessageHandler<M, IMessage> {

        protected abstract World getWorld(MessageContext ctxt);

        protected abstract IMessage handleMessage(M message, MessageContext ctx, TileEntity te);

        public IMessage onMessage(WorldMessage message, MessageContext ctx) {
            World world = getWorld(ctx);
            if(world == null) {
                LogHelper.fatal("Unable to resolve world from messagecontext for WorldMessage");
                return null;
            }

            BlockPos blockPos = new BlockPos(message.x, message.y, message.z);
            TileEntity te = world.getTileEntity(blockPos);
            if(te == null) {
                LogHelper.error("Unable to find tile entity for WorldMessage at " + message.x + "," + message.y + "," + message.z);
                return null;
            }

            return handleMessage((M)message, ctx, te);
        }
    }
}
