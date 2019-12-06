package cofh.thermal.cultivation;

import cofh.lib.registries.DeferredRegisterCoFH;
import cofh.thermal.cultivation.init.TCulBlocks;
import cofh.thermal.cultivation.init.TCulItems;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;

import static cofh.lib.util.constants.Constants.ID_THERMAL;
import static cofh.lib.util.constants.Constants.ID_THERMAL_CULTIVATION;

@Mod(ID_THERMAL_CULTIVATION)
public class ThermalCultivation {

    public static final DeferredRegisterCoFH<Block> BLOCKS = new DeferredRegisterCoFH<>(ForgeRegistries.BLOCKS, ID_THERMAL);
    public static final DeferredRegisterCoFH<Item> ITEMS = new DeferredRegisterCoFH<>(ForgeRegistries.ITEMS, ID_THERMAL);

    public ThermalCultivation() {

        final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        BLOCKS.register(modEventBus);
        ITEMS.register(modEventBus);

        TCulBlocks.register();
        TCulItems.register();
    }

}
