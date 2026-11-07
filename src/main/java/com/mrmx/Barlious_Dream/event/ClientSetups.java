package com.mrmx.Barlious_Dream.event;

import com.mrmx.Barlious_Dream.client.tooltip.ClientContainerTooltipComponent;
import com.mrmx.Barlious_Dream.client.tooltip.ContainerTooltipComponent;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterClientTooltipComponentFactoriesEvent;

@EventBusSubscriber(modid = "mrmx_dream", value = Dist.CLIENT)

public class ClientSetups {

    @SubscribeEvent
    public static void registerTooltipComponent(RegisterClientTooltipComponentFactoriesEvent event) {
        event.register(ContainerTooltipComponent.class, ClientContainerTooltipComponent::new);
    }
}