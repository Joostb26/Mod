package net.tj.mcmod.blocks.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.effect.LightningBoltEntity;
import net.minecraft.entity.monster.ZombieEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import java.util.Random;

public class Zombide extends Block {
    public Zombide(Properties properties) {
        super(properties);
    }

    // Hier laat ik met de method makeZombieSpawn de zombie's spawnen
    public static void makeZombieSpawn(ServerWorld world, BlockPos pos) {
        ZombieEntity entity = new ZombieEntity(world);
        entity.setPos(pos.getX(), pos.getY(), pos.getZ());
        world.addFreshEntity(entity);
    }

    //Hier laat ik met de method spawnLightning het lighting spawnen boven het block
    public static void spawnLightning(ServerWorld world, BlockPos pos) {
        LightningBoltEntity entity = new LightningBoltEntity(EntityType.LIGHTNING_BOLT, world);
        entity.setPos(pos.getX(), pos.getY(), pos.getZ());
        world.addFreshEntity(entity);
    }
    

    // Hier worden de methods gekoppeld aan de method spawnAfterBreak. Dus als het blok breekt actieveren de methods
    @SuppressWarnings("deprecation")
    @Override
    public void spawnAfterBreak(BlockState state, ServerWorld serverWorld, BlockPos pos, ItemStack itemStack) {
        Zombide.makeZombieSpawn(serverWorld, pos.above());
        Zombide.spawnLightning(serverWorld, pos.above());
        super.spawnAfterBreak(state, serverWorld, pos, itemStack);
    }

    // Hier worden particles aan het block toegevoegd
    @Override
    public void animateTick(BlockState state, World world, BlockPos pos, Random random) {
        double p0 = (double)pos.getX() + 0.5D;
        double p1 = (double)pos.getY() + 1.0D;
        double p2 = (double)pos.getZ() + 0.5D;

        world.addParticle(ParticleTypes.SMOKE.getType(), p0, p1, p2, 0.0D, 0.0D, 0.0D);
        super.animateTick(state, world, pos, random);
    }


}
