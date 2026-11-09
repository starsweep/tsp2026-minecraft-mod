package com.example.examplemod.items;

import com.example.examplemod.ExampleMod;
import com.example.examplemod.data.ModDataComponents;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister.Items ITEMS =
            DeferredRegister.createItems(ExampleMod.MODID);

    public static final DeferredItem<Item> FLASK_POISON;
    public static final DeferredItem<Item> FLASK_REGEN;
    public static final DeferredItem<Item> FLASK_STRENGTH;
    public static final DeferredItem<Item> FLASK_WEAK;
    public static final DeferredItem<Item> FLASK_SLOW;
    public static final DeferredItem<Item> FLASK_SPEED;
    public static final DeferredItem<Item> FLASK_WATER;
    public static final DeferredItem<Item> FLASK_FALL;
    public static final DeferredItem<Item> FLASK_WIND;
    public static final DeferredItem<Item> FLASK_VISION;
    public static final DeferredItem<Item> FLASK_BLIND;
    public static final DeferredItem<Item> FLASK_JUMP;
    public static final DeferredItem<Item> FLASK_LEVI;
    public static final DeferredItem<Item> FLASK_INVIS;
    public static final DeferredItem<Item> FLASK_FIRERES;
    public static final DeferredItem<Item> FLASK_WITHER;
    public static final DeferredItem<Item> FLASK_INSTAHEAL;
    public static final DeferredItem<Item> FLASK_INSTADMG;

    public static final DeferredItem<Item> MAGIC_SKILL;
    public static final DeferredItem<Item> SWORD_SKILL;
    public static final DeferredItem<Item> SHIELD_SKILL;
    public static final DeferredItem<Item> BOW_SKILL;
    public static final DeferredItem<Item> CROSSBOW_SKILL;
    public static final DeferredItem<Item> DUEL_SWORDS;
    public static final DeferredItem<Item> TRIPLE_BOW;
    public static final DeferredItem<Item> MELEE_SKILL;
    public static final DeferredItem<Item> POTION_SKILL;
    public static final DeferredItem<Item> SHIELD_SPEAR;
    public static final DeferredItem<Item> MACE_SKILL;

    static {

        MAGIC_SKILL = ITEMS.registerItem("magic_skill", Item::new, props -> props);
        SWORD_SKILL = ITEMS.registerItem("sword_skill", Item::new, props -> props);
        SHIELD_SKILL = ITEMS.registerItem("shield_skill", Item::new, props -> props);
        BOW_SKILL = ITEMS.registerItem("bow_skill", Item::new, props -> props);
        CROSSBOW_SKILL = ITEMS.registerItem("crossbow_skill", Item::new, props -> props);
        DUEL_SWORDS = ITEMS.registerItem("duel_swords", Item::new, props -> props);
        TRIPLE_BOW = ITEMS.registerItem("triple_bow", Item::new, props -> props);
        MELEE_SKILL = ITEMS.registerItem("melee_skill", Item::new, props -> props);
        POTION_SKILL = ITEMS.registerItem("potion_skill", Item::new, props -> props);
        SHIELD_SPEAR = ITEMS.registerItem("shield_spear", Item::new, props -> props);
        MACE_SKILL = ITEMS.registerItem("mace_skill", Item::new, props -> props);

        FLASK_POISON = ITEMS.registerItem("flaskpoison", FlaskItem::new,
                props -> {
                    return props
                            .stacksTo(1)
                            .component(ModDataComponents.CHARGES.get(), 5)
                            .component(ModDataComponents.DURATION.get(), 6000)
                            .component(ModDataComponents.EFFECT.get(), MobEffects.POISON);
                });
        FLASK_REGEN = ITEMS.registerItem("flaskregeneration", FlaskItem::new,
                props -> {
                    return props
                            .stacksTo(1)
                            .component(ModDataComponents.CHARGES.get(), 5)
                            .component(ModDataComponents.DURATION.get(), 6000)
                            .component(ModDataComponents.EFFECT.get(), MobEffects.REGENERATION);
                });
        FLASK_STRENGTH = ITEMS.registerItem("flaskstrength", FlaskItem::new,
                props -> {
                    return props
                            .stacksTo(1)
                            .component(ModDataComponents.CHARGES.get(), 5)
                            .component(ModDataComponents.DURATION.get(), 6000)
                            .component(ModDataComponents.EFFECT.get(), MobEffects.STRENGTH);
                });
        FLASK_WEAK = ITEMS.registerItem("flaskweakness", FlaskItem::new,
                props -> {
                    return props
                            .stacksTo(1)
                            .component(ModDataComponents.CHARGES.get(), 5)
                            .component(ModDataComponents.DURATION.get(), 6000)
                            .component(ModDataComponents.EFFECT.get(), MobEffects.WEAKNESS);
                });
        FLASK_SLOW = ITEMS.registerItem("flaskslowness", FlaskItem::new,
                props -> {
                    return props
                            .stacksTo(1)
                            .component(ModDataComponents.CHARGES.get(), 5)
                            .component(ModDataComponents.DURATION.get(), 6000)
                            .component(ModDataComponents.EFFECT.get(), MobEffects.SLOWNESS);
                });
        FLASK_SPEED = ITEMS.registerItem("flaskspeed", FlaskItem::new,
                props -> {
                    return props
                            .stacksTo(1)
                            .component(ModDataComponents.CHARGES.get(), 5)
                            .component(ModDataComponents.DURATION.get(), 6000)
                            .component(ModDataComponents.EFFECT.get(), MobEffects.SPEED);
                });

        FLASK_WATER = ITEMS.registerItem("flaskwaterbreathing", FlaskItem::new,
                props -> {
                    return props
                            .stacksTo(1)
                            .component(ModDataComponents.CHARGES.get(), 5)
                            .component(ModDataComponents.DURATION.get(), 6000)
                            .component(ModDataComponents.EFFECT.get(), MobEffects.WATER_BREATHING);
                });

        FLASK_FALL = ITEMS.registerItem("flaskslowfalling", FlaskItem::new,
                props -> {
                    return props
                            .stacksTo(1)
                            .component(ModDataComponents.CHARGES.get(), 5)
                            .component(ModDataComponents.DURATION.get(), 6000)
                            .component(ModDataComponents.EFFECT.get(), MobEffects.SLOW_FALLING);
                });

        FLASK_WIND = ITEMS.registerItem("flaskwindcharged", FlaskItem::new,
                props -> {
                    return props
                            .stacksTo(1)
                            .component(ModDataComponents.CHARGES.get(), 5)
                            .component(ModDataComponents.DURATION.get(), 6000)
                            .component(ModDataComponents.EFFECT.get(), MobEffects.WIND_CHARGED);
                });
        FLASK_VISION = ITEMS.registerItem("flasknightvision", FlaskItem::new,
                props -> {
                    return props
                            .stacksTo(1)
                            .component(ModDataComponents.CHARGES.get(), 5)
                            .component(ModDataComponents.DURATION.get(), 6000)
                            .component(ModDataComponents.EFFECT.get(), MobEffects.NIGHT_VISION);
                });

        FLASK_BLIND = ITEMS.registerItem("flaskblindness", FlaskItem::new,
                props -> {
                    return props
                            .stacksTo(1)
                            .component(ModDataComponents.CHARGES.get(), 5)
                            .component(ModDataComponents.DURATION.get(), 6000)
                            .component(ModDataComponents.EFFECT.get(), MobEffects.BLINDNESS);
                });

        FLASK_JUMP = ITEMS.registerItem("flaskjumpboost", FlaskItem::new,
                props -> {
                    return props
                            .stacksTo(1)
                            .component(ModDataComponents.CHARGES.get(), 5)
                            .component(ModDataComponents.DURATION.get(), 6000)
                            .component(ModDataComponents.EFFECT.get(), MobEffects.JUMP_BOOST);
                });

        FLASK_LEVI = ITEMS.registerItem("flasklevitation", FlaskItem::new,
                props -> {
                    return props
                            .stacksTo(1)
                            .component(ModDataComponents.CHARGES.get(), 5)
                            .component(ModDataComponents.DURATION.get(), 6000)
                            .component(ModDataComponents.EFFECT.get(), MobEffects.LEVITATION);
                });

        FLASK_INVIS = ITEMS.registerItem("flaskinvisibility", FlaskItem::new,
                props -> {
                    return props
                            .stacksTo(1)
                            .component(ModDataComponents.CHARGES.get(), 5)
                            .component(ModDataComponents.DURATION.get(), 6000)
                            .component(ModDataComponents.EFFECT.get(), MobEffects.INVISIBILITY);
                });

        FLASK_FIRERES = ITEMS.registerItem("flaskfireresistance", FlaskItem::new,
                props -> {
                    return props
                            .stacksTo(1)
                            .component(ModDataComponents.CHARGES.get(), 5)
                            .component(ModDataComponents.DURATION.get(), 6000)
                            .component(ModDataComponents.EFFECT.get(), MobEffects.FIRE_RESISTANCE);
                });

        FLASK_WITHER = ITEMS.registerItem("flaskwither", FlaskItem::new,
                props -> {
                    return props
                            .stacksTo(1)
                            .component(ModDataComponents.CHARGES.get(), 5)
                            .component(ModDataComponents.DURATION.get(), 6000)
                            .component(ModDataComponents.EFFECT.get(), MobEffects.WITHER);
                });

        FLASK_INSTADMG = ITEMS.registerItem("flaskharming", FlaskItem::new,
                props -> {
                    return props
                            .stacksTo(1)
                            .component(ModDataComponents.CHARGES.get(), 5)
                            .component(ModDataComponents.DURATION.get(), 1)
                            .component(ModDataComponents.EFFECT.get(), MobEffects.INSTANT_DAMAGE);
                });

        FLASK_INSTAHEAL = ITEMS.registerItem("flaskhealing", FlaskItem::new,
                props -> {
                    return props
                            .stacksTo(1)
                            .component(ModDataComponents.CHARGES.get(), 5)
                            .component(ModDataComponents.DURATION.get(), 1)
                            .component(ModDataComponents.EFFECT.get(), MobEffects.INSTANT_HEALTH);
                });
    }


    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}