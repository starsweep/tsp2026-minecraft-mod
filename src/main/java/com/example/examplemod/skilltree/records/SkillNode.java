package com.example.examplemod.skilltree.records;

import net.minecraft.world.item.Item;
import java.util.List;

public record SkillNode(
        String id,
        int x,
        int y,
        Item icon,
        List<String> parents,
        int cost,
        String description,
        String flavorText
) {}
