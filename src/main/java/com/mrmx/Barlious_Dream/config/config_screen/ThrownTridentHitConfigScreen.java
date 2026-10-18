package com.mrmx.Barlious_Dream.config.config_screen;

import com.mrmx.Barlious_Dream.config.ModulesEnabler;
import com.mrmx.Barlious_Dream.config.ThrownTridentHitConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.AbstractSliderButton;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.Checkbox;
import net.minecraft.client.gui.components.CycleButton;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

public class ThrownTridentHitConfigScreen extends Screen {
    private final Screen parent;

    public ThrownTridentHitConfigScreen(Screen parent) {
        super(Component.translatable("mrmx_dream.module.thrown_trident_hit_module"));
        this.parent = parent;
    }

    private class VolumeSlider extends AbstractSliderButton {
        VolumeSlider(int x, int y, int width, int height, double initialValue) {
            super(x, y, width, height, Component.empty(), initialValue); updateMessage();
        } @Override
        protected void updateMessage() {
            double realValue = Math.round(this.value * 2.0 * 100.0) / 100.0;
            this.setMessage(Component.translatable("mrmx_dream.config.sound.volume").append(": " + realValue));
        } @Override
        protected void applyValue() {
            ThrownTridentHitConfig.VOLUME.set(this.value * 2.0);
        }
    }

    private class PitchSlider extends AbstractSliderButton {
        PitchSlider(int x, int y, int width, int height, double initialValue) {
            super(x, y, width, height, Component.empty(), initialValue); updateMessage();
        } @Override
        protected void updateMessage() {
            double realValue = Math.round((0.5 + this.value * 1.5) * 100.0) / 100.0;
            this.setMessage(Component.translatable("mrmx_dream.config.sound.pitch").append(": " + realValue));
        } @Override
        protected void applyValue() { ThrownTridentHitConfig.PITCH.set(0.5 + this.value * 1.5); }
    }

    @Override
    public void onClose() {
        ModulesEnabler.SPEC.save(); ThrownTridentHitConfig.SPEC.save(); this.minecraft.setScreen(parent); }

    @Override
    protected void init() {
        int X_ScreenCenter = this.width / 2; int y = 40; int Menu_Spacing = 26;

        this.addRenderableWidget(Checkbox.builder(
                        Component.translatable("mrmx_dream.module.thrown_trident_hit_module"), this.font)
                .pos(X_ScreenCenter - 80, 20).selected(ModulesEnabler.THROWN_TRIDENT_HIT_ENABLED.get())
                .onValueChange((checkbox, value) -> ModulesEnabler.THROWN_TRIDENT_HIT_ENABLED.set(value)).build());



        y += Menu_Spacing; this.addRenderableWidget(Checkbox.builder(
                        Component.translatable("mrmx_dream.module.thrown_trident_hit.config.players"), this.font)
                .pos(X_ScreenCenter - 175, y).selected(ThrownTridentHitConfig.PLAYER_ENABLED.get())
                .onValueChange((checkbox, value) -> ThrownTridentHitConfig.PLAYER_ENABLED.set(value)).build());
        this.addRenderableWidget(Checkbox.builder(
                        Component.translatable("mrmx_dream.module.thrown_trident_hit.config.enemy_mob"), this.font)
                .pos(X_ScreenCenter + 25, y).selected(ThrownTridentHitConfig.ENEMY_ENABLED.get())
                .onValueChange((checkbox, value) -> ThrownTridentHitConfig.ENEMY_ENABLED.set(value)).build());
        y += Menu_Spacing; this.addRenderableWidget(Checkbox.builder(
                        Component.translatable("mrmx_dream.module.thrown_trident_hit.config.neutral_mob"), this.font)
                .pos(X_ScreenCenter - 175, y).selected(ThrownTridentHitConfig.NEUTRAL_ENABLED.get())
                .onValueChange((checkbox, value) -> ThrownTridentHitConfig.NEUTRAL_ENABLED.set(value)).build());
        this.addRenderableWidget(Checkbox.builder(
                        Component.translatable("mrmx_dream.module.thrown_trident_hit.config.pacific_mob"), this.font)
                .pos(X_ScreenCenter + 25, y).selected(ThrownTridentHitConfig.PACIFIC_ENABLED.get())
                .onValueChange((checkbox, value) -> ThrownTridentHitConfig.PACIFIC_ENABLED.set(value)).build());



        y += Menu_Spacing; this.addRenderableWidget(Button.builder(
                Component.translatable("mrmx_dream.config.sound.test"),
                button -> { var player = Minecraft.getInstance().player;
                    if (player != null) {
                        player.playSound(
                                ThrownTridentHitConfig.SOUND.get().getSound(),
                                (float) ThrownTridentHitConfig.VOLUME.get().doubleValue(),
                                (float) ThrownTridentHitConfig.PITCH.get().doubleValue()
                        );
                    }
                }).pos(X_ScreenCenter - 190, y).size(180, 20).build());
        this.addRenderableWidget(
                CycleButton.builder((ThrownTridentHitConfig.ThrownTridentHitSoundOption option) -> Component.literal(option.name()))
                        .withValues(ThrownTridentHitConfig.ThrownTridentHitSoundOption.values()).withInitialValue(ThrownTridentHitConfig.SOUND.get())
                        .create(X_ScreenCenter + 10, y, 180, 20,
                                Component.translatable("mrmx_dream.module.thrown_trident_hit.config.sound"),
                                (button, value) -> ThrownTridentHitConfig.SOUND.set(value)));



        y += Menu_Spacing; double currentVolume = ThrownTridentHitConfig.VOLUME.get();
        this.addRenderableWidget(new ThrownTridentHitConfigScreen.VolumeSlider(X_ScreenCenter - 190, y, 180, 20, currentVolume / 2.0));
        double currentPitch = ThrownTridentHitConfig.PITCH.get(); double pitchNormalized = (currentPitch - 0.5) / 1.5;
        this.addRenderableWidget(new ThrownTridentHitConfigScreen.PitchSlider(X_ScreenCenter + 10, y, 180, 20, pitchNormalized));



        y += Menu_Spacing; this.addRenderableWidget(Checkbox.builder(
                        Component.translatable("mrmx_dream.module.thrown_trident_hit.config.block_hit"), this.font)
                .pos(X_ScreenCenter - 30, y).selected(ThrownTridentHitConfig.SHAME_ENABLED.get())
                .onValueChange((checkbox, value) -> ThrownTridentHitConfig.SHAME_ENABLED.set(value)).build());
        y += Menu_Spacing; this.addRenderableWidget(Button.builder(
                        Component.translatable("gui.done"),
                        button -> this.onClose())
                .pos(X_ScreenCenter - 100, this.height - 27)
                .size(200, 20).build());
    }
}