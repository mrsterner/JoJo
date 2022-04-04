package dev.mrsterner.jojo.api;

import dev.mrsterner.jojo.api.stand.Stand;
import dev.mrsterner.jojo.common.entity.StandEntity;
import dev.mrsterner.jojo.common.registry.JoJoComponents;
import dev.mrsterner.jojo.common.registry.JoJoEntityTypes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;

public class JoJoApi {
    private static StandEntity entity;


    public static boolean hasStand(PlayerEntity entity){
        return JoJoComponents.STAND_USER_COMPONENT.get(entity).getStand() != Stand.NONE;
    }

    public static StandEntity getStand(PlayerEntity player) {
        if (hasStand(player)) {
            if(entity == null) {
                entity = JoJoEntityTypes.STAND.create(player.world);
                if (entity != null) {
                    entity.stand = JoJoComponents.STAND_USER_COMPONENT.get(player).getStand();
                }
            }
            return entity;
        }
        return null;
    }

    public static NbtCompound getTagCompoundSafe(ItemStack stack) {
        NbtCompound tagCompound = stack.getNbt();
        if (tagCompound == null) {
            tagCompound = new NbtCompound();
            stack.setNbt(tagCompound);
        }
        return tagCompound;
    }
}
