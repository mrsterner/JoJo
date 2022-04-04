package dev.mrsterner.jojo.mixin;

import com.mojang.datafixers.util.Pair;
import dev.mrsterner.jojo.client.shader.JoJoShader;
import net.minecraft.client.gl.Program;
import net.minecraft.client.gl.ShaderEffect;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.render.Shader;
import net.minecraft.client.render.VertexFormats;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.function.Consumer;

@Mixin(GameRenderer.class)
public class GameRendererMixin {

    @Unique
    private final HashMap<Identifier, ShaderEffect> shaderEffects = new HashMap<Identifier, ShaderEffect>();


    @Inject(method = "reload", at = @At("HEAD"))
    private void reloadSpaceTime(ResourceManager manager, CallbackInfo ci) {
        shaderEffects.forEach((id, shaderEffect) -> shaderEffect.close());
        shaderEffects.clear();
    }

    @Inject(method = "loadShaders", at = @At(value = "INVOKE_ASSIGN", target = "Ljava/util/List;add(Ljava/lang/Object;)Z", remap = false), locals = LocalCapture.CAPTURE_FAILHARD)
    private void loadShaders(ResourceManager manager, CallbackInfo ci, List<Program> list, List<Pair<Shader, Consumer<Shader>>> list2)
    throws IOException {
        JoJoShader.init(manager, list2);
    }
}
