package com.moddedmite.sbp;

import fi.dy.masa.malilib.config.SimpleConfigs;
import fi.dy.masa.malilib.config.options.ConfigHotkey;
import fi.dy.masa.malilib.config.options.ConfigInteger;

import java.util.List;

public class SBPConfigs extends SimpleConfigs {
    public static final int MAX_SIZE = 608; // 19*18，支持19列布局的最大值
    public static final int LARGE_THRESHOLD = 133; // 大于此值使用19列宽布局
    public static final ConfigHotkey OPEN_BACKPACK;
    public static final ConfigInteger BACKPACK_LEATHER_SIZE;
    public static final ConfigInteger BACKPACK_COPPER_SIZE;
    public static final ConfigInteger BACKPACK_IRON_SIZE;
    public static final ConfigInteger BACKPACK_ANCIENT_METAL_SIZE;
    public static final ConfigInteger BACKPACK_MITHRIL_SIZE;
    public static final ConfigInteger BACKPACK_ADAMANTIUM_SIZE;
    private static final SBPConfigs INSTANCE;

    static {
        OPEN_BACKPACK = new ConfigHotkey("simplebackpack.openBackpack", "B", "打开背包快捷键");
        BACKPACK_LEATHER_SIZE = new ConfigInteger("simplebackpack.backpackLeatherSize", 9, 9, MAX_SIZE, false, "皮革背包的格子数（<=133为9的倍数，>133为19的倍数）");
        BACKPACK_COPPER_SIZE = new ConfigInteger("simplebackpack.backpackCopperSize", 18, 9, MAX_SIZE, false, "铜背包的格子数（<=133为9的倍数，>133为19的倍数）");
        BACKPACK_IRON_SIZE = new ConfigInteger("simplebackpack.backpackIronSize", 27, 9, MAX_SIZE, false, "铁背包的格子数（<=133为9的倍数，>133为19的倍数）");
        BACKPACK_ANCIENT_METAL_SIZE = new ConfigInteger("simplebackpack.backpackAncientMetalSize", 36, 9, MAX_SIZE, false, "远古金属背包的格子数（<=133为9的倍数，>133为19的倍数）");
        BACKPACK_MITHRIL_SIZE = new ConfigInteger("simplebackpack.backpackMithrilSize", 45, 9, MAX_SIZE, false, "秘银背包的格子数（<=133为9的倍数，>133为19的倍数）");
        BACKPACK_ADAMANTIUM_SIZE = new ConfigInteger("simplebackpack.backpackAdamantiumSize", 54, 9, MAX_SIZE, false, "艾德曼背包的格子数（<=133为9的倍数，>133为19的倍数）");
        INSTANCE = new SBPConfigs();
    }

    private SBPConfigs() {
        super(SimpleBackPack.MOD_ID,
                List.of(OPEN_BACKPACK),
                List.of(BACKPACK_LEATHER_SIZE, BACKPACK_COPPER_SIZE, BACKPACK_IRON_SIZE,
                        BACKPACK_ANCIENT_METAL_SIZE, BACKPACK_MITHRIL_SIZE, BACKPACK_ADAMANTIUM_SIZE));
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
