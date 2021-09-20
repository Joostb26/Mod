package net.tj.mcmod.items.custom;

import net.minecraft.block.AbstractFireBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.effect.LightningBoltEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.tj.mcmod.tileentity.LightningChannelerTile;

public class lavian_hammer extends Item {
    public lavian_hammer(Properties properties) {
        super(properties);
    }
    @Override
    public ActionResultType onItemUseFirst(ItemStack stack, ItemUseContext context) {
        World world = context.getLevel();

        if (!world.isClientSide){
            PlayerEntity playerEntity = context.getPlayer();
            BlockState clickedBLock = world.getBlockState(context.getClickedPos());


            rightClickOnCertainBlockStates(clickedBLock, context, playerEntity);
            assert playerEntity != null;
            stack.hurtAndBreak(1,playerEntity, player -> player.broadcastBreakEvent(context.getHand()));

        }

        return super.onItemUseFirst(stack, context);
    }

    private void rightClickOnCertainBlockStates(BlockState clickedBLock, ItemUseContext context,
                                                PlayerEntity playerEntity) {

        boolean playerIsNotOnFire = !playerEntity.isOnFire();

        if (random.nextFloat() > 1f){
            lightEntityOnFire(playerEntity, 6);

        } else if (playerIsNotOnFire && blockIsValidForResistance (clickedBLock)){
            gainFireResistanceAndDestroyBlock(playerEntity, context.getLevel(),context.getClickedPos());


        } else {
            lightGroundOnFire(context);

        }


    }

    private boolean blockIsValidForResistance(BlockState clickedBLock) {
        return clickedBLock.getBlock() == Blocks.OBSIDIAN || clickedBLock.getBlock() == Blocks.NETHERRACK;
    }

    public static void lightEntityOnFire(Entity entity, int second) {
        entity.setSecondsOnFire(second);
    }
    private void gainFireResistanceAndDestroyBlock (PlayerEntity playerEntity, World world, BlockPos pos){

        gainFireResistance(playerEntity);
        world.destroyBlock(pos, false);
    }

    public static void gainFireResistance(PlayerEntity playerentity) {
        playerentity.addEffect(new EffectInstance(Effects.FIRE_RESISTANCE, 200));
    }
    
    public static void lightGroundOnFire (ItemUseContext context){


        PlayerEntity playerentity = context.getPlayer();
        World world = context.getLevel();
        BlockPos blockpos = context.getClickedPos().relative(context.getClickedFace());
        BlockState blockstate = world.getBlockState(blockpos);

        if (AbstractFireBlock.canBePlacedAt(world, blockpos, context.getHorizontalDirection())) {
            world.playSound(playerentity, blockpos, SoundEvents.FLINTANDSTEEL_USE, SoundCategory.BLOCKS, 1.0F,
                    random.nextFloat() * 0.4F + 0.8F);

            BlockState blockstate1 = AbstractFireBlock.getState(world, blockpos);
            world.setBlock(blockpos, blockstate1, 11);

        }

    }
}