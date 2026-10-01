package com.moddedmite.sbp;

import com.moddedmite.sbp.api.IServerPlayer;
import net.minecraft.*;

import java.util.function.IntSupplier;

public class ItemBackpack extends Item {
    // 惰性读取配置：物品注册时机可能早于配置文件加载（生产环境中 Item.<clinit> 先于 main 入口点），
    // 因此不能在构造时固化大小，必须在每次使用时动态读取
    private final IntSupplier sizeSupplier;

    public ItemBackpack(int id, Material material, String texture, IntSupplier sizeSupplier) {
        super(id, material, texture);
        this.sizeSupplier = sizeSupplier;
        this.setMaxStackSize(1);
        this.setCreativeTab(SBPCreativeTab.BACKPACK_TAB);
    }

    public int getInventorySize() {
        return this.sizeSupplier.getAsInt();
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
