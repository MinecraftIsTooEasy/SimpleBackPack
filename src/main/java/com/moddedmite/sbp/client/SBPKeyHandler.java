package com.moddedmite.sbp.client;

import com.moddedmite.sbp.network.C2SOpenBackpack;
import moddedmite.rustedironcore.api.event.Handlers;
import moddedmite.rustedironcore.api.event.listener.ITickListener;
import moddedmite.rustedironcore.api.keybinding.KeybindingV1;
import moddedmite.rustedironcore.network.Network;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.Minecraft;
import net.minecraft.ResourceLocation;
import net.minecraft.server.MinecraftServer;

/**
 * 客户端按键处理器：通过 RustedIronCore 1.5.7 的 KeybindingHandler 注册"打开背包"按键（默认 B）。
 * 使用 KeybindingV1 + 自定义 Category，按键会自动在 BetterGameSetting 控制界面中分组显示。
 * 在 TickHandler 中用 consumeClick() 边沿检测触发发包。
 */
@Environment(EnvType.CLIENT)
public class SBPKeyHandler implements ITickListener {

    /** B 键的 LWJGL 键码 */
    private static final int KEY_B = 48;

    /** 自定义按键分类，在 BetterGameSetting 中作为独立分组显示 */
    private static final KeybindingV1.Category BACKPACK_CATEGORY =
            KeybindingV1.Category.register(new ResourceLocation("simplebackpack", "backpack"));

    public final KeybindingV1 openBackpack;

    public SBPKeyHandler() {
        this.openBackpack = new KeybindingV1("key.simplebackpack.open", KEY_B, BACKPACK_CATEGORY);
        Handlers.Keybinding.register(event -> event.register(this.openBackpack));
        Handlers.Tick.register(this);
    }

    @Override
    public void onClientTick(Minecraft mc) {
        if (mc.thePlayer == null) {
            return;
        }
        // consumeClick() 自带边沿检测（基于 pressTime），仅在无 GUI 时发包
        if (this.openBackpack.consumeClick() && mc.currentScreen == null) {
            Network.sendToServer(new C2SOpenBackpack());
        }
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
