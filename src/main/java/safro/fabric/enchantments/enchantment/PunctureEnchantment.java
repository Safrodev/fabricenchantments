package safro.fabric.enchantments.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import safro.fabric.enchantments.config.EnchantmentConfigs;

public class PunctureEnchantment extends Enchantment {

    public PunctureEnchantment() {
        super(Rarity.RARE, EnchantmentTarget.CROSSBOW, new EquipmentSlot[] {EquipmentSlot.MAINHAND});

        if (EnchantmentConfigs.getValue("puncture")) {
            Registry.register(Registry.ENCHANTMENT, new Identifier("fabricenchantments", "puncture"), this);
        }
    }

    @Override
    public int getMinPower(int level) { return 20 + level; }

    @Override
    public int getMaxLevel() { return 3; }

    @Override
    public boolean canAccept(Enchantment other) {
        return super.canAccept(other) && other != Enchantments.QUICK_CHARGE;
    }
}
