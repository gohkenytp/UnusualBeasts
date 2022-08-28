package com.gohkenytp.unusualbeasts.registry;

import com.gohkenytp.unusualbeasts.UnusualBeasts;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, UnusualBeasts.MODID);
    public static final RegistryObject<Item> MOSSLING_SPAWN_EGG = ITEMS.register("mossling_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntities.MOSSLING, -10040320, -256, new Item.Properties().tab(CreativeModeTab.TAB_MISC)));
    public static final RegistryObject<Item> LEMMING_SPAWN_EGG = ITEMS.register("lemming_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntities.LEMMING, -10066330, -1, new Item.Properties().tab(CreativeModeTab.TAB_MISC)));
    public static final RegistryObject<Item> TIBETAN_FOX_SPAWN_EGG = ITEMS.register("tibetan_fox_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntities.TIBETAN_FOX, -3355648, -26317, new Item.Properties().tab(CreativeModeTab.TAB_MISC)));
}
