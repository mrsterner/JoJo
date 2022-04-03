package dev.mrsterner.jojo;

import com.google.common.collect.Maps;
import dev.mrsterner.jojo.client.renderer.StandEntityRenderer;
import dev.mrsterner.jojo.common.registry.JJEntityTypes;
import net.fabricmc.loader.api.ModContainer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import org.quiltmc.qsl.base.api.entrypoint.client.ClientModInitializer;

import java.util.Map;

public class JoJoClient implements ClientModInitializer {
	private static final Map<EntityType<?>, EntityRendererFactory<?>> RENDERER_FACTORIES = Maps.<EntityType<?>, EntityRendererFactory<?>>newHashMap();


	private static <T extends Entity> void register(EntityType<? extends T> type, EntityRendererFactory<T> factory) {
		RENDERER_FACTORIES.put(type, factory);
	}

	@Override
	public void onInitializeClient(ModContainer mod) {
		register(JJEntityTypes.STAND, StandEntityRenderer::new);
	}
}
