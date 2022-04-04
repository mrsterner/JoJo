package dev.mrsterner.jojo.mixin;

import dev.mrsterner.jojo.api.stand.Stand;
import dev.mrsterner.jojo.api.stand.StandUtils;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.hud.InGameOverlayRenderer;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Environment(EnvType.CLIENT)
@Mixin(InGameOverlayRenderer.class)
public abstract class InGameOverlayRendererMixin {

    @Inject(method = "renderFireOverlay", at = @At("HEAD"), cancellable = true)
    private static void dontGetSprite(MinecraftClient minecraftClient, MatrixStack matrixStack, CallbackInfo ci){
        if(StandUtils.getStand(minecraftClient.player) == Stand.THE_SUN){
            ci.cancel();
        }
    }
}
