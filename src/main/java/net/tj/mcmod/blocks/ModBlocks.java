package net.tj.mcmod.blocks;

import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.tj.mcmod.blocks.custom.BananaBlock;
import net.tj.mcmod.blocks.custom.*;
import net.tj.mcmod.blocks.custom.LightningChannelerBlock;
import net.tj.mcmod.blocks.custom.trees.ModTree;
import net.tj.mcmod.items.ModItemGroup;
import net.tj.mcmod.items.ModItems;
import net.tj.mcmod.mcmod;
import java.util.function.Supplier;

public class ModBlocks {

    // In deze class worden alle blokken geregisterd.
    public static final DeferredRegister<Block> BLOCKS
            = DeferredRegister.create(ForgeRegistries.BLOCKS, mcmod.MOD_ID);

    private static <T extends Block>RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    // Hier staan alle blokken die zijn geregisterd.

    public static final RegistryObject<Block> LAVIAN_ORE = registerBlock("lavian_ore",
            () -> new Block(AbstractBlock.Properties.of(Material.STONE).sound(SoundType.STONE).strength(3.0f, 3.0f).harvestLevel(3).harvestTool(ToolType.PICKAXE).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> LAVIAN_BLOCK = registerBlock("lavian_block",
            () -> new Block(AbstractBlock.Properties.of(Material.HEAVY_METAL).sound(SoundType.METAL).strength(3.0f, 3.0f).harvestLevel(3).harvestTool(ToolType.PICKAXE).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> LAVIANIDE = registerBlock("lavianide",
            () -> new Lavianide(AbstractBlock.Properties.of(Material.EXPLOSIVE).sound(SoundType.GRASS).strength(0.0f, 0.0f).harvestLevel(0)));

    public static final RegistryObject<Block> ZOMBIDE = registerBlock("zombide",
            () -> new Zombide(AbstractBlock.Properties.of(Material.SAND).sound(SoundType.GRASS).strength(1.0f, 1.0f).harvestLevel(0)));

    public static final RegistryObject<Block> LIGHTNING_CHANNELER = registerBlock("lightning_channeler",
            () -> new LightningChannelerBlock(AbstractBlock.Properties.of(Material.METAL).strength(0.4f)));

    public static final RegistryObject<Block> BANANA = BLOCKS.register("banana_crop",
            () -> new BananaBlock(AbstractBlock.Properties.copy(Blocks.WHEAT)));

    public static final RegistryObject<Block> COCONUT_LOG = registerBlock("coconut_log",
            () -> new RotatedPillarBlock(AbstractBlock.Properties.copy(Blocks.OAK_LOG)));

    public static final RegistryObject<Block> COCONUT_WOOD = registerBlock("coconut_wood",
            () -> new RotatedPillarBlock(AbstractBlock.Properties.copy(Blocks.OAK_WOOD)));

    public static final RegistryObject<Block> COCONUT_PLANKS = registerBlock("coconut_planks",
            () -> new Block(AbstractBlock.Properties.copy(Blocks.OAK_PLANKS)));

    public static final RegistryObject<Block> STRIPPED_COCONUT_LOG = registerBlock("stripped_coconut_log",
            () -> new RotatedPillarBlock(AbstractBlock.Properties.copy(Blocks.STRIPPED_OAK_LOG)));

    public static final RegistryObject<Block> STRIPPED_COCONUT_WOOD = registerBlock("stripped_coconut_wood",
            () -> new RotatedPillarBlock(AbstractBlock.Properties.copy(Blocks.STRIPPED_OAK_WOOD)));

    public static final RegistryObject<Block> COCONUT_LEAVES = registerBlock("coconut_leaves",
            () -> new LeavesBlock(AbstractBlock.Properties.of(Material.LEAVES).strength(0.1f)
                    .randomTicks().sound(SoundType.GRASS).noOcclusion()));

    public static final RegistryObject<Block> COCONUT_SAPLING = registerBlock("coconut_sapling",
            () -> new CustomSaplingBlock(AbstractBlock.Properties.of(Material.LEAVES).sound(SoundType.GRASS)
                    .strength(0f).randomTicks().noOcclusion(), ModTree.TREE));


    private static <T extends Block> void registerBlockItem(String name, RegistryObject<T> block) {
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(),
                new Item.Properties().tab(ModItemGroup.MC_GROUP)));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }

}
