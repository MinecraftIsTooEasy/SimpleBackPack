package com.moddedmite.sbp;

import com.google.common.eventbus.Subscribe;
import com.moddedmite.sbp.compat.SBPCompat;
import net.xiaoyu233.fml.reload.event.ItemRegistryEvent;


public class SBPFMLEvents {

    @Subscribe
    public void onItemRegister(ItemRegistryEvent event) {
        SBPCompat.registerCompatItems(event);
    }
}
