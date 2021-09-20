package net.tj.mcmod.biomes;

import com.mojang.serialization.Codec;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.IChunk;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;
import net.tj.mcmod.blocks.ModBlocks;

import java.util.Random;

public class GhostSurfaceBuilder extends SurfaceBuilder<SurfaceBuilderConfig> {

    public static final BlockState ZOMBIDE = ModBlocks.ZOMBIDE.get().defaultBlockState();
    public static final BlockState STONE = Blocks.STONE.getBlock().defaultBlockState();

    public static final SurfaceBuilderConfig ZOMBIDE_BLOCK_CONFIG = new SurfaceBuilderConfig(ZOMBIDE, ZOMBIDE, ZOMBIDE);
    public static final SurfaceBuilderConfig STONE_CONFIG = new SurfaceBuilderConfig(STONE, STONE, STONE);


    public GhostSurfaceBuilder(Codec<SurfaceBuilderConfig> codec) {
        super(codec);
    }

    @Override
    public void apply(Random random, IChunk chunk, Biome biome, int x, int z, int startHeight, double noise, BlockState defaultBlock, BlockState defaultFluid, int seaLevel, long seed, SurfaceBuilderConfig config) {
        if (random.nextInt(3) == 0) {
            SurfaceBuilder.DEFAULT.apply(random, chunk, biome, x, z, startHeight, noise, defaultBlock, defaultFluid, seaLevel, seed, ZOMBIDE_BLOCK_CONFIG);
        } else {
            SurfaceBuilder.DEFAULT.apply(random, chunk, biome, x, z, startHeight, noise, defaultBlock, defaultFluid, seaLevel, seed, STONE_CONFIG);
        }
    }
}
