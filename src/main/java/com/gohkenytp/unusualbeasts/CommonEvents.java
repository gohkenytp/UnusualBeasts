package com.gohkenytp.unusualbeasts;

import com.gohkenytp.unusualbeasts.entity.Lemming;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = UnusualBeasts.MODID)
public class CommonEvents {
    @SubscribeEvent
    public static void onEntityJoinWorld(EntityJoinLevelEvent event) {
        if (!(event.getLevel()).isClientSide && event.getEntity() instanceof PathfinderMob) {
            PathfinderMob creature = (PathfinderMob)event.getEntity();

            if (creature.getType() == EntityType.FOX || creature.getType() == EntityType.CAT || creature.getType() == EntityType.OCELOT) {
                creature.goalSelector.addGoal(10, (Goal)new NearestAttackableTargetGoal<>(creature, Lemming.class, false, false));
            }

        }
    }}
