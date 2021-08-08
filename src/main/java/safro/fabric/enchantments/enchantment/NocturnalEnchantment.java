package safro.fabric.enchantments.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import safro.fabric.enchantments.config.EnchantmentConfigs;

public class NocturnalEnchantment extends Enchantment {
    public NocturnalEnchantment() {
        super(Rarity.VERY_RARE, EnchantmentTarget.ARMOR_HEAD, new EquipmentSlot[] {EquipmentSlot.HEAD});

        if (EnchantmentConfigs.getValue("nocturnal")) {
            Registry.register(Registry.ENCHANTMENT, new Identifier("fabricenchantments", "nocturnal"), this);
        }
    }

    @Override
    public int getMinPower(int level) { return 20; }

    @Override
    public int getMaxLevel() {
        return 1;
    }

    @Override
    public void onUserDamaged(LivingEntity user, Entity attacker, int level) {
        if(!user.getEntityWorld().isDay()) {
            ((LivingEntity) user).addStatusEffect(new StatusEffectInstance(StatusEffects.INVISIBILITY,60, 0, true, false));
        }
    }
}
