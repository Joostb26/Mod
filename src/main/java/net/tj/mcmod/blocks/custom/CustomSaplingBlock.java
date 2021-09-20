package net.tj.mcmod.blocks.custom;

import net.minecraft.block.BlockState;
import net.minecraft.block.SaplingBlock;
import net.minecraft.block.trees.Tree;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.server.ServerWorld;
import net.tj.mcmod.world.gen.TreeSpawner;

import java.util.Random;

public class CustomSaplingBlock extends SaplingBlock {

    private final TreeSpawner tree;

    public CustomSaplingBlock(Properties properties, TreeSpawner spawner) {
        super(null, properties);
        this.tree = spawner;
    }

    @Override
    public void advanceTree(ServerWorld world, BlockPos pos, BlockState state, Random random) {
        if (state.getValue(STAGE) == 0) {
            world.setBlock(pos, state.cycle(STAGE), 4);
        } else {
            tree.spawn(world, world.getChunkSource().getGenerator(), pos, state, random);
        }
        super.advanceTree(world, pos, state, random);
    }
}
