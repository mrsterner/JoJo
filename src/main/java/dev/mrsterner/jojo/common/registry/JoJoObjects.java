package dev.mrsterner.jojo.common.registry;

import dev.mrsterner.jojo.JoJo;
import dev.mrsterner.jojo.common.item.KillerQueenTriggerItem;
import dev.mrsterner.jojo.common.item.StandDiscItem;
import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.fluid.FlowableFluid;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.minecraft.util.registry.Registry;

import java.util.LinkedHashMap;
import java.util.Map;

public class JoJoObjects {
    public static final Map<Block, Identifier> BLOCKS = new LinkedHashMap<>();
    public static final Map<Item, Identifier> ITEMS = new LinkedHashMap<>();
    public static final Map<FlowableFluid, Identifier> FLUIDS = new LinkedHashMap<>();

    public static final Item ARROW_HEAD = register("arrow_head", new Item(gen()));
    public static final Item KILLER_QUEEN_TRIGGER = register("killer_queen_trigger", new KillerQueenTriggerItem(gen()));


    public static final Item STAR_PLATINUM_DISC = register("star_platinum_disc", new StandDiscItem(gen().rarity(Rarity.RARE).maxCount(1)));
    public static final Item THE_WORLD_DISC = register("the_world_disc", new StandDiscItem(gen().rarity(Rarity.RARE).maxCount(1)));
    public static final Item KILLER_QUEEN_DISC = register("killer_queen_disc", new StandDiscItem(gen().rarity(Rarity.RARE).maxCount(1)));
    public static final Item CRAZY_DIAMOND_DISC = register("crazy_diamond_disc", new StandDiscItem(gen().rarity(Rarity.RARE).maxCount(1)));
    public static final Item WEATHER_REPORT_DISC = register("weather_report_disc", new StandDiscItem(gen().rarity(Rarity.RARE).maxCount(1)));
    public static final Item DARK_BLUE_MOON_DISC = register("dark_blue_moon_disc", new StandDiscItem(gen().rarity(Rarity.RARE).maxCount(1)));
    public static final Item THE_SUN_DISC = register("the_sun_disc", new StandDiscItem(gen().rarity(Rarity.RARE).maxCount(1)));
    public static final Item KING_CRIMSON_DISC = register("king_crimson_disc", new StandDiscItem(gen().rarity(Rarity.RARE).maxCount(1)));
    public static final Item TWENTY_CENTURY_BOY_DISC = register("20th_century_boy_disc", new StandDiscItem(gen().rarity(Rarity.RARE).maxCount(1)));
    public static final Item HIEROPHANT_GREEN_DISC = register("hierophant_green_disc", new StandDiscItem(gen().rarity(Rarity.RARE).maxCount(1)));


    public static <T extends Block> T register(String name, T block, boolean createItem) {
        BLOCKS.put(block, new Identifier(JoJo.MODID, name));
        if (createItem) {
            ITEMS.put(new BlockItem(block, gen()), BLOCKS.get(block));
        }
        return block;
    }

    public static <T extends Item> T register(String name, T item) {
        ITEMS.put(item, new Identifier(JoJo.MODID, name));
        return item;
    }

    public static Item.Settings gen() {
        return new Item.Settings().group(JoJo.JOJO_GROUP);
    }

    public static void init() {
        BLOCKS.keySet().forEach(block -> Registry.register(Registry.BLOCK, BLOCKS.get(block), block));
        ITEMS.keySet().forEach(item -> Registry.register(Registry.ITEM, ITEMS.get(item), item));
    }

}
