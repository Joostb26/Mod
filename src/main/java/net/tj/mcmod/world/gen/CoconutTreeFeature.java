package net.tj.mcmod.world.gen;

import com.mojang.serialization.Codec;
import net.minecraft.block.BlockState;
import net.minecraft.block.LeavesBlock;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.Direction;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.IWorldGenerationBaseReader;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.tj.mcmod.blocks.ModBlocks;

import java.util.Random;

public class CoconutTreeFeature extends Feature <NoFeatureConfig>{


    private static final Direction[] DIRECTIONS = new Direction[] { Direction.NORTH, Direction.EAST, Direction.SOUTH,
            Direction.WEST };

    private static final BlockState LOG = ModBlocks.COCONUT_LOG.get().defaultBlockState();
    private static final BlockState LEAVES = ModBlocks.COCONUT_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 2);

    public CoconutTreeFeature(Codec<NoFeatureConfig> codec) {
        super(codec);
    }

    @SuppressWarnings("deprecation")
    public boolean isAirOrLeaves(IWorldGenerationBaseReader reader, BlockPos pos) {
        if (!(reader instanceof IWorldReader)) {
            return reader.isStateAtPosition(pos, state -> state.isAir() || state.is(BlockTags.LEAVES));
        } else {
            return reader.isStateAtPosition(pos, state -> state.canBeReplacedByLeaves((IWorldReader) reader, pos));
        }
    }

    @Override
    public boolean place(ISeedReader reader, ChunkGenerator generator, Random random, BlockPos pos, NoFeatureConfig config) {

        while (pos.getY() > 1 && isAirOrLeaves(reader, pos)) {
            pos = pos.below();
        }

        // Trunks
        int height = 4 + random.nextInt(4);
        if (pos.getY() >= 1 && pos.getY() + 7 + 1 < reader.getHeight()) {
            for (int i = pos.getY() + 1; i < pos.getY() + height + 1; i++) {
                reader.setBlock(new BlockPos(pos.getX(), i, pos.getZ()), LOG, 3);
            }
        }
        else {
            return false;
        }

        // Leaves
        for (int i = 0; i < 3; i++) {
            for (Direction d : DIRECTIONS) {
                reader.setBlock(new BlockPos(pos.getX(), pos.getY() + height - i, pos.getZ()).relative(d, i), LEAVES, 3);
            }
        }

        return true;
    }
}

