package safro.fabric.enchantments.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import safro.fabric.enchantments.config.EnchantmentConfigs;

public class RiseEnchantment extends Enchantment {
    public RiseEnchantment() {
        super(Rarity.UNCOMMON, EnchantmentTarget.BOW, new EquipmentSlot[] {EquipmentSlot.MAINHAND});

        if (EnchantmentConfigs.getValue("rise")) {
            Registry.register(Registry.ENCHANTMENT, new Identifier("fabricenchantments", "rise"), this);
        }
    }

    @Override
    public int getMinPower(int level) { return 15; }

    @Override
    public int getMaxLevel() {
        return 3;
    }

    @Override
    public boolean canAccept(Enchantment other) {
        return super.canAccept(other) && other != Enchantments.FLAME;
    }

    @Override
    public void onTargetDamaged(LivingEntity user, Entity target, int level) {
        if(target instanceof LivingEntity) {
            ((LivingEntity) target).addStatusEffect(new StatusEffectInstance(StatusEffects.LEVITATION, 20 * level, level - 1, true, false));
        }

        super.onTargetDamaged(user, target, level);
    }

}
