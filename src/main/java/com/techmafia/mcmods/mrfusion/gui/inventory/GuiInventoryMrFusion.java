package com.techmafia.mcmods.mrfusion.gui.inventory;

import com.techmafia.mcmods.mrfusion.container.ContainerMrFusion;
import com.techmafia.mcmods.mrfusion.tileentity.TileEntityMrFusion;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

import java.awt.*;
import java.util.Random;

/**
 * Created by mengy007 on 1/31/2016.
 */
public class GuiInventoryMrFusion extends GuiContainer {
    private static final ResourceLocation texture = new ResourceLocation("mrfusion", "textures/gui/mrfusion_gui.png");
    private TileEntityMrFusion tileEntityMrFusion;

    public GuiInventoryMrFusion(InventoryPlayer inventoryPlayer, TileEntityMrFusion tileEntityMrFusion) {
        super(new ContainerMrFusion(inventoryPlayer, tileEntityMrFusion));
        this.tileEntityMrFusion = tileEntityMrFusion;

        xSize = 176;
        ySize = 133;
    }

    // draw the background for the GUI - rendered first
    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int x, int y) {
        // Bind the image texture of our custom container
        Minecraft.getMinecraft().getTextureManager().bindTexture(texture);
        // Draw the image
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
    }

    // draw the foreground for the GUI - rendered after the slots, but before the dragged items and tooltips
    // renders relative to the top left corner of the background
    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        final int LABEL_XPOS = 5;
        final int LABEL_YPOS = 5;

        Random ran = new Random();

        fontRendererObj.drawString(tileEntityMrFusion.getDisplayName().getUnformattedText(), LABEL_XPOS, LABEL_YPOS, Color.darkGray.getRGB());
        fontRendererObj.drawString("RF: " + tileEntityMrFusion.getEnergyStored(), LABEL_XPOS, LABEL_YPOS+10, Color.red.getRGB());
    }
}
