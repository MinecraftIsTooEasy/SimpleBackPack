package com.moddedmite.sbp.network;

import com.moddedmite.sbp.inventory.GuiBackpack;
import moddedmite.rustedironcore.network.Packet;
import moddedmite.rustedironcore.network.PacketByteBuf;
import net.minecraft.EntityPlayer;
import net.minecraft.InventoryBasic;
import net.minecraft.Minecraft;
import net.minecraft.ResourceLocation;

public class S2COpenWindow implements Packet {
    private final int windowId;
    private final String windowTitle;
    private final int slotsCount;
    private final boolean useProvidedWindowTitle;

    public S2COpenWindow(PacketByteBuf buf) {
        this(buf.readInt(), buf.readString(), buf.readInt(), buf.readBoolean());
    }

    public S2COpenWindow(int windowId, String windowTitle, int slotsCount, boolean useProvidedWindowTitle) {
        this.windowId = windowId;
        this.windowTitle = windowTitle == null ? "" : windowTitle;
        this.slotsCount = slotsCount;
        this.useProvidedWindowTitle = useProvidedWindowTitle;
    }

    @Override
    public void write(PacketByteBuf buf) {
        buf.writeInt(this.windowId);
        buf.writeString(this.windowTitle);
        buf.writeInt(this.slotsCount);
        buf.writeBoolean(this.useProvidedWindowTitle);
    }

    @Override
    public void apply(EntityPlayer player) {
        Minecraft.getMinecraft().displayGuiScreen(
                new GuiBackpack(player, new InventoryBasic(this.windowTitle, this.useProvidedWindowTitle, this.slotsCount)));
        player.openContainer.windowId = this.windowId;
    }

    @Override
    public ResourceLocation getChannel() {
        return SBPPackets.OPEN_WINDOW;
    }
}
