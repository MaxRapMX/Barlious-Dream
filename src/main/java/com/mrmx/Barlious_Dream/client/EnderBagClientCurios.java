package com.mrmx.Barlious_Dream.client;

import com.mrmx.Barlious_Dream.item.custom.tools.Ender_Bag;
import com.mrmx.Barlious_Dream.network.EnderBagPacketPayload;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.inventory.ContainerScreen;
import net.minecraft.network.chat.Component;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.client.event.ScreenEvent;
import net.neoforged.neoforge.network.PacketDistributor;

@EventBusSubscriber(modid = "mrmx_dream", value = Dist.CLIENT)
public class EnderBagClientCurios {

    @SubscribeEvent
    public static void onClientTick(ClientTickEvent.Post event) {
        Minecraft mc = Minecraft.getInstance();
        if (mc.player == null || mc.screen != null) return;
        while (ModKeyMappings.OPEN_ENDER_BAG.consumeClick()) {
            PacketDistributor.sendToServer(new EnderBagPacketPayload());
        }
    }

    @SubscribeEvent
    public static void onScreenKeyPressed(ScreenEvent.KeyPressed.Pre event) {
        Minecraft mc = Minecraft.getInstance();
        if (event.getScreen() instanceof ContainerScreen containerScreen) {
            Component title = containerScreen.getTitle();
            if (title.equals(Component.translatable("container.mrmx_dream.ender_bag"))) {

                if (Ender_Bag.isEquippedInCurios(mc.player)) {
                    if (ModKeyMappings.OPEN_ENDER_BAG.matches(event.getKeyCode(), event.getScanCode())) {
                        mc.player.closeContainer();
                        event.setCanceled(true);
                    }
                }
            }
        }
    }
}