package com.techmafia.mcmods.fusiontech.proxy;

import com.techmafia.mcmods.fusiontech.init.MrFusionGui;
import com.techmafia.mcmods.fusiontech.init.MrFusionTileEntities;
import com.techmafia.mcmods.fusiontech.init.MrFusionBlocks;
import com.techmafia.mcmods.fusiontech.init.MrFusionItems;
import com.techmafia.mcmods.fusiontech.net.CommonPacketHandler;

/**
 * Created by Meng on 7/31/2015.
 */
public class CommonProxy {
    public void preInit() {
        /* Network stuff */
        CommonPacketHandler.init();

        /* Items */
        MrFusionItems.init();

        /* Blocks */
        MrFusionBlocks.init();

        /* Tile Entities */
        MrFusionTileEntities.init();

        /* GUI */
        MrFusionGui.init();
    }

    public void registerClientStuff() {}

    public void init() {}
}
