package com.techmafia.mcmods.fusiontech.tileentity.base;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.IChatComponent;

import java.util.List;

/**
 * Created by myang on 2/10/16.
 */
public class TileEntityFusiontechInventory extends TileEntityFusiontechBase implements IInventory {
    protected List<ItemStack> itemStacks;

    public TileEntityFusiontechInventory() {
        super();
    }

    /* Server / Client sync */
    /*
    @Override
    public void onSendUpdate(NBTTagCompound nbt) {
        super.onSendUpdate(nbt);

        NBTTagCompound energyTag = new NBTTagCompound();
        this.energyStorage.writeToNBT(energyTag);
        nbt.setTag("energyStorage", energyTag);
    }

    public void onReceiveUpdate(NBTTagCompound nbt) {
        this.energyStorage.readFromNBT(nbt.getCompoundTag("energyStorage"));
    }
    */

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);

        /* IInventory */
        /*
        final byte NBT_TYPE_COMPOUND = 10;       // See NBTBase.createNewByType() for a listing
        NBTTagList dataForAllSlots = nbt.getTagList("Items", NBT_TYPE_COMPOUND);

        itemStack = null;
        NBTTagCompound dataForOneSlot = dataForAllSlots.getCompoundTagAt(0);
        this.itemStack = ItemStack.loadItemStackFromNBT(dataForOneSlot);
        */
    }

    @Override
    public void writeToNBT(NBTTagCompound nbt) {
        super.writeToNBT(nbt);

        /* IInventory */
        /*
        if (itemStack != null) {
            NBTTagList dataForAllSlots = new NBTTagList();
            NBTTagCompound dataForThisSlot = new NBTTagCompound();
            dataForThisSlot.setByte("Slot", (byte) 0);
            this.itemStack.writeToNBT(dataForThisSlot);
            dataForAllSlots.appendTag(dataForThisSlot);

            nbt.setTag("Items", dataForAllSlots);
        }
        */
    }

    /* IIventory */
    @Override
    public int getSizeInventory() {
        return 0;
    }

    @Override
    public ItemStack getStackInSlot(int index) {
        return null;
    }

    @Override
    public ItemStack decrStackSize(int index, int count) {
        return null;
    }

    @Override
    public ItemStack removeStackFromSlot(int index) {
        return null;
    }

    @Override
    public void setInventorySlotContents(int index, ItemStack stack) {

    }

    @Override
    public int getInventoryStackLimit() {
        return 0;
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer player) {
        return false;
    }

    @Override
    public void openInventory(EntityPlayer player) {

    }

    @Override
    public void closeInventory(EntityPlayer player) {

    }

    @Override
    public boolean isItemValidForSlot(int index, ItemStack stack) {
        return false;
    }

    @Override
    public int getField(int id) {
        return 0;
    }

    @Override
    public void setField(int id, int value) {

    }

    @Override
    public int getFieldCount() {
        return 0;
    }

    @Override
    public void clear() {

    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public boolean hasCustomName() {
        return false;
    }

    @Override
    public IChatComponent getDisplayName() {
        return null;
    }
}
