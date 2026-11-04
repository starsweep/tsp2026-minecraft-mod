package com.example.examplemod.command;

import com.example.examplemod.ExampleMod;
import com.example.examplemod.data.ModAttachments;
import com.example.examplemod.skilltree.records.SkillData;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.RegisterCommandsEvent;

@EventBusSubscriber(modid = ExampleMod.MODID)
public class DebugCommands {
    @SubscribeEvent
    public static void register(RegisterCommandsEvent event) {
        event.getDispatcher().register(
                Commands.literal("skills")
                        .requires(Commands.hasPermission(Commands.LEVEL_GAMEMASTERS))
                        .then(Commands.literal("reset")
                                // /skills reset  (yourself)
                                .executes(ctx -> reset(ctx.getSource(), ctx.getSource().getPlayerOrException()))
                                // /skills reset <player>
                                .then(Commands.argument("target", EntityArgument.player())
                                        .executes(ctx -> reset(ctx.getSource(),
                                                EntityArgument.getPlayer(ctx, "target"))))));
    }

    private static int reset(CommandSourceStack source, ServerPlayer target) {
        target.setData(ModAttachments.SKILLS, SkillData.EMPTY);
        source.sendSuccess(() -> Component.literal("Reset skills for " + target.getGameProfile().name()), true);
        return 1;
    }
}