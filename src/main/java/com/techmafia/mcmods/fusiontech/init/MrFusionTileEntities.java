package com.techmafia.mcmods.fusiontech.init;

import com.techmafia.mcmods.fusiontech.tileentity.TileEntityFluxNode;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Created by mengy007 on 1/31/2016.
 */
public class MrFusionTileEntities {
    public static TileEntityFluxNode tileEntityFluxNode;

    public static void init() {
        tileEntityFluxNode = new TileEntityFluxNode();
        GameRegistry.registerTileEntity(TileEntityFluxNode.class, "tileEntityFluxNode");
    }
}
