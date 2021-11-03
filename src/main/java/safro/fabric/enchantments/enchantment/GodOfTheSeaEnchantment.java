package safro.fabric.enchantments.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import safro.fabric.enchantments.config.EnchantmentConfigs;

public class GodOfTheSeaEnchantment extends Enchantment {

    public GodOfTheSeaEnchantment() {
        super(Rarity.VERY_RARE, EnchantmentTarget.TRIDENT, new EquipmentSlot[] {EquipmentSlot.MAINHAND});

        if (EnchantmentConfigs.getValue("god_of_the_sea")) {
            Registry.register(Registry.ENCHANTMENT, new Identifier("fabricenchantments", "god_of_the_sea"), this);
        }
    }

    @Override
    public int getMinPower(int level) { return 10; }

    @Override
    public boolean isTreasure() { return true; }

    @Override
    public int getMaxLevel() {
        return 1;
    }
}
