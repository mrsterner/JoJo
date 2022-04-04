package dev.mrsterner.jojo.common.registry;

import dev.mrsterner.jojo.JoJo;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.LinkedHashMap;
import java.util.Map;

public class JoJoStatusEffects {
    private static final Map<StatusEffect, Identifier> STATUS_EFFECTS = new LinkedHashMap<>();
    public static final StatusEffect DARK_BLUE_MOON = create("dark_blue_moon_effect", new BaseStatusEffect(StatusEffectCategory.BENEFICIAL, 0x75c1ff));

    private static <T extends StatusEffect> T create(String name, T effect) {
        STATUS_EFFECTS.put(effect, new Identifier(JoJo.MODID, name));
        return effect;
    }

    public static void init() {
        STATUS_EFFECTS.keySet().forEach(effect -> Registry.register(Registry.STATUS_EFFECT, STATUS_EFFECTS.get(effect), effect));
    }

    public static class BaseStatusEffect extends StatusEffect {
        public BaseStatusEffect(StatusEffectCategory type, int color) {
            super(type, color);
        }
    }
}
