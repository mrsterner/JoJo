package dev.mrsterner.jojo.api.stand;

import java.util.Locale;

public enum StandMode {
    IDLE,
    ATTACKING,
    HEALING;

    @Override
    public String toString() {
        return "status.stand."+name().toLowerCase(Locale.ROOT);
    }
}