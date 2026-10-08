package com.mrmx.Barlious_Dream.event;

import com.mrmx.Barlious_Dream.config.KillConfirmConfig;
import com.mrmx.Barlious_Dream.registry.Barlious_Dream;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingUseTotemEvent;

@EventBusSubscriber(modid = Barlious_Dream.MODID)
public class TotemPopped {

    @SubscribeEvent
    public static void TotemPopped(LivingUseTotemEvent event) {
        if (!KillConfirmConfig.ENABLED.get()) { return; }

        LivingEntity Popped_Mob = event.getEntity();
        if (!(Popped_Mob instanceof ServerPlayer player)) { return; }

        if (player.getServer() == null) { return; }
        player.getServer().getPlayerList().broadcastSystemMessage(
                Component.translatable("message.mrmx_dream.totem_popped", player.getDisplayName(),
                        event.getTotem().getHoverName()).withStyle(ChatFormatting.YELLOW),
                false
        );
    }
}