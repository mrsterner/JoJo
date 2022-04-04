package dev.mrsterner.jojo.api.timestop;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
import net.minecraft.entity.Entity;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.EntityDamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Pair;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;

public class StoppedTimeDamageHandler implements AttackEntityCallback, ServerTickEvents.EndWorldTick {
    private HashMap<Entity, Pair<PlayerEntity, Float>> damages = new HashMap<>();

    public void registerCallbacks() {
        AttackEntityCallback.EVENT.register(this);
        ServerTickEvents.END_WORLD_TICK.register(this);
    }

    @Override
    public ActionResult interact(PlayerEntity player, World world, Hand hand, Entity entity, @Nullable EntityHitResult hitResult) {
        if (!player.isSpectator() && TimeStopUtils.getTimeStoppedTicks(world) > 0 && !world.isClient) {
            float damageAlready = damages.getOrDefault(entity, new Pair<>(null, 0f)).getRight();
            damages.put(entity, new Pair<>(player, (float) player.getAttributeValue(EntityAttributes.GENERIC_ATTACK_DAMAGE)+damageAlready));
            return ActionResult.SUCCESS;
        }
        return ActionResult.PASS;
    }

    @Override
    public void onEndTick(ServerWorld world) {
        if (TimeStopUtils.getTimeStoppedTicks(world) < 0 && !damages.isEmpty()) {
            damages.forEach((entity, playerAndDamage) -> entity.damage(EntityDamageSource.player(playerAndDamage.getLeft()), playerAndDamage.getRight()));
            damages.clear();
        }
    }
}