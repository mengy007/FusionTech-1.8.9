package com.techmafia.mcmods.fusiontech.block;

import com.techmafia.mcmods.fusiontech.block.base.BlockFusiontechPowered;
import com.techmafia.mcmods.fusiontech.tileentity.TileEntityFluxNode;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * Created by myang on 2/10/16.
 */
public class BlockFluxNode extends BlockFusiontechPowered implements ITileEntityProvider {
    public BlockFluxNode() {
        super();
    }

    /* ITileEntityProvider */
    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileEntityFluxNode();
    }
}
