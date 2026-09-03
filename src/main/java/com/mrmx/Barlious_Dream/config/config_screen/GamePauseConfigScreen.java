package com.mrmx.Barlious_Dream.config.config_screen;

import com.mrmx.Barlious_Dream.config.GamePauseConfig;
import com.mrmx.Barlious_Dream.config.KillConfirmConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.AbstractSliderButton;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.Checkbox;
import net.minecraft.client.gui.components.CycleButton;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

public class GamePauseConfigScreen extends Screen {
    private final Screen parent;


    public GamePauseConfigScreen(Screen parent) {
        super(Component.translatable("mrmx_dream.config.game_pause.title"));
        this.parent = parent;
    }

    @Override
    protected void init() {
        int centerX = this.width / 2;
        int y = 40;
        int spacing = 26;

        this.addRenderableWidget(Checkbox.builder(
                        Component.translatable("mrmx_dream.config.game_pause.enabled"), this.font)
                .pos(centerX - 60, 20).selected(GamePauseConfig.ENABLED.get())
                .onValueChange((checkbox, value) -> GamePauseConfig.ENABLED.set(value))
                .build());
        y += spacing;

        this.addRenderableWidget(Button.builder(
                        Component.translatable("mrmx_dream.config.game_pause.sound_test"),
                        button -> { var player = Minecraft.getInstance().player;
                            if (player != null) {
                                player.playSound(
                                        GamePauseConfig.SOUND.get().getSound(),
                                        (float) GamePauseConfig.VOLUME.get().doubleValue(),
                                        (float) GamePauseConfig.PITCH.get().doubleValue()
                                );
                            }
                        })
                .pos(centerX - 190, y).size(180, 20).build());

        this.addRenderableWidget(
                CycleButton.builder((GamePauseConfig.GamePauseSoundOption option) -> Component.literal(option.name()))
                        .withValues(GamePauseConfig.GamePauseSoundOption.values())
                        .withInitialValue(GamePauseConfig.SOUND.get())
                        .create(centerX + 10, y, 180, 20,
                                Component.translatable("mrmx_dream.config.game_pause.sound"),
                                (button, value) -> GamePauseConfig.SOUND.set(value)));
        y += spacing;

        double currentVolume = GamePauseConfig.VOLUME.get();
        this.addRenderableWidget(new GamePauseConfigScreen.VolumeSlider(centerX - 190, y, 180, 20, currentVolume / 2.0));

        double currentPitch = GamePauseConfig.PITCH.get();
        double pitchNormalized = (currentPitch - 0.5) / 1.5;
        this.addRenderableWidget(new GamePauseConfigScreen.PitchSlider(centerX + 10, y, 180, 20, pitchNormalized));
        y += spacing;





        // Botón de "Done" (Listo) en la parte inferior
        this.addRenderableWidget(Button.builder(
                        Component.translatable("gui.done"),
                        button -> this.onClose()) // Ejecuta el método onClose() que ya programaste abajo
                .pos(centerX - 100, this.height - 27) // Centrado y a 27 píxeles del borde inferior
                .size(200, 20)
                .build());


    }

    @Override
    public void onClose() {
        KillConfirmConfig.SPEC.save();
        this.minecraft.setScreen(parent);
    }

    // ---------- Sliders internos ----------

    private class VolumeSlider extends AbstractSliderButton {
        VolumeSlider(int x, int y, int width, int height, double initialValue) {
            super(x, y, width, height, Component.empty(), initialValue);
            updateMessage();
        }

        @Override
        protected void updateMessage() {
            double realValue = Math.round(this.value * 2.0 * 100.0) / 100.0;
            this.setMessage(Component.translatable("mrmx_dream.config.game_pause.volume")
                    .append(": " + realValue));
        }

        @Override
        protected void applyValue() {
            GamePauseConfig.VOLUME.set(this.value * 2.0);
        }
    }

    private class PitchSlider extends AbstractSliderButton {
        PitchSlider(int x, int y, int width, int height, double initialValue) {
            super(x, y, width, height, Component.empty(), initialValue);
            updateMessage();
        }

        @Override
        protected void updateMessage() {
            double realValue = Math.round((0.5 + this.value * 1.5) * 100.0) / 100.0;
            this.setMessage(Component.translatable("mrmx_dream.config.game_pause.pitch")
                    .append(": " + realValue));
        }

        @Override
        protected void applyValue() {
            GamePauseConfig.PITCH.set(0.5 + this.value * 1.5);
        }
    }
}