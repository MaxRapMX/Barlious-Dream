package com.mrmx.Barlious_Dream.item;

import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;
import net.neoforged.neoforge.common.SimpleTier;

public class ModToolTiers {

    private static Tier IRON_CUSTOM(int uses, float speed, int enchantmentvalue) {
        return new SimpleTier( Tiers.IRON.getIncorrectBlocksForDrops(),
                uses, speed,
                Tiers.IRON.getAttackDamageBonus(),
                enchantmentvalue, () -> Tiers.IRON.getRepairIngredient()
        );
    }
    public static final Tier IRON_MACHETE = IRON_CUSTOM(325, 4.8f, 16);
    // 1.3 Mas de Durabilidad, 0.8 Menos de Velocidad, y 1.16 mas de Encantabilidad
    public static final Tier IRON_HEAVY_PICKAXE = IRON_CUSTOM(525, 3.9F, 12);
    // 2.1 Mas de Durabilidad, 0.65 Menos de Velocidad, y 0.85 mas de Encantabilidad

    private static Tier DIAMOND_CUSTOM(int uses, float speed, int enchantmentvalue) {
        return new SimpleTier( Tiers.DIAMOND.getIncorrectBlocksForDrops(),
                uses, speed,
                Tiers.DIAMOND.getAttackDamageBonus(),
                enchantmentvalue, () -> Tiers.DIAMOND.getRepairIngredient()
        );
    }
    public static final Tier DIAMOND_MACHETE = DIAMOND_CUSTOM(2029, 6.4f, 12);
    public static final Tier DIAMOND_HEAVY_PICKAXE = DIAMOND_CUSTOM(3278, 5.2f, 8);

    private static Tier NETHERITE_CUSTOM(int uses, float speed, int enchantmentvalue) {
        return new SimpleTier( Tiers.NETHERITE.getIncorrectBlocksForDrops(),
                uses, speed,
                Tiers.NETHERITE.getAttackDamageBonus(),
                enchantmentvalue, () -> Tiers.NETHERITE.getRepairIngredient()
        );
    }
    public static final Tier NETHERITE_MACHETE = NETHERITE_CUSTOM(2640, 7.2f, 17);
    public static final Tier NETHERITE_HEAVY_PICKAXE = NETHERITE_CUSTOM(4265, 5.8f, 13);

}