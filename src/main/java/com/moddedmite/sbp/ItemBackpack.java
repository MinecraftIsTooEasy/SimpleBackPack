package com.moddedmite.sbp;

import com.moddedmite.sbp.api.IServerPlayer;
import net.minecraft.*;

public class ItemBackpack extends Item {
    private final int inventorySize;

    public ItemBackpack(int id, Material material, String texture, int size) {
        super(id, material, texture);
        this.inventorySize = size;
        this.setMaxStackSize(1);
        this.setCreativeTab(SBPCreativeTab.BACKPACK_TAB);
    }

    public int getInventorySize() {
        return this.inventorySize;
    }

    @Override
    public boolean onItemRightClick(EntityPlayer player, float partial_tick, boolean ctrl_is_down) {
        if (player.onServer()) {
            ItemStack heldStack = player.getHeldItemStack();
            if (heldStack != null && heldStack.getItem() instanceof ItemBackpack) {
                InventoryBackpack.currentlyOpenBackpack = heldStack;
                ((IServerPlayer) player).sbp$displayBackpackGui(new InventoryBackpack(heldStack, (ItemBackpack) heldStack.getItem()));
            }
        }
        return true;
    }
}
