package safro.fabric.enchantments.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import safro.fabric.enchantments.config.EnchantmentConfigs;

public class GlimmeringEnchantment extends Enchantment {
    public GlimmeringEnchantment() {
        super(Rarity.UNCOMMON, EnchantmentTarget.ARMOR_FEET, new EquipmentSlot[] {EquipmentSlot.FEET});

        if (EnchantmentConfigs.getValue("glimmering")) {
            Registry.register(Registry.ENCHANTMENT, new Identifier("fabricenchantments", "glimmering"), this);
        }
    }

    @Override
    public int getMinPower(int level) { return 5; }

    @Override
    public int getMaxLevel() {
        return 1;
    }

    @Override
    public boolean canAccept (Enchantment other) {
        return super.canAccept(other) && other != Enchantments.FROST_WALKER;
    }
}
