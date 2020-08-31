package cofh.core.tileentity;

import cofh.core.util.helpers.MathHelper;
import net.minecraft.block.Blocks;
import net.minecraft.entity.effect.LightningBoltEntity;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.server.ServerWorld;

import static cofh.core.util.references.CoreReferences.LIGHTNING_AIR_TILE;

public class LightningAirTile extends TileEntity implements ITickableTileEntity {

    protected int duration = 100;

    public LightningAirTile() {

        super(LIGHTNING_AIR_TILE);
        duration = MathHelper.nextInt(MathHelper.RANDOM, 20, duration);
    }

    @Override
    public void tick() {

        if (world == null) {
            return;
        }
        if (--duration <= 0) {
            if (world.canSeeSky(pos) && world instanceof ServerWorld) {
                LightningBoltEntity bolt = new LightningBoltEntity(world, (double) pos.getX() + 0.5D, pos.getY(), (double) pos.getZ() + 0.5D, false);
                ((ServerWorld) world).addLightningBolt(bolt);
            }
            this.world.setBlockState(pos, Blocks.AIR.getDefaultState());
            this.world.removeTileEntity(this.pos);
            this.remove();
        }
    }

    public int getDuration() {

        return duration;
    }

    public void setDuration(int duration) {

        this.duration = duration;
    }

}
