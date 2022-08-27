package com.example.examplemod.platform;

import com.example.examplemod.data.MultiloaderDataTypes;
import com.example.examplemod.platform.services.IPlatformHelper;
import com.example.examplemod.power.data.IPowerData;
import com.example.examplemod.registry.PowerFactoriesForge;
import com.google.auto.service.AutoService;
import io.github.apace100.apoli.power.Power;
import io.github.apace100.apoli.power.PowerType;
import io.github.apace100.apoli.power.factory.PowerFactory;
import io.github.apace100.calio.data.SerializableDataType;
import io.github.edwinmindcraft.apoli.api.component.IPowerContainer;
import io.github.edwinmindcraft.apoli.fabric.FabricPowerConfiguration;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.loading.FMLLoader;

import java.util.List;

@AutoService(IPlatformHelper.class)
public class ForgePlatformHelper implements IPlatformHelper {

    @Override
    public String getPlatformName() {

        return "Forge";
    }

    @Override
    public boolean isModLoaded(String modId) {

        return ModList.get().isLoaded(modId);
    }

    @Override
    public boolean isDevelopmentEnvironment() {

        return !FMLLoader.isProduction();
    }

    @Override
    public <P extends Power> PowerFactory<P> registerPowerFactory(ResourceLocation id, IPowerData power) {
        PowerFactory<P> powerFactory = new PowerFactory<>(id, power.getSerializableData(), power.getPowerConstructorForge());
        PowerFactoriesForge.POWER_FACTORY_REGISTRY.register(id.getPath(), powerFactory::getWrapped);
        return powerFactory;
    }

    @Override
    public <P extends Power> List<P> getPowers(LivingEntity entity, Class<P> powerClass, PowerFactory<P> powerFactory) {
        return IPowerContainer.getPowers(entity, powerFactory.getWrapped()).stream().map(configuredPowerHolder -> ((FabricPowerConfiguration<P>)configuredPowerHolder.get().getConfiguration()).power().apply((PowerType<P>) configuredPowerHolder.get().getPowerType(), entity)).toList();
    }

    @Override
    public <P extends Power> boolean hasPower(LivingEntity living, Class<P> powerClass, PowerFactory<P> powerFactory) {
        return IPowerContainer.hasPower(living, powerFactory.getWrapped());
    }

    @Override
    public SerializableDataType<?> getBiEntityConditionDataType() {
        return MultiloaderDataTypes.BIENTITY_CONDITION;
    }

    @Override
    public SerializableDataType<?> getBiomeConditionDataType() {
        return MultiloaderDataTypes.BIOME_CONDITION;
    }

    @Override
    public SerializableDataType<?> getBlockConditionDataType() {
        return MultiloaderDataTypes.BLOCK_CONDITION;
    }

    @Override
    public SerializableDataType<?> getDamageConditionDataType() {
        return MultiloaderDataTypes.DAMAGE_CONDITION;
    }

    @Override
    public SerializableDataType<?> getEntityConditionDataType() {
        return MultiloaderDataTypes.ENTITY_CONDITION;
    }

    @Override
    public SerializableDataType<?> getFluidConditionDataType() {
        return MultiloaderDataTypes.FLUID_CONDITION;
    }

    @Override
    public SerializableDataType<?> getItemConditionDataType() {
        return MultiloaderDataTypes.ITEM_CONDITION;
    }

    @Override
    public SerializableDataType<?> getBiEntityActionDataType() {
        return MultiloaderDataTypes.BIENTITY_ACTION;
    }

    @Override
    public SerializableDataType<?> getBlockActionDataType() {
        return MultiloaderDataTypes.BLOCK_ACTION;
    }

    @Override
    public SerializableDataType<?> getEntityActionDataType() {
        return MultiloaderDataTypes.ENTITY_ACTION;
    }

    @Override
    public SerializableDataType<?> getItemActionDataType() {
        return MultiloaderDataTypes.ITEM_ACTION;
    }
}
