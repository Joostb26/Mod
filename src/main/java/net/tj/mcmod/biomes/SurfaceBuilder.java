package net.tj.mcmod.biomes;

import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.tj.mcmod.mcmod;

public class SurfaceBuilder {
    public static final DeferredRegister<net.minecraft.world.gen.surfacebuilders.SurfaceBuilder<?>> SURFACE_BUILDERS = DeferredRegister
            .create(ForgeRegistries.SURFACE_BUILDERS, mcmod.MOD_ID);

    public static final RegistryObject<GhostSurfaceBuilder> GHOST_SURFACE_BUILDER = SURFACE_BUILDERS.register("ghost",
            () -> new GhostSurfaceBuilder(SurfaceBuilderConfig.CODEC));
}
