package moze_intel.projecte.api.tile;

import net.minecraft.util.EnumFacing;
//import net.minecraftforge.common.util.ForgeDirection;

/**
 * Reference implementation of IEMCAcceptor
 *
 * @author williewillus
 */
public class TileEmcAcceptor extends TileEmcBase implements IEmcAcceptor
{
	@Override
	public double acceptEMC(EnumFacing side, double toAccept)
	{
		double toAdd = Math.min(maximumEMC - currentEMC, toAccept);
		addEMC(toAdd);
		return toAdd;
	}
}
