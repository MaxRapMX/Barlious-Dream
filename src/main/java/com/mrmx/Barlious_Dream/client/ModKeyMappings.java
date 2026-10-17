package com.mrmx.Barlious_Dream.client;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import org.lwjgl.glfw.GLFW;

@EventBusSubscriber(modid = "mrmx_dream", value = Dist.CLIENT)
public class ModKeyMappings {

    public static final KeyMapping OPEN_ENDER_BAG = new KeyMapping(
            "key.mrmx_dream.open_ender_bag_on_belt",
            InputConstants.Type.KEYSYM,
            GLFW.GLFW_KEY_B,
            "key.categories.mrmx_dream"
    );

    @SubscribeEvent
    public static void registerKeys(RegisterKeyMappingsEvent event) {
        event.register(OPEN_ENDER_BAG);
    }
}