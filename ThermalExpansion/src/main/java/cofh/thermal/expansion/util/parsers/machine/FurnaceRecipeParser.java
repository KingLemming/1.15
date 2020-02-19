package cofh.thermal.expansion.util.parsers.machine;

import cofh.thermal.core.util.parsers.AbstractContentParser;
import cofh.thermal.expansion.util.managers.machine.FurnaceRecipeManager;
import com.google.gson.JsonObject;
import it.unimi.dsi.fastutil.objects.ObjectOpenHashSet;
import net.minecraft.item.ItemStack;

import java.util.Set;

public class FurnaceRecipeParser extends AbstractContentParser {

	private static final FurnaceRecipeParser INSTANCE = new FurnaceRecipeParser();

	public static FurnaceRecipeParser instance() {

		return INSTANCE;
	}

	private FurnaceRecipeParser() {

	}

	@Override
	protected void parseObject(JsonObject object) {

		ItemStack input;
		ItemStack output;
		int energy = FurnaceRecipeManager.instance().getDefaultEnergy();

		/* INPUT */
		input = parseItemStack(object.get(INPUT));

		/* REMOVAL */
		if (object.has(REMOVE) && object.get(REMOVE).getAsBoolean()) {
			removeQueue.add(input);
			return;
		}

		/* OUTPUT */
		output = parseItemStack(object.get(OUTPUT));

		/* ENERGY */
		if (object.has(ENERGY)) {
			energy = object.get(ENERGY).getAsInt();
		}
		if (object.has(ENERGY_MOD)) {
			energy *= object.get(ENERGY_MOD).getAsFloat();
		}

		FurnaceRecipeManager.instance().addRecipe(energy, input, output);
	}

	@Override
	public void postProcess() {

		for (ItemStack stack : removeQueue) {
			FurnaceRecipeManager.instance().removeRecipe(stack);
		}
	}

	Set<ItemStack> removeQueue = new ObjectOpenHashSet<>();

}
