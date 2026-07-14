package com.moddedmite.sbp.inventory;

import net.minecraft.EntityPlayer;
import net.minecraft.GuiContainer;
import net.minecraft.I18n;
import net.minecraft.IInventory;
import net.minecraft.ResourceLocation;
import net.minecraft.Tessellator;
import com.moddedmite.sbp.SBPConfigs;
import org.lwjgl.opengl.GL11;

public class GuiBackpack extends GuiContainer {
    private static final ResourceLocation VANILLA_BG = new ResourceLocation("textures/gui/container/generic_54.png");
    private static final ResourceLocation CUSTOM_BG = new ResourceLocation("simplebackpack:textures/gui/backpack_gui.png");

    private final IInventory playerInventory;
    private final IInventory backpackInventory;
    private final int inventoryRows;
    private final int inventoryCols;
    private final boolean useCustomTexture;

    public GuiBackpack(EntityPlayer player, IInventory inventory) {
        super(new ContainerBackpack(player, inventory));
        this.playerInventory = player.inventory;
        this.backpackInventory = inventory;
        this.allowUserInput = false;
        int size = inventory.getSizeInventory();
        this.useCustomTexture = size >= SBPConfigs.LARGE_THRESHOLD;
        this.inventoryCols = this.useCustomTexture ? 19 : 9;
        this.inventoryRows = (size + this.inventoryCols - 1) / this.inventoryCols;
        if (this.useCustomTexture) {
            this.xSize = 356;
            this.ySize = 114 + this.inventoryRows * 18;
        } else {
            this.xSize = 176;
            this.ySize = 114 + this.inventoryRows * 18;
        }
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int par1, int par2) {
        this.fontRenderer.drawString(
                this.backpackInventory.hasCustomName() ? this.backpackInventory.getCustomNameOrUnlocalized()
                        : I18n.getString(this.backpackInventory.getCustomNameOrUnlocalized()),
                8, 6, 4210752);
        this.fontRenderer.drawString(
                this.playerInventory.hasCustomName() ? this.playerInventory.getCustomNameOrUnlocalized()
                        : I18n.getString(this.playerInventory.getCustomNameOrUnlocalized()),
                this.useCustomTexture ? 98 : 8, this.ySize - 96 + 2, 4210752);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3) {
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        int x = (this.width - this.xSize) / 2;
        int y = (this.height - this.ySize) / 2;

        if (this.useCustomTexture) {
            this.mc.getTextureManager().bindTexture(CUSTOM_BG);
            // 512x512 纹理，使用 Tessellator 绘制
            // 顶部边框 (356x17)
            blit512(x, y, 0, 0, this.xSize, 17);
            // 背包格子行（平铺 356x18）
            for (int i = 0; i < this.inventoryRows; i++) {
                blit512(x, y + 17 + i * 18, 0, 17, this.xSize, 18);
            }
            // 背包底部边框 (356x7, y=143~149)
            blit512(x, y + 17 + this.inventoryRows * 18, 0, 143, this.xSize, 7);
            // 玩家物品栏 (176x90, 居中 x=90, y=150~239)
            blit512(x + 90, y + 17 + this.inventoryRows * 18 + 7, 90, 150, 176, 90);
        } else {
            this.mc.getTextureManager().bindTexture(VANILLA_BG);
            // 顶部边框
            drawTexturedModalRect(x, y, 0, 0, this.xSize, 17);
            // 背包格子行（平铺）
            for (int i = 0; i < this.inventoryRows; i++) {
                drawTexturedModalRect(x, y + 17 + i * 18, 0, 17, this.xSize, 18);
            }
            // 底部（玩家物品栏）
            drawTexturedModalRect(x, y + 17 + this.inventoryRows * 18, 0, 126, this.xSize, 96);
        }
    }

    private void blit512(int x, int y, int u, int v, int w, int h) {
        float fW = 1f / 512f;
        float fH = 1f / 512f;
        Tessellator t = Tessellator.instance;
        t.startDrawingQuads();
        t.addVertexWithUV(x,     y + h, this.zLevel,      u * fW,     (v + h) * fH);
        t.addVertexWithUV(x + w, y + h, this.zLevel, (u + w) * fW, (v + h) * fH);
        t.addVertexWithUV(x + w, y,     this.zLevel, (u + w) * fW,      v * fH);
        t.addVertexWithUV(x,     y,     this.zLevel,      u * fW,      v * fH);
        t.draw();
    }
}
