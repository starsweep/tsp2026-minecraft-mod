package com.example.examplemod.items;

import net.minecraft.core.Holder;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.item.Item;

public class SplashFlaskItem extends Item {
    int charges;
    int chargesMax = -1;
    int duration;
    Holder<MobEffect> effect;

    public SplashFlaskItem(Item.Properties properties) {
        super(properties);
    }

    // TODO actual functionality
}
