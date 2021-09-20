package net.tj.mcmod.biomes;

import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeMaker;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.tj.mcmod.mcmod;

import java.util.function.Supplier;

public class CustomBiome {

    public static final DeferredRegister<Biome> BIOMES = DeferredRegister.create(ForgeRegistries.BIOMES, mcmod.MOD_ID);

    static {
        createBiome("ghost_biome", BiomeMaker::theVoidBiome);
    }

    public static RegistryKey<Biome> GHOST_BIOME = biomeRegistryKey("ghost_biome");

    public static RegistryKey<Biome> biomeRegistryKey(String biomeName){
        return RegistryKey.create(Registry.BIOME_REGISTRY, new ResourceLocation(mcmod.MOD_ID, biomeName));
    }

    public static RegistryObject<Biome> createBiome(String biomeName, Supplier<Biome> biome) {
        return BIOMES.register(biomeName, biome);
    }

    public static void registerBiomes(){
        BiomeManager.addBiome(BiomeManager.BiomeType.COOL, new BiomeManager.BiomeEntry(GHOST_BIOME, 10));
    }

}
