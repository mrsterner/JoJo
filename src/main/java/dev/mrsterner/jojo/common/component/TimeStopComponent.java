package dev.mrsterner.jojo.common.component;

import dev.mrsterner.jojo.api.interfaces.ITimeStop;
import dev.mrsterner.jojo.common.registry.JoJoComponents;
import dev.onyxstudios.cca.api.v3.component.sync.AutoSyncedComponent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.world.World;

public final class TimeStopComponent implements ITimeStop, AutoSyncedComponent {
    private World world;
    private long timeStoppedTicks = -1;
    private PlayerEntity timeStopper = null;
    private MinecraftServer server;

    public TimeStopComponent(World world) {
        this.world = world;
    }

    @Override
    public long getTimeStoppedTicks() {
        return timeStoppedTicks;
    }

    @Override
    public void setTimeStoppedTicks(long timeStoppedTicks) {
        this.timeStoppedTicks = timeStoppedTicks;
        JoJoComponents.TIME_STOP_COMPONENT.sync(world);
    }

    @Override
    public PlayerEntity getTimeStopper() {
        return timeStopper;
    }

    @Override
    public void setTimeStopper(PlayerEntity value) {
        timeStopper = value;
        JoJoComponents.TIME_STOP_COMPONENT.sync(world);
    }

    @Override
    public void serverTick() {
        timeStoppedTicks--;
        if (timeStoppedTicks < 0) {
            timeStopper = null;
        }
        JoJoComponents.TIME_STOP_COMPONENT.sync(world);
    }

    @Override
    public void writeSyncPacket(PacketByteBuf buf, ServerPlayerEntity recipient) {
        buf.writeVarLong(timeStoppedTicks);
        if (timeStopper != null) {
            buf.writeUuid(timeStopper.getUuid());
        }
    }

    @Override
    public void applySyncPacket(PacketByteBuf buf) {
        timeStoppedTicks = buf.readVarLong();
        try {
            timeStopper = world.getPlayerByUuid(buf.readUuid());
        } catch (IndexOutOfBoundsException e) {
            timeStopper = null;
        }
    }

    // We don't want to persist this
    @Override
    public void readFromNbt(NbtCompound tag) {}
    @Override
    public void writeToNbt(NbtCompound tag) {}
}