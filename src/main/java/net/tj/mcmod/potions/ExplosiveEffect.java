package net.tj.mcmod.potions;

import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;
import net.minecraft.world.Explosion;

public class ExplosiveEffect extends Effect {
    // Hier word het explsief effect aangemaakt voor de potions
    public ExplosiveEffect() {
        super(EffectType.HARMFUL, 7033103);
    }

    // Hier word de explosie gemaakt met de amplifier(Hier mee kan je de grote van de explosie veranderen)
    @Override
    public void applyEffectTick(LivingEntity entity, int amplifier) {
        entity.getCommandSenderWorld().explode(
                null, entity.getX(), entity.getY(), entity.getZ(), amplifier+1, Explosion.Mode.NONE);
        super.applyEffectTick(entity, amplifier);
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return duration % 100 ==0;
    }
}
