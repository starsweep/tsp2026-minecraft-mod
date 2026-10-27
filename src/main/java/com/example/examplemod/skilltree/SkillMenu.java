package com.example.examplemod.skilltree;

import com.example.examplemod.skilltree.records.SkillNode;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.input.MouseButtonEvent;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jspecify.annotations.NonNull;

import java.util.HashSet;
import java.util.Set;

import static net.neoforged.neoforge.client.ClientHooks.playSound;

public class SkillMenu extends Screen {
    private static final int NODE_SIZE = 24;
    private static final int MARGIN = 20;

    private double offsetX = 0, offsetY = 0;
    // Temporary: replace with your data attachment later
    private final Set<String> unlocked = new HashSet<>();
    private Level level;

    public SkillMenu() {
        super(Component.literal("Skill Tree"));
    }

    // Map window bounds
    private int left()   { return MARGIN; }
    private int top()    { return MARGIN; }
    private int right()  { return width - MARGIN; }
    private int bottom() { return height - MARGIN; }

    // Where a node's top-left corner lands on screen
    private int screenX(SkillNode n) { return (left() + right()) / 2 + n.x() + (int) offsetX - NODE_SIZE / 2; }
    private int screenY(SkillNode n) { return (top() + bottom()) / 2 + n.y() + (int) offsetY - NODE_SIZE / 2; }

    private boolean isAvailable(SkillNode n) {
        return unlocked.containsAll(n.parents());
    }

    @Override
    public void extractRenderState(GuiGraphicsExtractor g, int mouseX, int mouseY, float partialTick) {
        super.extractRenderState(g, mouseX, mouseY, partialTick);

        // Frame and parchment-dark background
        g.fill(left() - 3, top() - 3, right() + 3, bottom() + 3, 0xFF6B5428);
        g.fill(left(), top(), right(), bottom(), 0xFF1C1610);

        g.enableScissor(left(), top(), right(), bottom()); // clip to the window

        // Lines first so nodes draw on top
        for (SkillNode node : SkillRegistry.NODES.values()) {
            for (String parentId : node.parents()) {
                SkillNode parent = SkillRegistry.NODES.get(parentId);
                if (parent == null) continue;
                int color = unlocked.contains(parentId) ? 0xFFE0B040 : 0xFF554433;
                drawLine(g,
                        screenX(parent) + NODE_SIZE / 2, screenY(parent) + NODE_SIZE / 2,
                        screenX(node) + NODE_SIZE / 2, screenY(node) + NODE_SIZE / 2, color);
            }
        }

        // Nodes
        SkillNode hovered = null;
        for (SkillNode node : SkillRegistry.NODES.values()) {
            int x = screenX(node), y = screenY(node);
            int plate = unlocked.contains(node.id()) ? 0xFFE0B040
                    : isAvailable(node)            ? 0xFF8A7A5A
                    :                                0xFF3A3028;
            g.fill(x - 2, y - 2, x + NODE_SIZE + 2, y + NODE_SIZE + 2, 0xFF000000);
            g.fill(x, y, x + NODE_SIZE, y + NODE_SIZE, plate);
            g.item(new ItemStack(node.icon()), x + 4, y + 4);

            if (mouseX >= x && mouseX < x + NODE_SIZE && mouseY >= y && mouseY < y + NODE_SIZE) {
                hovered = node;
            }
        }

        g.disableScissor();

        if (hovered != null) {
            g.text(font, hovered.id(), mouseX + 8, mouseY - 12, 0xFFFFFFFF);
        }
    }

    // L-shaped connector: horizontal then vertical
    private void drawLine(GuiGraphicsExtractor g, int x1, int y1, int x2, int y2, int color) {
        g.fill(Math.min(x1, x2), y1 - 1, Math.max(x1, x2) + 1, y1 + 1, color);
        g.fill(x2 - 1, Math.min(y1, y2), x2 + 1, Math.max(y1, y2) + 1, color);
    }

    private boolean inWindow(double mx, double my) {
        return mx >= left() && mx < right() && my >= top() && my < bottom();
    }

    private boolean hit(SkillNode n, double mx, double my) {
        int x = screenX(n), y = screenY(n);
        return inWindow(mx, my) && mx >= x && mx < x + NODE_SIZE && my >= y && my < y + NODE_SIZE;
    }

    private boolean dragging = false;

    @Override
    public boolean mouseClicked(@NonNull MouseButtonEvent event, boolean doubleClick) {
        if (!inWindow(event.x(), event.y())) {
            return super.mouseClicked(event, doubleClick);
        }

        for (SkillNode node : SkillRegistry.NODES.values()) {
            if (hit(node, event.x(), event.y())) {
                if (SkillRules.isUnlockable(unlocked, node)) {
                    unlocked.add(node.id());
                    Minecraft.getInstance().getSoundManager().play(
                            SimpleSoundInstance.forUI(SoundEvents.EXPERIENCE_ORB_PICKUP, 1.0F));
                }
                return true;
            }
        }

        dragging = true; // pressed on empty space: start panning
        return true;
    }

    @Override
    public boolean mouseDragged(@NonNull MouseButtonEvent event, double dx, double dy) {
        if (!dragging) return false;
        offsetX += dx;
        offsetY += dy;
        return true;
    }

    @Override
    public boolean mouseReleased(@NonNull MouseButtonEvent event) {
        dragging = false;
        return true;
    }
}