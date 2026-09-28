package com.mrmx.Barlious_Dream.item;

import com.mrmx.Barlious_Dream.registry.Barlious_Dream;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModList;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Barlious_Dream.MODID);

    public static final Supplier<CreativeModeTab> BARLIOUS_DREAM_CONTENT_TAB = CREATIVE_MODE_TAB.register("barlious_dream_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.BARLIOUS_DREAM_LOGO.get()))
                    .title(Component.translatable("creativetab.mrmx_dream.content"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModItems.BARLIOUS_UPGRADE);
                        if (ModList.get().isLoaded("cnc")) output.accept(ModItems.PLANT_WHISTLE);
                        output.accept(ModItems.IRON_MACHETE);
                        output.accept(ModItems.DIAMOND_MACHETE);
                        output.accept(ModItems.NETHERITE_MACHETE);
                        output.accept(ModItems.IRON_HEAVY_HEAD);
                        output.accept(ModItems.IRON_HEAVY_PICKAXE);
                        output.accept(ModItems.DIAMOND_HEAVY_HEAD);
                        output.accept(ModItems.DIAMOND_HEAVY_PICKAXE);
                        output.accept(ModItems.NETHERITE_HEAVY_HEAD);
                        output.accept(ModItems.NETHERITE_HEAVY_PICKAXE);
                        if (ModList.get().isLoaded("corn_delight")) output.accept(ModItems.ARTISANAL_CUP);
                        if (ModList.get().isLoaded("corn_delight")) output.accept(ModItems.VANILLA_ATOLE);
                        if (ModList.get().isLoaded("corn_delight")) output.accept(ModItems.CHOCOLATE_ATOLE);
                        if (ModList.get().isLoaded("corn_delight") && ModList.get().isLoaded("cnc")) output.accept(ModItems.CHERRY_ATOLE);
                        if (ModList.get().isLoaded("corn_delight")) output.accept(ModItems.RICE_PUDING_ATOLE);
                        if (ModList.get().isLoaded("corn_delight")) output.accept(ModItems.CHAMPURRADO);
                    }).build());

    public static void register(IEventBus eventBus ) {
        CREATIVE_MODE_TAB.register(eventBus);
    }
}
