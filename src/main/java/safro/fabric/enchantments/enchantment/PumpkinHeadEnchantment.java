package safro.fabric.enchantments.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import safro.fabric.enchantments.FabricEnchantments;
import safro.fabric.enchantments.config.FabricEnchantmentsConfig;

public class PumpkinHeadEnchantment extends Enchantment {
    public PumpkinHeadEnchantment() {
        super(Rarity.UNCOMMON, EnchantmentTarget.ARMOR_HEAD, new EquipmentSlot[] {EquipmentSlot.HEAD});

        if (FabricEnchantmentsConfig.getBooleanValue("pumpkin_head")) {
            Registry.register(Registry.ENCHANTMENT, new Identifier("fabricenchantments", "pumpkin_head"), this);
        }
    }

    @Override
    public int getMinPower(int level) { return 10; }

    @Override
    public int getMaxLevel() {
        return 1;
    }

    @Override
    public boolean canAccept (Enchantment other){
        return super.canAccept(other) && other != FabricEnchantments.NOCTURNAL && other != FabricEnchantments.ENDER_MIND;
    }
}
