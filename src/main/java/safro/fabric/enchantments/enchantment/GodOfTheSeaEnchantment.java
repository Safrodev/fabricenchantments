package safro.fabric.enchantments.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import net.minecraft.registry.Registry;
import safro.fabric.enchantments.config.FabricEnchantmentsConfig;

public class GodOfTheSeaEnchantment extends Enchantment {

    public GodOfTheSeaEnchantment() {
        super(Rarity.VERY_RARE, EnchantmentTarget.TRIDENT, new EquipmentSlot[]{EquipmentSlot.MAINHAND, EquipmentSlot.OFFHAND});

        if (FabricEnchantmentsConfig.getBooleanValue("god_of_the_sea")) {
            Registry.register(Registries.ENCHANTMENT, new Identifier("fabricenchantments", "god_of_the_sea"), this);
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
