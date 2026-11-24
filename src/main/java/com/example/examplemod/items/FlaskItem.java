package com.example.examplemod.items;

import com.example.examplemod.data.ModDataComponents;
import com.llamalad7.mixinextras.expression.impl.ast.expressions.NullLiteralExpression;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import org.jspecify.annotations.NonNull;

public class FlaskItem extends Item {
    int charges;
    int chargesMax = -1;
    int duration;
    Holder<MobEffect> effect;

    public FlaskItem(Item.Properties properties) {
        super(properties);
    }

    // Right-click in air
    @Override
    public @NonNull InteractionResult use(Level level, Player player, @NonNull InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        if (level.isClientSide()) return InteractionResult.SUCCESS; // handle client

        chargesMax = stack.getOrDefault(ModDataComponents.MAX_CHARGES.get(), 0);
        charges = stack.getOrDefault(ModDataComponents.CHARGES.get(), 0);
        duration = stack.getOrDefault(ModDataComponents.DURATION.get(), 0);
        effect = stack.getOrDefault(ModDataComponents.EFFECT.get(), MobEffects.POISON);

        BlockHitResult hit = getPlayerPOVHitResult(level, player, ClipContext.Fluid.SOURCE_ONLY);

        if(hit.getType() == HitResult.Type.BLOCK) {
            BlockPos pos = hit.getBlockPos();

            if(level.getFluidState(pos).is(FluidTags.WATER)) {
                level.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.BOTTLE_FILL, SoundSource.NEUTRAL, 1.0F, 1.0F);
                level.gameEvent(player, GameEvent.FLUID_PICKUP, pos);
                stack.set(ModDataComponents.CHARGES.get(), chargesMax);
                return InteractionResult.SUCCESS;
            }
        }

        if (charges <= 0) return InteractionResult.FAIL;

        level.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.GENERIC_DRINK, SoundSource.NEUTRAL, 1.0F, 1.0F);
        player.gameEvent(GameEvent.DRINK);
        stack.set(ModDataComponents.CHARGES.get(), charges - 1);
        player.getCooldowns().addCooldown(stack, 20);


        // effect here
        MobEffectInstance instance = new MobEffectInstance(
                effect,
                duration,
                0
        );
        player.addEffect(instance);

        return InteractionResult.SUCCESS;
    }
}