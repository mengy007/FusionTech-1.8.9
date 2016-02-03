package com.techmafia.mcmods.mrfusion.block;

import com.techmafia.mcmods.mrfusion.MrFusion;
import com.techmafia.mcmods.mrfusion.creativetab.CreativeTabMrFusion;
import com.techmafia.mcmods.mrfusion.handler.gui.GuiHandlerBlockMrFusion;
import com.techmafia.mcmods.mrfusion.tileentity.TileEntityMrFusion;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Created by mengy007 on 1/31/2016.
 */
public class BlockMrFusion extends BlockContainer implements ITileEntityProvider {
    public BlockMrFusion() {
        super(Material.rock);
        this.setCreativeTab(CreativeTabMrFusion.MRFUSION_TAB);
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

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumFacing side, float hitX, float hitY, float hitZ) {
        // Uses the gui handler registered to your mod to open the gui for the given gui id
        // open on the server side only  (not sure why you shouldn't open client side too... vanilla doesn't, so we better not either)
        if (worldIn.isRemote) return true;

        playerIn.openGui(MrFusion.instance, GuiHandlerBlockMrFusion.getGuiID(), worldIn, pos.getX(), pos.getY(), pos.getZ());

        return true;
    }

    /* ITileEntityProvider */
    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileEntityMrFusion(100000000);
    }
}
