package dev.mrsterner.jojo.client.renderlayer;

import dev.mrsterner.jojo.JoJo;
import dev.mrsterner.jojo.client.shader.JoJoShader;
import dev.mrsterner.jojo.mixin.RenderLayerAccessor;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.RenderPhase;
import net.minecraft.client.render.VertexFormat;
import net.minecraft.client.render.VertexFormats;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;

import java.util.function.Function;

public class JoJoRenderLayers extends RenderLayer {
    public JoJoRenderLayers(String name, VertexFormat vertexFormat, VertexFormat.DrawMode drawMode, int expectedBufferSize, boolean hasCrumbling, boolean translucent, Runnable startAction, Runnable endAction) {
        super(name, vertexFormat, drawMode, expectedBufferSize, hasCrumbling, translucent, startAction, endAction);
    }

    /**
     * Used by {@link JoJoRenderLayers#STAND_AURA_LAYER} to create the new renderlayer
     * @param name
     * @param format
     * @param mode
     * @param bufSize
     * @param hasCrumbling
     * @param sortOnUpload
     * @param glState
     * @return
     */
    private static RenderLayer makeLayer(String name, VertexFormat format, VertexFormat.DrawMode mode, int bufSize, boolean hasCrumbling, boolean sortOnUpload, RenderLayer.MultiPhaseParameters glState) {
        return RenderLayerAccessor.of(name, format, mode, bufSize, hasCrumbling, sortOnUpload, glState);
    }

    public static final Function<Identifier, RenderLayer> STAND_AURA_LAYER = Util.memoize(texture -> {
        RenderLayer.MultiPhaseParameters glState = RenderLayer.MultiPhaseParameters.builder()
        .shader(new RenderPhase.Shader(JoJoShader::getStandAura))
        .texture(new RenderPhase.Texture(texture, false, false))
        .transparency(RenderLayer.TRANSLUCENT_TRANSPARENCY)
        .cull(DISABLE_CULLING)
        .lightmap(ENABLE_LIGHTMAP)
        .build(true);
        return makeLayer(JoJo.MODID + "jojo_stand_aura", VertexFormats.POSITION_COLOR_TEXTURE_LIGHT_NORMAL, VertexFormat.DrawMode.QUADS, 256, true, true, glState);
    });
}
