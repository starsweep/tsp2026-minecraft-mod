package com.example.examplemod.network;

import com.example.examplemod.data.ModAttachments;
import com.example.examplemod.skilltree.SkillRegistry;
import com.example.examplemod.skilltree.SkillRules;
import com.example.examplemod.skilltree.records.SkillData;
import com.example.examplemod.skilltree.records.SkillNode;
import com.example.examplemod.skilltree.records.UnlockSkillPayload;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;

@EventBusSubscriber(modid = "examplemod")
public class ModNetwork {
    @SubscribeEvent
    public static void registerPayloads(RegisterPayloadHandlersEvent event) {
        PayloadRegistrar registrar = event.registrar("1");
        registrar.playToServer(UnlockSkillPayload.TYPE, UnlockSkillPayload.STREAM_CODEC,
                (payload, ctx) -> {
                    Player player = ctx.player();
                    SkillNode node = SkillRegistry.NODES.get(payload.skillId());
                    SkillData data = player.getData(ModAttachments.SKILLS);
                    if (node != null && SkillRules.isUnlockable(data.unlocked(), node)) {
                        player.setData(ModAttachments.SKILLS, data.with(node.id()));
                    }
                });
    }
}
