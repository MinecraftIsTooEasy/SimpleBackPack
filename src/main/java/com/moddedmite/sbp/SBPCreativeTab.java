package com.moddedmite.sbp;

import huix.glacier.api.extension.creativetab.GlacierCreativeTabs;

public class SBPCreativeTab extends GlacierCreativeTabs {
    public static final SBPCreativeTab BACKPACK_TAB = new SBPCreativeTab();

    public SBPCreativeTab() {
        super("SimpleBackPack");
    }

    @Override
    public int getTabIconItemIndex() {
        return SBPRegistry.backpackLeather.itemID;
    }
}
