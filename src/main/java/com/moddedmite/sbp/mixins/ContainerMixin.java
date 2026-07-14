package com.moddedmite.sbp.mixins;

import com.moddedmite.sbp.InventoryBackpack;
import com.moddedmite.sbp.ItemBackpack;
import net.minecraft.EntityPlayer;
import net.minecraft.ItemStack;
import net.minecraft.Slot;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(net.minecraft.Container.class)
public class ContainerMixin {
    @Shadow
    public List<Slot> inventorySlots;

    @Inject(method = "slotClick", at = @At("HEAD"), cancellable = true)
    private void onSlotClick(int slotNum, int clickMode, int clickType, boolean par4, EntityPlayer player, CallbackInfoReturnable<ItemStack> cir) {
        if (InventoryBackpack.currentlyOpenBackpack != null && slotNum >= 0 && slotNum < this.inventorySlots.size()) {
            Slot slot = this.inventorySlots.get(slotNum);
            if (slot != null) {
                ItemStack stack = slot.getStack();
                if (stack != null && stack.getItem() instanceof ItemBackpack) {
                    cir.setReturnValue(null);
                }
            }
        }
    }
}
