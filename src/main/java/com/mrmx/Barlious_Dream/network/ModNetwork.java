package com.mrmx.Barlious_Dream.network;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;

@EventBusSubscriber(modid = "mrmx_dream")
public class ModNetwork {

    @SubscribeEvent
    public static void register(RegisterPayloadHandlersEvent event) {
        PayloadRegistrar registrar = event.registrar("1");

        registrar.playToServer(
                EnderBagPacketPayload.TYPE,
                EnderBagPacketPayload.STREAM_CODEC,
                EnderBagPacketPayload::handle
        );
    }
}