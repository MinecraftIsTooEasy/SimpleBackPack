package com.moddedmite.sbp;

import com.moddedmite.sbp.network.C2SOpenBackpack;
import fi.dy.masa.malilib.config.ConfigManager;
import fi.dy.masa.malilib.event.InitializationHandler;
import fi.dy.masa.malilib.hotkeys.IHotkeyCallback;
import fi.dy.masa.malilib.hotkeys.IKeybind;
import fi.dy.masa.malilib.hotkeys.KeyAction;
import fi.dy.masa.malilib.interfaces.IInitializationHandler;
import moddedmite.rustedironcore.network.Network;

public class SBPKeybindings {
    public static void register() {
        InitializationHandler.getInstance().registerInitializationHandler(new IInitializationHandler() {
            @Override
            public void registerModHandlers() {
                SBPConfigs.getInstance().load();
                ConfigManager.getInstance().registerConfig(SBPConfigs.getInstance());
                SBPConfigs.OPEN_BACKPACK.getKeybind().setCallback(new IHotkeyCallback() {
                    @Override
                    public boolean onKeyAction(KeyAction action, IKeybind key) {
                        Network.sendToServer(new C2SOpenBackpack());
                        return true;
                    }
                });
            }
        });
    }
}
