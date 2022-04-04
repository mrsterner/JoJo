package dev.mrsterner.jojo.api.stand;

import dev.mrsterner.jojo.api.interfaces.IStandUser;
import dev.mrsterner.jojo.common.registry.JoJoComponents;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;

public final class StandUtils {
    public static StandMode getStandMode(PlayerEntity playerEntity) {
        return JoJoComponents.STAND_USER_COMPONENT.get(playerEntity).getStandMode();
    }
    public static boolean isStandActive(PlayerEntity playerEntity) {
        return JoJoComponents.STAND_USER_COMPONENT.get(playerEntity).getStandActive();
    }
    public static Stand getStand(PlayerEntity playerEntity) {
        return JoJoComponents.STAND_USER_COMPONENT.get(playerEntity).getStand();
    }
    public static int getStandLevel(PlayerEntity playerEntity) {
        return JoJoComponents.STAND_USER_COMPONENT.get(playerEntity).getStandLevel();
    }
    public static int getStandEnergy(PlayerEntity playerEntity) {
        return JoJoComponents.STAND_USER_COMPONENT.get(playerEntity).getStandEnergy();
    }
    public static void setStandMode(PlayerEntity playerEntity, StandMode mode) {
        JoJoComponents.STAND_USER_COMPONENT.get(playerEntity).setStandMode(mode);
    }
    public static void setToNextStandMode(PlayerEntity playerEntity) {
        IStandUser component = JoJoComponents.STAND_USER_COMPONENT.get(playerEntity);
        StandMode mode = component.getStandMode();
        StandMode[] modes = StandMode.values();
        StandMode result = modes[(mode.ordinal()+1) % modes.length];
        component.setStandMode(result);
    }
    public static void setStandActive(PlayerEntity playerEntity, boolean value) {
        JoJoComponents.STAND_USER_COMPONENT.get(playerEntity).setStandActive(value);
    }
    public static void toggleStandActive(PlayerEntity playerEntity) {
        IStandUser component = JoJoComponents.STAND_USER_COMPONENT.get(playerEntity);
        component.setStandActive(!component.getStandActive());
    }
    public static void setStand(PlayerEntity playerEntity, Stand stand) {
        JoJoComponents.STAND_USER_COMPONENT.get(playerEntity).setStand(stand);
    }
    public static void setStandLevel(PlayerEntity playerEntity, int level) {
        JoJoComponents.STAND_USER_COMPONENT.get(playerEntity).setStandLevel(level);
    }
    public static void setStandEnergy(PlayerEntity playerEntity, int energy) {
        JoJoComponents.STAND_USER_COMPONENT.get(playerEntity).setStandEnergy(energy);
    }

    //Uniques
    public static void repairItem(ItemStack stack, int duration){
        if (!stack.isEmpty()&&stack.getDamage()>0) {
            int result = stack.getDamage()-duration;
            stack.setDamage(Math.max(result, 0));
        }
    }
}