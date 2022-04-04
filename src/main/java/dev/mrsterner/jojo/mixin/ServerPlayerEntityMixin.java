package dev.mrsterner.jojo.mixin;

import com.mojang.authlib.GameProfile;
import dev.mrsterner.jojo.api.stand.Stand;
import dev.mrsterner.jojo.api.stand.StandUtils;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerPlayerEntity.class)
public abstract class ServerPlayerEntityMixin extends PlayerEntity {

    public ServerPlayerEntityMixin(World world, BlockPos pos, float yaw, GameProfile profile) {
        super(world, pos, yaw, profile);
    }

    @Inject(method = "onDeath", at = @At("RETURN"))
    private void onDeathStandKiller(CallbackInfo ci) {
        StandUtils.setStandActive(this, false);
        StandUtils.setStand(this, Stand.NONE);
    }
}