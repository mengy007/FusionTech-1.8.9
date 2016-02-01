package com.techmafia.mcmods.mrfusion.tileentity;

import cofh.api.energy.IEnergyStorage;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.ITickable;
import net.minecraft.world.World;

/**
 * Created by mengy007 on 1/31/2016.
 */
public class TileEntityMrFusion extends TileEntity implements ITickable, IEnergyStorage, IInventory {
    final int NUMBER_OF_SLOTS = 1;
    final String DISPLAY_NAME = "Mr. Fusion";
    //EntityPlayer playersWatching[] = new EntityPlayer[];

    protected int energy;
    protected int capacity;

    private ItemStack itemStack;

    public TileEntityMrFusion() {
        this(0);
    }

    public TileEntityMrFusion(int capacity) {
        this.capacity = capacity;
    }

    /* Server sync */
    @Override
    public Packet getDescriptionPacket() {
        NBTTagCompound nbtTagCompound = new NBTTagCompound();
        writeToNBT(nbtTagCompound);
        int metadata = getBlockMetadata();
        return new S35PacketUpdateTileEntity(this.pos, metadata, nbtTagCompound);
    }

    @Override
    public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
        readFromNBT(pkt.getNbtCompound());
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);

        /* IEnergyContainer */
        this.energy = nbt.getInteger("Energy");

        if (this.energy > this.capacity) {
            this.energy = this.capacity;
        }

        /* IInventory */
        final byte NBT_TYPE_COMPOUND = 10;       // See NBTBase.createNewByType() for a listing
        NBTTagList dataForAllSlots = nbt.getTagList("Items", NBT_TYPE_COMPOUND);

        itemStack = null;
        NBTTagCompound dataForOneSlot = dataForAllSlots.getCompoundTagAt(0);
        this.itemStack = ItemStack.loadItemStackFromNBT(dataForOneSlot);
    }

    @Override
    public void writeToNBT(NBTTagCompound nbt) {
        super.writeToNBT(nbt);

        /* IEnergyContainer */
        if (this.energy < 0) {
            this.energy = 0;
        }

        nbt.setInteger("Energy", this.energy);

        /* IInventory */
        if (itemStack != null) {
            NBTTagList dataForAllSlots = new NBTTagList();
            NBTTagCompound dataForThisSlot = new NBTTagCompound();
            dataForThisSlot.setByte("Slot", (byte) 0);
            this.itemStack.writeToNBT(dataForThisSlot);
            dataForAllSlots.appendTag(dataForThisSlot);

            nbt.setTag("Items", dataForAllSlots);
        }
    }

    /* ITickable */
    public void update() {
        if (!this.hasWorldObj()) return;
        World world = this.getWorld();
        if (world.isRemote) return; // Don't do anything on client side

        this.receiveEnergy(1, false);

        this.markDirty();
    }

    /* IWorldNameable */
    @Override
    public String getName() {
        return DISPLAY_NAME;
    }

    @Override
    public boolean hasCustomName() {
        return true;
    }

    @Override
    public IChatComponent getDisplayName() {
        return (IChatComponent)(this.hasCustomName() ? new ChatComponentText(this.getName()) : new ChatComponentTranslation(this.getName(), new Object[0]));
    }

    /* IInventory */
    // Gets the number of slots in the inventory
    @Override
    public int getSizeInventory() {
        return NUMBER_OF_SLOTS;
    }

    // Gets the stack in the given slot
    @Override
    public ItemStack getStackInSlot(int slotIndex) {
        return itemStack;
    }

    /**
     * Removes some of the units from itemstack in the given slot, and returns as a separate itemstack
     * @param slotIndex the slot number to remove the items from
     * @param count the number of units to remove
     * @return a new itemstack containing the units removed from the slot
     */
    @Override
    public ItemStack decrStackSize(int slotIndex, int count) {
        ItemStack itemStackInSlot = getStackInSlot(slotIndex);
        if (itemStackInSlot == null) return null;

        ItemStack itemStackRemoved;
        if (itemStackInSlot.stackSize <= count) {
            itemStackRemoved = itemStackInSlot;
            setInventorySlotContents(slotIndex, null);
        } else {
            itemStackRemoved = itemStackInSlot.splitStack(count);
            if (itemStackInSlot.stackSize == 0) {
                setInventorySlotContents(slotIndex, null);
            }
        }
        markDirty();
        return itemStackRemoved;
    }

    // overwrites the stack in the given slotIndex with the given stack
    @Override
    public void setInventorySlotContents(int slotIndex, ItemStack itemstack) {
        itemStack = itemstack;
        if (itemstack != null && itemstack.stackSize > getInventoryStackLimit()) {
            itemstack.stackSize = getInventoryStackLimit();
        }
        markDirty();
    }

    // This is the maximum number if items allowed in each slot
    // This only affects things such as hoppers trying to insert items you need to use the container to enforce this for players
    // inserting items via the gui
    @Override
    public int getInventoryStackLimit() {
        return 64;
    }

    @Override
    public ItemStack removeStackFromSlot(int slot) {
        return itemStack;
    }

    // Return true if the given player is able to use this block. In this case it checks that
    // 1) the world tileentity hasn't been replaced in the meantime, and
    // 2) the player isn't too far away from the centre of the block
    @Override
    public boolean isUseableByPlayer(EntityPlayer player) {
        if (this.worldObj.getTileEntity(this.pos) != this) return false;
        final double X_CENTRE_OFFSET = 0.5;
        final double Y_CENTRE_OFFSET = 0.5;
        final double Z_CENTRE_OFFSET = 0.5;
        final double MAXIMUM_DISTANCE_SQ = 8.0 * 8.0;
        return player.getDistanceSq(pos.getX() + X_CENTRE_OFFSET, pos.getY() + Y_CENTRE_OFFSET, pos.getZ() + Z_CENTRE_OFFSET) < MAXIMUM_DISTANCE_SQ;
    }

    // Return true if the given stack is allowed to go in the given slot.  In this case, we can insert anything.
    // This only affects things such as hoppers trying to insert items you need to use the container to enforce this for players
    // inserting items via the gui
    @Override
    public boolean isItemValidForSlot(int slotIndex, ItemStack itemstack) {
        return true;
    }

    @Override
    public void openInventory(EntityPlayer player) {}

    @Override
    public void closeInventory(EntityPlayer player) {}

    @Override
    public int getField(int id) {
        return 0;
    }

    @Override
    public void setField(int id, int value) {}

    @Override
    public int getFieldCount() {
        return 0;
    }

    @Override
    public void clear() {
        itemStack = null;
    }

    /* IEnergyStorage */
    public void setCapacity(int capacity) {
        this.capacity = capacity;

        if (this.energy > capacity) {
            this.energy = capacity;
        }
    }

    public int receiveEnergy(int maxReceive, boolean simulate) {
        int energyReceived = Math.min(capacity - energy, maxReceive);

        if (!simulate) {
            energy += energyReceived;
        }
        return energyReceived;
    }

    public int extractEnergy(int maxExtract, boolean simulate) {
        int energyExtracted = Math.min(energy, maxExtract);

        if (!simulate) {
            energy -= energyExtracted;
        }
        return energyExtracted;
    }

    public int getMaxEnergyStored() {
        return capacity;
    }

    public int getEnergyStored() {
        return energy;
    }
}
