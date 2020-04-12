package cofh.core.client.gui.element;

import cofh.core.client.gui.IGuiAccess;
import cofh.lib.util.helpers.SoundHelper;
import net.minecraft.util.ResourceLocation;

import static cofh.lib.util.constants.Constants.PATH_ELEMENTS;

public abstract class ElementButtonBase extends ElementBase {

    public static final ResourceLocation HOVER = new ResourceLocation(PATH_ELEMENTS + "button_hover.png");
    public static final ResourceLocation ENABLED = new ResourceLocation(PATH_ELEMENTS + "button_enabled.png");
    public static final ResourceLocation DISABLED = new ResourceLocation(PATH_ELEMENTS + "button_disabled.png");

    public ElementButtonBase(IGuiAccess gui, int posX, int posY, int sizeX, int sizeY) {

        super(gui, posX, posY, sizeX, sizeY);
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int mouseButton) {

        playSound(mouseButton);
        switch (mouseButton) {
            case 0:
                leftClick();
                break;
            case 1:
                rightClick();
                break;
            case 2:
                middleClick();
                break;
        }
        return true;
    }

    protected void playSound(int button) {

        if (button == 0) {
            SoundHelper.playClickSound(1.0F);
        }
    }

    protected void leftClick() {

    }

    protected void rightClick() {

    }

    protected void middleClick() {

    }

}
