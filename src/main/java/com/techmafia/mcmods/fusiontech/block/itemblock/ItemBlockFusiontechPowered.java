package com.techmafia.mcmods.fusiontech.block.itemblock;

import com.techmafia.mcmods.fusiontech.reference.Reference;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumChatFormatting;

import java.util.List;

/**
 * Created by myang on 2/3/16.
 */
public class ItemBlockFusiontechPowered extends ItemBlock {
    public ItemBlockFusiontechPowered(Block block) {
        super(block);
    }

    public void addInformation(ItemStack itemStack, EntityPlayer entityPlayer, List list, boolean par4) {
        super.addInformation(itemStack, entityPlayer, list, par4);

        NBTTagCompound nbt = itemStack.getTagCompound();

        if (nbt != null) {
            if (!nbt.hasKey("Energy")) {
                nbt.setInteger("Energy", 0);
            }

            if (!nbt.hasKey("MaxEnergy")) {
                nbt.setInteger("MaxEnergy", 0);
            }

            list.add(EnumChatFormatting.WHITE + "" + nbt.getInteger("Energy") + "/" + nbt.getInteger("MaxEnergy") + " RF");
        }
    }
}
