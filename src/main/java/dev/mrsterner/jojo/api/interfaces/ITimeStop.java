package dev.mrsterner.jojo.api.interfaces;

import dev.onyxstudios.cca.api.v3.component.ComponentV3;
import dev.onyxstudios.cca.api.v3.component.tick.ServerTickingComponent;
import net.minecraft.entity.player.PlayerEntity;

public interface ITimeStop extends ComponentV3, ServerTickingComponent {
    long getTimeStoppedTicks();
    void setTimeStoppedTicks(long value);

    PlayerEntity getTimeStopper();
    void setTimeStopper(PlayerEntity value);


}
