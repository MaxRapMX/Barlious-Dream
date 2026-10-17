package com.mrmx.Barlious_Dream.client;

import com.mrmx.Barlious_Dream.client.render.EnderBagCurioRenderer;
import com.mrmx.Barlious_Dream.item.ModItems;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModList;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import top.theillusivec4.curios.api.client.CuriosRendererRegistry;

@EventBusSubscriber(modid = "mrmx_dream", value = Dist.CLIENT)
public class CuriosClientSetup {

    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        if (ModList.get().isLoaded("curios")) {
            event.enqueueWork(() -> {
                CuriosRendererRegistry.register(
                        ModItems.ENDER_BAG.get(),
                        EnderBagCurioRenderer::new
                );
            });
        }
    }
}