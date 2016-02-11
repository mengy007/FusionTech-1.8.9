package com.techmafia.mcmods.fusiontech.tileentity.base;

import com.techmafia.mcmods.fusiontech.net.CommonPacketHandler;
import com.techmafia.mcmods.fusiontech.net.messages.DeviceUpdateMessage;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

import java.util.Set;

/**
 * Created by myang on 2/10/16.
 */
public class TileEntityFusiontechBase extends TileEntity {
    protected Set<EntityPlayer> playersWatching;

    public TileEntityFusiontechBase() {
        super();
    }

    /* Server / Client sync */
    @Override
    public Packet getDescriptionPacket() {
        NBTTagCompound nbtTagCompound = new NBTTagCompound();
        writeToNBT(nbtTagCompound);
        int metadata = getBlockMetadata();
        return new S35PacketUpdateTileEntity(this.pos, metadata, nbtTagCompound);
    }

    @Override
    public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
        readFromNBT(pkt.getNbtCompound());
    }

    public void beginUpdatingPlayer(EntityPlayer player) {
        playersWatching.add(player);
        sendUpdatePacketToPlayer(player);
    }

    public void stopUpdatingPlayer(EntityPlayer player) {
        playersWatching.remove(player);
    }

    private void sendUpdatePacketToPlayer(EntityPlayer player) {
        if (this.worldObj.isRemote) { return; }

        CommonPacketHandler.INSTANCE.sendTo(getUpdatePacket(), (EntityPlayerMP)player);
    }

    private void sendUpdatePacket() {
        if (this.worldObj.isRemote) { return; }
        if (this.playersWatching.size() <= 0) { return; }

        for (EntityPlayer player : playersWatching) {
            CommonPacketHandler.INSTANCE.sendTo(getUpdatePacket(), (EntityPlayerMP)player);
        }
    }

    protected IMessage getUpdatePacket() {
        NBTTagCompound childData = new NBTTagCompound();

        onSendUpdate(childData);

        return new DeviceUpdateMessage(pos.getX(), pos.getY(), pos.getZ(), childData);
    }

    /**
     * Sets information to send
     * @param nbt
     */
    public void onSendUpdate(NBTTagCompound nbt) { }

    /**
     * Called on received update packet from server
     * @param nbt
     */
    public void onReceiveUpdate(NBTTagCompound nbt) { }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);
    }

    @Override
    public void writeToNBT(NBTTagCompound nbt) {
        super.writeToNBT(nbt);
    }
}
