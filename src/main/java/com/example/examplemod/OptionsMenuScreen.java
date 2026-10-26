package com.example.examplemod;

import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

public class OptionsMenuScreen extends Screen {
    private boolean toggled = false;

    public OptionsMenuScreen() {
        super(Component.literal("My Menu"));
    }

    @Override
    protected void init() {
        int w = 200, h = 20;
        int x = this.width / 2 - w / 2;
        int y = this.height / 2 - 40;

        // Option 1: do something (send a chat message)
        addRenderableWidget(Button.builder(Component.literal("Say Hello"), btn -> {
            if (this.minecraft.player != null) {
                this.minecraft.player.sendSystemMessage(Component.literal("Hello from my mod!"));
            }
            this.onClose();
        }).bounds(x, y, w, h).build());

        // Option 2: a toggle that updates its own label
        addRenderableWidget(Button.builder(Component.literal("Toggle: OFF"), btn -> {
            toggled = !toggled;
            btn.setMessage(Component.literal("Toggle: " + (toggled ? "ON" : "OFF")));
        }).bounds(x, y + 25, w, h).build());

        // Option 3: close
        addRenderableWidget(Button.builder(Component.literal("Close"), btn -> this.onClose())
                .bounds(x, y + 50, w, h).build());
    }
}