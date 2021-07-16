package safro.fabric.enchantments.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import safro.fabric.enchantments.FabricEnchantments;
import safro.fabric.enchantments.config.EnchantmentConfigs;

public class BeheadingEnchantment extends Enchantment {
    public BeheadingEnchantment() {
        super(Rarity.VERY_RARE, EnchantmentTarget.WEAPON, new EquipmentSlot[] {EquipmentSlot.MAINHAND});

        if (EnchantmentConfigs.getValue("beheading")) {
            Registry.register(Registry.ENCHANTMENT, new Identifier("fabricenchantments", "beheading"), this);
        }
    }

    @Override
    public int getMinPower(int level) {
        return 20;
    }

    @Override
    public int getMaxLevel() {
        return 1;
    }

    @Override
    public boolean canAccept(Enchantment other) {
        return super.canAccept(other) && other != Enchantments.SILK_TOUCH;
    }
}
