package com.mrmx.Barlious_Dream.item.custom;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.common.ItemAbilities;
import net.neoforged.neoforge.common.ItemAbility;
import net.minecraft.core.registries.Registries;

import java.util.List;

public class MacheteItem extends AxeItem {
    public MacheteItem(Tier tier, Properties properties) {
        super(tier, properties);
    }

    public boolean canPerformAction(ItemStack stack, ItemAbility itemAbility) {
        return super.canPerformAction(stack, itemAbility) ||
                ItemAbilities.DEFAULT_SWORD_ACTIONS.contains(itemAbility);
    }

    public boolean canAttackBlock(BlockState state, Level level, BlockPos pos, Player player) {
        return !player.isCreative();
    }

    @Override
    public float getDestroySpeed(ItemStack stack, BlockState state) {
        if (state.is(Blocks.COBWEB)) { return 25F; }
        if (state.is(BlockTags.SWORD_EFFICIENT)) { return 5.0F; }
        return super.getDestroySpeed(stack, state);
    }

    @Override
    public boolean isCorrectToolForDrops(ItemStack stack, BlockState state) {
        if (state.is(Blocks.COBWEB)) {
            return true;
        } if (state.is(BlockTags.SWORD_EFFICIENT)) {
            return true;
        } return super.isCorrectToolForDrops(stack, state);
    }

    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (target instanceof Monster) {
            float MonsterBonus = 4.0f;

            int sharpnessLevel = stack.getEnchantmentLevel(
                    attacker.level().registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.SHARPNESS)
            ); if (sharpnessLevel > 0) { MonsterBonus += 1f * sharpnessLevel + 1f; }

            int smiteLevel = stack.getEnchantmentLevel(
                    attacker.level().registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(Enchantments.SMITE)
            ); if (target.getType().is(EntityTypeTags.UNDEAD) && smiteLevel > 0) {
                MonsterBonus += smiteLevel * 2.5f;
            } target.invulnerableTime = 0;

            target.hurt(target.damageSources().mobAttack(attacker), MonsterBonus);
        } return super.hurtEnemy(stack, target, attacker);
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        tooltipComponents.add(Component.translatable("tooltip.mrmx_dream.machete")
                .withStyle(ChatFormatting.GRAY).append(Component.literal(" +4").withStyle(ChatFormatting.GRAY)));
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
    }
}
