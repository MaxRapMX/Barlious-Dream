package com.mrmx.Barlious_Dream.event;

import com.mrmx.Barlious_Dream.config.ModulesEnabler;
import com.mrmx.Barlious_Dream.registry.Barlious_Dream;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingUseTotemEvent;

@EventBusSubscriber(modid = Barlious_Dream.MODID, bus = EventBusSubscriber.Bus.GAME)
public class TotemPoppedGlobalNotify {
    @SubscribeEvent
    public static void onTotemUse(LivingUseTotemEvent event) {
        if (event.getEntity() instanceof ServerPlayer player) {
            triggerPopMessage(player, event.getTotem());
        }
    }

    public static void triggerPopMessage(ServerPlayer player, ItemStack totemStack) {
        if (!ModulesEnabler.TOTEM_POPPED_ENABLED.get()) { return; }
        if (player.getServer() == null) { return; }
        player.getServer().getPlayerList().broadcastSystemMessage(
                Component.translatable("message.mrmx_dream.totem_popped", player.getDisplayName(),
                        totemStack.getHoverName()).withStyle(ChatFormatting.YELLOW),
                false
        );
    }
}