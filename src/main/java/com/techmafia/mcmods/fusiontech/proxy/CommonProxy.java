package com.techmafia.mcmods.fusiontech.proxy;

import com.techmafia.mcmods.fusiontech.init.FusiontechGui;
import com.techmafia.mcmods.fusiontech.init.FusiontechTileEntities;
import com.techmafia.mcmods.fusiontech.init.FusiontechBlocks;
import com.techmafia.mcmods.fusiontech.init.FusiontechItems;
import com.techmafia.mcmods.fusiontech.net.CommonPacketHandler;

/**
 * Created by Meng on 7/31/2015.
 */
public class CommonProxy {
    public void preInit() {
        /* Network stuff */
        CommonPacketHandler.init();

        /* Items */
        FusiontechItems.init();

        /* Blocks */
        FusiontechBlocks.init();

        /* Tile Entities */
        FusiontechTileEntities.init();

        /* GUI */
        FusiontechGui.init();
    }

    public void registerClientStuff() {}

    public void init() {}
}
