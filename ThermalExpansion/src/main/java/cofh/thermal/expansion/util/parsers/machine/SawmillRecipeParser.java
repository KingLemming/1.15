package cofh.thermal.expansion.util.parsers.machine;

import cofh.thermal.core.util.parsers.AbstractContentParser;
import cofh.thermal.expansion.util.managers.machine.SawmillRecipeManager;
import com.google.gson.JsonObject;
import it.unimi.dsi.fastutil.objects.ObjectOpenHashSet;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.Set;

public class SawmillRecipeParser extends AbstractContentParser {

	private static final SawmillRecipeParser INSTANCE = new SawmillRecipeParser();

	public static SawmillRecipeParser instance() {

		return INSTANCE;
	}

	private SawmillRecipeParser() {

	}

	@Override
	protected void parseObject(JsonObject object) {

		ItemStack input;
		ArrayList<ItemStack> output = new ArrayList<>();
		ArrayList<Float> chance = new ArrayList<>();
		int energy = SawmillRecipeManager.instance().getDefaultEnergy();

		/* INPUT */
		input = parseItemStack(object.get(INPUT));

		/* REMOVAL */
		if (object.has(REMOVE) && object.get(REMOVE).getAsBoolean()) {
			removeQueue.add(input);
			return;
		}

		/* OUTPUT */
		parseItemStacks(output, chance, object.get(OUTPUT));

		/* ENERGY */
		if (object.has(ENERGY)) {
			energy = object.get(ENERGY).getAsInt();
		}
		if (object.has(ENERGY_MOD)) {
			energy *= object.get(ENERGY_MOD).getAsFloat();
		}

		SawmillRecipeManager.instance().addRecipe(energy, input, output, chance);
	}

	@Override
	public void postProcess() {

		for (ItemStack stack : removeQueue) {
			SawmillRecipeManager.instance().removeRecipe(stack);
		}
	}

	Set<ItemStack> removeQueue = new ObjectOpenHashSet<>();

}
