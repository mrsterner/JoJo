package dev.mrsterner.jojo.common.item;

import dev.mrsterner.jojo.api.stand.Stand;
import dev.mrsterner.jojo.api.stand.StandUtils;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class ArrowHeadItem extends Item {
    public ArrowHeadItem(Settings settings) {
        super(settings);
    }

    @Override
    public boolean isEnchantable(ItemStack stack) {
        return true;
    }

    @Override
    public int getEnchantability() {
        return 1;
    }


    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (!world.isClient) {
            if (StandUtils.getStand(user) == Stand.NONE && !user.getStackInHand(hand).hasEnchantments()) {
                int t = MathHelper.nextInt(world.random, 1, 9);
                switch (t){
                    case 1: StandUtils.setStand(user, Stand.STAR_PLATINUM);break;
                    case 2: StandUtils.setStand(user, Stand.THE_WORLD);break;
                    case 3: StandUtils.setStand(user, Stand.KILLER_QUEEN);break;
                    case 4: StandUtils.setStand(user, Stand.CRAZY_DIAMOND);break;
                    case 5: StandUtils.setStand(user, Stand.WEATHER_REPORT);break;
                    case 6: StandUtils.setStand(user, Stand.DARK_BLUE_MOON);break;
                    case 7: StandUtils.setStand(user, Stand.THE_SUN);break;
                    case 8: StandUtils.setStand(user, Stand.HIEROPHANT_GREEN);break;
                    case 9: StandUtils.setStand(user, Stand.TWENTY_CENTURY_BOY);break;
                    default: break;
                }
                StandUtils.setStandLevel(user, 0);
            } else if(StandUtils.getStandLevel(user) == 0 && !user.getStackInHand(hand).hasEnchantments()){
                StandUtils.setStandLevel(user, 1);
            } else {

            String enchant = user.getStackInHand(hand).getEnchantments().getString(0);
            switch (enchant) {
                case "{id:\"phantomblood:star_platinum_enchantment\",lvl:1s}":
                    StandUtils.setStandLevel(user, StandUtils.getStand(user) == Stand.STAR_PLATINUM ? 1 : 0);
                    StandUtils.setStand(user, Stand.STAR_PLATINUM);
                    break;
                case "{id:\"phantomblood:the_world_enchantment\",lvl:1s}":
                    StandUtils.setStandLevel(user, StandUtils.getStand(user) == Stand.THE_WORLD ? 1 : 0);
                    StandUtils.setStand(user, Stand.THE_WORLD);
                    break;
                case "{id:\"phantomblood:the_sun_enchantment\",lvl:1s}":
                    StandUtils.setStandLevel(user, StandUtils.getStand(user) == Stand.THE_SUN ? 1 : 0);
                    StandUtils.setStand(user, Stand.THE_SUN);
                    break;
                case "{id:\"phantomblood:dark_blue_moon_enchantment\",lvl:1s}":
                    StandUtils.setStandLevel(user, StandUtils.getStand(user) == Stand.DARK_BLUE_MOON ? 1 : 0);
                    StandUtils.setStand(user, Stand.DARK_BLUE_MOON);
                    break;
                case "{id:\"phantomblood:killer_queen_enchantment\",lvl:1s}":
                    StandUtils.setStandLevel(user, StandUtils.getStand(user) == Stand.KILLER_QUEEN ? 1 : 0);
                    StandUtils.setStand(user, Stand.KILLER_QUEEN);
                    break;
                case "{id:\"phantomblood:weather_report_enchantment\",lvl:1s}":
                    StandUtils.setStandLevel(user, StandUtils.getStand(user) == Stand.WEATHER_REPORT ? 1 : 0);
                    StandUtils.setStand(user, Stand.WEATHER_REPORT);
                    break;
                case "{id:\"phantomblood:crazy_diamond_enchantment\",lvl:1s}":
                    StandUtils.setStandLevel(user, StandUtils.getStand(user) == Stand.CRAZY_DIAMOND ? 1 : 0);
                    StandUtils.setStand(user, Stand.CRAZY_DIAMOND);
                    break;

                case "{id:\"phantomblood:king_crimson_enchantment\",lvl:1s}":
                    StandUtils.setStandLevel(user, StandUtils.getStand(user) == Stand.KING_CRIMSON ? 1 : 0);
                    StandUtils.setStand(user, Stand.KING_CRIMSON);
                    break;
                case "{id:\"phantomblood:hierophant_green_enchantment\",lvl:1s}":
                    StandUtils.setStandLevel(user, StandUtils.getStand(user) == Stand.HIEROPHANT_GREEN ? 1 : 0);
                    StandUtils.setStand(user, Stand.HIEROPHANT_GREEN);
                    break;
                case "{id:\"phantomblood:20th_century_boy_enchantment\",lvl:1s}":
                    StandUtils.setStandLevel(user, StandUtils.getStand(user) == Stand.TWENTY_CENTURY_BOY ? 1 : 0);
                    StandUtils.setStand(user, Stand.TWENTY_CENTURY_BOY);
                    break;
                default:
                    break;
                }
            }
        }
            world.playSound(null,user.getBlockPos(), SoundEvents.ITEM_SHIELD_BREAK, SoundCategory.PLAYERS,1F,1);
            user.damage(DamageSource.GENERIC,user.getMaxHealth() + 4); //Kills you even if you have 2 absorption hearts
            return TypedActionResult.consume(ItemStack.EMPTY);

    }
}
