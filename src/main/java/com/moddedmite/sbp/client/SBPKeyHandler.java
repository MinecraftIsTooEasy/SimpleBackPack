package com.moddedmite.sbp.client;

import com.moddedmite.sbp.network.C2SOpenBackpack;
import moddedmite.rustedironcore.api.event.Handlers;
import moddedmite.rustedironcore.api.event.listener.IKeybindingListener;
import moddedmite.rustedironcore.api.event.listener.ITickListener;
import moddedmite.rustedironcore.keybinding.KeyBindingExtra;
import moddedmite.rustedironcore.network.Network;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.KeyBinding;
import net.minecraft.Minecraft;
import net.minecraft.server.MinecraftServer;

import java.lang.reflect.Method;
import java.util.function.Consumer;

/**
 * 客户端按键处理器：通过 RustedIronCore 的 KeybindingHandler 注册"打开背包"按键（默认 B），
 * 在 TickHandler 中轮询按键状态并边沿检测触发发包。
 * 同时通过反射向 BetterGameSetting 注册按键分组，使按键在 BGS 控制界面中归类显示。
 * 参考 BearGrylls 的 EatKeyHandler 和 VeinMiner 的 ActivateMinerKeybindManager 实现。
 */
@Environment(EnvType.CLIENT)
public class SBPKeyHandler implements IKeybindingListener, ITickListener {

    /** B 键的 LWJGL 键码 */
    private static final int KEY_B = 48;
    private static final String KEY_CATEGORY = "simplebackpack.key.category";

    public final KeyBindingExtra openBackpack;
    private boolean wasPressed = false;

    public SBPKeyHandler() {
        this.openBackpack = new KeyBindingExtra("key.simplebackpack.open", KEY_B, KEY_CATEGORY);
        this.registerBetterGameSettingCategory();
        Handlers.Keybinding.register(this);
        Handlers.Tick.register(this);
    }

    /**
     * 通过反射向 BetterGameSetting 注册按键分类，使按键在 BGS 控制界面中分组显示。
     * 使用反射避免对 BetterGameSetting 硬依赖（未安装时跳过）。
     */
    private void registerBetterGameSettingCategory() {
        try {
            Class<?> bgsClass = Class.forName("moddedmite.xylose.bettergamesetting.client.KeyBindingExtra");
            Method method = bgsClass.getMethod("setKeyKeyCategory", String.class, String.class);
            method.invoke(null, this.openBackpack.getKeyDescription(), this.openBackpack.getKeyCategory());
        } catch (Throwable ignored) {
            // BetterGameSetting 未安装，按键将显示在未分类组
        }
    }

    @Override
    public void onKeybindingRegister(Consumer<KeyBinding> registry) {
        registry.accept(this.openBackpack);
    }

    @Override
    public void onClientTick(Minecraft mc) {
        if (mc.thePlayer == null || mc.currentScreen != null) {
            this.wasPressed = false;
            return;
        }
        boolean pressed = this.openBackpack.pressed;
        // 边沿检测：仅在按下瞬间触发一次，避免持续按住时重复发包
        if (pressed && !this.wasPressed) {
            Network.sendToServer(new C2SOpenBackpack());
        }
        this.wasPressed = pressed;
    }

    // --- 未使用的 ITickListener 方法 ---
    @Override
    public void onEntityPlayerTick(net.minecraft.EntityPlayer player) {
    }

    @Override
    public void onServerTick(MinecraftServer server) {
    }

    @Override
    public void onRenderTick(float partialTick) {
    }

    @Override
    public void onEntityTick(net.minecraft.Entity entity) {
    }
}
