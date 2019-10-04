package cofh.lib.registries;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.IForgeRegistryEntry;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

/**
 * Basically a copy of Forge's Deferred Register system, with a little more cowbell. See {@link DeferredRegister}
 *
 * @param <T> The base registry type, must be a concrete base class, do not use subclasses or wild cards.
 * @author King Lemming
 */
public class DeferredRegisterCoFH<T extends IForgeRegistryEntry<T>> {

    private final IForgeRegistry<T> type;
    private final String modid;
    private List<Supplier<? extends T>> entries = new ArrayList<>();

    public DeferredRegisterCoFH(IForgeRegistry<T> reg, String modid) {

        this.type = reg;
        this.modid = modid;
    }

    public <I extends T> RegistryObject<I> register(final String name, final Supplier<I> sup) {

        return register(modid, name, sup);
    }

    public <I extends T> RegistryObject<I> registerSpec(final String key, final Supplier<I> sup) {

        return register(new ResourceLocation(key), sup);
    }

    public <I extends T> RegistryObject<I> register(final String modid, final String name, final Supplier<I> sup) {

        return register(new ResourceLocation(modid, name), sup);
    }

    public <I extends T> RegistryObject<I> register(final ResourceLocation key, final Supplier<I> sup) {

        entries.add(() -> sup.get().setRegistryName(key));
        return RegistryObject.of(key.toString(), this.type);
    }

    public void register(IEventBus bus) {

        bus.addListener(this::addEntries);
    }

    private void addEntries(RegistryEvent.Register<?> event) {

        if (event.getGenericType() == this.type.getRegistrySuperType()) {
            @SuppressWarnings("unchecked")
            IForgeRegistry<T> reg = (IForgeRegistry<T>) event.getRegistry();
            entries.stream().map(Supplier::get).forEach(reg::register);
        }
    }

}
