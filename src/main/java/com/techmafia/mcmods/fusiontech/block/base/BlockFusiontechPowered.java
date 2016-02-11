package com.techmafia.mcmods.fusiontech.block.base;

import com.techmafia.mcmods.fusiontech.tileentity.base.TileEntityFusiontechPowered;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

/**
 * Created by myang on 2/10/16.
 */
public class BlockFusiontechPowered extends BlockFusiontechBase {
    public BlockFusiontechPowered() {
        super();
    }

    /* Transfer energy on block break and place */
    @Override
    public void breakBlock(World world, BlockPos blockPos, IBlockState blockState) {
        if (world.isRemote) { return; }

        NBTTagCompound nbt = new NBTTagCompound();
        TileEntity te = world.getTileEntity(blockPos);

        te.writeToNBT(nbt);

        ItemStack itemStack = new ItemStack(Item.getItemFromBlock(this), 1);

        if (te != null && te instanceof TileEntityFusiontechPowered) {
            EntityItem ei = new EntityItem(world, blockPos.getX(), blockPos.getY(), blockPos.getZ(), itemStack);

            nbt.setInteger("Energy", ((TileEntityFusiontechPowered)te).getEnergyStored(null));
            nbt.setInteger("MaxEnergy", ((TileEntityFusiontechPowered)te).getMaxEnergyStored(null));
            ei.getEntityItem().setTagCompound(nbt);

            world.spawnEntityInWorld(ei);

            ei = null;

            // Drop items in inv as well
            /*
            ItemStack invStack = ((TileEntityFusiontechPowered)te).getStackInSlot(0);
            if (invStack != null && invStack.stackSize > 0) {
                ei = new EntityItem(world, blockPos.getX(), blockPos.getY(), blockPos.getZ(), invStack);
                world.spawnEntityInWorld(ei);
            }
            */
        }
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return null;
    }

    @Override
    public void onBlockPlacedBy(World world, BlockPos blockPos, IBlockState blockState, EntityLivingBase placer, ItemStack stack) {
        super.onBlockPlacedBy(world, blockPos, blockState, placer, stack);

        TileEntity te = world.getTileEntity(blockPos);

        if (te != null && te instanceof TileEntityFusiontechPowered) {
            NBTTagCompound nbt = stack.getTagCompound();

            if (nbt != null && nbt.hasKey("Energy")) {
                ((TileEntityFusiontechPowered) te).setEnergy(nbt.getInteger("Energy"));
            }
        }
    }
}
