package net.tj.mcmod.potions;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.potion.*;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;
import net.minecraftforge.common.brewing.IBrewingRecipe;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.tj.mcmod.mcmod;

public class ModPotions {

    // Hier worden de potions geregistreerd: In dit geval de explosive potion

    public static final DeferredRegister<Effect> EFFECTS
            = DeferredRegister.create(ForgeRegistries.POTIONS, mcmod.MOD_ID);

    public static final DeferredRegister<Potion> POTIONS
            = DeferredRegister.create(ForgeRegistries.POTION_TYPES, mcmod.MOD_ID);

    public static final RegistryObject<Effect> EXPLOSIVE_EFFECT = EFFECTS.register("explosive", ExplosiveEffect::new);

    public static final RegistryObject<Potion> EXPLOSIVE_POTION =  POTIONS.register("explosive",
            () -> new Potion(new EffectInstance(EXPLOSIVE_EFFECT.get(), 1200, 2)));

    // Hier word het recept gemaakt voor de explosive potion
    public static void AddPotionRecipes(){
        BrewingRecipeRegistry.addRecipe(new BrewingRecipe(Potions.AWKWARD, Items.TNT, EXPLOSIVE_POTION.get()));
    }

    private static class BrewingRecipe implements IBrewingRecipe {
        private final Potion bottleInput;
        private final Item itemInput;
        private final ItemStack output;

        public BrewingRecipe(Potion bottleInputIn, Item itemInputIn, Potion outputIn) {
            this.bottleInput = bottleInputIn;
            this.itemInput = itemInputIn;
            this.output = PotionUtils.setPotion(new ItemStack(Items.POTION), outputIn);
        }

        @Override
        public boolean isInput(ItemStack input) {
            return PotionUtils.getPotion(input).equals(this.bottleInput);
        }

        @Override
        public boolean isIngredient(ItemStack ingredient) {
            return ingredient.getItem().equals(this.itemInput);
        }

        @Override
        public ItemStack getOutput(ItemStack input, ItemStack ingredient) {
            if (isInput(input) && isIngredient(ingredient)) {
                return this.output.copy();
            } else {
                return ItemStack.EMPTY;
            }
        }
    }

}
