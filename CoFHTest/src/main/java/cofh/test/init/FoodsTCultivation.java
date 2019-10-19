package cofh.test.init;

import net.minecraft.item.Food;

public class FoodsTCultivation {

    private FoodsTCultivation() {

    }

    public static final Food CORN = new Food.Builder().hunger(3).saturation(0.6F).build();
    public static final Food ONION = new Food.Builder().hunger(1).saturation(0.3F).build();
    public static final Food SPINACH = new Food.Builder().hunger(1).saturation(0.3F).build();

    public static final Food BELL_PEPPER = new Food.Builder().hunger(1).saturation(0.3F).build();
    public static final Food GREEN_BEAN = new Food.Builder().hunger(1).saturation(0.3F).build();
    public static final Food PEANUT = new Food.Builder().hunger(2).saturation(0.3F).build();
    public static final Food STRAWBERRY = new Food.Builder().hunger(2).saturation(0.1F).build();
    public static final Food TOMATO = new Food.Builder().hunger(2).saturation(0.3F).build();

    public static final Food COFFEE_CHERRY = new Food.Builder().hunger(1).saturation(0.1F).build();

}
