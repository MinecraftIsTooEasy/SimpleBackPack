package com.moddedmite.sbp.network;

import com.moddedmite.sbp.InventoryBackpack;
import com.moddedmite.sbp.ItemBackpack;
import com.moddedmite.sbp.api.IServerPlayer;
import com.moddedmite.sbp.compat.BaubleImpl;
import moddedmite.rustedironcore.network.Packet;
import moddedmite.rustedironcore.network.PacketByteBuf;
import net.minecraft.EntityPlayer;
import net.minecraft.ItemStack;
import net.minecraft.ResourceLocation;
import net.xiaoyu233.fml.FishModLoader;

public class C2SOpenBackpack implements Packet {
    public C2SOpenBackpack() {}

    public C2SOpenBackpack(PacketByteBuf buf) {}

    @Override
    public void write(PacketByteBuf buf) {}

    @Override
    public void apply(EntityPlayer player) {
        ItemStack backpack = findBackpack(player);
        if (backpack != null && backpack.getItem() instanceof ItemBackpack) {
            InventoryBackpack.currentlyOpenBackpack = backpack;
            ((IServerPlayer) player).sbp$displayBackpackGui(new InventoryBackpack(backpack, (ItemBackpack) backpack.getItem()));
        }
    }

    @Override
    public ResourceLocation getChannel() {
        return SBPPackets.OPEN_BACKPACK;
    }

    private ItemStack findBackpack(EntityPlayer player) {
        if (FishModLoader.hasMod("baubles")) {
            ItemStack back = BaubleImpl.getBackStack(player);
            if (back != null) return back;
        }
        for (int i = 0; i < player.inventory.mainInventory.length; i++) {
            ItemStack stack = player.inventory.mainInventory[i];
            if (stack != null && stack.getItem() instanceof ItemBackpack) {
                return stack;
            }
        }
        return null;
    }
}
