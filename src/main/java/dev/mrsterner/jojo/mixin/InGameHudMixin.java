package dev.mrsterner.jojo.mixin;


import dev.mrsterner.jojo.JoJo;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Environment(EnvType.CLIENT)
@Mixin(InGameHud.class)
public abstract class InGameHudMixin extends DrawableHelper {
    private static final Identifier PHANTOMBLOOD_GUI_ICONS_TEXTURE = new Identifier(JoJo.MODID, "textures/gui/icons.png");
    @Shadow
    protected abstract PlayerEntity getCameraPlayer();

    @Shadow
    private int scaledHeight;

    @Shadow
    private int scaledWidth;

    @Shadow
    @Final
    private MinecraftClient client;


}