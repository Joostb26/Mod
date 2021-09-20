package net.tj.mcmod.blocks.custom;

import net.minecraft.block.*;
import net.minecraft.entity.Entity;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

import java.util.Random;

public class Lavianide extends Block {
    public Lavianide(Properties properties) {
        super(properties);
    }


    // Hier word de explosie gemaakt met de method setExplodeonBlock
    public static void setExplodeonBlock(Entity entity, int amplifier, BlockPos pos) {
        entity.getCommandSenderWorld().explode(null, pos.getX(), pos.getY(), pos.getZ(), amplifier, Explosion.Mode.BREAK);
    }

    // Hier word de method opgeroepen en gekoppeld aan het block - Lavianide
    @Override
    public void stepOn(World world, BlockPos pos, Entity entity) {
        Lavianide.setExplodeonBlock(entity, 8, pos.above());
        super.stepOn(world, pos, entity);
    }

    // Hier word een particle toegevoegd aan het block
    @Override
    public void animateTick(BlockState state, World world, BlockPos pos, Random random) {
        double p0 = (double)pos.getX() + 0.5D;
        double p1 = (double)pos.getY() + 1.0D;
        double p2 = (double)pos.getZ() + 0.5D;

        world.addParticle(ParticleTypes.LAVA.getType(), p0, p1, p2, 2.0D, 2.0D, 2.0D);
        super.animateTick(state, world, pos, random);
    }
}
