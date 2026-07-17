package com.moddedmite.sbp.compat;

import com.moddedmite.sbp.SBPRegistry;

import moddedmite.rustedironcore.api.event.events.CraftingRecipeRegisterEvent;
import net.minecraft.Item;
import net.minecraft.ItemStack;
import net.moddedmite.mitemod.bex.register.BEXItems;
import net.oilcake.mitelros.registry.item.Items;
import net.xiaoyu233.mitemod.miteite.item.MITEITEItemRegistryInit;

public final class SBPRecipeCompat {

    private SBPRecipeCompat() {}

    public static void registerCompatRecipes(CraftingRecipeRegisterEvent event) {
        registerItfrbCompat(event);
        registerIteCompat(event);
        registerBexCompat(event);
    }

    private static void registerItfrbCompat(CraftingRecipeRegisterEvent event) {
        if (!SBPModChecker.HAS_ITFRB) return;

        if (Items.nickelIngot != null && SBPRegistry.backpackNickel != null) {
            event.registerShapedRecipe(
                    new ItemStack(SBPRegistry.backpackNickel, 1), true,
                    "III", "IBI", "III",
                    Character.valueOf('I'), Items.nickelIngot,
                    Character.valueOf('B'), SBPRegistry.backpackCopper
            ).extendsNBT();
        }
        if (Items.tungstenIngot != null && SBPRegistry.backpackTungsten != null) {
            event.registerShapedRecipe(
                    new ItemStack(SBPRegistry.backpackTungsten, 1), true,
                    "III", "IBI", "III",
                    Character.valueOf('I'), Items.tungstenIngot,
                    Character.valueOf('B'), SBPRegistry.backpackIron
            ).extendsNBT();
        }
        if (Items.uruIngot != null && SBPRegistry.backpackUru != null) {
            event.registerShapedRecipe(
                    new ItemStack(SBPRegistry.backpackUru, 1), true,
                    "III", "IBI", "III",
                    Character.valueOf('I'), Items.uruIngot,
                    Character.valueOf('B'), SBPRegistry.backpackAdamantium
            ).extendsNBT();
        }
    }

    private static void registerIteCompat(CraftingRecipeRegisterEvent event) {
        if (!SBPModChecker.HAS_ITE) return;

        if (MITEITEItemRegistryInit.VIBRANIUM_INGOT != null && SBPRegistry.backpackVibranium != null) {
            event.registerShapedRecipe(
                    new ItemStack(SBPRegistry.backpackVibranium, 1), true,
                    "III", "IBI", "III",
                    Character.valueOf('I'), MITEITEItemRegistryInit.VIBRANIUM_INGOT,
                    Character.valueOf('B'), SBPRegistry.backpackAdamantium
            ).extendsNBT();
        }
    }

    private static void registerBexCompat(CraftingRecipeRegisterEvent event) {
        if (!SBPModChecker.HAS_BEX) return;

        if (BEXItems.infinityingot != null
                && SBPRegistry.backpackInfinity != null
                && SBPRegistry.backpackVibranium != null) {
            event.registerShapedRecipe(
                    new ItemStack(SBPRegistry.backpackInfinity, 1), true,
                    "III", "IBI", "III",
                    Character.valueOf('I'), BEXItems.infinityingot,
                    Character.valueOf('B'), SBPRegistry.backpackVibranium
            ).extendsNBT();
        }
    }
}
