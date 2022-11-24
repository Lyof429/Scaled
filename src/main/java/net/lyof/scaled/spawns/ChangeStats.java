package net.lyof.scaled.spawns;

import net.lyof.scaled.configs.Configs;
import net.lyof.scaled.Scaled;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Arrays;

@Mod.EventBusSubscriber
public class ChangeStats {
    @SubscribeEvent
    public static void EntitySpawns(EntityJoinWorldEvent event) {
        Entity eventEntity = event.getEntity();
        Level world = event.getWorld();

        if (world.isClientSide) return;
        if (eventEntity == null) return;
        if (eventEntity instanceof Player) return;

// Target definition
        String target = world.dimension().location().toString();
        String biome = world.getBiome(new BlockPos(eventEntity.getX(), eventEntity.getY(), eventEntity.getZ())).value().toString();
        if (Configs.getIndex(biome) != -1)
            target = biome;
        if (Configs.getIndex(eventEntity.getEncodeId()) != -1) {
            target = eventEntity.getEncodeId();
        } else {
            if (Configs.getOnlyMonsters(target) && !(eventEntity instanceof Monster)) return;
        }

        if (eventEntity instanceof LivingEntity entity) {
            AttributeInstance health = entity.getAttribute(Attributes.MAX_HEALTH);
            AttributeInstance damage = entity.getAttribute(Attributes.ATTACK_DAMAGE);
            AttributeInstance armor = entity.getAttribute(Attributes.ARMOR);
            AttributeInstance speed = entity.getAttribute(Attributes.MOVEMENT_SPEED);

// Multiplier definition
            double multiplier = Configs.getDefaultValue(target);
            double rnd = (Math.random() - 0.5) * 2 * Configs.getRandomStatsRange(target) + 1;
// Height based stats
            if (!(Configs.getDoHeightStats(target).equals("none"))) {
                int distance = (int) (Configs.getDefaultHeight(target) - entity.getY());
                if (distance > 0) {
                    if (Configs.getDoHeightStats(target).equals("flat")) {
                        multiplier *= Configs.getBlockValue(target);
                    } else {
                        multiplier *= 1 + (distance * Configs.getBlockValue(target));
                    }
                }
            }
// Time based stats
            if (!Configs.getDoTimeStats(target).equals("none") && (Math.abs(world.getTimeOfDay(0) - 0.5) > 0.25)) {
                if (Configs.getDoTimeStats(target).equals("midnight")) {
                    multiplier *= 4 * (1 - Configs.getMidnightValue(target)) * Math.abs(world.getTimeOfDay(0) - 0.5)
                            + Configs.getMidnightValue(target);
                } else
                if (Configs.getDoTimeStats(target).equals("flat"))
                    multiplier *= Configs.getMidnightValue(target);
            }
            Scaled.LOGGER.debug("multiplier: " + multiplier + " rnd: " + rnd);

// Stats changes
            if (health != null && Configs.getBaseHealth(target) > 0) {
                health.setBaseValue(health.getBaseValue() * multiplier * rnd * Configs.getBaseHealth(target));
                entity.setHealth(entity.getMaxHealth());
            }
            if (damage != null && Configs.getBaseDamage(target) > 0) {
                damage.setBaseValue(damage.getBaseValue() * multiplier * rnd * Configs.getBaseDamage(target));
            }
            if (armor != null && Configs.getBaseArmor(target) > 0) {
                armor.setBaseValue(armor.getBaseValue() * multiplier * rnd * Configs.getBaseArmor(target));
            }
            if (speed != null && Configs.getBaseSpeed(target) > 0) {
                speed.setBaseValue(speed.getBaseValue() * rnd * Configs.getBaseSpeed(target));
            }
        }
    }
}
