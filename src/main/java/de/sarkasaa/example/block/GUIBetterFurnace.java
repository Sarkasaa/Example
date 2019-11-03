package de.sarkasaa.example.block;

import com.mojang.blaze3d.platform.GlStateManager;
import de.sarkasaa.example.Example;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

public class GUIBetterFurnace extends ContainerScreen<ContainerBetterFurnace> {

    private static final ResourceLocation TEXTURE = new ResourceLocation(Example.MOD_ID, "textures/gui/betterfurnace.png");

    public GUIBetterFurnace(ContainerBetterFurnace screenContainer, PlayerInventory inv, ITextComponent name) {
        super(screenContainer, inv, name);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        this.minecraft.getTextureManager().bindTexture(TEXTURE);
        GlStateManager.color4f(1, 1, 1, 1);
        this.blit(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);

    }

    @Override
    public void render(int mouseX, int mouseY, float f) {
        this.renderBackground();
        super.render(mouseX, mouseY, f);
        this.renderHoveredToolTip(mouseX, mouseY);
    }
}
