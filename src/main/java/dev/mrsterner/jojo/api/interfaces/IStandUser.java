package dev.mrsterner.jojo.api.interfaces;

import dev.mrsterner.jojo.api.stand.Stand;
import dev.mrsterner.jojo.api.stand.StandMode;
import dev.onyxstudios.cca.api.v3.component.ComponentV3;
import dev.onyxstudios.cca.api.v3.component.tick.ServerTickingComponent;

public interface IStandUser extends ComponentV3, ServerTickingComponent {
    Stand getStand();
    void setStand(Stand value);
    boolean getStandActive();
    void setStandActive(boolean value);
    StandMode getStandMode();
    void setStandMode(StandMode value);
    int getStandEnergy();
    void setStandEnergy(int value);
    int getStandLevel();
    void setStandLevel(int value);


}