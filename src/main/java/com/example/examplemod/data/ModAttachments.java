package com.example.examplemod.data;

import com.example.examplemod.skilltree.records.SkillData;
import com.mojang.serialization.Codec;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.function.Supplier;

public class ModAttachments {
    private static final DeferredRegister<AttachmentType<?>> TYPES =
            DeferredRegister.create(NeoForgeRegistries.ATTACHMENT_TYPES, "examplemod");

    public static final Supplier<AttachmentType<SkillData>> SKILLS =
            register("skills", SkillData.EMPTY, SkillData.CODEC, SkillData.STREAM_CODEC);

    private static <T> Supplier<AttachmentType<T>> register(
            String name,
            T empty,
            Codec<T> codec,
            StreamCodec<? super RegistryFriendlyByteBuf, T> stream) {
        return TYPES.register(name, () -> AttachmentType.builder(() -> empty)
                .serialize(codec.fieldOf(name))
                .sync(stream)
                .copyOnDeath()
                .build());
    }

    public static void register(IEventBus modEventBus) {
        TYPES.register(modEventBus);
    }
}
