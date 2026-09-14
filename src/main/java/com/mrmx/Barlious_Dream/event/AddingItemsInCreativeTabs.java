package com.mrmx.Barlious_Dream.event;

import com.mrmx.Barlious_Dream.item.ModItems; // Asegúrate de importar tus ítems
import com.mrmx.Barlious_Dream.registry.Barlious_Dream;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModList;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import uwu.llkc.cnc.common.init.ItemRegistry;

@EventBusSubscriber(modid = Barlious_Dream.MODID)
public class AddingItemsInCreativeTabs {

    @SubscribeEvent
    public static void AddingItemsInCreativeTabs(BuildCreativeModeTabContentsEvent event) {

        // Tools and Utilities
        if (event.getTabKey() == CreativeModeTabs.TOOLS_AND_UTILITIES) {
            event.insertAfter(
                    new ItemStack(Items.IRON_PICKAXE), ModItems.IRON_HEAVY_PICKAXE.get().getDefaultInstance(),
                    CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(
                    new ItemStack(Items.IRON_AXE), ModItems.IRON_MACHETE.get().getDefaultInstance(),
                    CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(
                    new ItemStack(Items.DIAMOND_PICKAXE), ModItems.DIAMOND_HEAVY_PICKAXE.get().getDefaultInstance(),
                    CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(
                    new ItemStack(Items.DIAMOND_AXE), ModItems.DIAMOND_MACHETE.get().getDefaultInstance(),
                    CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(
                    new ItemStack(Items.NETHERITE_PICKAXE), ModItems.NETHERITE_HEAVY_PICKAXE.get().getDefaultInstance(),
                    CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(
                    new ItemStack(Items.NETHERITE_AXE), ModItems.NETHERITE_MACHETE.get().getDefaultInstance(),
                    CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
        }

        // Combat
        if (event.getTabKey() == CreativeModeTabs.COMBAT) {
            event.insertAfter(
                    new ItemStack(Items.NETHERITE_AXE), ModItems.IRON_MACHETE.get().getDefaultInstance(),
                    CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(
                    new ItemStack((ItemLike) ModItems.IRON_MACHETE), ModItems.DIAMOND_MACHETE.get().getDefaultInstance(),
                    CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(
                    new ItemStack((ItemLike) ModItems.DIAMOND_MACHETE), ModItems.NETHERITE_MACHETE.get().getDefaultInstance(),
                    CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
        }

        // CNC Tab
        if (ModList.get().isLoaded("cnc")) {
            ResourceKey<CreativeModeTab> cncTabKey = ResourceKey.create(
                    Registries.CREATIVE_MODE_TAB,
                    ResourceLocation.fromNamespaceAndPath("cnc", "cnc_tab")
            ); if (event.getTabKey() == cncTabKey) {
                event.insertBefore(
                        new ItemStack((ItemLike) ItemRegistry.PLANT_FOOD), ModItems.PLANT_WHISTLE.get().getDefaultInstance(),
                        CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            }
        }
    }
    @SubscribeEvent
    public static void addCreative(BuildCreativeModeTabContentsEvent event) {
        System.out.println("[DEBUG TAB IDENTIFIER] Tab ID: " + event.getTabKey().location().toString());
    }
}
