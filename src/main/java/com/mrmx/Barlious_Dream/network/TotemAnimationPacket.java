package com.mrmx.Barlious_Dream.network;

import com.mrmx.Barlious_Dream.registry.Barlious_Dream;
import net.minecraft.client.Minecraft;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public record TotemAnimationPacket(ItemStack totemStack) implements CustomPacketPayload {

    public static final Type<TotemAnimationPacket> TYPE =
            new Type<>(ResourceLocation.fromNamespaceAndPath(Barlious_Dream.MODID, "totem_animation"));

    public static final StreamCodec<RegistryFriendlyByteBuf, TotemAnimationPacket> STREAM_CODEC =
            StreamCodec.composite(
                    ItemStack.OPTIONAL_STREAM_CODEC, TotemAnimationPacket::totemStack,
                    TotemAnimationPacket::new
            );

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public static void handleOnClient(final TotemAnimationPacket payload, final IPayloadContext context) {
        context.enqueueWork(() -> {
            Minecraft.getInstance().gameRenderer.displayItemActivation(payload.totemStack());
        });
    }
}