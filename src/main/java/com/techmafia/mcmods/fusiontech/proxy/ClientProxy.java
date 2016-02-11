package com.techmafia.mcmods.fusiontech.proxy;

import com.techmafia.mcmods.fusiontech.reference.Reference;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Created by Meng on 7/31/2015.
 */
@SideOnly(Side.CLIENT)
public class ClientProxy extends CommonProxy {
    @Override
    public void preInit() {
        super.preInit();
    }

    @Override
    public void registerClientStuff() {
    }

    @Override
    public void init() {
        final int DEFAULT_ITEM_SUBTYPE = 0;

        /**
         * Items
         */
        //Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(KinetiCraft2Items.kineticDust, DEFAULT_ITEM_SUBTYPE, new ModelResourceLocation("kineticraft2:kineticDust", "inventory"));

        /**
         * Blocks
         */
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(GameRegistry.findItem(Reference.MOD_ID, "fusiontech"), DEFAULT_ITEM_SUBTYPE, new ModelResourceLocation("fusiontech:fluxNode", "inventory"));
    }
}
