package safro.fabric.enchantments.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import net.minecraft.registry.Registry;
import safro.fabric.enchantments.config.FabricEnchantmentsConfig;

public class ChargedBoltEnchantment extends Enchantment {

    public ChargedBoltEnchantment() {
        super(Rarity.UNCOMMON, EnchantmentTarget.CROSSBOW, new EquipmentSlot[]{EquipmentSlot.MAINHAND, EquipmentSlot.OFFHAND});

        if (FabricEnchantmentsConfig.getBooleanValue("charged_bolt")) {
            Registry.register(Registries.ENCHANTMENT, new Identifier("fabricenchantments", "charged_bolt"), this);
        }
    }

    @Override
    public int getMinPower(int level) { return 20 + level; }

    @Override
    public int getMaxLevel() { return 1; }

    @Override
    public boolean canAccept(Enchantment other) {
        return super.canAccept(other) && other != Enchantments.QUICK_CHARGE && other != Enchantments.MULTISHOT;
    }
}
