package net.tj.mcmod.items;

import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.*;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.tj.mcmod.items.custom.lavian_hammer;
import net.tj.mcmod.mcmod;


public class ModItems {

    // In deze class worden alle items geregistreerd.

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, mcmod.MOD_ID);

    public static final RegistryObject<Item> LAVIAN = ITEMS.register("lavian",
            () -> new Item(new Item.Properties().tab(ModItemGroup.MC_GROUP)));

    public static final RegistryObject<Item> LAVIAN_SWORDS = ITEMS.register("lavian_swords",
            () -> new SwordItem(ModItemTier.LAVIAN, 5, 3f,
                    new Item.Properties().tab(ModItemGroup.MC_GROUP)));

    public static final RegistryObject<Item> LAVIAN_PICKAXE = ITEMS.register("lavian_pickaxe",
            () -> new PickaxeItem(ModItemTier.LAVIAN, -7, -1f,
                    new Item.Properties().tab(ModItemGroup.MC_GROUP)));

    public static final RegistryObject<Item> LAVIAN_AXE = ITEMS.register("lavian_axe",
            () -> new AxeItem(ModItemTier.LAVIAN, 4, 0f,
                    new Item.Properties().tab(ModItemGroup.MC_GROUP)));

    public static final RegistryObject<Item> LAVIAN_HOE = ITEMS.register("lavian_hoe",
            () -> new HoeItem(ModItemTier.LAVIAN, -8, 0f,
                    new Item.Properties().tab(ModItemGroup.MC_GROUP)));

    public static final RegistryObject<Item> LAVIAN_SHOVEL = ITEMS.register("lavian_shovel",
            () -> new ShovelItem(ModItemTier.LAVIAN, -7, 0f,
                    new Item.Properties().tab(ModItemGroup.MC_GROUP)));

    public static final RegistryObject<Item> LAVIAN_HELMET = ITEMS.register("lavian_helmet",
            () -> new ArmorItem(ModArmorMaterial.LAVIAN, EquipmentSlotType.HEAD,
                    new Item.Properties().tab(ModItemGroup.MC_GROUP)));

    public static final RegistryObject<Item> LAVIAN_CHESTPLATE = ITEMS.register("lavian_chestplate",
            () -> new ArmorItem(ModArmorMaterial.LAVIAN, EquipmentSlotType.CHEST,
                    new Item.Properties().tab(ModItemGroup.MC_GROUP)));

    public static final RegistryObject<Item> LAVIAN_LEGGINGS = ITEMS.register("lavian_leggings",
            () -> new ArmorItem(ModArmorMaterial.LAVIAN, EquipmentSlotType.LEGS,
                    new Item.Properties().tab(ModItemGroup.MC_GROUP)));

    public static final RegistryObject<Item> LAVIAN_BOOTS = ITEMS.register("lavian_boots",
            () -> new ArmorItem(ModArmorMaterial.LAVIAN, EquipmentSlotType.FEET,
                    new Item.Properties().tab(ModItemGroup.MC_GROUP)));

    public static  final RegistryObject<Item> BANANA = ITEMS.register("banana",
            () -> new Item(new Item.Properties()
                    .food(new Food.Builder()
                            .nutrition(2)
                            .saturationMod(0.4f)
                            .alwaysEat()
                            .effect(new EffectInstance(Effects.JUMP, 200, 10), 1.0f).build())
                    .tab(ModItemGroup.MC_GROUP)));


    public static final RegistryObject<Item> LAVIAN_HAMMER = ITEMS.register("lavian_hammer",
            () -> new lavian_hammer(new Item.Properties().tab(ModItemGroup.MC_GROUP).durability(8)));

    public static final RegistryObject<Item> COCONUT = ITEMS.register("coconut",
            () -> new Item(new Item.Properties().tab(ModItemGroup.MC_GROUP)));


    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

}
