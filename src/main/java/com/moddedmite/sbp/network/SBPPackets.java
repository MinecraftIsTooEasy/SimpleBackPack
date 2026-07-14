package com.moddedmite.sbp.network;

import com.moddedmite.sbp.SimpleBackPack;
import moddedmite.rustedironcore.network.PacketReader;
import net.minecraft.ResourceLocation;

public class SBPPackets {
    public static final ResourceLocation OPEN_BACKPACK = new ResourceLocation(SimpleBackPack.MOD_ID, "OpenBackpack");
    public static final ResourceLocation OPEN_WINDOW = new ResourceLocation(SimpleBackPack.MOD_ID, "OpenWindow");

    public static void init() {
        PacketReader.registerServerPacketReader(OPEN_BACKPACK, C2SOpenBackpack::new);
        PacketReader.registerClientPacketReader(OPEN_WINDOW, S2COpenWindow::new);
    }
}
