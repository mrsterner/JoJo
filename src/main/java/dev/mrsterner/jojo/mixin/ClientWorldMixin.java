package dev.mrsterner.jojo.mixin;


import dev.mrsterner.jojo.api.timestop.TimeStopUtils;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particle.ParticleEffect;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static dev.mrsterner.jojo.api.timestop.TimeStopUtils.getTimeStopper;


@Mixin(ClientWorld.class)
public class ClientWorldMixin {

   @Shadow @Final private final MinecraftClient client = MinecraftClient.getInstance();

    @Inject(method = "tickEntity", at = @At("HEAD"), cancellable = true)
    private void doNotTickEntityWhenTimeIsStopped(Entity entity, CallbackInfo ci) {
        if (TimeStopUtils.getTimeStoppedTicks(entity.world) > 0 && TimeStopUtils.isInRangeOfTimeStop(entity) && !(entity instanceof PlayerEntity)) {
            entity.prevHorizontalSpeed = entity.horizontalSpeed;
            entity.prevPitch = entity.getPitch();
            entity.prevYaw = entity.getYaw();
            entity.prevX = entity.getX();
            entity.prevY = entity.getY();
            entity.prevZ = entity.getZ();
            entity.lastRenderX = entity.getX();
            entity.lastRenderY = entity.getY();
            entity.lastRenderZ = entity.getZ();
            if (entity instanceof LivingEntity) {
                LivingEntity lEntity = (LivingEntity) entity;
                lEntity.prevBodyYaw = lEntity.bodyYaw;
                lEntity.prevHeadYaw = lEntity.headYaw;
                lEntity.lastHandSwingProgress = lEntity.handSwingProgress;
                lEntity.lastLimbDistance = lEntity.limbDistance;
            }
            ci.cancel();
        }
    }
    @Inject(method = "addParticle(Lnet/minecraft/particle/ParticleEffect;DDDDDD)V", at = @At("HEAD"), cancellable = true)
    private void doNotSpawnParticles(ParticleEffect parameters, double x, double y, double z, double velocityX, double velocityY, double velocityZ, CallbackInfo ci) {
        if (TimeStopUtils.getTimeStoppedTicks(client.world) > 0 && TimeStopUtils.isInRangeOfTimeStop(getTimeStopper(client.world))){
            ci.cancel();
        }
    }
}
