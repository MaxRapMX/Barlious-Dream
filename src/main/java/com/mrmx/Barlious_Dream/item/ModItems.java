package com.mrmx.Barlious_Dream.item;

import com.mrmx.Barlious_Dream.item.SupportsModsToolTiers.VanillaBackportToolTiers;
import com.mrmx.Barlious_Dream.item.custom.foods.AtoleItem;
import com.mrmx.Barlious_Dream.item.custom.foods.ChorusFruitAtole;
import com.mrmx.Barlious_Dream.item.custom.foods.DurabilityFoods;
import com.mrmx.Barlious_Dream.item.custom.foods.FasterChorusFruitItem;
import com.mrmx.Barlious_Dream.item.custom.misc.Barlious_Upgrade;
import com.mrmx.Barlious_Dream.item.custom.misc.Heavy_Heads;
import com.mrmx.Barlious_Dream.item.custom.tools.Ender_Bag;
import com.mrmx.Barlious_Dream.item.custom.tools.HeavyPickaxeItem;
import com.mrmx.Barlious_Dream.item.custom.tools.MacheteItem;
import com.mrmx.Barlious_Dream.item.custom.tools.PlantWhistleCNC;
import com.mrmx.Barlious_Dream.item.custom.totems.MacheteOfUndying;
import com.mrmx.Barlious_Dream.registry.Barlious_Dream;
import net.minecraft.ChatFormatting;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModList;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.List;

public class ModItems {
    public static final boolean VB_REQUIRED = ModList.get().isLoaded("vanillabackport");
    public static final boolean CNC_REQUIRED = ModList.get().isLoaded("cnc");
    public static final boolean CORN_DELIGHT_REQUIRED = ModList.get().isLoaded("corn_delight");
    public static final boolean REQUIRES_FOR_COPPER = VB_REQUIRED;
    public static final boolean REQUIRES_FOR_CNC_ATOLE = CNC_REQUIRED && CORN_DELIGHT_REQUIRED;
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(Barlious_Dream.MODID);
    public static final DeferredRegister.Items TOOLS = DeferredRegister.createItems(Barlious_Dream.MODID);

    public static final DeferredItem<Item> BARLIOUS_DREAM_LOGO = ITEMS.register("icon",
            () -> new Item(new Item.Properties().stacksTo(1).fireResistant().rarity(Rarity.RARE)) {
                @Override
                public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
                    tooltipComponents.add(Component.translatable("tooltip.mrmx_dream.logo").withStyle(ChatFormatting.GRAY));
                    super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
                }
            });
    public static final DeferredItem<Item> BARLIOUS_UPGRADE = ITEMS.register("barlious_upgrade_smithing_template",
            () -> new Barlious_Upgrade(new Item.Properties().stacksTo(8)));

    // Miscelaneos
    public static final DeferredItem<FasterChorusFruitItem> CRYING_CHORUS_FRUIT = ITEMS.register("crying_chorus_fruit",
            () -> new FasterChorusFruitItem(new Item.Properties().rarity(Rarity.RARE).food(Food_Dreamed.CRYING_CHORUS_FRUIT)));

    // Machetes; 1.3 mas de daño, pero 1.16 mas lento que las espadas vb_copper_machete
    public static final DeferredItem<MacheteItem> IRON_MACHETE = TOOLS.register("iron_machete",
            () -> new MacheteItem(ModToolTiers.IRON_MACHETE, new Item.Properties()
                    .attributes(SwordItem.createAttributes(ModToolTiers.IRON_MACHETE, 4, -2.7f))));
    public static final DeferredItem<MacheteItem> DIAMOND_MACHETE = TOOLS.register("diamond_machete",
            () -> new MacheteItem(ModToolTiers.DIAMOND_MACHETE, new Item.Properties()
                    .attributes(SwordItem.createAttributes(ModToolTiers.DIAMOND_MACHETE, 4, -2.7f))));
    public static final DeferredItem<MacheteItem> NETHERITE_MACHETE = TOOLS.register("netherite_machete",
            () -> new MacheteItem(ModToolTiers.NETHERITE_MACHETE, new Item.Properties().fireResistant()
                    .attributes(SwordItem.createAttributes(ModToolTiers.NETHERITE_MACHETE, 4, -2.7f))));
    public static final DeferredItem<MacheteItem> VB_COPPER_MACHETE = VB_REQUIRED
            ? TOOLS.register("vb_copper_machete",
            () -> new MacheteItem(VanillaBackportToolTiers.VB_COPPER_MACHETE, new Item.Properties()
                    .attributes(SwordItem.createAttributes(VanillaBackportToolTiers.VB_COPPER_MACHETE, 4, -2.7f)))): null;
    public static final DeferredItem<MacheteOfUndying> MACHETE_OF_UNDYING = TOOLS.register("machete_of_undying",
            () -> new MacheteOfUndying(ModToolTiers.MACHETE_OF_UNDYING, new Item.Properties().fireResistant().rarity(Rarity.UNCOMMON)
                    .attributes(SwordItem.createAttributes(ModToolTiers.MACHETE_OF_UNDYING, 5, -2.7f))));

    // Cabezales de Picos reforzados; para mejorar los picos vanilla
    public static final DeferredItem<Item> IRON_HEAVY_HEAD = ITEMS.register("iron_heavy_head",
            () -> new Heavy_Heads(new Item.Properties().stacksTo(1)));
    public static final DeferredItem<Item> DIAMOND_HEAVY_HEAD = ITEMS.register("diamond_heavy_head",
            () -> new Heavy_Heads(new Item.Properties().stacksTo(1)));
    public static final DeferredItem<Item> NETHERITE_HEAVY_HEAD = ITEMS.register("netherite_heavy_head",
            () -> new Heavy_Heads(new Item.Properties().stacksTo(1).fireResistant()));
    public static final DeferredItem<Item> COPPER_HEAVY_HEAD = REQUIRES_FOR_COPPER
            ? TOOLS.register("copper_heavy_head",
            () -> new Heavy_Heads(new Item.Properties().stacksTo(1))): null;
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
    public static final DeferredItem<Item> VB_COPPER_HEAVY_PICKAXE = VB_REQUIRED
            ? TOOLS.register("vb_copper_heavy_pickaxe",
            () -> new HeavyPickaxeItem(VanillaBackportToolTiers.VB_HEAVY_PICKAXE, new Item.Properties()
                    .attributes(PickaxeItem.createAttributes(VanillaBackportToolTiers.VB_HEAVY_PICKAXE,
                            2.0f, -3.1f)))): null;

    // Atoles
    public static final DeferredItem<Item> ARTISANAL_CUP = CORN_DELIGHT_REQUIRED
            ? ITEMS.register("artisanal_cup",
            () -> new Item(new Item.Properties().stacksTo(16))): null;
    public static final DeferredItem<AtoleItem> VANILLA_ATOLE = CORN_DELIGHT_REQUIRED
        ? ITEMS.register("vanilla_atole",
            () -> new AtoleItem(new Item.Properties().durability(32).food(Atoles.VANILLA_ATOLE))): null;
    public static final DeferredItem<AtoleItem> CHOCOLATE_ATOLE = CORN_DELIGHT_REQUIRED
            ? ITEMS.register("chocolate_atole",
            () -> new AtoleItem(new Item.Properties().durability(32).food(Atoles.CHOCOLATE_ATOLE))): null;
    public static final DeferredItem<AtoleItem> APPLE_ATOLE = CORN_DELIGHT_REQUIRED
            ? ITEMS.register("apple_atole",
            () -> new AtoleItem(new Item.Properties().durability(32).food(Atoles.APPLE_ATOLE))): null;
    public static final DeferredItem<AtoleItem> CARROT_ATOLE = CORN_DELIGHT_REQUIRED
            ? ITEMS.register("carrot_atole",
            () -> new AtoleItem(new Item.Properties().durability(32).food(Atoles.CARROT_ATOLE))): null;
    public static final DeferredItem<AtoleItem> GLOW_BERRIES_ATOLE = CORN_DELIGHT_REQUIRED
            ? ITEMS.register("glow_berries_atole",
            () -> new AtoleItem(new Item.Properties().durability(16).rarity(Rarity.UNCOMMON)
                    .food(Atoles.GLOW_BERRIES_ATOLE))): null;
    public static final DeferredItem<ChorusFruitAtole> CHORUS_FRUIT_ATOLE = CORN_DELIGHT_REQUIRED
            ? ITEMS.register("chorus_fruit_atole",
            () -> new ChorusFruitAtole(new Item.Properties().durability(16).rarity(Rarity.UNCOMMON)
                    .food(Atoles.CHORUS_FRUIT_ATOLE))): null;
    public static final DeferredItem<AtoleItem> RICE_PUDING_ATOLE = CORN_DELIGHT_REQUIRED
            ? ITEMS.register("rice_puding_atole",
            () -> new AtoleItem(new Item.Properties().durability(32).food(Atoles.RICE_PUDING_ATOLE))): null;
    public static final DeferredItem<AtoleItem> CHAMPURRADO = CORN_DELIGHT_REQUIRED
            ? ITEMS.register("champurrado",
            () -> new AtoleItem(new Item.Properties().durability(16).rarity(Rarity.UNCOMMON)
                    .food(Atoles.CHAMPURRADO))): null;

    // CNC Atoles
    public static final DeferredItem<AtoleItem> CHERRY_ATOLE = REQUIRES_FOR_CNC_ATOLE
            ? ITEMS.register("cherry_atole",
            () -> new AtoleItem(new Item.Properties().durability(32).food(Atoles.CHERRY_ATOLE))): null;
    public static final DeferredItem<AtoleItem> WALNUT_ATOLE = REQUIRES_FOR_CNC_ATOLE
            ? ITEMS.register("walnut_atole",
            () -> new AtoleItem(new Item.Properties().durability(32).food(Atoles.WALNUT_ATOLE))): null;
    public static final DeferredItem<AtoleItem> VANILLA_WITH_WALNUT_ATOLE = REQUIRES_FOR_CNC_ATOLE
            ? ITEMS.register("mazapan_atole",
            () -> new AtoleItem(new Item.Properties().durability(16).rarity(Rarity.UNCOMMON)
                    .food(Atoles.VANILLA_WITH_WALNUT_ATOLE))): null;
    public static final DeferredItem<AtoleItem> VANILLA_WITH_CHERRIES_ATOLE = REQUIRES_FOR_CNC_ATOLE
            ? ITEMS.register("angels_kiss_atole",
            () -> new AtoleItem(new Item.Properties().durability(16).rarity(Rarity.UNCOMMON)
                    .food(Atoles.VANILLA_WITH_CHERRIES_ATOLE))): null;

    // Extra
    public static final DeferredItem<Ender_Bag> ENDER_BAG = TOOLS.register("ender_bag",
            () -> new Ender_Bag(new Item.Properties().stacksTo(1).rarity(Rarity.EPIC)));
    public static final DeferredItem<Item> PLANT_WHISTLE = CNC_REQUIRED
            ? TOOLS.register("plant_whistle",
            () -> new PlantWhistleCNC(new Item.Properties().stacksTo(1).rarity(Rarity.UNCOMMON))): null;

    static class Food_Dreamed {
        public static final FoodProperties CRYING_CHORUS_FRUIT;
        Food_Dreamed() {}
        static {
            var CryingChorusFruit = (new FoodProperties.Builder()).nutrition(4).saturationModifier(1.2F).alwaysEdible().fast()
                    .effect(() -> new MobEffectInstance(MobEffects.REGENERATION, 300, 1), 1.0F)
                    .effect(() -> new MobEffectInstance(MobEffects.ABSORPTION, 2400, 1), 1.0F)
                    .effect(() -> new MobEffectInstance(MobEffects.HEAL, 10, 0), 0.35F)
                    .effect(() -> new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 6000, 0), 1.0F)
                    .effect(() -> new MobEffectInstance(MobEffects.SLOW_FALLING, 300, 0), 1.0F)
                    .effect(() -> new MobEffectInstance(MobEffects.NIGHT_VISION, 7200, 0), 1.0F);
            CRYING_CHORUS_FRUIT = CryingChorusFruit.build();
        }
    }

    static class Atoles {
        public static final FoodProperties VANILLA_ATOLE;
        public static final FoodProperties CHOCOLATE_ATOLE;
        public static final FoodProperties APPLE_ATOLE;
        public static final FoodProperties CHERRY_ATOLE;
        public static final FoodProperties CARROT_ATOLE;
        public static final FoodProperties GLOW_BERRIES_ATOLE;
        public static final FoodProperties CHORUS_FRUIT_ATOLE;
        public static final FoodProperties WALNUT_ATOLE;
        public static final FoodProperties VANILLA_WITH_WALNUT_ATOLE;
        public static final FoodProperties VANILLA_WITH_CHERRIES_ATOLE;
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

            var AppleAtole = (new FoodProperties.Builder()).nutrition(3).saturationModifier(1.2f).alwaysEdible().fast()
                    .effect(() -> new MobEffectInstance(MobEffects.HEAL, 1, 1), 1.0F);
            BuiltInRegistries.MOB_EFFECT.getHolder(ResourceLocation.parse("farmersdelight:nourishment"))
                    .ifPresent(nourishment -> AppleAtole.effect(() -> new MobEffectInstance(nourishment, 900, 0), 1.0F));
            APPLE_ATOLE = AppleAtole.build();

            var CherryAtole = (new FoodProperties.Builder()).nutrition(3).saturationModifier(1.2f).alwaysEdible().fast()
                    .effect(() -> new MobEffectInstance(MobEffects.REGENERATION, 200, 1), 1.0F);
            BuiltInRegistries.MOB_EFFECT.getHolder(ResourceLocation.parse("farmersdelight:nourishment"))
                    .ifPresent(nourishment -> CherryAtole.effect(() -> new MobEffectInstance(nourishment, 900, 0), 1.0F));
            CHERRY_ATOLE = CherryAtole.build();

            var CarrotAtole = (new FoodProperties.Builder()).nutrition(3).saturationModifier(1.2f).alwaysEdible().fast()
                    .effect(() -> new MobEffectInstance(MobEffects.NIGHT_VISION, 900, 0), 1.0F);
            BuiltInRegistries.MOB_EFFECT.getHolder(ResourceLocation.parse("farmersdelight:nourishment"))
                    .ifPresent(nourishment -> CarrotAtole.effect(() -> new MobEffectInstance(nourishment, 900, 0), 1.0F));
            CARROT_ATOLE = CarrotAtole.build();

            var GlowBerriesAtole = (new FoodProperties.Builder()).nutrition(5).saturationModifier(0.9f).alwaysEdible().fast()
                    .effect(() -> new MobEffectInstance(MobEffects.ABSORPTION, 2100, 1), 1.0F)
                    .effect(() -> new MobEffectInstance(MobEffects.GLOWING, 500, 0), 1.0F);
            BuiltInRegistries.MOB_EFFECT.getHolder(ResourceLocation.parse("farmersdelight:nourishment"))
                    .ifPresent(nourishment -> GlowBerriesAtole.effect(() -> new MobEffectInstance(nourishment, 900, 0), 1.0F));
            GLOW_BERRIES_ATOLE = GlowBerriesAtole.build();

            var ChorusFruitAtole = (new FoodProperties.Builder()).nutrition(5).saturationModifier(0.9f).alwaysEdible().fast()
                    .effect(() -> new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 500, 0), 1.0F)
                    .effect(() -> new MobEffectInstance(MobEffects.SLOW_FALLING, 2400, 1), 1.0F);
            BuiltInRegistries.MOB_EFFECT.getHolder(ResourceLocation.parse("farmersdelight:nourishment"))
                    .ifPresent(nourishment -> ChorusFruitAtole.effect(() -> new MobEffectInstance(nourishment, 900, 0), 1.0F));
            CHORUS_FRUIT_ATOLE = ChorusFruitAtole.build();

            var WalnutAtole = (new FoodProperties.Builder()).nutrition(3).saturationModifier(1.2f).alwaysEdible().fast()
                    .effect(() -> new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 1700, 0), 1.0F);
            BuiltInRegistries.MOB_EFFECT.getHolder(ResourceLocation.parse("farmersdelight:nourishment"))
                    .ifPresent(nourishment -> WalnutAtole.effect(() -> new MobEffectInstance(nourishment, 900, 0), 1.0F));
            WALNUT_ATOLE = WalnutAtole.build();

            var MazapanAtole = (new FoodProperties.Builder()).nutrition(5).saturationModifier(0.9f).alwaysEdible().fast()
                    .effect(() -> new MobEffectInstance(MobEffects.ABSORPTION, 900, 2), 1.0F);
            BuiltInRegistries.MOB_EFFECT.getHolder(ResourceLocation.parse("farmersdelight:comfort"))
                    .ifPresent(comfort -> MazapanAtole.effect(() -> new MobEffectInstance(comfort, 1700, 0), 1.0F));
            BuiltInRegistries.MOB_EFFECT.getHolder(ResourceLocation.parse("farmersdelight:nourishment"))
                    .ifPresent(nourishment -> MazapanAtole.effect(() -> new MobEffectInstance(nourishment, 900, 0), 1.0F));
            VANILLA_WITH_WALNUT_ATOLE = MazapanAtole.build();

            var AngelsKissAtole = (new FoodProperties.Builder()).nutrition(5).saturationModifier(0.9f).alwaysEdible().fast()
                    .effect(() -> new MobEffectInstance(MobEffects.HEALTH_BOOST, 9000, 1), 1.0F);
            BuiltInRegistries.MOB_EFFECT.getHolder(ResourceLocation.parse("farmersdelight:nourishment"))
                    .ifPresent(nourishment -> AngelsKissAtole.effect(() -> new MobEffectInstance(nourishment, 300, 0), 1.0F));
            VANILLA_WITH_CHERRIES_ATOLE = AngelsKissAtole.build();

            var RicePudingAtole = (new FoodProperties.Builder()).nutrition(3).saturationModifier(1.2f).alwaysEdible().fast()
                    .effect(() -> new MobEffectInstance(MobEffects.DAMAGE_BOOST, 900, 0), 1.0F);
            BuiltInRegistries.MOB_EFFECT.getHolder(ResourceLocation.parse("farmersdelight:nourishment"))
                    .ifPresent(nourishment -> RicePudingAtole.effect(() -> new MobEffectInstance(nourishment, 900, 0), 1.0F));
            RICE_PUDING_ATOLE = RicePudingAtole.build();

            var Champurrado = (new FoodProperties.Builder()).nutrition(5).saturationModifier(0.9f).alwaysEdible().fast()
                    .effect(() -> new MobEffectInstance(MobEffects.DIG_SPEED, 1700, 0), 1.0F)
                    .effect(() -> new MobEffectInstance(MobEffects.NIGHT_VISION, 900, 0), 1.0F);
            BuiltInRegistries.MOB_EFFECT.getHolder(ResourceLocation.parse("farmersdelight:nourishment"))
                    .ifPresent(nourishment -> Champurrado.effect(() -> new MobEffectInstance(nourishment, 600, 0), 1.0F));
            CHAMPURRADO = Champurrado.build();
        }
    }

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus); TOOLS.register(eventBus);
    }
}
