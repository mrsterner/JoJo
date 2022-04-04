package dev.mrsterner.jojo.mixin;

import dev.mrsterner.jojo.JoJo;
import dev.mrsterner.jojo.api.JoJoApi;
import dev.mrsterner.jojo.common.entity.StandEntity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.PlayerEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Hand;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntityRenderer.class)
public class PlayerEntityRendererMixin {
	@Inject(method = "render(Lnet/minecraft/client/network/AbstractClientPlayerEntity;FFLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;I)V", at = @At("HEAD"), cancellable = true)
	private void render(AbstractClientPlayerEntity player, float yaw, float tickDelta, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int light, CallbackInfo callbackInfo) {
		StandEntity standEntity = JoJoApi.getStand(player);
		if(standEntity!=null){
			standEntity.age = player.age;
			standEntity.hurtTime = player.hurtTime;
			standEntity.maxHurtTime = Integer.MAX_VALUE;
			standEntity.limbDistance = player.limbDistance;
			standEntity.lastLimbDistance = player.lastLimbDistance;
			standEntity.limbAngle = player.limbAngle;
			standEntity.headYaw = player.headYaw;
			standEntity.prevHeadYaw = player.prevHeadYaw;
			standEntity.bodyYaw = player.bodyYaw;
			standEntity.prevBodyYaw = player.prevBodyYaw;
			standEntity.handSwinging = player.handSwinging;
			standEntity.handSwingTicks = player.handSwingTicks;
			standEntity.handSwingProgress = player.handSwingProgress;
			standEntity.lastHandSwingProgress = player.lastHandSwingProgress;
			standEntity.setPitch(player.getPitch());
			standEntity.prevPitch = player.prevPitch;
			standEntity.preferredHand = player.preferredHand;
			standEntity.setStackInHand(Hand.MAIN_HAND, player.getMainHandStack());
			standEntity.setStackInHand(Hand.OFF_HAND, player.getOffHandStack());
			standEntity.setCurrentHand(player.getActiveHand() == null ? Hand.MAIN_HAND : player.getActiveHand());
			standEntity.setSneaking(player.isSneaking());
			standEntity.motionCalc = new Vec3d(player.getX()-player.prevX, player.getY()-player.prevY,player.getZ()-player.prevZ);
			standEntity.isSneaking();
			standEntity.forwardSpeed=player.forwardSpeed;
			standEntity.setPose(player.getPose());
			standEntity.setSprinting(player.isSprinting());
			MinecraftClient.getInstance().getEntityRenderDispatcher().getRenderer(standEntity).render(standEntity, yaw, tickDelta, matrixStack, vertexConsumerProvider, light);
		}
	}
}
