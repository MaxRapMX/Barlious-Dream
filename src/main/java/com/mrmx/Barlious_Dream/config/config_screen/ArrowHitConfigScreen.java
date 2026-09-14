package com.mrmx.Barlious_Dream.config.config_screen;

import com.mrmx.Barlious_Dream.config.ArrowHitConfig;
import com.mrmx.Barlious_Dream.config.KillConfirmConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.AbstractSliderButton;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.Checkbox;
import net.minecraft.client.gui.components.CycleButton;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

public class ArrowHitConfigScreen extends Screen {
    private final Screen parent;

    public ArrowHitConfigScreen(Screen parent) {
        super(Component.translatable("mrmx_dream.config.arrow_hit.title"));
        this.parent = parent;
    }

    private class VolumeSlider extends AbstractSliderButton {
        VolumeSlider(int x, int y, int width, int height, double initialValue) {
            super(x, y, width, height, Component.empty(), initialValue); updateMessage();
        } @Override
        protected void updateMessage() {
            double realValue = Math.round(this.value * 2.0 * 100.0) / 100.0;
            this.setMessage(Component.translatable("mrmx_dream.config.sound_modules.volume").append(": " + realValue));
        } @Override
        protected void applyValue() {
            ArrowHitConfig.VOLUME.set(this.value * 2.0);
        }
    }

    private class PitchSlider extends AbstractSliderButton {
        PitchSlider(int x, int y, int width, int height, double initialValue) {
            super(x, y, width, height, Component.empty(), initialValue); updateMessage();
        } @Override
        protected void updateMessage() {
            double realValue = Math.round((0.5 + this.value * 1.5) * 100.0) / 100.0;
            this.setMessage(Component.translatable("mrmx_dream.config.sound_modules.pitch").append(": " + realValue));
        } @Override
        protected void applyValue() { ArrowHitConfig.PITCH.set(0.5 + this.value * 1.5); }
    }

    @Override
    public void onClose() { ArrowHitConfig.SPEC.save(); this.minecraft.setScreen(parent); }

    @Override
    protected void init() {
        int X_ScreenCenter = this.width / 2; int y = 40; int Menu_Spacing = 26;

        this.addRenderableWidget(Checkbox.builder(
                        Component.translatable("mrmx_dream.config.arrow_hit.enabled"), this.font)
                .pos(X_ScreenCenter - 60, 20).selected(ArrowHitConfig.ENABLED.get())
                .onValueChange((checkbox, value) -> ArrowHitConfig.ENABLED.set(value)).build());



        y += Menu_Spacing; this.addRenderableWidget(Checkbox.builder(
                        Component.translatable("mrmx_dream.config.arrow_hit.players"), this.font)
                .pos(X_ScreenCenter - 175, y).selected(ArrowHitConfig.PLAYER_ENABLED.get())
                .onValueChange((checkbox, value) -> ArrowHitConfig.PLAYER_ENABLED.set(value)).build());
        this.addRenderableWidget(Checkbox.builder(
                        Component.translatable("mrmx_dream.config.arrow_hit.enemy_mob"), this.font)
                .pos(X_ScreenCenter + 25, y).selected(ArrowHitConfig.ENEMY_ENABLED.get())
                .onValueChange((checkbox, value) -> ArrowHitConfig.ENEMY_ENABLED.set(value)).build());
        y += Menu_Spacing; this.addRenderableWidget(Checkbox.builder(
                        Component.translatable("mrmx_dream.config.arrow_hit.neutral_mob"), this.font)
                .pos(X_ScreenCenter - 175, y).selected(ArrowHitConfig.NEUTRAL_ENABLED.get())
                .onValueChange((checkbox, value) -> ArrowHitConfig.NEUTRAL_ENABLED.set(value)).build());
        this.addRenderableWidget(Checkbox.builder(
                        Component.translatable("mrmx_dream.config.arrow_hit.pacific_mob"), this.font)
                .pos(X_ScreenCenter + 25, y).selected(ArrowHitConfig.PACIFIC_ENABLED.get())
                .onValueChange((checkbox, value) -> ArrowHitConfig.PACIFIC_ENABLED.set(value)).build());



        y += Menu_Spacing; this.addRenderableWidget(Button.builder(
                Component.translatable("mrmx_dream.config.sound_modules.sound_test"),
                button -> { var player = Minecraft.getInstance().player;
                    if (player != null) {
                        player.playSound(
                                ArrowHitConfig.SOUND.get().getSound(),
                                (float) ArrowHitConfig.VOLUME.get().doubleValue(),
                                (float) ArrowHitConfig.PITCH.get().doubleValue()
                        );
                    }
                }).pos(X_ScreenCenter - 190, y).size(180, 20).build());
        this.addRenderableWidget(
                CycleButton.builder((ArrowHitConfig.ArrowHitSoundOption option) -> Component.literal(option.name()))
                        .withValues(ArrowHitConfig.ArrowHitSoundOption.values()).withInitialValue(ArrowHitConfig.SOUND.get())
                        .create(X_ScreenCenter + 10, y, 180, 20,
                                Component.translatable("mrmx_dream.config.arrow_hit.sound"),
                                (button, value) -> ArrowHitConfig.SOUND.set(value)));



        y += Menu_Spacing; double currentVolume = ArrowHitConfig.VOLUME.get();
        this.addRenderableWidget(new ArrowHitConfigScreen.VolumeSlider(X_ScreenCenter - 190, y, 180, 20, currentVolume / 2.0));
        double currentPitch = ArrowHitConfig.PITCH.get(); double pitchNormalized = (currentPitch - 0.5) / 1.5;
        this.addRenderableWidget(new ArrowHitConfigScreen.PitchSlider(X_ScreenCenter + 10, y, 180, 20, pitchNormalized));



        y += Menu_Spacing; this.addRenderableWidget(Checkbox.builder(
                        Component.translatable("mrmx_dream.config.arrow_block_hit.enabled"), this.font)
                .pos(X_ScreenCenter - 30, y).selected(ArrowHitConfig.SHAME_ENABLED.get())
                .onValueChange((checkbox, value) -> ArrowHitConfig.SHAME_ENABLED.set(value)).build());
        y += Menu_Spacing; this.addRenderableWidget(Button.builder(
                        Component.translatable("gui.done"),
                        button -> this.onClose())
                .pos(X_ScreenCenter - 100, this.height - 27)
                .size(200, 20).build());
    }
}