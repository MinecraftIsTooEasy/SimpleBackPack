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
    }
}
