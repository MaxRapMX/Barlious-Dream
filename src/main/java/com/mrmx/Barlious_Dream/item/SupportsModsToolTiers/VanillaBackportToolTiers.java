package com.mrmx.Barlious_Dream.item.SupportsModsToolTiers;

import com.blackgear.vanillabackport.common.registries.items.ModToolMaterials;
import net.minecraft.world.item.Tier;
import net.neoforged.neoforge.common.SimpleTier;

public class VanillaBackportToolTiers {
    private static Tier VB_COPPER_CUSTOM(Tier base, int uses, float speed, int enchantmentValue) {
        return new SimpleTier(
                base.getIncorrectBlocksForDrops(),
                uses,
                speed,
                base.getAttackDamageBonus(),
                enchantmentValue,
                base::getRepairIngredient
        );
    }

    public static final Tier VB_COPPER_MACHETE = VB_COPPER_CUSTOM(ModToolMaterials.COPPER, 247, 4.0f, 15);
    public static final Tier VB_HEAVY_PICKAXE = VB_COPPER_CUSTOM(ModToolMaterials.COPPER, 399, 3.25f, 11);
}
