package dev.mrsterner.jojo.mixin;


import dev.mrsterner.jojo.api.stand.Stand;
import dev.mrsterner.jojo.api.stand.StandUtils;
import dev.mrsterner.jojo.api.timestop.TimeStopUtils;
import net.minecraft.client.render.WorldRenderer;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Mixin(WorldRenderer.class)
public class WorldRendererMixin {

    @Shadow @Final private ClientWorld world;

    @ModifyArgs(
            method = "renderEntity(Lnet/minecraft/entity/Entity;DDDFLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;)V",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/entity/EntityRenderDispatcher;render(Lnet/minecraft/entity/Entity;DDDFFLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;I)V"))
    private void doNotDeltaTickEntityWhenTimeIsStopped(Args args) {
        Entity entity = args.get(0);
        if(TimeStopUtils.getTimeStoppedTicks(world) > 0 && TimeStopUtils.isInRangeOfTimeStop(entity)){
            if(entity instanceof PlayerEntity){
                if(StandUtils.getStand((PlayerEntity) entity) != Stand.THE_WORLD && StandUtils.getStand((PlayerEntity) entity) != Stand.STAR_PLATINUM){
                    args.set(5, 0.0F);
                }
            }else {
                args.set(5, 0.0F);
            }
        }
    }
}
