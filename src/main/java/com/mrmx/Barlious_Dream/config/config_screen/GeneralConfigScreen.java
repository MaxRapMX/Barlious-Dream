package com.mrmx.Barlious_Dream.config.config_screen;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

public class GeneralConfigScreen extends Screen {
    private final Screen parent;

    public GeneralConfigScreen(Screen parent) {
        super(Component.translatable("mrmx_dream.config.general.title"));
        this.parent = parent;
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        int centerX = this.width / 2;
        super.render(guiGraphics, mouseX, mouseY, partialTick);
        guiGraphics.drawCenteredString(this.font, this.title, centerX, 20, 0xFFFFFF);
    }

    @Override
    protected void init() {
        int centerX = this.width / 2;
        int y = 60;
        int spacing = 26;

        this.addRenderableWidget(Button.builder(
                Component.translatable("mrmx_dream.config.general.kill_confirm_module"),
                        button -> this.minecraft.setScreen(new KillConfirmConfigScreen(this)))
                .pos(centerX - 190, y).size(180, 20).build());

        this.addRenderableWidget(Button.builder(
                        Component.translatable("mrmx_dream.config.general.game_pause_module"),
                        button -> this.minecraft.setScreen(new GamePauseConfigScreen(this)))
                .pos(centerX + 10, y)
                .size(180, 20)
                .build());

        y += spacing;

        this.addRenderableWidget(Button.builder(
                Component.translatable("gui.done"),
                        button -> this.onClose())
                .pos(centerX - 100, this.height - 27).size(200, 20).build());
    }

    @Override
    public void onClose() {
        this.minecraft.setScreen(parent);
    }
}