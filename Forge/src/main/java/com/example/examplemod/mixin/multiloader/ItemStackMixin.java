package com.example.examplemod.mixin.multiloader;

import com.example.examplemod.access.ItemStackLevelAccess;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Implements;
import org.spongepowered.asm.mixin.Interface;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Replace all "yourmodid" in this class to your modid.
 */
@Mixin(ItemStack.class)
@Implements(@Interface(iface = ItemStackLevelAccess.class, prefix = "yourmodid$"))
public class ItemStackMixin {
    public Level yourmodid$level;

    @Inject(method = "inventoryTick", at = @At("HEAD"))
    private void originsmultiloader$getLevelFrOmInventory(Level level, Entity entity, int slot, boolean selected, CallbackInfo ci) {
        yourmodid$level = level;
    }
    
    public Level yourmodid$getLevel() {
        return yourmodid$level;
    }
    
    public void yourmodid$setLevel(Level value) {
        yourmodid$level = value;
    }
    
}
