package dev.mrsterner.jojo.common.component;

import dev.mrsterner.jojo.api.hamon.Hamon;
import dev.mrsterner.jojo.api.hamon.HamonMode;
import dev.mrsterner.jojo.api.interfaces.IHamonUser;
import dev.mrsterner.jojo.common.registry.JoJoComponents;
import dev.onyxstudios.cca.api.v3.component.sync.AutoSyncedComponent;
import dev.onyxstudios.cca.api.v3.component.tick.ServerTickingComponent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;

public final class HamonComponent implements IHamonUser, AutoSyncedComponent, ServerTickingComponent {
    private PlayerEntity user;
    private Hamon hamon = Hamon.NONE;
    private boolean hamonActive = false;
    private HamonMode hamonMode = HamonMode.IDLE;
    private int hamonEnergy = 100000;
    private int hamonLevel = 0;


    public HamonComponent(PlayerEntity user) {
        this.user = user;
    }

    @Override
    public void serverTick() {
        hamonEnergy = Math.min(hamonEnergy+100, 100000);
        JoJoComponents.HAMON_USER_COMPONENT.sync(user);
    }

    @Override
    public Hamon getHamon() {
        return hamon;
    }

    @Override
    public boolean getHamonActive() {
        return hamonActive;
    }

    @Override
    public HamonMode getHamonMode() {
        return hamonMode;
    }

    @Override
    public int getHamonEnergy() {
        return hamonEnergy;
    }

    @Override
    public int getHamonLevel() {
        return hamonLevel;
    }

    @Override
    public void setHamon(Hamon hamon) {
        this.hamon = hamon;
        JoJoComponents.HAMON_USER_COMPONENT.sync(user);
    }

    @Override
    public void setHamonActive(boolean hamonActive) {
        this.hamonActive = hamonActive;
        JoJoComponents.HAMON_USER_COMPONENT.sync(user);
    }

    @Override
    public void setHamonMode(HamonMode hamonMode) {
        this.hamonMode = hamonMode;
        JoJoComponents.HAMON_USER_COMPONENT.sync(user);
    }

    @Override
    public void setHamonEnergy(int hamonEnergy) {
        this.hamonEnergy = hamonEnergy;
        JoJoComponents.HAMON_USER_COMPONENT.sync(user);
    }

    @Override
    public void setHamonLevel(int hamonLevel) {
        this.hamonLevel = hamonLevel;
        JoJoComponents.HAMON_USER_COMPONENT.sync(user);
    }

    @Override
    public void readFromNbt(NbtCompound tag) {
        try {
            hamon = Hamon.valueOf(tag.getString("HamonType"));
        } catch (IllegalArgumentException e) {
            hamon = Hamon.NONE;
        }
        hamonActive = tag.getBoolean("HamonActive");
        hamonMode = HamonMode.values()[tag.getByte("HamonMode")];
        hamonEnergy = tag.getInt("HamonEnergy");
        hamonLevel = tag.getInt("HamonLevel");
    }

    @Override
    public void writeToNbt(NbtCompound tag) {
        tag.putString("HamonType", hamon.name());
        tag.putBoolean("HamonActive", hamonActive);
        tag.putByte("HamonMode", (byte) hamonMode.ordinal());
        tag.putInt("HamonEnergy", hamonEnergy);
        tag.putInt("HamonLevel", hamonLevel);
    }


}