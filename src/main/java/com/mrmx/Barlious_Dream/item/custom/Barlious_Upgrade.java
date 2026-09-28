package com.mrmx.Barlious_Dream.item.custom;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SmithingTemplateItem;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.Item;
import net.neoforged.fml.ModList;

import java.util.ArrayList;
import java.util.List;

public class Barlious_Upgrade extends SmithingTemplateItem {
    private static final ResourceLocation EMPTY_SLOT_SWORD = ResourceLocation.withDefaultNamespace("item/empty_slot_sword");
    private static final ResourceLocation EMPTY_SLOT_PICKAXE = ResourceLocation.withDefaultNamespace("item/empty_slot_pickaxe");
    private static final ResourceLocation EMPTY_SLOT_MACHETE = ResourceLocation.fromNamespaceAndPath("mrmx_dream", "item/empty_slot_machete");
    private static final ResourceLocation EMPTY_SLOT_PLANT_FOOD = ResourceLocation.fromNamespaceAndPath("mrmx_dream", "item/empty_slot_plant_food");
    private static final ResourceLocation EMPTY_SLOT_GLASS_CUP = ResourceLocation.fromNamespaceAndPath("mrmx_dream", "item/empty_slot_glass_cup");
    private static final ResourceLocation EMPTY_SLOT_ARTISANAL_CUP = ResourceLocation.fromNamespaceAndPath("mrmx_dream", "item/empty_slot_artisanal_cup");
    private static final ResourceLocation EMPTY_SLOT_BLOCK = ResourceLocation.fromNamespaceAndPath("mrmx_dream", "item/empty_slot_block");
    private static final ResourceLocation EMPTY_SLOT_HEAVY_HEAD = ResourceLocation.fromNamespaceAndPath("mrmx_dream", "item/empty_slot_heavy_head");
    private static final ResourceLocation EMPTY_SLOT_ROTTEN_FLESH = ResourceLocation.fromNamespaceAndPath("mrmx_dream", "item/empty_slot_rotten_flesh");
    private static final ResourceLocation EMPTY_SLOT_SUN = ResourceLocation.fromNamespaceAndPath("mrmx_dream", "item/empty_slot_sun");
    private static final ResourceLocation EMPTY_SLOT_CHARCOAL = ResourceLocation.fromNamespaceAndPath("mrmx_dream", "item/empty_slot_charcoal");
    private static final ResourceLocation EMPTY_SLOT_SNOWBALL = ResourceLocation.fromNamespaceAndPath("mrmx_dream", "item/empty_slot_snowball");

    public Barlious_Upgrade(Item.Properties properties) {
        super(
                Component.translatable("tooltip.mrmx_dream.barlious_upgrade.applies_to").withStyle(ChatFormatting.BLUE),
                Component.translatable("tooltip.mrmx_dream.barlious_upgrade.ingredients").withStyle(ChatFormatting.BLUE),
                Component.translatable("tooltip.mrmx_dream.barlious_upgrade").withStyle(ChatFormatting.GRAY),
                Component.translatable("tooltip.mrmx_dream.barlious_upgrade.base_slot_description"),
                Component.translatable("tooltip.mrmx_dream.barlious_upgrade.additions_slot_description"),
                AppliestoIcons(),
                IngredientIcons()
        );
    }

    private static List<ResourceLocation> AppliestoIcons() {
        List<ResourceLocation> icons = new ArrayList<>();
        icons.add(EMPTY_SLOT_SWORD);
        icons.add(EMPTY_SLOT_PICKAXE);
        icons.add(EMPTY_SLOT_MACHETE);
        if (ModList.get().isLoaded("cnc")) {
            icons.add(EMPTY_SLOT_PLANT_FOOD);
            if (ModList.get().isLoaded("corn_delight")) {
                icons.add(EMPTY_SLOT_GLASS_CUP);
                icons.add(EMPTY_SLOT_ARTISANAL_CUP);
            } } return icons;
    }

    private static List<ResourceLocation> IngredientIcons() {
        List<ResourceLocation> icons = new ArrayList<>();
        icons.add(EMPTY_SLOT_BLOCK);
        icons.add(EMPTY_SLOT_HEAVY_HEAD);
        icons.add(EMPTY_SLOT_ROTTEN_FLESH);
        if (ModList.get().isLoaded("cnc")) {
            icons.add(EMPTY_SLOT_SUN);
            if (ModList.get().isLoaded("corn_delight")) {
                icons.add(EMPTY_SLOT_CHARCOAL);
                icons.add(EMPTY_SLOT_SNOWBALL);
            } } return icons;
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
    }
}