package com.example.examplemod.skilltree;

import com.example.examplemod.skilltree.records.SkillNode;

import java.util.Set;

public class SkillRules {
    public static boolean isUnlockable(Set<String> unlocked, SkillNode node) {
        return unlocked.containsAll(node.parents());
    }
}
