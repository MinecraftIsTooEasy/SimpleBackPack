package com.moddedmite.sbp.compat;

import baubles.api.BaubleSlotHelper;
import baubles.api.BaubleType;
import baubles.api.BaublesApi;
import baubles.api.IBaublePlugin;
import com.moddedmite.sbp.ItemBackpack;
import net.minecraft.EntityLivingBase;
import net.minecraft.EntityPlayer;
import net.minecraft.IInventory;
import net.minecraft.ItemStack;

public class BaubleImpl implements IBaublePlugin {
    @Override
    public boolean canPutBaubleSlot(ItemStack itemStack, BaubleType baubleType) {
        return itemStack.getItem() instanceof ItemBackpack && baubleType == BaubleType.BACK;
    }

    @Override
    public void onWornTick(ItemStack itemStack, EntityLivingBase entityLivingBase) {
    }

    @Override
    public void onEquipped(ItemStack itemStack, EntityLivingBase entityLivingBase) {
    }

    @Override
    public void onUnequipped(ItemStack itemStack, EntityLivingBase entityLivingBase) {
    }

    public static int countBackpacks(EntityPlayer player) {
        int count = 0;
        IInventory baubles = BaublesApi.getBaubles(player);
        if (baubles != null) {
            for (int i = 0; i < baubles.getSizeInventory(); i++) {
                ItemStack stack = baubles.getStackInSlot(i);
                if (stack != null && stack.getItem() instanceof ItemBackpack) {
                    count++;
                }
            }
        }
        return count;
    }

    public static ItemStack getBackStack(EntityPlayer player) {
        IInventory baubles = BaublesApi.getBaubles(player);
        if (baubles != null) {
            ItemStack back = baubles.getStackInSlot(BaubleSlotHelper.BACK_SLOT);
            if (back != null && back.getItem() instanceof ItemBackpack) {
                return back;
            }
        }
        return null;
    }
}
