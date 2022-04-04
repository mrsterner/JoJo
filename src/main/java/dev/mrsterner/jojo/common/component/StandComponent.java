package dev.mrsterner.jojo.common.component;

import dev.mrsterner.jojo.api.interfaces.IStandUser;
import dev.mrsterner.jojo.api.stand.Stand;
import dev.mrsterner.jojo.api.stand.StandMode;
import dev.mrsterner.jojo.common.registry.JoJoComponents;
import dev.onyxstudios.cca.api.v3.component.sync.AutoSyncedComponent;
import dev.onyxstudios.cca.api.v3.component.tick.ServerTickingComponent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;

public class StandComponent implements IStandUser, AutoSyncedComponent, ServerTickingComponent {
    private PlayerEntity player;
    private Stand stand = Stand.NONE;
    private boolean standActive = false;
    private StandMode standMode = StandMode.IDLE;
    private int standEnergy = 100000;
    private int standLevel = 0;

    public StandComponent(PlayerEntity playerEntity) {
        this.player = playerEntity;
    }

    @Override
    public void serverTick() {

    }

    @Override
    public void readFromNbt(NbtCompound tag) {
        try {
            stand = Stand.valueOf(tag.getString("StandType"));
        } catch (IllegalArgumentException e) {
            stand = Stand.NONE;
        }
        standActive = tag.getBoolean("StandActive");
        standMode = StandMode.values()[tag.getByte("StandMode")];
        standEnergy = tag.getInt("StandEnergy");
        standLevel = tag.getInt("StandLevel");
    }

    @Override
    public void writeToNbt(NbtCompound tag) {
        tag.putString("StandType", stand.name());
        tag.putBoolean("StandActive", standActive);
        tag.putByte("StandMode", (byte) standMode.ordinal());
        tag.putInt("StandEnergy", standEnergy);
        tag.putInt("StandLevel", standLevel);
    }

    @Override
    public Stand getStand() {
        return stand;
    }

    @Override
    public void setStand(Stand value) {
        this.stand = stand;
        JoJoComponents.STAND_USER_COMPONENT.sync(player);
    }

    @Override
    public boolean getStandActive() {
        return standActive;
    }

    @Override
    public void setStandActive(boolean value) {
        this.standActive = standActive;
        JoJoComponents.STAND_USER_COMPONENT.sync(player);
    }

    @Override
    public StandMode getStandMode() {
        return standMode;
    }

    @Override
    public void setStandMode(StandMode value) {
        this.standMode = standMode;
        JoJoComponents.STAND_USER_COMPONENT.sync(player);
    }

    @Override
    public int getStandEnergy() {
        return standEnergy;
    }

    @Override
    public void setStandEnergy(int value) {
        this.standEnergy = standEnergy;
        JoJoComponents.STAND_USER_COMPONENT.sync(player);
    }

    @Override
    public int getStandLevel() {
        return standLevel;
    }

    @Override
    public void setStandLevel(int value) {
        this.standLevel = standLevel;
        JoJoComponents.STAND_USER_COMPONENT.sync(player);
    }
}
