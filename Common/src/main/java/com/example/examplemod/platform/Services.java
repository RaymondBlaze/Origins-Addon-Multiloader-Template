package com.example.examplemod.platform;

import com.example.examplemod.Constants;
import com.example.examplemod.platform.services.IPlatformHelper;
import com.example.examplemod.power.data.IActionOnEquipPowerData;

import java.util.ServiceLoader;

public class Services {

    public static final IPlatformHelper PLATFORM = load(IPlatformHelper.class);

    public static final IActionOnEquipPowerData ACTION_ON_EQUIP = load(IActionOnEquipPowerData.class);

    public static <T> T load(Class<T> clazz) {

        final T loadedService = ServiceLoader.load(clazz)
                .findFirst()
                .orElseThrow(() -> new NullPointerException("Failed to load service for " + clazz.getName()));
        Constants.LOG.debug("Loaded {} for service {}", loadedService, clazz);
        return loadedService;
    }
}
