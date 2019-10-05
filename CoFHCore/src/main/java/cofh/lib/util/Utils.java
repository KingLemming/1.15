package cofh.lib.util;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.io.WritingMode;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.event.entity.living.EnderTeleportEvent;
import net.minecraftforge.registries.ForgeRegistries;

import java.nio.file.Path;

import static cofh.lib.util.constants.Tags.TAG_ENCHANTMENTS;
import static net.minecraft.enchantment.EnchantmentHelper.getEnchantmentLevel;

public class Utils {

    private Utils() {

    }

    public static boolean isClientWorld(World world) {

        return world.isRemote;
    }

    public static boolean isServerWorld(World world) {

        return !world.isRemote;
    }

    public static boolean isFakePlayer(Entity player) {

        return player instanceof FakePlayer;
    }

    public static String createPrettyJSON(String jsonString) {

        JsonParser parser = new JsonParser();
        JsonObject json = parser.parse(jsonString).getAsJsonObject();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        return gson.toJson(json);
    }

    public static void loadConfig(ForgeConfigSpec spec, Path path) {

        final CommentedFileConfig configData = CommentedFileConfig.builder(path).sync().autosave().writingMode(WritingMode.REPLACE).build();
        configData.load();
        spec.setConfig(configData);
    }

    // region CHAT UTILS

    // endregion

    // region ENTITY UTILS
    public static boolean addToPlayerInventory(PlayerEntity player, ItemStack stack) {

        if (stack.isEmpty() || player == null) {
            return false;
        }
        if (stack.getItem() instanceof ArmorItem) {
            int index = ((ArmorItem) stack.getItem()).getEquipmentSlot().getIndex();
            if (player.inventory.armorInventory.get(index).isEmpty()) {
                player.inventory.armorInventory.set(index, stack);
                return true;
            }
        }
        PlayerInventory inv = player.inventory;
        for (int i = 0; i < inv.mainInventory.size(); i++) {
            if (inv.mainInventory.get(i).isEmpty()) {
                inv.mainInventory.set(i, stack.copy());
                return true;
            }
        }
        return false;
    }

    public static boolean dropItemStackIntoWorld(ItemStack stack, World world, Vec3d pos) {

        return dropItemStackIntoWorld(stack, world, pos, false);
    }

    public static boolean dropItemStackIntoWorldWithVelocity(ItemStack stack, World world, BlockPos pos) {

        return dropItemStackIntoWorld(stack, world, new Vec3d(pos), true);
    }

    public static boolean dropItemStackIntoWorldWithVelocity(ItemStack stack, World world, Vec3d pos) {

        return dropItemStackIntoWorld(stack, world, pos, true);
    }

    public static boolean dropItemStackIntoWorld(ItemStack stack, World world, Vec3d pos, boolean velocity) {

        if (stack.isEmpty()) {
            return false;
        }
        float x2 = 0.5F;
        float y2 = 0.0F;
        float z2 = 0.5F;

        if (velocity) {
            x2 = world.rand.nextFloat() * 0.8F + 0.1F;
            y2 = world.rand.nextFloat() * 0.8F + 0.1F;
            z2 = world.rand.nextFloat() * 0.8F + 0.1F;
        }
        ItemEntity entity = new ItemEntity(world, pos.x + x2, pos.y + y2, pos.z + z2, stack.copy());

        if (velocity) {
            entity.setMotion(world.rand.nextGaussian() * 0.05F, world.rand.nextGaussian() * 0.05F + 0.2F, world.rand.nextGaussian() * 0.05F);
        } else {
            entity.setMotion(-0.05, 0, 0);
        }
        world.addEntity(entity);

        return true;
    }

    public static boolean dropDismantleStackIntoWorld(ItemStack stack, World world, BlockPos pos) {

        if (stack.isEmpty()) {
            return false;
        }
        float f = 0.3F;
        double x2 = world.rand.nextFloat() * f + (1.0F - f) * 0.5D;
        double y2 = world.rand.nextFloat() * f + (1.0F - f) * 0.5D;
        double z2 = world.rand.nextFloat() * f + (1.0F - f) * 0.5D;
        ItemEntity dropEntity = new ItemEntity(world, pos.getX() + x2, pos.getY() + y2, pos.getZ() + z2, stack);
        dropEntity.setPickupDelay(10);
        world.addEntity(dropEntity);

        return true;
    }

    public static boolean teleportEntityTo(Entity entity, BlockPos pos) {

        return teleportEntityTo(entity, pos.getX(), pos.getY(), pos.getZ());
    }

    public static boolean teleportEntityTo(Entity entity, double x, double y, double z) {

        if (entity instanceof LivingEntity) {
            return teleportEntityTo((LivingEntity) entity, x, y, z);
        } else {
            entity.setLocationAndAngles(x, y, z, entity.rotationYaw, entity.rotationPitch);
            entity.playSound(SoundEvents.ENTITY_ENDERMAN_TELEPORT, 1.0F, 1.0F);
        }
        return true;
    }

    public static boolean teleportEntityTo(LivingEntity entity, double x, double y, double z) {

        EnderTeleportEvent event = new EnderTeleportEvent(entity, x, y, z, 0);
        if (MinecraftForge.EVENT_BUS.post(event)) {
            return false;
        }
        entity.setPositionAndUpdate(event.getTargetX(), event.getTargetY(), event.getTargetZ());
        entity.playSound(SoundEvents.ENTITY_ENDERMAN_TELEPORT, 1.0F, 1.0F);

        return true;
    }
    // endregion

    // ENCHANT UTILS
    public static int getHeldEnchantmentLevel(LivingEntity living, Enchantment ench) {

        return Math.max(getEnchantmentLevel(ench, living.getHeldItemMainhand()), getEnchantmentLevel(ench, living.getHeldItemOffhand()));
    }

    public static void addEnchantment(ItemStack stack, Enchantment enc, int level) {

        stack.addEnchantment(enc, level);
    }

    public static void removeEnchantment(ItemStack stack, Enchantment ench) {

        if (stack.getTag() == null || !stack.getTag().contains(TAG_ENCHANTMENTS, 9)) {
            return;
        }
        ListNBT list = stack.getTag().getList(TAG_ENCHANTMENTS, 10);
        String encId = String.valueOf(ForgeRegistries.ENCHANTMENTS.getKey(ench));

        for (int i = 0; i < list.size(); i++) {
            CompoundNBT tag = list.getCompound(i);
            String id = tag.getString("id");
            if (encId.equals(id)) {
                list.remove(i);
                break;
            }
        }
        if (list.isEmpty()) {
            stack.removeChildTag(TAG_ENCHANTMENTS);
        }
    }
    // endregion
}
