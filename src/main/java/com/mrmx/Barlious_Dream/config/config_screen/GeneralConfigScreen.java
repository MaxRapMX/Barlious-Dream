package com.mrmx.Barlious_Dream.config.config_screen;

import com.mrmx.Barlious_Dream.config.ModulesEnabler;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.CycleButton;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

public class GeneralConfigScreen extends Screen {
    private final Screen parent;

    public GeneralConfigScreen(Screen parent) {
        super(Component.translatable("mrmx_dream.config.title"));
        this.parent = parent;
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        int X_ScreenCenter = this.width / 2;
        super.render(guiGraphics, mouseX, mouseY, partialTick);
        guiGraphics.drawCenteredString(this.font, this.title, X_ScreenCenter, 20, 0xFFFFFF);
    }

    @Override
    protected void init() {
        int X_ScreenCenter = this.width / 2;
        int y = 60;
        int spacing = 26;

        this.addRenderableWidget(Button.builder(
                Component.translatable("mrmx_dream.module.kill_confirm_module"),
                        button -> this.minecraft.setScreen(new KillConfirmConfigScreen(this)))
                .pos(X_ScreenCenter - 190, y).size(180, 20).build());
        this.addRenderableWidget(Button.builder(
                        Component.translatable("mrmx_dream.module.game_pause_module"),
                        button -> this.minecraft.setScreen(new GamePauseConfigScreen(this)))
                .pos(X_ScreenCenter + 10, y).size(180, 20).build());

        y += spacing; this.addRenderableWidget(Button.builder(
                        Component.translatable("mrmx_dream.module.arrow_hit_module"),
                        button -> this.minecraft.setScreen(new ArrowHitConfigScreen(this)))
                .pos(X_ScreenCenter - 190, y).size(180, 20).build());
        this.addRenderableWidget(Button.builder(
                        Component.translatable("mrmx_dream.module.thrown_trident_hit_module"),
                        button -> this.minecraft.setScreen(new ThrownTridentHitConfigScreen(this)))
                .pos(X_ScreenCenter + 10, y).size(180, 20).build());

        y += spacing; this.addRenderableWidget(
                CycleButton.booleanBuilder(
                        Component.translatable("options.on"), Component.translatable("options.off")
                        ).withInitialValue(ModulesEnabler.TOTEM_POPPED_ENABLED.get()).create(
                                X_ScreenCenter - 90, y, 180, 20,
                                Component.translatable("config.mrmx_dream.totem_popped_notify.enabled"),
                                (button, value) -> ModulesEnabler.TOTEM_POPPED_ENABLED.set(value)
                        )
        );

        y += spacing; this.addRenderableWidget(Button.builder(
                Component.translatable("gui.done"),
                        button -> this.onClose())
                .pos(X_ScreenCenter - 100, this.height - 27).size(200, 20).build());
    }

    @Override
    public void onClose() {
        ModulesEnabler.SPEC.save(); this.minecraft.setScreen(parent);
    }
}