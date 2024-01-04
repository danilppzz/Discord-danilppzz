package dev.soizx.util;

import java.lang.management.ManagementFactory;

public class SystemPerformance {

    public static String getUsedMemory() {
        if (ManagementFactory.getOperatingSystemMXBean() instanceof com.sun.management.OperatingSystemMXBean osBean) {
            long totalMemory = osBean.getTotalMemorySize();
            long freeMemory = osBean.getFreeMemorySize();
            long usedMemory = totalMemory - freeMemory;

            return formatBytes(usedMemory);
        } else {
            return null;
        }
    }

    public static String getTotalMemory() {
        if (ManagementFactory.getOperatingSystemMXBean() instanceof com.sun.management.OperatingSystemMXBean osBean) {
            long totalMemory = osBean.getTotalMemorySize();

            return formatBytes(totalMemory);
        } else {
            return null;
        }
    }

    private static String formatBytes(long bytes) {
        return String.format("%.2f GB", bytes / (double) (1024 * 1024 * 1024));
    }
}
