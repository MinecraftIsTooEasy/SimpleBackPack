package com.moddedmite.sbp;

import huix.glacier.api.entrypoint.IGameRegistry;
import huix.glacier.api.registry.MinecraftRegistry;
import net.minecraft.Material;
import net.xiaoyu233.fml.reload.utils.IdUtil;

public class SBPRegistry implements IGameRegistry {

    public static ItemBackpack backpackLeather;
    public static ItemBackpack backpackCopper;
    public static ItemBackpack backpackIron;
    public static ItemBackpack backpackAncientMetal;
    public static ItemBackpack backpackMithril;
    public static ItemBackpack backpackAdamantium;

    // 兼容背包（仅当对应 mod 加载时，由 SBPCompat 通过 ItemRegistryEvent 注册）
    public static ItemBackpack backpackNickel;     // ITF-Reborn：铜背包 + 镍锭，27格
    public static ItemBackpack backpackTungsten;   // ITF-Reborn：铁背包 + 钨钢锭，36格
    public static ItemBackpack backpackUru;         // ITF-Reborn：艾德曼背包 + 乌鲁金属锭，72格
    public static ItemBackpack backpackVibranium;   // MITE-ITE：艾德曼背包 + 振金锭，72格
    public static ItemBackpack backpackInfinity;    // BeyondExtreme：振金背包 + 无尽锭，133格

    @Override
    public void onGameRegistry() {
        MinecraftRegistry registry = new MinecraftRegistry("simplebackpack");

        backpackLeather = new ItemBackpack(IdUtil.getNextItemID(), Material.stone, "simplebackpack:backpack_leather", SBPConfigs.getValidatedSize(SBPConfigs.BACKPACK_LEATHER_SIZE));
        backpackCopper = new ItemBackpack(IdUtil.getNextItemID(), Material.stone, "simplebackpack:backpack_copper", SBPConfigs.getValidatedSize(SBPConfigs.BACKPACK_COPPER_SIZE));
        backpackIron = new ItemBackpack(IdUtil.getNextItemID(), Material.stone, "simplebackpack:backpack_iron", SBPConfigs.getValidatedSize(SBPConfigs.BACKPACK_IRON_SIZE));
        backpackAncientMetal = new ItemBackpack(IdUtil.getNextItemID(), Material.stone, "simplebackpack:backpack_ancient_metal", SBPConfigs.getValidatedSize(SBPConfigs.BACKPACK_ANCIENT_METAL_SIZE));
        backpackMithril = new ItemBackpack(IdUtil.getNextItemID(), Material.stone, "simplebackpack:backpack_mithril", SBPConfigs.getValidatedSize(SBPConfigs.BACKPACK_MITHRIL_SIZE));
        backpackAdamantium = new ItemBackpack(IdUtil.getNextItemID(), Material.stone, "simplebackpack:backpack_adamantium", SBPConfigs.getValidatedSize(SBPConfigs.BACKPACK_ADAMANTIUM_SIZE));

        registry.registerItem("simplebackpack:backpack_leather", "backpack_leather", backpackLeather);
        registry.registerItem("simplebackpack:backpack_copper", "backpack_copper", backpackCopper);
        registry.registerItem("simplebackpack:backpack_iron", "backpack_iron", backpackIron);
        registry.registerItem("simplebackpack:backpack_ancient_metal", "backpack_ancient_metal", backpackAncientMetal);
        registry.registerItem("simplebackpack:backpack_mithril", "backpack_mithril", backpackMithril);
        registry.registerItem("simplebackpack:backpack_adamantium", "backpack_adamantium", backpackAdamantium);

        // 兼容背包物品注册移至 SBPCompat（通过 ItemRegistryEvent 事件总线，参考 UtilityCraft）
    }
}
