package dev.mrsterner.jojo.api.stand;

import dev.mrsterner.jojo.common.registry.JoJoSoundEvents;
import dev.mrsterner.jojo.common.registry.JoJoStatusEffects;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;

public class StandPunchHandler implements ServerTickEvents.StartWorldTick {
    double ticksSinceSound;
    double ticksSinceSun;
    double boxX = 0;
    double boxY = 0;
    double boxZ = 0;


    @Override
    public void onStartTick(ServerWorld world) {
        ticksSinceSound++;
        world.getPlayers().stream()
        .filter(it -> StandUtils.getStand(it) != Stand.NONE && StandUtils.isStandActive(it) && StandUtils.getStandMode(it) == StandMode.ATTACKING)
        .forEach(player -> {
            Stand stand = StandUtils.getStand(player);
            int level = StandUtils.getStandLevel(player);
            switch (stand) {
                case NONE:
                    break;
                case STAR_PLATINUM, THE_WORLD, CRAZY_DIAMOND, KILLER_QUEEN:
                    boxX = 3.0;
                    boxY = 1.0;
                    boxZ = 2.0;
                    break;
                case WEATHER_REPORT, DARK_BLUE_MOON, THE_SUN, KING_CRIMSON, TWENTY_CENTURY_BOY, HIEROPHANT_GREEN:
                    boxX = 10.0;
                    boxY = 10.0;
                    boxZ = 10.0;
                    break;
            }
            world.getOtherEntities(player, player.getBoundingBox()
            .expand(boxX * MathHelper.sin(player.getYaw()), boxY, boxZ * MathHelper.cos(player.getYaw()))).stream()
            .filter(it -> it instanceof LivingEntity)
            .forEach(it -> {
                if (ticksSinceSound > 8) {
                    ticksSinceSound = 0;
                    world.playSound(null, player.getBlockPos(), JoJoSoundEvents.PUNCH, SoundCategory.PLAYERS, 0.05F, 1);
                }
                switch (stand) {
                    case STAR_PLATINUM, THE_WORLD, CRAZY_DIAMOND -> it.damage(DamageSource.player(player), level == 0 ? 3.0f : 6.0f);
                    case KILLER_QUEEN, WEATHER_REPORT -> it.damage(DamageSource.player(player), level == 0 ? 1.0f : 2.0f);
                }
            });
        });
        world.getPlayers().stream()
        .filter(it -> StandUtils.getStand(it) == Stand.WEATHER_REPORT && StandUtils.isStandActive(it) && StandUtils.getStandMode(it) == StandMode.ATTACKING)
        .forEach(player -> {
            int level = StandUtils.getStandLevel(player);
            world.getOtherEntities(player, player.getBoundingBox().expand(10.0 * MathHelper.sin(player.getYaw()), 10.0, (level == 0 ? 10.0f : 15.0f) * MathHelper.cos(player.getYaw())))
            .forEach(it -> {
                double distanceX = player.getX() - it.getX();
                double distanceZ = player.getZ() - it.getZ();
                it.addVelocity(level == 0 ? -0.5f * distanceX : -1.0f * distanceX, 0, level == 0 ? -0.5f * distanceZ : -1.0f * distanceZ);
            });
        });
        //Dark blue moons ability triggered by the scales status effect
        world.getPlayers().stream()
        .filter(it -> StandUtils.getStand(it) == Stand.DARK_BLUE_MOON && StandUtils.isStandActive(it) && it.hasStatusEffect(JoJoStatusEffects.DARK_BLUE_MOON)  && it.isTouchingWaterOrRain())
        .forEach(player -> {
            int level = StandUtils.getStandLevel(player);
            world.getOtherEntities(player, player.getBoundingBox().expand(10.0 * MathHelper.sin(player.getYaw()), 10.0, (level == 0 ? 10.0f : 15.0f) * MathHelper.cos(player.getYaw()))).stream()
            .filter(Entity::isTouchingWaterOrRain)
            .forEach(it -> {
                it.damage(DamageSource.player(player), level == 0 ? 1.0f : 2.0f);
            });
        });
        world.getPlayers().stream()
        .filter(it -> StandUtils.getStand(it) == Stand.CRAZY_DIAMOND && StandUtils.isStandActive(it) && StandUtils.getStandMode(it) == StandMode.HEALING)
        .forEach(player -> {
            int level = StandUtils.getStandLevel(player);
            int energy = StandUtils.getStandEnergy(player);
            int energyForAbility = StandUtils.getStand(player).energyForAbility;
            world.getOtherEntities(player, player.getBoundingBox().expand(3.0 * MathHelper.sin(player.getYaw()), 1.0, 2.0 * MathHelper.cos(player.getYaw()))).stream()
            .filter(it -> it instanceof LivingEntity)
            .forEach(it -> {

                if (ticksSinceSound > 8 && energy >= energyForAbility) {
                    ticksSinceSound = 0;
                    world.playSound(null, player.getBlockPos(), JoJoSoundEvents.PUNCH, SoundCategory.PLAYERS, 0.05F, 1);
                    StandUtils.setStandEnergy(player, energy - energyForAbility);
                    ((LivingEntity) it).heal(1 + level);
                }

            });
        });
        world.getPlayers().stream()
        .filter(it -> StandUtils.isStandActive(it) && StandUtils.getStand(it) == Stand.THE_SUN && StandUtils.getStandMode(it) == StandMode.ATTACKING)
        .forEach(player -> {
            int level = StandUtils.getStandLevel(player);
            int energy = StandUtils.getStandEnergy(player);
            int energyForAbility = StandUtils.getStand(player).energyForAbility;
            world.getOtherEntities(player, player.getBoundingBox().expand(10.0 * MathHelper.sin(player.getYaw()), 10.0, (level == 0 ? 10.0f : 15.0f) * MathHelper.cos(player.getYaw())))
            .forEach(it -> {
                ticksSinceSun++;

                if(ticksSinceSun > 8 && energy >= energyForAbility){
                    StandUtils.setStandEnergy(player, energy - energyForAbility);
                    ticksSinceSun =0;
                    Vec3d playerVec = player.getPos().add(0,3.2D,0);
                    Vec3d targetVec = it.getPos();
                    Vec3d diff = targetVec.subtract(playerVec);
                    double distance = diff.length();
                    for(int i=0; i < distance; i++){
                        double randx = MathHelper.nextDouble(world.random, -0.05,0.05);
                        double randy = MathHelper.nextDouble(world.random, -0.05,0.05);
                        double randz = MathHelper.nextDouble(world.random, -0.05,0.05);
                        double progress = i / distance;
                        Vec3d lerp = playerVec.add(diff.multiply((progress)));
                        MinecraftClient.getInstance().worldRenderer.addParticle(ParticleTypes.CAMPFIRE_COSY_SMOKE, true, lerp.x, lerp.y, lerp.z, randx,randy,randz);
                        MinecraftClient.getInstance().worldRenderer.addParticle(ParticleTypes.FLAME, true, lerp.x, lerp.y, lerp.z, randx,randy,randz);
                    }
                    it.setOnFireFor(level == 0 ? 2 : 4);
                    it.damage(DamageSource.LAVA, level + 1);
                }

            });
        });
    }
}
