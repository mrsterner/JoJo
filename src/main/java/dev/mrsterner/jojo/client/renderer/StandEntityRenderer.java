package dev.mrsterner.jojo.client.renderer;

import dev.mrsterner.jojo.JoJoClient;
import dev.mrsterner.jojo.client.model.StandEntityModel;
import dev.mrsterner.jojo.client.shader.JoJoShader;
import dev.mrsterner.jojo.common.entity.StandEntity;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.Shader;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class StandEntityRenderer extends GeoEntityRenderer<StandEntity> {
    public StandEntityRenderer(EntityRendererFactory.Context ctx) {
        super(ctx, new StandEntityModel());
    }

    @Override
    public RenderLayer getRenderType(StandEntity animatable, float partialTicks, MatrixStack stack, @Nullable VertexConsumerProvider renderTypeBuffer, @Nullable VertexConsumer vertexBuilder, int packedLightIn, Identifier textureLocation) {
        return RenderLayer.getEntityTranslucent(this.getTextureLocation(animatable));
    }

    @Override
    public void render(StandEntity entity, float entityYaw, float partialTicks, MatrixStack stack, VertexConsumerProvider bufferIn, int packedLightIn) {
        super.render(entity, entityYaw, partialTicks, stack, bufferIn, packedLightIn);
        Shader shader = JoJoShader.getStandAura();
        double ticks = (JoJoClient.ClientTickHandler.ticksInGame + partialTicks) * 0.5;
        double cycle = ticks / 10 % 30;
        if (shader != null) {
            shader.getUniformOrDefault("Disfiguration").set((float) ((0.025F + cycle * ((1F - 0.15F) / 20F)) / 2F));
        }
    }
}


