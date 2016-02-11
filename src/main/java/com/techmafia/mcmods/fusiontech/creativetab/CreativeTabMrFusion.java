package com.techmafia.mcmods.fusiontech.creativetab;

import com.techmafia.mcmods.fusiontech.init.MrFusionBlocks;
import com.techmafia.mcmods.fusiontech.reference.Reference;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

/**
 * Created by mengy007 on 1/31/2016.
 */
public class CreativeTabMrFusion {
    public static final CreativeTabs FUSIONTECH_TAB = new CreativeTabs(Reference.MOD_ID) {
        @Override
        public Item getTabIconItem() {
            return new ItemStack(MrFusionBlocks.blockFluxNode).getItem();
        }

        @Override
        public String getTranslatedTabLabel() {
            return "Fusion Tech";
        }
    };
}