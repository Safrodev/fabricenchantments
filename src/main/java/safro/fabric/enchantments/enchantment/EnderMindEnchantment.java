package safro.fabric.enchantments.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.FoxEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.MathHelper;
import safro.fabric.enchantments.FabricEnchantments;

public class EnderMindEnchantment extends Enchantment {
    public EnderMindEnchantment() {
        super(Rarity.VERY_RARE, EnchantmentTarget.ARMOR_HEAD, new EquipmentSlot[] {EquipmentSlot.HEAD});
    }

    @Override
    public int getMinPower(int level) { return 20; }

    @Override
    public int getMaxLevel() {
        return 1;
    }

    @Override
    public void onUserDamaged(LivingEntity user, Entity attacker, int level) {
        if (!user.getEntityWorld().isClient) {
            if (user.getHealth() <= 8.0F) {
                double d = user.getX();
                double e = user.getY();
                double f = user.getZ();

                for (int i = 0; i < 16; ++i) {
                    double g = user.getX() + (user.getRandom().nextDouble() - 0.5D) * 16.0D;
                    double h = MathHelper.clamp(user.getY() + (double) (user.getRandom().nextInt(16) - 8), (double) user.getEntityWorld().getBottomY(), (double) (user.getEntityWorld().getBottomY() + ((ServerWorld) user.getEntityWorld()).getLogicalHeight() - 1));
                    double j = user.getZ() + (user.getRandom().nextDouble() - 0.5D) * 16.0D;
                    if (user.hasVehicle()) {
                        user.stopRiding();
                    }

                    if (user.teleport(g, h, j, true)) {
                        SoundEvent soundEvent = user instanceof FoxEntity ? SoundEvents.ENTITY_FOX_TELEPORT : SoundEvents.ENTITY_ENDERMAN_TELEPORT;
                        user.getEntityWorld().playSound((PlayerEntity) null, d, e, f, soundEvent, SoundCategory.PLAYERS, 1.0F, 1.0F);
                        user.playSound(soundEvent, 1.0F, 1.0F);
                        break;
                    }
                }
            }
        }

    }
    @Override
    public boolean canAccept (Enchantment other){
        return super.canAccept(other) && other != FabricEnchantments.NOCTURNAL_ENCHANTMENT;
    }
}
