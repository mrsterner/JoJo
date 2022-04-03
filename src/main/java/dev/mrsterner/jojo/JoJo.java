package dev.mrsterner.jojo;

import dev.mrsterner.jojo.common.registry.JJEntityTypes;
import net.fabricmc.loader.api.ModContainer;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;
import org.quiltmc.qsl.base.api.entrypoint.ModInitializer;
import org.quiltmc.qsl.item.group.api.QuiltItemGroup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JoJo implements ModInitializer {
	public static final String MODID = "jojo";
	public static final ItemGroup JOJO_GROUP = QuiltItemGroup.createWithIcon(new Identifier(MODID, MODID), () -> new ItemStack(Items.ACACIA_PLANKS));
	public static final Logger LOGGER = LoggerFactory.getLogger(MODID);


	@Override
	public void onInitialize(ModContainer mod) {
		JJEntityTypes.init();
	}
}
