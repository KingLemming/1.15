package cofh.lib.key;

import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SortedKeyBinding extends KeyBinding {

    private final int priority;

    public SortedKeyBinding(String description, int keyCode, String category, int priority) {

        super(description, keyCode, category);
        this.priority = priority;
    }

    @Override
    public int compareTo(KeyBinding keyBinding) {

        if (this.getKeyCategory().equals(keyBinding.getKeyCategory()) && keyBinding instanceof SortedKeyBinding)
            return Integer.compare(priority, ((SortedKeyBinding) keyBinding).priority);
        return super.compareTo(keyBinding);
    }

}
