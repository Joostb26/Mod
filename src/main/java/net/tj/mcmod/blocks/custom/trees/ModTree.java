package net.tj.mcmod.blocks.custom.trees;

import net.minecraft.world.gen.feature.Feature;
import net.tj.mcmod.world.gen.ModConfiguredFeatures;
import net.tj.mcmod.world.gen.TreeSpawner;

import javax.annotation.Nullable;
import java.util.Random;

public class ModTree {
    public static final TreeSpawner TREE = new TreeSpawner() {
        @Nullable
        @Override
        protected Feature getFeature(Random random) {
            return ModConfiguredFeatures.COCONUT_TREE.get();
        }
    };
}
