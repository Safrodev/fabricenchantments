package safro.fabric.enchantments.mixin.enchantment;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import safro.fabric.enchantments.FabricEnchantments;
import safro.fabric.enchantments.config.FabricEnchantmentsConfig;
import safro.fabric.enchantments.util.FEUtil;

@Mixin(LivingEntity.class)
public abstract class ExperienceMixin extends Entity {

    @Shadow protected abstract int getXpToDrop(PlayerEntity player);

    protected ExperienceMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(at = @At("HEAD"), method = "onDeath")
    private void experienceKill(DamageSource source, CallbackInfo ci) {
        LivingEntity e = (LivingEntity) (Object) this;
        if (source.getAttacker() instanceof PlayerEntity player && this.getXpToDrop(player) > 0) {
            if (FEUtil.hasEnchantment(player, FabricEnchantments.EXPERIENCE)) {
                int level = FEUtil.getLevel(player, FabricEnchantments.EXPERIENCE);
                player.addExperience(level * FabricEnchantmentsConfig.getIntValue("experience_base_amount"));
            }
        }
    }
}
