package com.techmafia.mcmods.fusiontech.block.base;

import com.techmafia.mcmods.fusiontech.creativetab.CreativeTabMrFusion;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Created by myang on 2/10/16.
 */
public class BlockFusiontechBase extends BlockContainer {
    public BlockFusiontechBase() {
        super(Material.rock);
        this.setCreativeTab(CreativeTabMrFusion.FUSIONTECH_TAB);
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return null;
    }

    @SideOnly(Side.CLIENT)
    public EnumWorldBlockLayer getBlockLayer() {
        return EnumWorldBlockLayer.SOLID;
    }

    @Override
    public boolean isOpaqueCube() {
        return true;
    }

    @Override
    public boolean isFullCube() {
        return true;
    }

    @Override
    public int getRenderType() {
        return 3;
    }
}
