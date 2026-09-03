package com.mrmx.Barlious_Dream.config.config_screen;

import com.mrmx.Barlious_Dream.config.KillConfirmConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.AbstractSliderButton;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.Checkbox;
import net.minecraft.client.gui.components.CycleButton;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

public class KillConfirmConfigScreen extends Screen {
    private final Screen parent;


    public KillConfirmConfigScreen(Screen parent) {
        super(Component.translatable("mrmx_dream.config.kill_confirm.title"));
        this.parent = parent;
    }

    @Override
    protected void init() {
        int centerX = this.width / 2;
        int y = 40;
        int spacing = 26;

        this.addRenderableWidget(Checkbox.builder(
                Component.translatable("mrmx_dream.config.kill_confirm.enabled"), this.font)
                .pos(centerX - 60, 20).selected(KillConfirmConfig.ENABLED.get())
                .onValueChange((checkbox, value) -> KillConfirmConfig.ENABLED.set(value))
                .build());
        y += spacing;

        this.addRenderableWidget(Checkbox.builder(
                Component.translatable("mrmx_dream.config.kill_confirm.players"), this.font)
                .pos(centerX - 175, y).selected(KillConfirmConfig.PLAYER_ENABLED.get())
                .onValueChange((checkbox, value) -> KillConfirmConfig.PLAYER_ENABLED.set(value))
                .build());
        this.addRenderableWidget(Checkbox.builder(
                Component.translatable("mrmx_dream.config.kill_confirm.hostile_mob"), this.font)
                .pos(centerX + 25, y).selected(KillConfirmConfig.HOSTILE_ENABLED.get())
                .onValueChange((checkbox, value) -> KillConfirmConfig.HOSTILE_ENABLED.set(value))
                .build());
        y += spacing;

        this.addRenderableWidget(Checkbox.builder(
                Component.translatable("mrmx_dream.config.kill_confirm.neutral_mob"), this.font)
                .pos(centerX - 175, y).selected(KillConfirmConfig.NEUTRAL_ENABLED.get())
                .onValueChange((checkbox, value) -> KillConfirmConfig.NEUTRAL_ENABLED.set(value))
                .build());
        this.addRenderableWidget(Checkbox.builder(
                        Component.translatable("mrmx_dream.config.kill_confirm.pacific_mob"), this.font)
                .pos(centerX + 25, y).selected(KillConfirmConfig.PACIFIC_ENABLED.get())
                .onValueChange((checkbox, value) -> KillConfirmConfig.PACIFIC_ENABLED.set(value))
                .build());
        y += spacing; y += spacing;

        this.addRenderableWidget(Button.builder(
                Component.translatable("mrmx_dream.config.kill_confirm.sound_test"),
                        button -> { var player = Minecraft.getInstance().player;
                            if (player != null) {
                                player.playSound(
                                        KillConfirmConfig.SOUND.get().getSound(),
                                        (float) KillConfirmConfig.VOLUME.get().doubleValue(),
                                        (float) KillConfirmConfig.PITCH.get().doubleValue()
                                );
                            }
                        })
                .pos(centerX - 190, y).size(180, 20).build());

        this.addRenderableWidget(
                CycleButton.builder((KillConfirmConfig.KillConfirmSoundOption option) -> Component.literal(option.name()))
                        .withValues(KillConfirmConfig.KillConfirmSoundOption.values())
                        .withInitialValue(KillConfirmConfig.SOUND.get())
                        .create(centerX + 10, y, 180, 20,
                                Component.translatable("mrmx_dream.config.kill_confirm.sound"),
                                (button, value) -> KillConfirmConfig.SOUND.set(value)));
        y += spacing;

        double currentVolume = KillConfirmConfig.VOLUME.get();
        this.addRenderableWidget(new VolumeSlider(centerX - 190, y, 180, 20, currentVolume / 2.0));

        double currentPitch = KillConfirmConfig.PITCH.get();
        double pitchNormalized = (currentPitch - 0.5) / 1.5;
        this.addRenderableWidget(new PitchSlider(centerX + 10, y, 180, 20, pitchNormalized));
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
            this.setMessage(Component.translatable("mrmx_dream.config.kill_confirm.volume")
                    .append(": " + realValue));
        }

        @Override
        protected void applyValue() {
            KillConfirmConfig.VOLUME.set(this.value * 2.0);
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
            this.setMessage(Component.translatable("mrmx_dream.config.kill_confirm.pitch")
                    .append(": " + realValue));
        }

        @Override
        protected void applyValue() {
            KillConfirmConfig.PITCH.set(0.5 + this.value * 1.5);
        }
    }
}