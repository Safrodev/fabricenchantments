package safro.fabric.enchantments.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import net.minecraft.registry.Registry;
import safro.fabric.enchantments.config.FabricEnchantmentsConfig;

public class NocturnalEnchantment extends Enchantment {
    public NocturnalEnchantment() {
        super(Enchantment.Rarity.VERY_RARE, EnchantmentTarget.ARMOR_HEAD, new EquipmentSlot[] {EquipmentSlot.HEAD});

        if (FabricEnchantmentsConfig.getBooleanValue("nocturnal")) {
            Registry.register(Registries.ENCHANTMENT, new Identifier("fabricenchantments", "nocturnal"), this);
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
            ((LivingEntity) user).addStatusEffect(new StatusEffectInstance(StatusEffects.INVISIBILITY,FabricEnchantmentsConfig.getIntValue("nocturnal_duration"), 0, true, false));
        }
    }
}
