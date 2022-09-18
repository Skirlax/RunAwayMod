package dev.skyr.thebeast.client;

import com.mojang.blaze3d.systems.RenderSystem;
import dev.skyr.thebeast.TheBeast;
import dev.skyr.thebeast.networking.packet.WaterDataSync;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;

public class WaterHud  {
    private static final ResourceLocation WATER_FULL = new ResourceLocation(TheBeast.MODID, "textures/water/water_drop_full_small.png");
    private static final ResourceLocation WATER_EMPTY = new ResourceLocation(TheBeast.MODID, "textures/water/water_drop_empty.png");
    public static boolean waterOverlayVisible = true;

    public static  IGuiOverlay WATER_HUD = ((gui, poseStack, partialTick, screenWidth, screenHeight) -> {
        if (!waterOverlayVisible) {
            return;
        }

        int x = screenWidth / 2 - 91;
        int y = screenHeight - 49;

        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, WATER_EMPTY);
        for (int i = 0; i < 10; i++) {
            gui.blit(poseStack, x + i * 8, y, 0, 0, 9, 9, 9, 9);
        }
        RenderSystem.setShaderTexture(0, WATER_FULL);
        for(int i = 0; i < 10; i++) {
            if(WaterData.getPlayerThirst() > i) {
                gui.blit(poseStack, x + i * 8, y, 0, 0, 9, 9, 9, 9);
            } else {
                break;
            }
        }
    });




}
