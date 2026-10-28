package com.example.examplemod.items;

import com.example.examplemod.ExampleMod;
import com.mojang.serialization.Codec;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.codec.ByteBufCodecs;
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

    // Add more components the same way, e.g. a max-charges int or a String "contents".

    public static void register(IEventBus modEventBus) {
        DATA_COMPONENTS.register(modEventBus);
    }
}