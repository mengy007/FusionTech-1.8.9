package com.techmafia.mcmods.fusiontech.tileentity;

import com.techmafia.mcmods.fusiontech.reference.Reference;
import com.techmafia.mcmods.fusiontech.tileentity.base.TileEntityFusiontechPowered;
import net.minecraft.util.ITickable;

/**
 * Created by myang on 2/10/16.
 */
public class TileEntityFluxNode extends TileEntityFusiontechPowered implements ITickable {
    public TileEntityFluxNode() {
        super(Reference.FLUXNODE_CAPACITY);
    }

    /* ITickable */
    @Override
    public void update() {

    }
}
