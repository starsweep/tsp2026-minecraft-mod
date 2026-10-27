package com.example.examplemod.skilltree;

import com.example.examplemod.skilltree.records.SkillNode;
import net.minecraft.world.item.Items;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class SkillRegistry {
    public static final Map<String, SkillNode> NODES = new LinkedHashMap<>();

    static {
        int spacer = 80;

        // T1 base categories -----------------------------------------

        add(new SkillNode(
                "The Beginning",
                0, 0,
                Items.GRASS_BLOCK,
                List.of(),
                1,
                "Unlock the skilltree!",
                "The start of a grand adventure begins with a single step."
        ));
        add(new SkillNode(
                "Magic",
                spacer, 0,
                Items.EXPERIENCE_BOTTLE,
                List.of("The Beginning"),
                5,
                "Gain a 5% chance to boost experience dropped by mobs.",
                "A glimpse into the arcane."
        ));
        add(new SkillNode(
                "Heavy Melee",
                -spacer, 0,
                Items.IRON_CHESTPLATE,
                List.of("The Beginning"),
                5,
                "Critical strikes have a 5% chance to deal more damage.",
                "Well placed strikes can fell even the strongest of foes."
        ));
        add(new SkillNode(
                "Light Melee",
                0, -spacer,
                Items.IRON_SWORD,
                List.of("The Beginning"),
                5,
                "Gain a 5% chance to dodge incoming damage.",
                "\"All the world will be your enemy, and whenever they catch you, they will kill you. But first they must catch you.\""
        ));
        add(new SkillNode(
                "Ranged Weaponry",
                0, spacer,
                Items.ARROW,
                List.of("The Beginning"),
                5,
                "Gain a 5% chance to not consume ammo when firing a bow or crossbow.",
                "Steady yourself, take aim, and fire without hesitation."
        ));

        // T2 Magic ---------------------------------------------------

        add(new SkillNode(
                "Enchanting",
                2 * spacer, -spacer,
                Items.LECTERN,
                List.of("Magic"),
                10,
                "Gain the ability to enchant with a lectern.",
                "TODO"
        ));
        add(new SkillNode(
                "Brewing",
                2 * spacer, spacer,
                Items.CAULDRON,
                List.of("Magic"),
                10,
                "Gain the ability to brew with a cauldron.",
                "TODO"
        ));

        // T2 Light Melee ---------------------------------------------------

        add(new SkillNode(
                "Dual Wielding",
                spacer, -2 * spacer,
                Items.GOLDEN_SWORD,
                List.of("Light Melee"),
                10,
                "Gain the ability to use a sword in your offhand.",
                "Death by a thousand cuts."
        ));
        add(new SkillNode(
                "Lunging",
                -spacer, -2 * spacer,
                Items.WOODEN_SPEAR,
                List.of("Light Melee"),
                10,
                "Spears have an innate weak lunge effect",
                "TODO"
        ));

        // T2 Heavy Melee ---------------------------------------------------

        add(new SkillNode(
                "Macing",
                -2 * spacer, -spacer,
                Items.MACE,
                List.of("Heavy Melee"),
                10,
                "All strikes have a weak mace effect.",
                "A single, decisive blow."
        ));
        add(new SkillNode(
                "Parry",
                -2 * spacer, spacer,
                Items.SHIELD,
                List.of("Heavy Melee"),
                10,
                "Gain the ability to parry with your shield.",
                "The best defense..."
        ));

        // T2 Ranged Weaponry ---------------------------------------------------

        add(new SkillNode(
                "Bow Fluency",
                -spacer, 2 * spacer,
                Items.BOW,
                List.of("Ranged Weaponry"),
                10,
                "Bows have an increased charge rate.",
                "TODO"
        ));
        add(new SkillNode(
                "Crossbow Fluency",
                spacer, 2 * spacer,
                Items.CROSSBOW,
                List.of("Ranged Weaponry"),
                10,
                "Crossbows deal more damage on headshots.",
                "TODO"
        ));
    }

    private static void add(SkillNode node) {
        NODES.put(node.id(), node);
    }
}
