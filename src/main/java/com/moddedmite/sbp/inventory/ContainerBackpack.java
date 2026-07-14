package com.moddedmite.sbp.inventory;

import net.minecraft.Container;
import net.minecraft.EntityPlayer;
import net.minecraft.IInventory;
import net.minecraft.ItemStack;
import net.minecraft.Slot;
import com.moddedmite.sbp.SBPConfigs;

public class ContainerBackpack extends Container {
    private final IInventory backpackInventory;
    private final int numRows;
    private final int numCols;
    private final boolean large;

    public ContainerBackpack(EntityPlayer player, IInventory inventory) {
        super(player);
        this.backpackInventory = inventory;
        int size = inventory.getSizeInventory();
        this.large = size >= SBPConfigs.LARGE_THRESHOLD;
        this.numCols = this.large ? 19 : 9;
        this.numRows = (size + this.numCols - 1) / this.numCols;
        inventory.openChest();

        // 背包格子
        for (int row = 0; row < this.numRows; row++) {
            for (int col = 0; col < this.numCols; col++) {
                int index = col + row * this.numCols;
                if (index >= size) break;
                addSlotToContainer(new Slot(inventory, index, 8 + col * 18, 18 + row * 18));
            }
        }

        // 玩家物品栏
        int playerX = this.large ? 98 : 8;
        int playerY = this.large ? this.numRows * 18 + 32 : 103 + (this.numRows - 4) * 18;
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 9; col++) {
                addSlotToContainer(new Slot(player.inventory, col + row * 9 + 9, playerX + col * 18, playerY + row * 18));
            }
        }
        // 快捷栏
        for (int col = 0; col < 9; col++) {
            addSlotToContainer(new Slot(player.inventory, col, playerX + col * 18, playerY + 58));
        }
    }

    @Override
    public boolean canInteractWith(EntityPlayer player) {
        return this.backpackInventory.isUseableByPlayer(player);
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer player, int slotIndex) {
        ItemStack result = null;
        Slot slot = (Slot) this.inventorySlots.get(slotIndex);
        if (slot != null && slot.getHasStack()) {
            ItemStack stack = slot.getStack();
            result = stack.copy();
            int backpackSlots = this.backpackInventory.getSizeInventory();
            if (slotIndex < backpackSlots) {
                if (!mergeItemStack(stack, backpackSlots, this.inventorySlots.size(), true)) {
                    return null;
                }
            } else if (!mergeItemStack(stack, 0, backpackSlots, false)) {
                return null;
            }
            if (stack.stackSize == 0) {
                slot.putStack(null);
            } else {
                slot.onSlotChanged();
            }
        }
        return result;
    }

    @Override
    public void onContainerClosed(EntityPlayer player) {
        super.onContainerClosed(player);
        this.backpackInventory.closeChest();
    }
}
