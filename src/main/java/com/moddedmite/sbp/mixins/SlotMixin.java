package com.moddedmite.sbp.mixins;

import com.moddedmite.sbp.InventoryBackpack;
import com.moddedmite.sbp.ItemBackpack;
import net.minecraft.EntityPlayer;
import net.minecraft.ItemStack;
import net.minecraft.Slot;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Slot.class)
public class SlotMixin {
    // 打开背包时锁定所有背包，不允许拿起
    @Inject(method = "canTakeStack", at = @At("HEAD"), cancellable = true)
    private void onCanTakeStack(EntityPlayer player, CallbackInfoReturnable<Boolean> cir) {
        if (InventoryBackpack.currentlyOpenBackpack != null) {
            Slot self = (Slot) (Object) this;
            ItemStack stack = self.getStack();
            if (stack != null && stack.getItem() instanceof ItemBackpack) {
                cir.setReturnValue(false);
            }
        }
    }
}
