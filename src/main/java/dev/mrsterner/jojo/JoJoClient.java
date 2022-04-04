package dev.mrsterner.jojo;

import com.google.common.collect.Maps;
import dev.mrsterner.jojo.client.renderer.StandEntityRenderer;
import dev.mrsterner.jojo.common.registry.JoJoEntityTypes;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;

import java.util.Map;

public class JoJoClient implements ClientModInitializer {
	private static final Map<EntityType<?>, EntityRendererFactory<?>> RENDERER_FACTORIES = Maps.<EntityType<?>, EntityRendererFactory<?>>newHashMap();


	private static <T extends Entity> void register(EntityType<? extends T> type, EntityRendererFactory<T> factory) {
		RENDERER_FACTORIES.put(type, factory);
	}

	@Override
	public void onInitializeClient() {
		register(JoJoEntityTypes.STAND, StandEntityRenderer::new);

		ClientTickEvents.END_CLIENT_TICK.register(ClientTickHandler::clientTickEnd);
	}

	public static final class ClientTickHandler {
		private ClientTickHandler() {
		}

		public static int ticksInGame = 0;
		public static float partialTicks = 0;
		public static float delta = 0;
		public static float total = 0;

		public static void calcDelta() {
			float oldTotal = total;
			total = ticksInGame + partialTicks;
			delta = total - oldTotal;
		}

		public static void renderTick(float renderTickTime) {
			partialTicks = renderTickTime;
		}

		public static void clientTickEnd(MinecraftClient mc) {
			if (!mc.isPaused()) {
				ticksInGame++;
				partialTicks = 0;
			}
			calcDelta();
		}
	}
}
