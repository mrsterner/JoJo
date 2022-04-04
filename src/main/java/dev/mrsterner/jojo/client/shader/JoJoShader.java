package dev.mrsterner.jojo.client.shader;

import com.mojang.datafixers.util.Pair;
import net.minecraft.client.render.Shader;
import net.minecraft.client.render.VertexFormats;
import net.minecraft.resource.ResourceManager;

import java.io.IOException;
import java.util.List;
import java.util.function.Consumer;

public class JoJoShader  {
    private static Shader standAura;
    private static Shader spaceTime;

    public static void init(ResourceManager resourceManager, List<Pair<Shader, Consumer<Shader>>> registrations) throws IOException {
        registrations.add(Pair.of(
        new Shader(resourceManager, "jojo_stand_aura", VertexFormats.POSITION_COLOR_TEXTURE_LIGHT_NORMAL),
        shader -> standAura = shader));

        registrations.add(Pair.of(
        new Shader(resourceManager, "jojo_spacetime", VertexFormats.POSITION),
        shader -> spaceTime = shader));
    }


    public static Shader getStandAura() {
        return standAura;
    }

    public static Shader getSpaceTime() {
        return spaceTime;
    }
}