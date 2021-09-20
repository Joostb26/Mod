package net.tj.mcmod.world.gen;

import net.minecraft.block.BlockState;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.feature.template.RuleTest;
import net.minecraft.world.gen.foliageplacer.AcaciaFoliagePlacer;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.placement.TopSolidRangeConfig;
import net.minecraft.world.gen.trunkplacer.ForkyTrunkPlacer;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.tj.mcmod.blocks.ModBlocks;
import net.tj.mcmod.mcmod;

public class ModConfiguredFeatures {

    // Hier register ik Features
    public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(ForgeRegistries.FEATURES, mcmod.MOD_ID);

    // Register van de GHOST_FEATURE - Biome
    public static final RegistryObject<GhostFeature> GHOST_FEATURE = FEATURES.register("ghost_feature",
            () -> new GhostFeature(NoFeatureConfig.CODEC));

    public static final RegistryObject<CoconutTreeFeature> COCONUT_TREE = FEATURES.register("coconut_tree",
            () -> new CoconutTreeFeature(NoFeatureConfig.CODEC));

    public static void addOres(final BiomeLoadingEvent event) {
        addOre(event, OreFeatureConfig.FillerBlockType.NATURAL_STONE,
                ModBlocks.LAVIAN_ORE.get().defaultBlockState(), 4, 0, 30, 20);

    }

    // Hier laat ik ore's spawnen in de wereld.
    public static void addOre(final BiomeLoadingEvent event, RuleTest rule, BlockState state, int veinSize,
                              int minHeight, int maxHeight, int amount) {
        event.getGeneration().addFeature(GenerationStage.Decoration.UNDERGROUND_ORES,
                Feature.ORE.configured(new OreFeatureConfig(rule, state, veinSize))
                        .decorated(Placement.RANGE.configured(new TopSolidRangeConfig(minHeight, 0, maxHeight)))
                        .squared().count(amount));
    }


    private static <FC extends IFeatureConfig> ConfiguredFeature<FC, ?> register(String key, ConfiguredFeature<FC, ?> configuredFeature) {
            return Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, key, configuredFeature);
        }


    }
