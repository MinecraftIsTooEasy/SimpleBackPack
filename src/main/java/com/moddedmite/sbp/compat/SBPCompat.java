package com.moddedmite.sbp.compat;

import com.moddedmite.sbp.ItemBackpack;
import com.moddedmite.sbp.SBPConfigs;
import com.moddedmite.sbp.SBPRegistry;
import net.minecraft.Material;
import net.xiaoyu233.fml.reload.event.ItemRegistryEvent;
import net.xiaoyu233.fml.reload.utils.IdUtil;

/**
 * 兼容背包物品注册（仅当对应 mod 加载时执行）。
 * 参考 UtilityCraft 的 UCCompat，通过 ItemRegistryEvent 事件总线注册，
 * 确保注册时机在所有 mod 物品注册阶段统一触发。
 */
public final class SBPCompat {

    private SBPCompat() {}

    public static void registerCompatItems(ItemRegistryEvent event) {
        if (SBPModChecker.HAS_ITFRB) {
            SBPRegistry.backpackNickel = new ItemBackpack(IdUtil.getNextItemID(), Material.stone, "simplebackpack:backpack_nickel", SBPConfigs.getValidatedSize(SBPConfigs.BACKPACK_NICKEL_SIZE));
            SBPRegistry.backpackTungsten = new ItemBackpack(IdUtil.getNextItemID(), Material.stone, "simplebackpack:backpack_tungsten", SBPConfigs.getValidatedSize(SBPConfigs.BACKPACK_TUNGSTEN_SIZE));
            SBPRegistry.backpackUru = new ItemBackpack(IdUtil.getNextItemID(), Material.stone, "simplebackpack:backpack_uru", SBPConfigs.getValidatedSize(SBPConfigs.BACKPACK_URU_SIZE));
            event.register("simplebackpack", "simplebackpack:backpack_nickel", "backpack_nickel", SBPRegistry.backpackNickel);
            event.register("simplebackpack", "simplebackpack:backpack_tungsten", "backpack_tungsten", SBPRegistry.backpackTungsten);
            event.register("simplebackpack", "simplebackpack:backpack_uru", "backpack_uru", SBPRegistry.backpackUru);
        }
        if (SBPModChecker.HAS_ITE) {
            SBPRegistry.backpackVibranium = new ItemBackpack(IdUtil.getNextItemID(), Material.stone, "simplebackpack:backpack_vibranium", SBPConfigs.getValidatedSize(SBPConfigs.BACKPACK_VIBRANIUM_SIZE));
            event.register("simplebackpack", "simplebackpack:backpack_vibranium", "backpack_vibranium", SBPRegistry.backpackVibranium);
        }
        if (SBPModChecker.HAS_BEX) {
            SBPRegistry.backpackInfinity = new ItemBackpack(IdUtil.getNextItemID(), Material.stone, "simplebackpack:backpack_infinity", SBPConfigs.getValidatedSize(SBPConfigs.BACKPACK_INFINITY_SIZE));
            event.register("simplebackpack", "simplebackpack:backpack_infinity", "backpack_infinity", SBPRegistry.backpackInfinity);
        }
    }
}
