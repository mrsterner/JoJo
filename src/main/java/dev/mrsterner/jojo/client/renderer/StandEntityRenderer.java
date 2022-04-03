package dev.mrsterner.jojo.client.renderer;

import dev.mrsterner.jojo.client.model.StandEntityModel;
import dev.mrsterner.jojo.common.entity.StandEntity;
import net.minecraft.client.render.RenderLayer;
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
}


