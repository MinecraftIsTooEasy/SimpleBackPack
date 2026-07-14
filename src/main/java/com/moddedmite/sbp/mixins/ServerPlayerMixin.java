package com.moddedmite.sbp.mixins;

import com.moddedmite.sbp.api.IServerPlayer;
import com.moddedmite.sbp.inventory.ContainerBackpack;
import com.moddedmite.sbp.network.S2COpenWindow;
import moddedmite.rustedironcore.network.Network;
import net.minecraft.EntityPlayer;
import net.minecraft.IInventory;
import net.minecraft.ServerPlayer;
import net.minecraft.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(ServerPlayer.class)
public abstract class ServerPlayerMixin extends EntityPlayer implements IServerPlayer {

    @Shadow
    private int currentWindowId;

    @Shadow
    protected abstract void incrementWindowID();

    public ServerPlayerMixin(World par1World, String par2Str) {
        super(par1World, par2Str);
    }

    @Override
    public void sbp$displayBackpackGui(IInventory inventory) {
        incrementWindowID();
        Network.sendToClient((ServerPlayer) (Object) this, new S2COpenWindow(
                this.currentWindowId,
                inventory.getCustomNameOrUnlocalized(),
                inventory.getSizeInventory(),
                inventory.hasCustomName()));
        this.openContainer = new ContainerBackpack((EntityPlayer) (Object) this, inventory);
        this.openContainer.windowId = this.currentWindowId;
        this.openContainer.addCraftingToCrafters((ServerPlayer) (Object) this);
    }
}
