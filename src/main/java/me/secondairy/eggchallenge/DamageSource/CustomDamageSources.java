package me.secondairy.eggchallenge.DamageSource;

import net.minecraft.entity.damage.DamageSource;

public class CustomDamageSources extends DamageSource {
    public static final DamageSource Egg = new CustomDamageSources(
            "egg.dropped");

    protected CustomDamageSources(String name) {
        super(name);
    }
}