package com.moddedmite.sbp.mixins;

import com.moddedmite.sbp.ItemBackpack;
import com.moddedmite.sbp.inventory.ContainerBackpack;
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
    private void onSlotClick(int slotNum, int clickMode, int clickType, boolean holding_shift, EntityPlayer player, CallbackInfoReturnable<ItemStack> cir) {
        // 以玩家当前打开的容器类型判断：客户端 currentlyOpenBackpack 为 null，
        // 只有用容器类型判断才能让客户端也拦截，避免客户端先本地移动再被服务端拒绝产生不同步
        if (!(player.openContainer instanceof ContainerBackpack)) {
            return;
        }
        // 数字键交换（clickType == 2）：目标快捷栏格是背包时也禁止，否则背包会被换到悬停格
        if (clickType == 2 && clickMode >= 0 && clickMode < 9) {
            ItemStack hotbarStack = player.inventory.getStackInSlot(clickMode);
            if (hotbarStack != null && hotbarStack.getItem() instanceof ItemBackpack) {
                cir.setReturnValue(null);
                return;
            }
        }
        if (slotNum >= 0 && slotNum < this.inventorySlots.size()) {
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
