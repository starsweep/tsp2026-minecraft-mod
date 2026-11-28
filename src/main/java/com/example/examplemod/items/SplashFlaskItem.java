package com.example.examplemod.items;

import com.example.examplemod.data.ModDataComponents;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.Position;
import net.minecraft.core.component.DataComponents;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.throwableitemprojectile.AbstractThrownPotion;
import net.minecraft.world.entity.projectile.throwableitemprojectile.ThrownSplashPotion;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ProjectileItem;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;

public class SplashFlaskItem extends ThrowableFlaskItem {
    int charges;
    int chargesMax = -1;
    int duration;
    PotionContents effect;

    public SplashFlaskItem(Item.Properties properties) {
        super(properties);
    }

    // TODO actual functionality
    @Override
    public InteractionResult use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        chargesMax = stack.getOrDefault(ModDataComponents.MAX_CHARGES.get(), 0);
        charges = stack.getOrDefault(ModDataComponents.CHARGES.get(), 0);
        duration = stack.getOrDefault(ModDataComponents.DURATION.get(), 0);
        effect = stack.getOrDefault(DataComponents.POTION_CONTENTS, PotionContents.EMPTY);

        BlockHitResult hit = getPlayerPOVHitResult(level, player, ClipContext.Fluid.SOURCE_ONLY);

        if(hit.getType() == HitResult.Type.BLOCK) {
            BlockPos pos = hit.getBlockPos();
            BlockState state = level.getBlockState(pos);

            if(level.getFluidState(pos).is(FluidTags.WATER)) {
                level.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.BOTTLE_FILL, SoundSource.NEUTRAL, 1.0F, 1.0F);
                level.gameEvent(player, GameEvent.FLUID_PICKUP, pos);
                stack.set(ModDataComponents.CHARGES.get(), chargesMax);
                return InteractionResult.SUCCESS;
            } else if (state.is(BlockTags.CAULDRONS) && level.getBlockState(pos).is(Blocks.WATER_CAULDRON)) {
                // left separate so that it can drain cauldron in the future
                level.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.BOTTLE_FILL, SoundSource.NEUTRAL, 1.0F, 1.0F);
                level.gameEvent(player, GameEvent.FLUID_PICKUP, pos);
                stack.set(ModDataComponents.CHARGES.get(), chargesMax);
                return InteractionResult.SUCCESS;
            }
        }

        if (charges <= 0) return InteractionResult.FAIL;

        level.playSound(
                null,
                player.getX(),
                player.getY(),
                player.getZ(),
                SoundEvents.SPLASH_POTION_THROW,
                SoundSource.PLAYERS,
                0.5F,
                0.4F / (level.getRandom().nextFloat() * 0.4F + 0.8F)
        );

        stack.set(ModDataComponents.CHARGES.get(), charges - 1);
        player.getCooldowns().addCooldown(stack, 5);

        return super.use(level, player, hand);
    }


    @Override
    protected AbstractThrownPotion createPotion(final ServerLevel level, final LivingEntity owner, final ItemStack itemStack) {
        return new ThrownSplashPotion(level, owner, itemStack);
    }

    @Override
    protected AbstractThrownPotion createPotion(final Level level, final Position position, final ItemStack itemStack) {
        return new ThrownSplashPotion(level, position.x(), position.y(), position.z(), itemStack);
    }
}
