package net.tj.mcmod.items;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class ModItemGroup {

    // Hier word de tab gemaakt voor de mod. Zodat alles overzichtelijk word en je het makkelijk kan vinden.
    public static final ItemGroup MC_GROUP = new ItemGroup("McModTab")
    {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModItems.BANANA.get());
        }
    };

}