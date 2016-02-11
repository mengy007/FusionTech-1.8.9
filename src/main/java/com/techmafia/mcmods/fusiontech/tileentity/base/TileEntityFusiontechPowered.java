package com.techmafia.mcmods.fusiontech.tileentity.base;

import cofh.api.energy.*;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;

/**
 * Created by myang on 2/10/16.
 */
public class TileEntityFusiontechPowered extends TileEntityFusiontechBase implements IEnergyHandler, IEnergyProvider, IEnergyReceiver {
    protected EnergyStorage energyStorage;

    public TileEntityFusiontechPowered(int energyCapacity) {
        super();
        this.energyStorage = new EnergyStorage(energyCapacity);
    }

    /* IEnergy_ */
    @Override
    public int extractEnergy(EnumFacing from, int maxExtract, boolean simulate) {
        if (this.canConnectEnergy(from)) {
            return this.energyStorage.extractEnergy(maxExtract, simulate);
        } else {
            return 0;
        }
    }

    @Override
    public int receiveEnergy(EnumFacing from, int maxReceive, boolean simulate) {
        if (this.canConnectEnergy(from)) {
            return this.energyStorage.receiveEnergy(maxReceive, simulate);
        } else {
            return 0;
        }
    }

    @Override
    public int getEnergyStored(EnumFacing from) {
        return this.energyStorage.getEnergyStored();
    }

    @Override
    public int getMaxEnergyStored(EnumFacing from) {
        return this.energyStorage.getMaxEnergyStored();
    }

    @Override
    public boolean canConnectEnergy(EnumFacing from) {
        return true;
    }

    /* Overrides to handle energy */
    @Override
    public void onSendUpdate(NBTTagCompound nbt) {
        super.onSendUpdate(nbt);

        NBTTagCompound energyTag = new NBTTagCompound();
        this.energyStorage.writeToNBT(energyTag);
        nbt.setTag("energyStorage", energyTag);
    }

    @Override
    public void onReceiveUpdate(NBTTagCompound nbt) {
        super.onReceiveUpdate(nbt);

        this.energyStorage.readFromNBT(nbt.getCompoundTag("energyStorage"));
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);

        /* IEnergyContainer */
        if (nbt.hasKey("energyStorage")) {
            this.energyStorage.readFromNBT(nbt.getCompoundTag("energyStorage"));
        }
    }

    @Override
    public void writeToNBT(NBTTagCompound nbt) {
        super.writeToNBT(nbt);

        /* IEnergyContainer */
        NBTTagCompound energyTag = new NBTTagCompound();
        this.energyStorage.writeToNBT(energyTag);
        nbt.setTag("energyStorage", energyTag);
    }

    public void setEnergy(int energy) {
        this.energyStorage.setEnergyStored(energy);
    }
}
