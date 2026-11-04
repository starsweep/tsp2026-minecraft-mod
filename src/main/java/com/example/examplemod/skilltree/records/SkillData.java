package com.example.examplemod.skilltree.records;

import com.mojang.serialization.Codec;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public record SkillData(Set<String> unlocked) {
    public static final SkillData EMPTY = new SkillData(Set.of());

    public static final Codec<SkillData> CODEC = Codec.STRING.listOf()
            .xmap(l -> new SkillData(Set.copyOf(l)), d -> List.copyOf(d.unlocked()))
            ; // see the note on MapCodec below

    public static final StreamCodec<ByteBuf, SkillData> STREAM_CODEC =
            ByteBufCodecs.STRING_UTF8.apply(ByteBufCodecs.list())
                    .map(l -> new SkillData(Set.copyOf(l)), d -> List.copyOf(d.unlocked()));

    public SkillData with(String id) {
        Set<String> s = new HashSet<>(unlocked);
        s.add(id);
        return new SkillData(Set.copyOf(s));
    }
}