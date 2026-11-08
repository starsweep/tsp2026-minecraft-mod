package com.example.examplemod.data;

import com.example.examplemod.ExampleMod;
import com.mojang.serialization.Codec;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffects;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModDataComponents {
    public static final DeferredRegister.DataComponents DATA_COMPONENTS =
            DeferredRegister.createDataComponents(Registries.DATA_COMPONENT_TYPE, ExampleMod.MODID);

    // How many charges a flask has left. Stored per item stack.
    public static final DeferredHolder<DataComponentType<?>, DataComponentType<Integer>> CHARGES =
            DATA_COMPONENTS.registerComponentType("charges", builder -> builder
                    .persistent(Codec.INT)                      // saved to disk
                    .networkSynchronized(ByteBufCodecs.VAR_INT) // sent to the client
            );

    // The effect used by a flask. Stored per item stack.
    public static final DeferredHolder<DataComponentType<?>, DataComponentType<Holder<MobEffect>>> EFFECT =
            DATA_COMPONENTS.registerComponentType("effect", builder -> builder
                    .persistent(MobEffect.CODEC)
                    .networkSynchronized(MobEffect.STREAM_CODEC)
            );

    // The duration of the effect in a flask. Stored per item stack.
    public static final DeferredHolder<DataComponentType<?>, DataComponentType<Integer>> DURATION =
            DATA_COMPONENTS.registerComponentType("duration", builder -> builder
                    .persistent(Codec.INT)
                    .networkSynchronized(ByteBufCodecs.VAR_INT)
            );

    // Add more components the same way, e.g. a max-charges int or a String "contents".

    public static void register(IEventBus modEventBus) {
        DATA_COMPONENTS.register(modEventBus);
    }
}