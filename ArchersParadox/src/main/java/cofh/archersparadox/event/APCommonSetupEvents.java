package cofh.archersparadox.event;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.entity.merchant.villager.VillagerProfession;
import net.minecraft.entity.merchant.villager.VillagerTrades;
import net.minecraft.item.Items;
import net.minecraftforge.common.BasicTrade;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;

import static cofh.archersparadox.ArchersParadox.ITEMS;
import static cofh.archersparadox.init.APIDs.*;
import static cofh.lib.util.constants.Constants.ID_ARCHERS_PARADOX;
import static cofh.lib.util.helpers.ItemHelper.cloneStack;

@Mod.EventBusSubscriber(modid = ID_ARCHERS_PARADOX)
public class APCommonSetupEvents {

    private APCommonSetupEvents() {

    }

    @SubscribeEvent
    public static void setupVillagerTrades(final VillagerTradesEvent event) {

        if (event.getType() == VillagerProfession.FLETCHER) {
            Int2ObjectMap<List<VillagerTrades.ITrade>> trades = event.getTrades();

            List<VillagerTrades.ITrade> noviceTrades = trades.get(1);
            noviceTrades.add(new BasicTrade(cloneStack(Items.EMERALD, 1), cloneStack(Items.ARROW, 8), cloneStack(ITEMS.get(ID_TRAINING_ARROW), 8), 16, 1, 0.05F));

            List<VillagerTrades.ITrade> apprenticeTrades = trades.get(2);
            apprenticeTrades.add(new BasicTrade(cloneStack(Items.EMERALD, 1), cloneStack(Items.ARROW, 4), cloneStack(ITEMS.get(ID_PRISMARINE_ARROW), 4), 12, 5, 0.05F));
            apprenticeTrades.add(new BasicTrade(cloneStack(Items.EMERALD, 1), cloneStack(Items.ARROW, 4), cloneStack(ITEMS.get(ID_QUARTZ_ARROW), 4), 12, 5, 0.05F));
            apprenticeTrades.add(new BasicTrade(cloneStack(Items.EMERALD, 1), cloneStack(Items.ARROW, 4), cloneStack(ITEMS.get(ID_SLIME_ARROW), 4), 12, 5, 0.05F));

            List<VillagerTrades.ITrade> journeymanTrades = trades.get(3);
            journeymanTrades.add(new BasicTrade(cloneStack(Items.EMERALD, 3), cloneStack(Items.ARROW, 4), cloneStack(ITEMS.get(ID_BLAZE_ARROW), 4), 12, 10, 0.05F));
            journeymanTrades.add(new BasicTrade(cloneStack(Items.EMERALD, 3), cloneStack(Items.ARROW, 4), cloneStack(ITEMS.get(ID_FROST_ARROW), 4), 12, 10, 0.05F));
            journeymanTrades.add(new BasicTrade(cloneStack(Items.EMERALD, 3), cloneStack(Items.ARROW, 4), cloneStack(ITEMS.get(ID_LIGHTNING_ARROW), 4), 12, 10, 0.05F));

            List<VillagerTrades.ITrade> expertTrades = trades.get(4);
            expertTrades.add(new BasicTrade(cloneStack(Items.EMERALD, 4), cloneStack(Items.ARROW, 4), cloneStack(ITEMS.get(ID_CHALLENGE_ARROW), 4), 8, 15, 0.05F));

            List<VillagerTrades.ITrade> masterTrades = trades.get(5);
            masterTrades.add(new BasicTrade(cloneStack(Items.EMERALD, 4), cloneStack(Items.ARROW, 2), cloneStack(ITEMS.get(ID_PHANTASMAL_ARROW), 2), 8, 20, 0.05F));
        }
    }

}
