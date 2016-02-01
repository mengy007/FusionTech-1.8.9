package com.techmafia.mcmods.mrfusion.init;

import com.techmafia.mcmods.mrfusion.block.BlockMrFusion;
import com.techmafia.mcmods.mrfusion.creativetab.CreativeTabMrFusion;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Created by mengy007 on 1/31/2016.
 */
public class MrFusionBlocks {
    public static final BlockMrFusion blockMrFusion = new BlockMrFusion();

    public static void init() {
        blockMrFusion.setUnlocalizedName("mrfusion");

        /* Register Blocks */
        GameRegistry.registerBlock(blockMrFusion, "mrfusion");
    }
}
