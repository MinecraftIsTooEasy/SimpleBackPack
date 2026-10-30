package com.moddedmite.sbp.mixins;

import com.moddedmite.sbp.InventoryBackpack;
import com.moddedmite.sbp.ItemBackpack;
import net.minecraft.EntityItem;
import net.minecraft.EntityPlayer;
import net.minecraft.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EntityPlayer.class)
public class DropItemMixin {
    // 背包打开期间禁止丢弃背包（Q 键丢弃路径）
    @Inject(method = "dropOneItem", at = @At("HEAD"), cancellable = true)
    private void onDropOneItem(boolean dropStack, CallbackInfoReturnable<EntityItem> cir) {
        if (InventoryBackpack.currentlyOpenBackpack == null) {
            return;
        }
        ItemStack heldStack = ((EntityPlayer) (Object) this).getHeldItemStack();
        if (heldStack != null && heldStack.getItem() instanceof ItemBackpack) {
            cir.setReturnValue(null);
        }
    }
}
