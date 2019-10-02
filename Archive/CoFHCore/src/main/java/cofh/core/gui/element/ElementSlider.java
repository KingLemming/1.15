package cofh.core.gui.element;

import cofh.core.gui.GuiColor;
import cofh.core.gui.IGuiAccess;
import cofh.lib.util.helpers.RenderHelper;

import static cofh.core.gui.element.ElementButtonBase.*;

public abstract class ElementSlider extends ElementBase {

    protected int _value;
    protected int _valueMin;
    protected int _valueMax;
    protected int _sliderWidth;
    protected int _sliderHeight;

    protected boolean _isDragging;

    public int borderColor = new GuiColor(120, 120, 120, 255).getColor();
    public int backgroundColor = new GuiColor(0, 0, 0, 255).getColor();

    protected ElementSlider(IGuiAccess containerScreen, int x, int y, int width, int height, int maxValue) {

        this(containerScreen, x, y, width, height, maxValue, 0);
    }

    protected ElementSlider(IGuiAccess containerScreen, int x, int y, int width, int height, int maxValue, int minValue) {

        super(containerScreen, x, y, width, height);
        _valueMax = maxValue;
        _valueMin = minValue;
    }

    public ElementSlider setColor(int backgroundColor, int borderColor) {

        this.borderColor = borderColor;
        this.backgroundColor = backgroundColor;
        return this;
    }

    public ElementSlider setSliderSize(int width, int height) {

        _sliderWidth = width;
        _sliderHeight = height;
        return this;
    }

    public ElementSlider setValue(int value) {

        value = Math.max(_valueMin, Math.min(_valueMax, value));
        if (value != _value) {
            _value = value;
            onValueChanged(_value);
        }
        return this;
    }

    public ElementSlider setLimits(int min, int max) {

        _valueMin = min;
        _valueMax = max;
        setValue(_value);
        return this;
    }

    @Override
    public void drawBackground(int mouseX, int mouseY) {

        drawColoredModalRect(posX - 1, posY - 1, posX + width + 1, posY + height + 1, borderColor);
        drawColoredModalRect(posX, posY, posX + width, posY + height, backgroundColor);
        RenderHelper.resetColor();
    }

    protected void drawSlider(int mouseX, int mouseY, int sliderX, int sliderY) {

        int sliderMidX = _sliderWidth / 2;
        int sliderMidY = _sliderHeight / 2;
        int sliderEndX = _sliderWidth - sliderMidX;
        int sliderEndY = _sliderHeight - sliderMidY;

        if (!enabled()) {
            RenderHelper.bindTexture(DISABLED);
        } else if (isHovering(mouseX, mouseY)) {
            RenderHelper.bindTexture(HOVER);
        } else {
            RenderHelper.bindTexture(ENABLED);
        }
        RenderHelper.resetColor();
        drawTexturedModalRect(sliderX, sliderY, 0, 0, sliderMidX, sliderMidY);
        drawTexturedModalRect(sliderX, sliderY + sliderMidY, 0, 256 - sliderEndY, sliderMidX, sliderEndY);
        drawTexturedModalRect(sliderX + sliderMidX, sliderY, 256 - sliderEndX, 0, sliderEndX, sliderMidY);
        drawTexturedModalRect(sliderX + sliderMidX, sliderY + sliderMidY, 256 - sliderEndX, 256 - sliderEndY, sliderEndX, sliderEndY);
    }

    @Override
    public void drawForeground(int mouseX, int mouseY) {

        int sliderX = posX + getSliderX();
        int sliderY = posY + getSliderY();

        drawSlider(mouseX, mouseY, sliderX, sliderY);
        RenderHelper.resetColor();
    }

    protected boolean isHovering(int x, int y) {

        return intersectsWith(x, y);
    }

    public int getSliderX() {

        return 0;
    }

    public int getSliderY() {

        return 0;
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int mouseButton) {

        _isDragging = mouseButton == 0;
        update((int) mouseX, (int) mouseY);
        return true;
    }

    @Override
    public void mouseReleased(double mouseX, double mouseY) {

        if (_isDragging) {
            onStopDragging();
        }
        _isDragging = false;
    }

    @Override
    public void update(int mouseX, int mouseY) {

        if (_isDragging) {
            dragSlider(mouseX - posX, mouseY - posY);
        }
    }

    protected abstract void dragSlider(int x, int y);

    @Override
    public boolean mouseWheel(double mouseX, double mouseY, int movement) {

        if (movement > 0) {
            setValue(_value - 1);
        } else if (movement < 0) {
            setValue(_value + 1);
        }
        return true;
    }

    public void onValueChanged(int value) {

    }

    public void onStopDragging() {

    }

    public int getValue() {

        return _value;
    }

}
