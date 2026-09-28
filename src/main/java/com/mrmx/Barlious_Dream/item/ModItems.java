package com.mrmx.Barlious_Dream.item;

import com.mrmx.Barlious_Dream.item.custom.*;
import com.mrmx.Barlious_Dream.registry.Barlious_Dream;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.SwordItem;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModList;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final boolean CNC_REQUIRED = ModList.get().isLoaded("cnc");
    public static final boolean CORN_DELIGHT_REQUIRED = ModList.get().isLoaded("corn_delight");
    public static final boolean REQUIRES_FOR_CHERRY_ATOLE = CNC_REQUIRED && CORN_DELIGHT_REQUIRED;
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(Barlious_Dream.MODID);
    public static final DeferredRegister.Items TOOLS = DeferredRegister.createItems(Barlious_Dream.MODID);

    public static final DeferredItem<Item> BARLIOUS_DREAM_LOGO = ITEMS.register("logo",
            () -> new Item(new Item.Properties().stacksTo(1).fireResistant()));
    public static final DeferredItem<Item> BARLIOUS_UPGRADE = ITEMS.register("barlious_upgrade_smithing_template",
            () -> new Barlious_Upgrade(new Item.Properties().stacksTo(8).fireResistant()));

    // Atoles
    public static final DeferredItem<Item> ARTISANAL_CUP = CORN_DELIGHT_REQUIRED
            ? ITEMS.register("artisanal_cup",
            () -> new Item(new Item.Properties())): null;
    public static final DeferredItem<AtoleItem> VANILLA_ATOLE = CORN_DELIGHT_REQUIRED
        ? ITEMS.register("vanilla_atole",
            () -> new AtoleItem(new Item.Properties().durability(32).food(Atoles.VANILLA_ATOLE))): null;
    public static final DeferredItem<AtoleItem> CHOCOLATE_ATOLE = CORN_DELIGHT_REQUIRED
            ? ITEMS.register("chocolate_atole",
            () -> new AtoleItem(new Item.Properties().durability(32).food(Atoles.CHOCOLATE_ATOLE))): null;
    public static final DeferredItem<AtoleItem> CHERRY_ATOLE = REQUIRES_FOR_CHERRY_ATOLE
            ? ITEMS.register("cherry_atole",
            () -> new AtoleItem(new Item.Properties().durability(32).food(Atoles.CHERRY_ATOLE))): null;
    public static final DeferredItem<AtoleItem> RICE_PUDING_ATOLE = CORN_DELIGHT_REQUIRED
            ? ITEMS.register("rice_puding_atole",
            () -> new AtoleItem(new Item.Properties().durability(32).food(Atoles.RICE_PUDING_ATOLE))): null;
    public static final DeferredItem<AtoleItem> CHAMPURRADO = CORN_DELIGHT_REQUIRED
            ? ITEMS.register("champurrado",
            () -> new AtoleItem(new Item.Properties().durability(32).food(Atoles.CHAMPURRADO))): null;

    // Cabezales de Picos reforzados; para mejorar los picos vanilla
    public static final DeferredItem<Item> IRON_HEAVY_HEAD = ITEMS.register("iron_heavy_head",
            () -> new Heavy_Heads(new Item.Properties().stacksTo(1)));
    public static final DeferredItem<Item> DIAMOND_HEAVY_HEAD = ITEMS.register("diamond_heavy_head",
            () -> new Heavy_Heads(new Item.Properties().stacksTo(1)));
    public static final DeferredItem<Item> NETHERITE_HEAVY_HEAD = ITEMS.register("netherite_heavy_head",
            () -> new Heavy_Heads(new Item.Properties().stacksTo(1).fireResistant()));

    // Machetes; 1.3 mas de daño, pero 1.16 mas lento que las espadas
    public static final DeferredItem<MacheteItem> IRON_MACHETE = TOOLS.register("iron_machete",
            () -> new MacheteItem(ModToolTiers.IRON_MACHETE, new Item.Properties()
                    .attributes(SwordItem.createAttributes(ModToolTiers.IRON_MACHETE, 4, -2.7f))));
    public static final DeferredItem<MacheteItem> DIAMOND_MACHETE = TOOLS.register("diamond_machete",
            () -> new MacheteItem(ModToolTiers.DIAMOND_MACHETE, new Item.Properties()
                    .attributes(SwordItem.createAttributes(ModToolTiers.DIAMOND_MACHETE, 4, -2.7f))));
    public static final DeferredItem<MacheteItem> NETHERITE_MACHETE = TOOLS.register("netherite_machete",
            () -> new MacheteItem(ModToolTiers.NETHERITE_MACHETE, new Item.Properties().fireResistant()
                    .attributes(SwordItem.createAttributes(ModToolTiers.NETHERITE_MACHETE, 4, -2.7f))));

    // Picos reforzados; +1 de daño, pero +3 de coldoown
    public static final DeferredItem<HeavyPickaxeItem> IRON_HEAVY_PICKAXE = TOOLS.register("iron_heavy_pickaxe",
            () -> new HeavyPickaxeItem(ModToolTiers.IRON_HEAVY_PICKAXE, new Item.Properties()
                    .attributes(PickaxeItem.createAttributes(ModToolTiers.IRON_HEAVY_PICKAXE, 2.0f, -3.1f))));
    public static final DeferredItem<HeavyPickaxeItem> DIAMOND_HEAVY_PICKAXE = TOOLS.register("diamond_heavy_pickaxe",
            () -> new HeavyPickaxeItem(ModToolTiers.DIAMOND_HEAVY_PICKAXE, new Item.Properties()
                    .attributes(PickaxeItem.createAttributes(ModToolTiers.DIAMOND_HEAVY_PICKAXE, 2.0f, -3.1f))));
    public static final DeferredItem<HeavyPickaxeItem> NETHERITE_HEAVY_PICKAXE = TOOLS.register("netherite_heavy_pickaxe",
            () -> new HeavyPickaxeItem(ModToolTiers.NETHERITE_HEAVY_PICKAXE, new Item.Properties().fireResistant()
                    .attributes(PickaxeItem.createAttributes(ModToolTiers.NETHERITE_HEAVY_PICKAXE, 2.0f, -3.1f))));

    // Extra
    public static final DeferredItem<Item> PLANT_WHISTLE = CNC_REQUIRED
            ? ITEMS.register("plant_whistle",
            () -> new PlantWhistleCNC(new Item.Properties().stacksTo(1))): null;

    static class Atoles {
        public static final FoodProperties VANILLA_ATOLE;
        public static final FoodProperties CHOCOLATE_ATOLE;
        public static final FoodProperties CHERRY_ATOLE;
        public static final FoodProperties RICE_PUDING_ATOLE;
        public static final FoodProperties CHAMPURRADO;
        Atoles() {}
        static {
            var VanillaAtole = (new FoodProperties.Builder()).nutrition(3).saturationModifier(1.2f).alwaysEdible().fast()
                    .effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 900, 1), 1.0F);
            BuiltInRegistries.MOB_EFFECT.getHolder(ResourceLocation.parse("farmersdelight:nourishment"))
                    .ifPresent(nourishment -> VanillaAtole.effect(() -> new MobEffectInstance(nourishment, 900, 0), 1.0F));
            VANILLA_ATOLE = VanillaAtole.build();

            var ChocolateAtole = (new FoodProperties.Builder()).nutrition(3).saturationModifier(1.2f).alwaysEdible().fast()
                    .effect(() -> new MobEffectInstance(MobEffects.DIG_SPEED, 1700, 1), 1.0F);
            BuiltInRegistries.MOB_EFFECT.getHolder(ResourceLocation.parse("farmersdelight:nourishment"))
                    .ifPresent(nourishment -> ChocolateAtole.effect(() -> new MobEffectInstance(nourishment, 900, 0), 1.0F));
            CHOCOLATE_ATOLE = ChocolateAtole.build();

            var CherryAtole = (new FoodProperties.Builder()).nutrition(3).saturationModifier(1.2f).alwaysEdible().fast()
                    .effect(() -> new MobEffectInstance(MobEffects.REGENERATION, 200, 1), 1.0F);
            BuiltInRegistries.MOB_EFFECT.getHolder(ResourceLocation.parse("farmersdelight:nourishment"))
                    .ifPresent(nourishment -> CherryAtole.effect(() -> new MobEffectInstance(nourishment, 900, 0), 1.0F));
            CHERRY_ATOLE = CherryAtole.build();

            var RicePudingAtole = (new FoodProperties.Builder()).nutrition(3).saturationModifier(1.2f).alwaysEdible().fast()
                    .effect(() -> new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 2100, 0), 1.0F);
            BuiltInRegistries.MOB_EFFECT.getHolder(ResourceLocation.parse("farmersdelight:nourishment"))
                    .ifPresent(nourishment -> RicePudingAtole.effect(() -> new MobEffectInstance(nourishment, 900, 0), 1.0F));
            RICE_PUDING_ATOLE = RicePudingAtole.build();

            var Champurrado = (new FoodProperties.Builder()).nutrition(3).saturationModifier(1.2f).alwaysEdible().fast()
                    .effect(() -> new MobEffectInstance(MobEffects.NIGHT_VISION, 1700, 0), 1.0F);
            BuiltInRegistries.MOB_EFFECT.getHolder(ResourceLocation.parse("farmersdelight:nourishment"))
                    .ifPresent(nourishment -> Champurrado.effect(() -> new MobEffectInstance(nourishment, 900, 0), 1.0F));
            CHAMPURRADO = Champurrado.build();
        }
    }

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus); TOOLS.register(eventBus);
    }
}
