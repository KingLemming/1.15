package cofh.omgourd;

import cofh.core.init.CoreItems;
import cofh.lib.block.deco.CarvedPumpkinBlockCoFH;
import cofh.lib.registries.DeferredRegisterCoFH;
import cofh.omgourd.data.ModItemModels;
import cofh.omgourd.data.ModLootTables;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.data.DataGenerator;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static cofh.lib.util.constants.Constants.ID_OMGOURD;

@Mod(ID_OMGOURD)
public class OMGourd {

    public static final Logger LOG = LogManager.getLogger(ID_OMGOURD);

    public static final DeferredRegisterCoFH<Block> BLOCKS = new DeferredRegisterCoFH<>(ForgeRegistries.BLOCKS, ID_OMGOURD);
    public static final DeferredRegisterCoFH<Item> ITEMS = new DeferredRegisterCoFH<>(ForgeRegistries.ITEMS, ID_OMGOURD);

    public OMGourd() {

        final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(this::clientSetup);
        modEventBus.addListener(this::gatherData);

        BLOCKS.register(modEventBus);
        ITEMS.register(modEventBus);

        registerBlocks();

        CoreItems.registerShearsOverride();
    }

    public static final ItemGroup ITEM_GROUP = new ItemGroup(-1, ID_OMGOURD) {

        @Override
        @OnlyIn(Dist.CLIENT)
        public ItemStack createIcon() {

            return new ItemStack(ITEMS.get("carved_pumpkin_1"));
        }
    };

    // region INITIALIZATION
    private void commonSetup(final FMLCommonSetupEvent event) {

        CarvedPumpkinBlockCoFH pumpkin;
        CarvedPumpkinBlockCoFH lantern;

        // NEXT
        for (int i = 2; i <= 23; ++i) {
            pumpkin = (CarvedPumpkinBlockCoFH) BLOCKS.get("carved_pumpkin_" + i);
            pumpkin.setCarvePrev(BLOCKS.getSup("carved_pumpkin_" + (i - 1)));
            pumpkin.setCarveNext(BLOCKS.getSup("carved_pumpkin_" + (i + 1)));

            lantern = (CarvedPumpkinBlockCoFH) BLOCKS.get("jack_o_lantern_" + i);
            lantern.setCarvePrev(BLOCKS.getSup("jack_o_lantern_" + (i - 1)));
            lantern.setCarveNext(BLOCKS.getSup("jack_o_lantern_" + (i + 1)));
        }
        // ENDS
        pumpkin = (CarvedPumpkinBlockCoFH) BLOCKS.get("carved_pumpkin_" + 1);
        pumpkin.setCarvePrev(BLOCKS.getSup("minecraft:carved_pumpkin"));
        pumpkin.setCarveNext(BLOCKS.getSup("carved_pumpkin_" + 2));

        lantern = (CarvedPumpkinBlockCoFH) BLOCKS.get("jack_o_lantern_" + 1);
        lantern.setCarvePrev(BLOCKS.getSup("minecraft:jack_o_lantern"));
        lantern.setCarveNext(BLOCKS.getSup("jack_o_lantern_" + 2));

        pumpkin = (CarvedPumpkinBlockCoFH) BLOCKS.get("carved_pumpkin_" + 24);
        pumpkin.setCarvePrev(BLOCKS.getSup("carved_pumpkin_" + 23));
        pumpkin.setCarveNext(BLOCKS.getSup("minecraft:carved_pumpkin"));

        lantern = (CarvedPumpkinBlockCoFH) BLOCKS.get("jack_o_lantern_" + 24);
        lantern.setCarvePrev(BLOCKS.getSup("jack_o_lantern_" + 23));
        lantern.setCarveNext(BLOCKS.getSup("minecraft:jack_o_lantern"));

        // VANILLA
        pumpkin = (CarvedPumpkinBlockCoFH) BLOCKS.get("minecraft:carved_pumpkin");
        pumpkin.setCarvePrev(BLOCKS.getSup("carved_pumpkin_" + 24));
        pumpkin.setCarveNext(BLOCKS.getSup("carved_pumpkin_" + 1));

        lantern = (CarvedPumpkinBlockCoFH) BLOCKS.get("minecraft:jack_o_lantern");
        lantern.setCarvePrev(BLOCKS.getSup("jack_o_lantern_" + 24));
        lantern.setCarveNext(BLOCKS.getSup("jack_o_lantern_" + 1));
    }

    private void clientSetup(final FMLClientSetupEvent event) {

    }
    // endregion

    // region REGISTRATION
    private static void registerBlocks() {

        for (int i = 1; i <= 24; ++i) {
            BLOCKS.register("carved_pumpkin_" + i, () -> new CarvedPumpkinBlockCoFH(Block.Properties.create(Material.GOURD, MaterialColor.ADOBE).hardnessAndResistance(1.0F).sound(SoundType.WOOD)));
            BLOCKS.register("jack_o_lantern_" + i, () -> new CarvedPumpkinBlockCoFH(Block.Properties.create(Material.GOURD, MaterialColor.ADOBE).hardnessAndResistance(1.0F).sound(SoundType.WOOD).lightValue(15)));

            int j = i;
            ITEMS.register("carved_pumpkin_" + j, () -> new BlockItem(BLOCKS.get("carved_pumpkin_" + j), new Item.Properties().group(ITEM_GROUP)));
            ITEMS.register("jack_o_lantern_" + j, () -> new BlockItem(BLOCKS.get("jack_o_lantern_" + j), new Item.Properties().group(ITEM_GROUP)));
        }
        BLOCKS.register("minecraft:carved_pumpkin", () -> new CarvedPumpkinBlockCoFH(Block.Properties.create(Material.GOURD, MaterialColor.ADOBE).hardnessAndResistance(1.0F).sound(SoundType.WOOD)));
        BLOCKS.register("minecraft:jack_o_lantern", () -> new CarvedPumpkinBlockCoFH(Block.Properties.create(Material.GOURD, MaterialColor.ADOBE).hardnessAndResistance(1.0F).sound(SoundType.WOOD).lightValue(15)));

        ITEMS.register("minecraft:carved_pumpkin", () -> new BlockItem(BLOCKS.get("minecraft:carved_pumpkin"), new Item.Properties().group(ItemGroup.BUILDING_BLOCKS)));
        ITEMS.register("minecraft:jack_o_lantern", () -> new BlockItem(BLOCKS.get("minecraft:jack_o_lantern"), new Item.Properties().group(ItemGroup.BUILDING_BLOCKS)));
    }
    // endregion

    // region DATA
    private void gatherData(final GatherDataEvent event) {

        if (event.includeServer()) {
            registerServerProviders(event.getGenerator());
        }
        if (event.includeClient()) {
            registerClientProviders(event.getGenerator(), event);
        }
    }

    private void registerServerProviders(DataGenerator generator) {

        generator.addProvider(new ModLootTables(generator));
    }

    private void registerClientProviders(DataGenerator generator, GatherDataEvent event) {

        generator.addProvider(new ModItemModels(generator, event.getExistingFileHelper()));
    }
    // endregion
}
