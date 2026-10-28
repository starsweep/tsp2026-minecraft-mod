package com.example.examplemod.items;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jspecify.annotations.NonNull;

public class FlaskItem extends Item {
    public FlaskItem(Item.Properties properties) {
        super(properties);
    }

    // Right-click in air
    @Override
    public @NonNull InteractionResult use(Level level, Player player, @NonNull InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        if (level.isClientSide()) return InteractionResult.SUCCESS; // handle client

        int charges = stack.getOrDefault(ModDataComponents.CHARGES.get(), 0);
        if (charges <= 0) return InteractionResult.FAIL;

        stack.set(ModDataComponents.CHARGES.get(), charges - 1);
        player.getCooldowns().addCooldown(stack, 20);
        // effect here
        return InteractionResult.SUCCESS;
    }
}