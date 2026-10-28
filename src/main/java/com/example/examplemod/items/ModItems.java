package com.example.examplemod.items;

import com.example.examplemod.ExampleMod;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister.Items ITEMS =
            DeferredRegister.createItems(ExampleMod.MODID);

    public static final DeferredItem<Item> FLASK;

    static {
        FLASK = ITEMS.registerItem("flask", FlaskItem::new,
                props -> {
                    return props
                            .stacksTo(1)
                            .component(ModDataComponents.CHARGES.get(), 5);
                });
    }

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}