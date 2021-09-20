package net.tj.mcmod.world.gen;

import com.mojang.serialization.Codec;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.IWorldGenerationBaseReader;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;


import java.util.Random;



public class GhostFeature extends Feature<NoFeatureConfig> {

    // Hier worden de blokken geregistreed die we gaan gebruiken bij de feature.

    private static final BlockState FIRE = Blocks.FIRE.getBlock().defaultBlockState();
    private static final BlockState BLACKSTONE = Blocks.BLACKSTONE.getBlock().defaultBlockState();
    private static final BlockState GILDEDBLACKSTONE = Blocks.GILDED_BLACKSTONE.getBlock().defaultBlockState();

    public GhostFeature(Codec<NoFeatureConfig> codec) {
        super(codec);
    }

    // Hier worden de features boven de grond neergezet.
    @SuppressWarnings("deprecation")
    public boolean isAirOrLeaves(IWorldGenerationBaseReader reader, BlockPos pos) {
        if (!(reader instanceof IWorldReader)) {
            return reader.isStateAtPosition(pos, state -> state.isAir() || state.is(BlockTags.LEAVES));
        } else {
            return reader.isStateAtPosition(pos, state -> state.canBeReplacedByLeaves((IWorldReader)reader, pos));
        }
    }

    // Hier kan je feature aanpassen met welke blokken etc en wat de hoogte - breedte is. Alle posities
    @Override
    public boolean place(ISeedReader reader, ChunkGenerator generator, Random random, BlockPos pos, NoFeatureConfig config) {

        while (pos.getY() > 1 && isAirOrLeaves(reader, pos)) {
            pos = pos.below();
        }
        pos = pos.above();

        for (int i = 0; i <4; i++)
            setBlock(reader, pos.above(i) ,BLACKSTONE);
        setBlock(reader, pos.above(4), GILDEDBLACKSTONE);
        setBlock(reader, pos.above(5) ,FIRE);

        return false;
    }
}
