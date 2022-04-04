package dev.mrsterner.jojo;

import dev.mrsterner.jojo.api.stand.Stand;
import dev.mrsterner.jojo.api.stand.StandUtils;
import dev.mrsterner.jojo.common.item.KillerQueenTriggerItem;
import dev.mrsterner.jojo.common.registry.JoJoEntityTypes;
import dev.mrsterner.jojo.common.registry.JoJoObjects;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.event.player.AttackBlockCallback;
import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class JoJo implements ModInitializer {
	public static final String MODID = "jojo";
	public static final ItemGroup JOJO_GROUP = FabricItemGroupBuilder.build(new Identifier(MODID, MODID), () -> new ItemStack(Items.ACACIA_PLANKS));
	public static final Logger LOGGER = LoggerFactory.getLogger(MODID);


	@Override
	public void onInitialize() {
		JoJoEntityTypes.init();

		AttackEntityCallback.EVENT.register(this::killerQueenGetEntity);
		AttackBlockCallback.EVENT.register(this::killerQueenGetBlock);
	}


	/**
	 *
	 * @param player
	 * @param world
	 * @param hand
	 * @param blockPos
	 * @param direction
	 * @return
	 */
	private ActionResult killerQueenGetBlock(PlayerEntity player, World world, Hand hand, BlockPos blockPos, Direction direction) {
		if(StandUtils.getStand(player) == Stand.KILLER_QUEEN && hand == Hand.MAIN_HAND) {
			if(!player.getInventory().contains(new ItemStack(JoJoObjects.KILLER_QUEEN_TRIGGER))){
				HitResult hitResult = MinecraftClient.getInstance().crosshairTarget;

				float newPosX = blockPos.getX();
				float newPosY = blockPos.getY();
				float newPosZ = blockPos.getZ();

				if (hitResult instanceof BlockHitResult) {
					direction = ((BlockHitResult) hitResult).getSide();
					switch (direction) {
						case UP:
							newPosY = blockPos.getY() + 1.0F;
							break;
						case DOWN:
							newPosY = blockPos.getY() - 1.0F;
							break;
						case NORTH:
							newPosX = blockPos.getX() + 1.0F;
							break;
						case SOUTH:
							newPosX = blockPos.getX() - 1.0F;
							break;
						case EAST:
							newPosZ = blockPos.getZ() + 1.0F;
							break;
						case WEST:
							newPosZ = blockPos.getZ() - 1.0F;
							break;
						default:
					}
				}

				ItemStack trigger = new ItemStack(JoJoObjects.KILLER_QUEEN_TRIGGER);
				KillerQueenTriggerItem.setData(trigger, KillerQueenTriggerItem.TYPE.BLOCK.getName(),"empty",newPosX, newPosY, newPosZ);

				if(player.getStackInHand(Hand.MAIN_HAND).isEmpty()){
					player.setStackInHand(Hand.MAIN_HAND, trigger);
				}else{
					player.dropItem(trigger, false, true);
				}
			}else{
				PlayerInventory inventory = player.getInventory();
				List<ItemStack> mainInventory = inventory.main;
				for(ItemStack trigger : mainInventory) {
					if(trigger.getItem() == JoJoObjects.KILLER_QUEEN_TRIGGER) {
						KillerQueenTriggerItem.setData(trigger, KillerQueenTriggerItem.TYPE.BLOCK.getName(),"empty",blockPos.getX(), blockPos.getY(), blockPos.getZ());
						break;
					}
				}
			}
		}
		return ActionResult.PASS;
	}

	/**
	 *
	 * @param player
	 * @param world
	 * @param hand
	 * @param entity
	 * @param entityHitResult
	 * @return
	 */
	private ActionResult killerQueenGetEntity(PlayerEntity player, World world, Hand hand, Entity entity, @Nullable EntityHitResult entityHitResult) {
		if(StandUtils.getStand(player) == Stand.KILLER_QUEEN && hand == Hand.MAIN_HAND){
			if(!player.getInventory().contains(new ItemStack(JoJoObjects.KILLER_QUEEN_TRIGGER))){
				ItemStack trigger = new ItemStack(JoJoObjects.KILLER_QUEEN_TRIGGER);
				KillerQueenTriggerItem.setData(trigger, KillerQueenTriggerItem.TYPE.ENTITY.getName(), entity.getUuid().toString(), 0, 0, 0);
				if(player.getStackInHand(Hand.MAIN_HAND).isEmpty()){
					player.setStackInHand(Hand.MAIN_HAND, trigger);
				}else{
					player.dropItem(trigger, false, true);
				}
			}else{
				PlayerInventory inventory = player.getInventory();
				List<ItemStack> mainInventory = inventory.main;
				for(ItemStack trigger : mainInventory) {
					if(trigger.getItem() == JoJoObjects.KILLER_QUEEN_TRIGGER) {
						KillerQueenTriggerItem.setData(trigger, KillerQueenTriggerItem.TYPE.ENTITY.getName(), entity.getUuid().toString(), 0, 0, 0);
						break;
					}
				}
			}
		}
		return ActionResult.PASS;
	}


}
