package com.mrmx.Barlious_Dream.event.PlantWhistleCNC;

import com.mrmx.Barlious_Dream.registry.Barlious_Dream;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModList;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingChangeTargetEvent;
import net.neoforged.neoforge.event.entity.player.AttackEntityEvent;
import net.neoforged.neoforge.event.tick.EntityTickEvent;

@EventBusSubscriber(modid = Barlious_Dream.MODID)
public class MainPlantWhistle {

    @SubscribeEvent
    public static void onChangeTarget(LivingChangeTargetEvent event) {
        if (!ModList.get().isLoaded("cnc")) return;
        ExecutePlantWhistle.handleChangeTarget(event);
    }

    @SubscribeEvent
    public static void onEntityTickPost(EntityTickEvent.Post event) {
        if (!ModList.get().isLoaded("cnc")) return;
        ExecutePlantWhistle.handleEntityTickPost(event);
    }

    @SubscribeEvent
    public static void onAttackEntity(AttackEntityEvent event) {
        if (!ModList.get().isLoaded("cnc")) return;
        ExecutePlantWhistle.handleAttackEntity(event);
    }
}