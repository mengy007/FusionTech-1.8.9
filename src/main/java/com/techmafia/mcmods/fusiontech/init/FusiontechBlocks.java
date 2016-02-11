package com.techmafia.mcmods.fusiontech.init;

import com.techmafia.mcmods.fusiontech.block.BlockFluxNode;
import com.techmafia.mcmods.fusiontech.block.itemblock.ItemBlockFusiontechPowered;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.event.FMLInterModComms;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.ShapedOreRecipe;

/**
 * Created by mengy007 on 1/31/2016.
 */
public class FusiontechBlocks {
    public static final BlockFluxNode blockFluxNode = new BlockFluxNode();

    public static void init() {
        blockFluxNode.setUnlocalizedName("fluxNode");

        /* Register Blocks */
        GameRegistry.registerBlock(blockFluxNode, ItemBlockFusiontechPowered.class, "fluxNode");

        /* Crafting recipes */
        /*
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(blockFluxNode, 1), new Object[]{
                "DID",
                "ISI",
                "DOD",
                'S', Items.nether_star,
                'D', Blocks.diamond_block,
                'O', Blocks.obsidian,
                'I', Blocks.iron_block
        }));
        */

        /* Waila */
        FMLInterModComms.sendMessage("Waila", "register", "com.techmafia.mcmods.fusiontech.block.handler.WailaTileHandler.callbackRegister");
    }
}
