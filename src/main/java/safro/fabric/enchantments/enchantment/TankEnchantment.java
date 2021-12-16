package safro.fabric.enchantments.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import safro.fabric.enchantments.config.FabricEnchantmentsConfig;

public class TankEnchantment extends Enchantment {

    public TankEnchantment() {
        super(Rarity.UNCOMMON, EnchantmentTarget.ARMOR_CHEST, new EquipmentSlot[]{EquipmentSlot.CHEST});

        if (FabricEnchantmentsConfig.getBooleanValue("tank")) {
            Registry.register(Registry.ENCHANTMENT, new Identifier("fabricenchantments", "tank"), this);
        }
    }

    @Override
    public int getMinPower(int level) { return (level - 1) * 11; }

    @Override
    public int getMaxLevel() {
        return 5;
    }

    @Override
    public int getProtectionAmount(int level, DamageSource source) {
        if (source.bypassesArmor() || source.isUnblockable()) {
            return level;
        } else
            return 0;
    }
}
