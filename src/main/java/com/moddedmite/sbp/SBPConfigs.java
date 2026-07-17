package com.moddedmite.sbp;

import fi.dy.masa.malilib.config.SimpleConfigs;
import fi.dy.masa.malilib.config.options.ConfigHotkey;
import fi.dy.masa.malilib.config.options.ConfigInteger;

import java.util.List;

public class SBPConfigs extends SimpleConfigs {
    public static final int MAX_SIZE = 608;
    public static final int LARGE_THRESHOLD = 133;
    public static final ConfigHotkey OPEN_BACKPACK;
    public static final ConfigInteger BACKPACK_LEATHER_SIZE;
    public static final ConfigInteger BACKPACK_COPPER_SIZE;
    public static final ConfigInteger BACKPACK_IRON_SIZE;
    public static final ConfigInteger BACKPACK_ANCIENT_METAL_SIZE;
    public static final ConfigInteger BACKPACK_MITHRIL_SIZE;
    public static final ConfigInteger BACKPACK_ADAMANTIUM_SIZE;

    public static final ConfigInteger BACKPACK_NICKEL_SIZE;
    public static final ConfigInteger BACKPACK_TUNGSTEN_SIZE;
    public static final ConfigInteger BACKPACK_URU_SIZE;
    public static final ConfigInteger BACKPACK_VIBRANIUM_SIZE;
    public static final ConfigInteger BACKPACK_INFINITY_SIZE;
    private static final SBPConfigs INSTANCE;

    static {
        OPEN_BACKPACK = new ConfigHotkey("simplebackpack.openBackpack", "B", "打开背包快捷键");
        BACKPACK_LEATHER_SIZE = new ConfigInteger("simplebackpack.backpackLeatherSize", 9, 9, MAX_SIZE, false, "皮革背包的格子数（<=133为9的倍数，>133为19的倍数）");
        BACKPACK_COPPER_SIZE = new ConfigInteger("simplebackpack.backpackCopperSize", 18, 9, MAX_SIZE, false, "铜背包的格子数（<=133为9的倍数，>133为19的倍数）");
        BACKPACK_IRON_SIZE = new ConfigInteger("simplebackpack.backpackIronSize", 27, 9, MAX_SIZE, false, "铁背包的格子数（<=133为9的倍数，>133为19的倍数）");
        BACKPACK_ANCIENT_METAL_SIZE = new ConfigInteger("simplebackpack.backpackAncientMetalSize", 36, 9, MAX_SIZE, false, "远古金属背包的格子数（<=133为9的倍数，>133为19的倍数）");
        BACKPACK_MITHRIL_SIZE = new ConfigInteger("simplebackpack.backpackMithrilSize", 45, 9, MAX_SIZE, false, "秘银背包的格子数（<=133为9的倍数，>133为19的倍数）");
        BACKPACK_ADAMANTIUM_SIZE = new ConfigInteger("simplebackpack.backpackAdamantiumSize", 54, 9, MAX_SIZE, false, "艾德曼背包的格子数（<=133为9的倍数，>133为19的倍数）");
        BACKPACK_NICKEL_SIZE = new ConfigInteger("simplebackpack.backpackNickelSize", 27, 9, MAX_SIZE, false, "镍背包的格子数（<=133为9的倍数，>133为19的倍数，需ITF-Reborn）");
        BACKPACK_TUNGSTEN_SIZE = new ConfigInteger("simplebackpack.backpackTungstenSize", 36, 9, MAX_SIZE, false, "钨钢背包的格子数（<=133为9的倍数，>133为19的倍数，需ITF-Reborn）");
        BACKPACK_URU_SIZE = new ConfigInteger("simplebackpack.backpackUruSize", 72, 9, MAX_SIZE, false, "乌鲁金属背包的格子数（<=133为9的倍数，>133为19的倍数，需ITF-Reborn）");
        BACKPACK_VIBRANIUM_SIZE = new ConfigInteger("simplebackpack.backpackVibraniumSize", 72, 9, MAX_SIZE, false, "振金背包的格子数（<=133为9的倍数，>133为19的倍数，需MITE-ITE）");
        BACKPACK_INFINITY_SIZE = new ConfigInteger("simplebackpack.backpackInfinitySize", 133, 9, MAX_SIZE, false, "无尽背包的格子数（<=133为9的倍数，>133为19的倍数，需BeyondExtreme）");
        INSTANCE = new SBPConfigs();
    }

    private SBPConfigs() {
        super(SimpleBackPack.MOD_ID,
                List.of(OPEN_BACKPACK),
                List.of(BACKPACK_LEATHER_SIZE, BACKPACK_COPPER_SIZE, BACKPACK_IRON_SIZE,
                        BACKPACK_ANCIENT_METAL_SIZE, BACKPACK_MITHRIL_SIZE, BACKPACK_ADAMANTIUM_SIZE,
                        BACKPACK_NICKEL_SIZE, BACKPACK_TUNGSTEN_SIZE, BACKPACK_URU_SIZE,
                        BACKPACK_VIBRANIUM_SIZE, BACKPACK_INFINITY_SIZE));
    }

    public static SBPConfigs getInstance() {
        return INSTANCE;
    }

    public static int getValidatedSize(ConfigInteger config) {
        int value = config.getIntegerValue();
        int clamped = Math.max(9, Math.min(MAX_SIZE, value));
        if (clamped >= LARGE_THRESHOLD) {
            // >=133时，取19的倍数
            return ((clamped / 19) * 19);
        }
        // <133时，取9的倍数
        return (clamped / 9) * 9;
    }
}
