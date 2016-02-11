package com.techmafia.mcmods.fusiontech.block.handler;

import com.techmafia.mcmods.fusiontech.block.base.BlockFusiontechPowered;
import com.techmafia.mcmods.fusiontech.tileentity.base.TileEntityFusiontechPowered;
import mcp.mobius.waila.api.IWailaConfigHandler;
import mcp.mobius.waila.api.IWailaDataAccessor;
import mcp.mobius.waila.api.IWailaDataProvider;
import mcp.mobius.waila.api.IWailaRegistrar;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.Optional;

import java.util.List;

@Optional.Interface(iface = "mcp.mobius.waila.api.IWailaDataProvider", modid = "Waila")
public class WailaTileHandler implements IWailaDataProvider {
    @Optional.Method(modid = "Waila")
    public static void callbackRegister(IWailaRegistrar register) {
        WailaTileHandler instance = new WailaTileHandler();

        register.registerNBTProvider(instance, BlockFusiontechPowered.class);
        register.registerBodyProvider(instance, BlockFusiontechPowered.class);
    }

    @Override
    @Optional.Method(modid = "Waila")
    public ItemStack getWailaStack(IWailaDataAccessor iWailaDataAccessor, IWailaConfigHandler iWailaConfigHandler) {
        return iWailaDataAccessor.getStack();
    }

    @Override
    @Optional.Method(modid = "Waila")
    public List<String> getWailaHead(ItemStack itemStack, List<String> list, IWailaDataAccessor iWailaDataAccessor, IWailaConfigHandler iWailaConfigHandler) {
        return list;
    }

    @Override
    @Optional.Method(modid = "Waila")
    public List<String> getWailaBody(ItemStack itemStack, List<String> list, IWailaDataAccessor iWailaDataAccessor, IWailaConfigHandler iWailaConfigHandler) {
        String color;
        TileEntity te = iWailaDataAccessor.getTileEntity();

        if (te != null && te instanceof TileEntityFusiontechPowered) {
            TileEntityFusiontechPowered tile = (TileEntityFusiontechPowered)te;

            if (tile.getEnergyStored(null) > 0) {
                color = EnumChatFormatting.DARK_GREEN.toString();
            } else {
                color = EnumChatFormatting.DARK_RED.toString();
            }

            list.add(color + tile.getEnergyStored(null) + "/" + tile.getMaxEnergyStored(null) + " RF");
            tile.markDirty();
        }

        return list;
    }

    @Override
    @Optional.Method(modid = "Waila")
    public List<String> getWailaTail(ItemStack itemStack, List<String> list, IWailaDataAccessor iWailaDataAccessor, IWailaConfigHandler iWailaConfigHandler) {
        return list;
    }

    @Override
    public NBTTagCompound getNBTData(EntityPlayerMP entityPlayerMP, TileEntity tileEntity, NBTTagCompound nbtTagCompound, World world, BlockPos blockPos) {
        if (tileEntity != null) {
            tileEntity.writeToNBT(nbtTagCompound);
        }

        return nbtTagCompound;
    }
}
