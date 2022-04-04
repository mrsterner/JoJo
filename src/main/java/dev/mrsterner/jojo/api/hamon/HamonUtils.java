package dev.mrsterner.jojo.api.hamon;

import dev.mrsterner.jojo.api.interfaces.IHamonUser;
import dev.mrsterner.jojo.common.registry.JoJoComponents;
import net.minecraft.entity.player.PlayerEntity;

public final class HamonUtils {
    public static HamonMode getHamonMode(PlayerEntity playerEntity) {
        return JoJoComponents.HAMON_USER_COMPONENT.get(playerEntity).getHamonMode();
    }
    public static boolean isHamonActive(PlayerEntity playerEntity) {
        return JoJoComponents.HAMON_USER_COMPONENT.get(playerEntity).getHamonActive();
    }
    public static Hamon getHamon(PlayerEntity playerEntity) {
        return JoJoComponents.HAMON_USER_COMPONENT.get(playerEntity).getHamon();
    }
    public static int getHamonLevel(PlayerEntity playerEntity) {
        return JoJoComponents.HAMON_USER_COMPONENT.get(playerEntity).getHamonLevel();
    }
    public static int getHamonEnergy(PlayerEntity playerEntity) {
        return JoJoComponents.HAMON_USER_COMPONENT.get(playerEntity).getHamonEnergy();
    }
    public static void setHamonMode(PlayerEntity playerEntity, HamonMode mode) {
        JoJoComponents.HAMON_USER_COMPONENT.get(playerEntity).setHamonMode(mode);
    }
    public static void setToNextHamonMode(PlayerEntity playerEntity) {
        IHamonUser component = JoJoComponents.HAMON_USER_COMPONENT.get(playerEntity);
        HamonMode mode = component.getHamonMode();
        HamonMode[] modes = HamonMode.values();
        HamonMode result = modes[(mode.ordinal()+1) % modes.length];
        component.setHamonMode(result);
    }
    public static void setHamonActive(PlayerEntity playerEntity, boolean value) {
        JoJoComponents.HAMON_USER_COMPONENT.get(playerEntity).setHamonActive(value);
    }
    public static void toggleHamonActive(PlayerEntity playerEntity) {
        IHamonUser component = JoJoComponents.HAMON_USER_COMPONENT.get(playerEntity);
        component.setHamonActive(!component.getHamonActive());
    }
    public static void setHamon(PlayerEntity playerEntity, Hamon hamon) {
        JoJoComponents.HAMON_USER_COMPONENT.get(playerEntity).setHamon(hamon);
    }
    public static void setHamonLevel(PlayerEntity playerEntity, int level) {
        JoJoComponents.HAMON_USER_COMPONENT.get(playerEntity).setHamonLevel(level);
    }
    public static void setHamonEnergy(PlayerEntity playerEntity, int energy) {
        JoJoComponents.HAMON_USER_COMPONENT.get(playerEntity).setHamonEnergy(energy);
    }
}