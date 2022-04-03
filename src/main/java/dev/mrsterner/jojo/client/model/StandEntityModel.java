package dev.mrsterner.jojo.client.model;

import dev.mrsterner.jojo.JoJo;
import dev.mrsterner.jojo.common.entity.StandEntity;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class StandEntityModel extends AnimatedGeoModel<StandEntity> {
    @Override
    public Identifier getModelLocation(StandEntity object) {
        return new Identifier(JoJo.MODID, "geo/theworld.geo.json");
    }

    @Override
    public Identifier getTextureLocation(StandEntity object) {
        return new Identifier(JoJo.MODID, "textures/entity/theworld.png");
    }

    @Override
    public Identifier getAnimationFileLocation(StandEntity animatable) {
        return new Identifier(JoJo.MODID, "animations/theworld.animation.json");
    }
}


