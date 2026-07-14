package com.moddedmite.sbp;

import net.minecraft.*;

public class InventoryBackpack implements IInventory {
    public static ItemStack currentlyOpenBackpack = null;

    private final ItemStack backpackStack;
    private final ItemBackpack backpackItem;
    private final int size;
    private final ItemStack[] items;

    public InventoryBackpack(ItemStack stack, ItemBackpack item) {
        this.backpackStack = stack;
        this.backpackItem = item;
        this.size = item.getInventorySize();
        this.items = new ItemStack[this.size];
        this.loadFromNBT();
    }

    private void loadFromNBT() {
        if (this.backpackStack.hasTagCompound()) {
            NBTTagList list = this.backpackStack.stackTagCompound.getTagList("Items");
            for (int i = 0; i < list.tagCount(); i++) {
                NBTTagCompound compound = (NBTTagCompound) list.tagAt(i);
                int slot = compound.getByte("Slot") & 0xFF;
                if (slot >= 0 && slot < this.size) {
                    this.items[slot] = ItemStack.loadItemStackFromNBT(compound);
                }
            }
        }
    }

    private void saveToNBT() {
        if (!this.backpackStack.hasTagCompound()) {
            this.backpackStack.setTagCompound(new NBTTagCompound());
        }
        NBTTagList list = new NBTTagList();
        for (int i = 0; i < this.size; i++) {
            if (this.items[i] != null) {
                NBTTagCompound compound = new NBTTagCompound();
                compound.setByte("Slot", (byte) i);
                this.items[i].writeToNBT(compound);
                list.appendTag(compound);
            }
        }
        this.backpackStack.stackTagCompound.setTag("Items", list);
    }

    @Override
    public int getSizeInventory() {
        return this.size;
    }

    @Override
    public ItemStack getStackInSlot(int slot) {
        return this.items[slot];
    }

    @Override
    public ItemStack decrStackSize(int slot, int amount) {
        ItemStack stack = this.items[slot];
        if (stack != null) {
            if (stack.stackSize <= amount) {
                this.items[slot] = null;
                this.onInventoryChanged();
                return stack;
            }
            ItemStack result = stack.splitStack(amount);
            if (stack.stackSize <= 0) {
                this.items[slot] = null;
            }
            this.onInventoryChanged();
            return result;
        }
        return null;
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int slot) {
        ItemStack stack = this.items[slot];
        this.items[slot] = null;
        return stack;
    }

    @Override
    public void setInventorySlotContents(int slot, ItemStack stack) {
        this.items[slot] = stack;
        if (stack != null && stack.stackSize > this.getInventoryStackLimit()) {
            stack.stackSize = this.getInventoryStackLimit();
        }
        this.onInventoryChanged();
    }

    @Override
    public String getCustomNameOrUnlocalized() {
        return this.backpackItem.getUnlocalizedName() + ".name";
    }

    @Override
    public boolean hasCustomName() {
        return false;
    }

    @Override
    public int getInventoryStackLimit() {
        return 64;
    }

    @Override
    public void onInventoryChanged() {
        this.saveToNBT();
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer player) {
        return true;
    }

    @Override
    public void openChest() {
    }

    @Override
    public void closeChest() {
        this.saveToNBT();
        currentlyOpenBackpack = null;
    }

    @Override
    public boolean isItemValidForSlot(int slot, ItemStack stack) {
        return true;
    }

    @Override
    public void destroyInventory() {
        for (int i = 0; i < this.size; i++) {
            this.items[i] = null;
        }
        this.saveToNBT();
    }
}
