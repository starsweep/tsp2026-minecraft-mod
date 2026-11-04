package com.example.examplemod.skilltree.records;

import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;
import org.jspecify.annotations.NonNull;

public record UnlockSkillPayload(String skillId) implements CustomPacketPayload {
    public static final Type<UnlockSkillPayload> TYPE =
            new Type<>(Identifier.fromNamespaceAndPath("examplemod", "unlock_skill"));
    public static final StreamCodec<ByteBuf, UnlockSkillPayload> STREAM_CODEC =
            ByteBufCodecs.STRING_UTF8.map(UnlockSkillPayload::new, UnlockSkillPayload::skillId);

    @Override
    public @NonNull Type<? extends CustomPacketPayload> type() { return TYPE; }
}
