package dev.mrsterner.jojo.mixin;


import dev.mrsterner.jojo.api.timestop.TimeStopUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.server.world.ServerWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerWorld.class)
public class ServerWorldMixin {

    @Inject(method = "tickEntity", at = @At("HEAD"), cancellable = true)
    void doNotTickEntityWhenTimeIsStopped(Entity entity, CallbackInfo ci) {
        if (TimeStopUtils.getTimeStoppedTicks(entity.world) > 0 && TimeStopUtils.isInRangeOfTimeStop(entity)) {
            entity.prevHorizontalSpeed = entity.horizontalSpeed;
            entity.prevPitch = entity.getPitch();
            entity.prevYaw = entity.getYaw();
            entity.prevX = entity.getX();
            entity.prevY = entity.getY();
            entity.prevZ = entity.getZ();
            entity.lastRenderX = entity.getX();
            entity.lastRenderY = entity.getY();
            entity.lastRenderZ = entity.getZ();
            if (entity instanceof LivingEntity lEntity) {
                lEntity.prevBodyYaw = lEntity.bodyYaw;
                lEntity.prevHeadYaw = lEntity.headYaw;
                lEntity.lastHandSwingProgress = lEntity.handSwingProgress;
                lEntity.lastLimbDistance = lEntity.limbDistance;
            }
            ci.cancel();
        }
    }
}
