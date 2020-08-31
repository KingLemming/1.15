package cofh.core.client.gui.element;

import cofh.core.client.gui.IGuiAccess;
import cofh.core.util.helpers.RenderHelper;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.ResourceLocation;
import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.function.BooleanSupplier;
import java.util.function.Supplier;

public class ElementConditionalLayered extends ElementBase {

    protected ArrayList<Pair<Supplier<TextureAtlasSprite>, BooleanSupplier>> conditionalTextures = new ArrayList<>();

    public ElementConditionalLayered(IGuiAccess gui) {

        super(gui);
    }

    public ElementConditionalLayered(IGuiAccess gui, int posX, int posY) {

        super(gui, posX, posY);
    }

    public ElementConditionalLayered addSprite(String location, BooleanSupplier condition) {

        return addSprite(new ResourceLocation(location), condition);
    }

    public ElementConditionalLayered addSprite(ResourceLocation location, BooleanSupplier condition) {

        return addSprite(() -> RenderHelper.getTexture(location), condition);
    }

    public ElementConditionalLayered addSprite(TextureAtlasSprite sprite, BooleanSupplier condition) {

        return addSprite(() -> sprite, condition);
    }

    public ElementConditionalLayered addSprite(Supplier<TextureAtlasSprite> sprite, BooleanSupplier condition) {

        conditionalTextures.add(Pair.of(sprite, condition));
        return this;
    }

    @Override
    public void drawBackground(int mouseX, int mouseY) {

        for (Pair<Supplier<TextureAtlasSprite>, BooleanSupplier> entry : conditionalTextures) {
            if (entry.getRight().getAsBoolean()) {
                gui.drawIcon(entry.getLeft().get(), posX(), posY());
            }
        }
    }

}
