package com.moddedmite.sbp;

import fi.dy.masa.malilib.config.SimpleConfigs;
import fi.dy.masa.malilib.config.options.ConfigHotkey;

import java.util.List;

public class SBPConfigs extends SimpleConfigs {
    public static final ConfigHotkey OPEN_BACKPACK;
    private static final SBPConfigs INSTANCE;

    static {
        OPEN_BACKPACK = new ConfigHotkey("simplebackpack.openBackpack", "B", "打开背包快捷键");
        INSTANCE = new SBPConfigs();
    }

    private SBPConfigs() {
        super(SimpleBackPack.MOD_ID, List.of(OPEN_BACKPACK), List.of());
    }

    public static SBPConfigs getInstance() {
        return INSTANCE;
    }
}
