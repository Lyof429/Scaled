package net.lyof.scaled.configs;

import net.minecraftforge.common.ForgeConfigSpec;

public class ModCommonConfigs {
    public static final ForgeConfigSpec.Builder BUILDER =
            new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;

    public static final ForgeConfigSpec.ConfigValue<String> TARGETS;
    public static final ForgeConfigSpec.ConfigValue<String> DEFAULT_MULTIPLIER;
    public static final ForgeConfigSpec.ConfigValue<String> ONLY_MONSTERS;
    public static final ForgeConfigSpec.ConfigValue<String> CHANGE_HEALTH;
    public static final ForgeConfigSpec.ConfigValue<String> CHANGE_DAMAGE;
    public static final ForgeConfigSpec.ConfigValue<String> CHANGE_ARMOR;
    public static final ForgeConfigSpec.ConfigValue<String> CHANGE_SPEED;
    public static final ForgeConfigSpec.ConfigValue<String> STATS_VARIATION;

    public static final ForgeConfigSpec.ConfigValue<String> DEFAULT_HEIGHT;
    public static final ForgeConfigSpec.ConfigValue<String> DO_HEIGHT_STATS;
    public static final ForgeConfigSpec.ConfigValue<String> TIMES_PER_BLOCKY;

    public static final ForgeConfigSpec.ConfigValue<String> DO_TIME_STATS;
    public static final ForgeConfigSpec.ConfigValue<String> MIDNIGHT_VALUE;


    static {
        BUILDER.push("Scaled Configs");

        BUILDER.push("Targets Definition");
        TARGETS = BUILDER.comment("Dimension, Biome and Mob ids which should get modified stats")
                .define("targets", "minecraft:overworld, minecraft:the_nether, minecraft:the_end, minecraft:zombie");
        DEFAULT_MULTIPLIER = BUILDER.comment("Target base stats multiplier")
                .define("defaultMultiplier", "1.0, 1.5, 1.5, 1.0");

        ONLY_MONSTERS = BUILDER.comment("Should only monsters have modified stats. Setting this to 0 will disable the stat from being scaled")
                .define("onlyMonsters", "true, true, true, true");
        CHANGE_HEALTH = BUILDER.comment("By how much should Health value be scaled. Setting this to 0 will disable the stat from being scaled")
                .define("baseHealth", "1, 1, 1, 1.5");
        CHANGE_DAMAGE = BUILDER.comment("By how much should Damage value be scaled. Setting this to 0 will disable the stat from being scaled")
                .define("baseDamage", "1, 1, 1, 0");
        CHANGE_ARMOR = BUILDER.comment("By how much should Armor value be scaled. Setting this to 0 will disable the stat from being scaled")
                .define("baseArmor", "0, 0, 0, 1.25");
        CHANGE_SPEED = BUILDER.comment("By how much should Speed value be scaled (won't get other modifiers than randomStatsChange and this). Setting this to 0 will disable the stat to be scaled")
                .define("baseSpeed", "1, 1, 1, 1");
        STATS_VARIATION = BUILDER.comment("Random range for stats\nThis means stat outcome will be between stat*(range-1) and stat*(range+1)")
                .define("randomStatsRange", "0.1, 0.1, 0.1, 0.25");
        BUILDER.pop();

        BUILDER.push("Height based stats");
        DEFAULT_HEIGHT = BUILDER.comment("y level to be considered default")
                .define("defaultHeight", "64, 64, 48, 64");
        DO_HEIGHT_STATS = BUILDER.comment("""
                        "up": Target will have increased stats the higher from defaultHeight it is
                        "down": Target will have increased stats the lower from defaultHeight it is
                        "both": Target will have increased stats the further from defaultHeight it is
                        "none": Target won't have increased stats depending on spawn height""")
                .define("doHeightStats", "down, none, none, down");
        TIMES_PER_BLOCKY = BUILDER.comment("Value to multiply target stats with per block far from defaultHeight")
                .define("timesPerBlockY", "0.02, 0, 0, 0.015");
        BUILDER.pop();

        BUILDER.push("Time based stats");
        DO_TIME_STATS = BUILDER.comment("Should target stats vary depending on the time of day, maximum value being reached at midnight")
                .define("doTimeStats", "true, false, false, true");
        MIDNIGHT_VALUE = BUILDER.comment("Maximum multiplier reached at in game midnight\nDisabled should be 1")
                .define("midnightValue", "1.5, 1, 1, 1.5");
        BUILDER.pop();

        BUILDER.pop();
        SPEC = BUILDER.build();
    }
}
