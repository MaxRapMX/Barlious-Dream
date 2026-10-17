package com.mrmx.Barlious_Dream.network;

import com.mrmx.Barlious_Dream.item.custom.tools.Ender_Bag;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;

public record EnderBagPacketPayload() implements CustomPacketPayload {
    public static final Type<EnderBagPacketPayload> TYPE =
            new Type<>(ResourceLocation.fromNamespaceAndPath("mrmx_dream", "open_ender_bag"));

    public static final StreamCodec<RegistryFriendlyByteBuf, EnderBagPacketPayload> STREAM_CODEC =
            StreamCodec.unit(new EnderBagPacketPayload());

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public static void handle(EnderBagPacketPayload payload, net.neoforged.neoforge.network.handling.IPayloadContext context) {
        context.enqueueWork(() -> {
            if (context.player() instanceof ServerPlayer serverPlayer) {
                if (Ender_Bag.isEquippedInCurios(serverPlayer)) {
                    Ender_Bag.openEnderBagMenu(serverPlayer);
                }
            }
        });
    }
}