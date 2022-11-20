package net.lyof.scaled.configs;

import java.util.Objects;

public class Configs {
    public static final String[] targets = ModCommonConfigs.TARGETS.get().split(", ");
    public static final String[] defaultValues = ModCommonConfigs.DEFAULT_MULTIPLIER.get().split(", ");
    public static final String[] onlyMonsters = ModCommonConfigs.ONLY_MONSTERS.get().split(", ");
    public static final String[] changeHealth = ModCommonConfigs.CHANGE_HEALTH.get().split(", ");
    public static final String[] changeDamage = ModCommonConfigs.CHANGE_DAMAGE.get().split(", ");
    public static final String[] changeArmor = ModCommonConfigs.CHANGE_ARMOR.get().split(", ");
    public static final String[] changeSpeed = ModCommonConfigs.CHANGE_SPEED.get().split(", ");
    public static final String[] randomStatsRanges = ModCommonConfigs.STATS_VARIATION.get().split(", ");

    public static final String[] defaultHeights = ModCommonConfigs.DEFAULT_HEIGHT.get().split(", ");
    public static final String[] doHeightStats = ModCommonConfigs.DO_HEIGHT_STATS.get().split(", ");
    public static final String[] blockValues = ModCommonConfigs.TIMES_PER_BLOCKY.get().split(", ");

    public static final String[] doTimeStats = ModCommonConfigs.DO_TIME_STATS.get().split(", ");
    public static final String[] midnightValue = ModCommonConfigs.MIDNIGHT_VALUE.get().split(", ");

    public static int getIndex(String searched) {
        int i = 0;
        for (String element:targets) {
            if (Objects.equals(element, searched))
                return i;
            i++;
        }
        return -1;
    }

    public static float getDefaultValue(String target) {
        int i = getIndex(target);
        if (i == -1)
            return 1;
        return Float.parseFloat(defaultValues[i]);
    }
    public static boolean getOnlyMonsters(String target) {
        int i = getIndex(target);
        if (i == -1)
            return true;
        return Boolean.parseBoolean(onlyMonsters[i]);
    }
    public static double getBaseHealth(String target) {
        int i = getIndex(target);
        if (i == -1)
            return 1;
        return Double.parseDouble(changeHealth[i]);
    }
    public static double getBaseDamage(String target) {
        int i = getIndex(target);
        if (i == -1)
            return 1;
        return Double.parseDouble(changeDamage[i]);
    }
    public static double getBaseArmor(String target) {
        int i = getIndex(target);
        if (i == -1)
            return 0;
        return Double.parseDouble(changeArmor[i]);
    }
    public static double getBaseSpeed(String target) {
        int i = getIndex(target);
        if (i == -1)
            return 1;
        return Double.parseDouble(changeSpeed[i]);
    }
    public static double getRandomStatsRange(String target) {
        int i = getIndex(target);
        if (i == -1)
            return 0.1;
        return Double.parseDouble(randomStatsRanges[i]);
    }
    public static float getDefaultHeight(String target) {
        int i = getIndex(target);
        if (i == -1)
            return 64;
        return Float.parseFloat(defaultHeights[i]);
    }
    public static String getDoHeightStats(String target) {
        int i = getIndex(target);
        if (i == -1)
            return "none";
        return doHeightStats[i];
    }
    public static double getBlockValue(String target) {
        int i = getIndex(target);
        if (i == -1)
            return 0;
        return Double.parseDouble(blockValues[i]);
    }
    public static boolean getDoTimeStats(String target) {
        int i = getIndex(target);
        if (i == -1)
            return false;
        return Boolean.parseBoolean(doTimeStats[i]);
    }
    public static double getMidnightValue(String target) {
        int i = getIndex(target);
        if (i == -1)
            return 1;
        return Double.parseDouble(midnightValue[i]);
    }





    public static String[] getTargetInfos(String searched) {
        int index = getIndex(searched);
        if (index == -1)
            return new String[]{
                    "scaled:null",          ////// 0
                    "1",                        // 1
                    "true",                     // 2
                    "0.1",                      // 3
                    "64",                       // 4
                    "none",                     // 5
                    "0",                        // 6
                    "-1"};                      // 7
        return new String[]{targets[index], ////// 0
                defaultValues[index],           // 1
                onlyMonsters[index],            // 2
                randomStatsRanges[index],       // 3
                defaultHeights[index],          // 4
                doHeightStats[index],           // 5
                blockValues[index],             // 6
                String.valueOf(index)};         // 7
    }
}
