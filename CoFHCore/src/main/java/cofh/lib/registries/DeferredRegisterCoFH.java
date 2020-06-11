package cofh.lib.registries;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.IForgeRegistryEntry;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

import static cofh.lib.util.helpers.StringHelper.decompose;

/**
 * Basically a copy of Forge's Deferred Register system, with a little more cowbell. See {@link DeferredRegister}
 *
 * @param <T> The base registry type, must be a concrete base class, do not use subclasses or wild cards.
 * @author King Lemming
 */

@SuppressWarnings({"rawtypes", "unchecked"})
public class DeferredRegisterCoFH<T extends IForgeRegistryEntry<T>> {

    private final IForgeRegistry<T> type;
    private final String modid;
    private final List<Supplier<? extends T>> entries = new ArrayList<>();
    private final Map<ResourceLocation, RegistryObject> registryObjects = new HashMap<>();

    public DeferredRegisterCoFH(IForgeRegistry<T> reg, String modid) {

        this.type = reg;
        this.modid = modid;
    }

    // region REGISTRATION
    public synchronized <I extends T> RegistryObject<I> register(final String resourceLoc, final Supplier<I> sup) {

        return register(decompose(modid, resourceLoc, ':'), sup);
    }

    private synchronized <I extends T> RegistryObject<I> register(final String[] resourceLoc, final Supplier<I> sup) {

        return register(resourceLoc[0], resourceLoc[1], sup);
    }

    public synchronized <I extends T> RegistryObject<I> register(final String modid, final String name, final Supplier<I> sup) {

        return register(new ResourceLocation(modid, name), sup);
    }

    public synchronized <I extends T> RegistryObject<I> register(final ResourceLocation resourceLoc, final Supplier<I> sup) {

        entries.add(() -> sup.get().setRegistryName(resourceLoc));
        RegistryObject<I> reg = RegistryObject.of(resourceLoc, this.type);
        registryObjects.put(resourceLoc, reg);
        return reg;
    }
    // endregion

    // region OBJECT RETRIEVAL
    public T get(final String resourceLoc) {

        return get(decompose(modid, resourceLoc, ':'));
    }

    private T get(final String[] resourceLoc) {

        return get(resourceLoc[0], resourceLoc[1]);
    }

    public T get(final String modid, final String name) {

        return get(new ResourceLocation(modid, name));
    }

    public T get(final ResourceLocation resourceLoc) {

        RegistryObject<T> reg = registryObjects.get(resourceLoc);
        return reg == null ? null : reg.get();
    }

    // region SUPPLIER RETRIEVAL
    public RegistryObject<T> getSup(final String resourceLoc) {

        return getSup(decompose(modid, resourceLoc, ':'));
    }

    private RegistryObject<T> getSup(final String[] resourceLoc) {

        return getSup(resourceLoc[0], resourceLoc[1]);
    }

    public RegistryObject<T> getSup(final String modid, final String name) {

        return getSup(new ResourceLocation(modid, name));
    }

    @Nullable
    public RegistryObject<T> getSup(final ResourceLocation resourceLoc) {

        return registryObjects.get(resourceLoc);
    }
    // endregion

    public void register(IEventBus bus) {

        bus.addListener(this::addEntries);
    }

    private void addEntries(RegistryEvent.Register<?> event) {

        if (event.getGenericType() == this.type.getRegistrySuperType()) {
            @SuppressWarnings("unchecked") IForgeRegistry<T> reg = (IForgeRegistry<T>) event.getRegistry();
            entries.stream().map(Supplier::get).forEach(reg::register);
        }
    }

}
