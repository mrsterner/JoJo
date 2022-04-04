package dev.mrsterner.jojo.api.interfaces;

import dev.mrsterner.jojo.api.hamon.Hamon;
import dev.mrsterner.jojo.api.hamon.HamonMode;
import dev.onyxstudios.cca.api.v3.component.ComponentV3;
import dev.onyxstudios.cca.api.v3.component.tick.ServerTickingComponent;

public interface IHamonUser extends ComponentV3, ServerTickingComponent {
    Hamon getHamon();
    void setHamon(Hamon value);
    boolean getHamonActive();
    void setHamonActive(boolean value);
    HamonMode getHamonMode();
    void setHamonMode(HamonMode value);
    int getHamonEnergy();
    void setHamonEnergy(int value);
    int getHamonLevel();
    void setHamonLevel(int value);

}