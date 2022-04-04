package dev.mrsterner.jojo.common.item;

import dev.mrsterner.jojo.api.stand.Stand;
import dev.mrsterner.jojo.api.stand.StandMode;
import dev.mrsterner.jojo.api.stand.StandUtils;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class StandDiscItem extends Item {
    public StandDiscItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        String s = this.asItem().toString();
        if(s!=null){
            StandUtils.setStandMode(user, StandMode.IDLE);
            switch (s){
                case "the_world_disc":
                    StandUtils.setStand(user, Stand.THE_WORLD);
                    break;
                case "star_platinum_disc":
                    StandUtils.setStand(user, Stand.STAR_PLATINUM);
                    break;
                case "killer_queen_disc":
                    StandUtils.setStand(user, Stand.KILLER_QUEEN);
                    break;
                case "crazy_diamond_disc":
                    StandUtils.setStand(user, Stand.CRAZY_DIAMOND);
                    break;
                case "weather_report_disc":
                    StandUtils.setStand(user, Stand.WEATHER_REPORT);
                    break;
                case "dark_blue_moon_disc":
                    StandUtils.setStand(user, Stand.DARK_BLUE_MOON);
                    break;
                case "the_sun_disc":
                    StandUtils.setStand(user, Stand.THE_SUN);
                    break;
                case "king_crimson_disc":
                    StandUtils.setStand(user, Stand.KING_CRIMSON);
                    break;
                case "20th_century_boy_disc":
                    StandUtils.setStand(user, Stand.TWENTY_CENTURY_BOY);
                    break;
                case "hierophant_green_disc":
                    StandUtils.setStand(user, Stand.HIEROPHANT_GREEN);
                    break;
                default:
                    break;
            }
            StandUtils.setStandLevel(user, 1);
        }
        return super.use(world, user, hand);
    }
    @Environment(EnvType.CLIENT)
    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        String s = this.asItem().toString();
        tooltip.add(new TranslatableText("tooltip.jojo."+s).formatted(Formatting.GOLD));

    }
}