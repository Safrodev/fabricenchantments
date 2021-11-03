package safro.fabric.enchantments.mixin.enchantment;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.Angerable;
import net.minecraft.entity.mob.EndermanEntity;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;
import safro.fabric.enchantments.FabricEnchantments;


@Mixin(EndermanEntity.class)
public abstract class PumpkinHeadMixin extends HostileEntity implements Angerable {

    protected PumpkinHeadMixin(EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;isOf(Lnet/minecraft/item/Item;)Z", ordinal = 0),
            method = "isPlayerStaring(Lnet/minecraft/entity/player/PlayerEntity;)Z",
            locals = LocalCapture.CAPTURE_FAILHARD,
            cancellable = true)
    private void doesPlayerHavePumpkinHead(PlayerEntity player, CallbackInfoReturnable<Boolean> cir, @NotNull ItemStack stack) {
        if (EnchantmentHelper.getLevel(FabricEnchantments.PUMPKIN_HEAD, stack) >= 1) {
            cir.setReturnValue(false);
        }
    }
}
