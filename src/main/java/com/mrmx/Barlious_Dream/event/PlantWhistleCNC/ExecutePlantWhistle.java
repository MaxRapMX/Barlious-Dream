package com.mrmx.Barlious_Dream.event.PlantWhistleCNC;

import com.mrmx.Barlious_Dream.item.custom.PlantWhistleCNC;
import com.mrmx.Barlious_Dream.registry.ModAttachments;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.OwnableEntity;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.event.entity.living.LivingChangeTargetEvent;
import net.neoforged.neoforge.event.entity.player.AttackEntityEvent;
import net.neoforged.neoforge.event.tick.EntityTickEvent;
import uwu.llkc.cnc.common.entities.plants.CNCPlant;

public class ExecutePlantWhistle {
    public static void handleChangeTarget(LivingChangeTargetEvent event) {
        if (!(event.getEntity() instanceof CNCPlant plant)) return;
        LivingEntity newTarget = event.getNewAboutToBeSetTarget();
        int mode = plant.getData(ModAttachments.PLANT_DEFENSE_MODE.get());
        if (mode != 1) return;
        if (newTarget != null && !(newTarget instanceof Enemy)) {
            event.setCanceled(true);
        }
    }

    public static void handleEntityTickPost(EntityTickEvent.Post event) {
        if (!(event.getEntity() instanceof CNCPlant plant)) return;
        if (plant.level().isClientSide()) return;
        int mode = plant.getData(ModAttachments.PLANT_DEFENSE_MODE.get());
        if (mode != 2) return;
        LivingEntity currentTarget = plant.getTarget();
        if (currentTarget != null) {
            boolean stillValid = currentTarget.isAlive()
                    && currentTarget.distanceToSqr(plant) <= 144.0D
                    && plant.hasLineOfSight(currentTarget);
            if (stillValid) return; plant.setTarget(null);
        } LivingEntity owner = plant.getOwner();

        plant.level().getEntitiesOfClass(LivingEntity.class, plant.getBoundingBox().inflate(12.0D),
                        e -> e.isAlive()
                                && e != owner
                                && e != plant
                                && plant.hasLineOfSight(e)
                                && !(e instanceof OwnableEntity other && owner != null && owner.equals(other.getOwner())))
                .stream()
                .min((a, b) -> Double.compare(a.distanceToSqr(plant), b.distanceToSqr(plant)))
                .ifPresent(plant::setTarget);
    }

    public static void handleAttackEntity(AttackEntityEvent event) {
        Player player = event.getEntity();
        if (!(player.getMainHandItem().getItem() instanceof PlantWhistleCNC)) return;
        if (!(event.getTarget() instanceof CNCPlant plant)) return;
        event.setCanceled(true);

        if (!player.level().isClientSide()) {
            int mode = plant.getData(ModAttachments.PLANT_DEFENSE_MODE.get());
            player.displayClientMessage(PlantWhistleCNC.modeMessage(mode), true);
        }
    }
}
