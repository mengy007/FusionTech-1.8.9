package com.techmafia.mcmods.fusiontech.init;

import com.techmafia.mcmods.fusiontech.FusionTech;
import com.techmafia.mcmods.fusiontech.gui.handler.GuiHandlerRegistry;
import net.minecraftforge.fml.common.network.NetworkRegistry;

/**
 * Created by mengy007 on 1/31/2016.
 */
public class FusiontechGui {
    public static void init() {
        NetworkRegistry.INSTANCE.registerGuiHandler(FusionTech.instance, GuiHandlerRegistry.getInstance());
        //GuiHandlerRegistry.getInstance().registerGuiHandler(new GuiHandlerBlockMrFusion(), GuiHandlerBlockMrFusion.getGuiID());
    }
}
