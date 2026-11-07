package com.mrmx.Barlious_Dream.network;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerContainerEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.network.PacketDistributor;

import java.util.ArrayList;
import java.util.List;

@EventBusSubscriber(modid = "mrmx_dream")
public class EnderSync {

    public static void send(ServerPlayer player) {
        var chest = player.getEnderChestInventory();
        List<ItemStack> items = new ArrayList<>();
        for (int i = 0; i < chest.getContainerSize(); i++) {
            items.add(chest.getItem(i).copy());
        }
        PacketDistributor.sendToPlayer(player, new EnderContentsPayload(items));
    }

    @SubscribeEvent
    public static void onLogin(PlayerEvent.PlayerLoggedInEvent event) {
        if (event.getEntity() instanceof ServerPlayer player) { send(player); }
    }

    @SubscribeEvent
    public static void onRespawn(PlayerEvent.PlayerRespawnEvent event) {
        if (event.getEntity() instanceof ServerPlayer player) { send(player); }
    }

    @SubscribeEvent
    public static void onContainerClose(PlayerContainerEvent.Close event) {
        if (event.getEntity() instanceof ServerPlayer player) { send(player); }
    }
}