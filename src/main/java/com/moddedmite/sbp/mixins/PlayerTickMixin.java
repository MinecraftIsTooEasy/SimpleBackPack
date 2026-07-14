package com.moddedmite.sbp.mixins;

import com.moddedmite.sbp.ItemBackpack;
import com.moddedmite.sbp.compat.BaubleImpl;
import net.minecraft.*;
import net.xiaoyu233.fml.FishModLoader;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityPlayer.class)
public class PlayerTickMixin {
    @Inject(method = "onUpdate", at = @At("RETURN"))
    private void onPlayerTick(CallbackInfo ci) {
        EntityPlayer player = (EntityPlayer) (Object) this;
        if (player.onServer()) {
            applyBackpackSlowdown(player);
        }
    }

    private void applyBackpackSlowdown(EntityPlayer player) {
        int count = countBackpacks(player);
        if (count >= 3) {
            player.addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 20, 4));
        } else if (count == 2) {
            player.addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 20, 1));
        }
    }

    private int countBackpacks(EntityPlayer player) {
        int count = 0;
        for (int i = 0; i < player.inventory.mainInventory.length; i++) {
            ItemStack stack = player.inventory.mainInventory[i];
            if (stack != null && stack.getItem() instanceof ItemBackpack) {
                count++;
            }
        }
        if (FishModLoader.hasMod("baubles")) {
            count += BaubleImpl.countBackpacks(player);
        }
        return count;
    }
}
