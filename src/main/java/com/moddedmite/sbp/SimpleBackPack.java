package com.moddedmite.sbp;

import com.moddedmite.sbp.client.SBPKeyHandler;
import com.moddedmite.sbp.compat.SBPRecipeCompat;
import com.moddedmite.sbp.network.SBPPackets;
import fi.dy.masa.malilib.config.ConfigManager;
import moddedmite.rustedironcore.api.event.Handlers;
import moddedmite.rustedironcore.api.event.events.CraftingRecipeRegisterEvent;
import net.fabricmc.api.ModInitializer;
import net.minecraft.*;
import net.xiaoyu233.fml.ModResourceManager;
import net.xiaoyu233.fml.reload.event.MITEEvents;

public class SimpleBackPack implements ModInitializer {
    public static final String MOD_ID = "simplebackpack";

    @Override
    public void onInitialize() {
        ModResourceManager.addResourcePackDomain(MOD_ID);
        SBPConfigs.getInstance().load();
        ConfigManager.getInstance().registerConfig(SBPConfigs.getInstance());
        this.registerRecipes();
        // 通过 RustedIronCore 的 KeybindingHandler 注册"打开背包"按键（默认 B），并在 BetterGameSetting 中分组
        new SBPKeyHandler();
        SBPPackets.init();
        // 注册 FML 事件总线监听器，用于在 ItemRegistryEvent 触发时注册兼容背包物品（参考 UtilityCraft）
        MITEEvents.MITE_EVENT_BUS.register(new SBPFMLEvents());
    }

    private void registerRecipes() {
        Handlers.Crafting.register(this::onCraftingRecipeRegister);
    }

    private void onCraftingRecipeRegister(CraftingRecipeRegisterEvent event) {
        event.registerShapedRecipe(
                new ItemStack(SBPRegistry.backpackLeather, 1), true,
                "SLS", "SCS", "LLL",
                Character.valueOf('S'), Item.silk,
                Character.valueOf('L'), Item.leather,
                Character.valueOf('C'), Block.chest
        );

        event.registerShapedRecipe(
                new ItemStack(SBPRegistry.backpackCopper, 1), true,
                "III", "IBI", "III",
                Character.valueOf('I'), Item.ingotCopper,
                Character.valueOf('B'), SBPRegistry.backpackLeather
        ).extendsNBT();

        event.registerShapedRecipe(
                new ItemStack(SBPRegistry.backpackIron, 1), true,
                "III", "IBI", "III",
                Character.valueOf('I'), Item.ingotIron,
                Character.valueOf('B'), SBPRegistry.backpackCopper
        ).extendsNBT();

        event.registerShapedRecipe(
                new ItemStack(SBPRegistry.backpackAncientMetal, 1), true,
                "III", "IBI", "III",
                Character.valueOf('I'), Item.ingotAncientMetal,
                Character.valueOf('B'), SBPRegistry.backpackIron
        ).extendsNBT();

        event.registerShapedRecipe(
                new ItemStack(SBPRegistry.backpackMithril, 1), true,
                "III", "IBI", "III",
                Character.valueOf('I'), Item.ingotMithril,
                Character.valueOf('B'), SBPRegistry.backpackAncientMetal
        ).extendsNBT();

        event.registerShapedRecipe(
                new ItemStack(SBPRegistry.backpackAdamantium, 1), true,
                "III", "IBI", "III",
                Character.valueOf('I'), Item.ingotAdamantium,
                Character.valueOf('B'), SBPRegistry.backpackMithril
        ).extendsNBT();

        // 兼容背包配方（仅当对应 mod 加载时注册）
        SBPRecipeCompat.registerCompatRecipes(event);
    }
}
