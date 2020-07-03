package cofh.core.key;

import cofh.core.network.packet.server.ModeChangePacket;
import net.minecraft.client.settings.KeyBinding;

public class KeyBindingModeChange extends KeyBinding {

    public KeyBindingModeChange(String description, int keyCode, String category) {

        super(description, keyCode, category);
    }

    public static class Increment extends KeyBindingModeChange {

        public Increment(String description, int keyCode, String category) {

            super(description, keyCode, category);
        }

        @Override
        public void setPressed(boolean valueIn) {

            super.setPressed(valueIn);

            if (isPressed()) {
                ModeChangePacket.incrMode();
            }
        }

    }

    public static class Decrement extends KeyBindingModeChange {

        public Decrement(String description, int keyCode, String category) {

            super(description, keyCode, category);
        }

        @Override
        public void setPressed(boolean valueIn) {

            super.setPressed(valueIn);

            if (isPressed()) {
                ModeChangePacket.decrMode();
            }
        }

    }

}
