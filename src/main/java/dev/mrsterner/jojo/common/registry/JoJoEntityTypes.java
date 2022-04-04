package dev.mrsterner.jojo.common.registry;

import dev.mrsterner.jojo.common.entity.StandEntity;
import net.minecraft.entity.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.util.registry.Registry;


public class JoJoEntityTypes {
    public static final EntityType<StandEntity> STAND = register("stand",StandEntity.createAttributes(), EntityType.Builder.create(StandEntity::new, SpawnGroup.MISC).setDimensions(0.8F, 0.8F));


    private static <T extends Entity> EntityType<T> register(String id,DefaultAttributeContainer.Builder attributes, EntityType.Builder<T> type) {
        return Registry.register(Registry.ENTITY_TYPE, id, type.build(id));
    }

    public static void init(){

    }
}
