package com.mrmx.Barlious_Dream.network;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

import java.util.List;

public record EnderContentsPayload(List<ItemStack> items) implements CustomPacketPayload {
    public static final Type<EnderContentsPayload> TYPE =
            new Type<>(ResourceLocation.fromNamespaceAndPath("mrmx_dream", "ender_contents"));

    public static final StreamCodec<RegistryFriendlyByteBuf, EnderContentsPayload> STREAM_CODEC =
            ItemStack.OPTIONAL_LIST_STREAM_CODEC.map(EnderContentsPayload::new, EnderContentsPayload::items);

    @Override
    public Type<? extends CustomPacketPayload> type() { return TYPE; }
}
