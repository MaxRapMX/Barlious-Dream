package com.mrmx.Barlious_Dream.registry;

import com.mojang.serialization.Codec;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

public class ModAttachments {

    public static final DeferredRegister<AttachmentType<?>> ATTACHMENT_TYPES =
            DeferredRegister.create(NeoForgeRegistries.Keys.ATTACHMENT_TYPES, "mrmx_dream");

    public static final DeferredHolder<AttachmentType<?>, AttachmentType<Integer>> PLANT_DEFENSE_MODE =
            ATTACHMENT_TYPES.register("plant_defense_mode",
                    () -> AttachmentType.builder(() -> 0)
                            .serialize(Codec.INT)
                            .build());

    public static void register(IEventBus eventBus) {
        ATTACHMENT_TYPES.register(eventBus);
    }
}