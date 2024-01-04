package dev.soizx.util;

import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;
import java.text.DecimalFormat;

public class SystemPerformance {
    private static final DecimalFormat decimalFormat = new DecimalFormat("#.##");

    // RAM Performance
    public static double getTotalMemory() {
        Runtime runtime = Runtime.getRuntime();
        return (double) (runtime.totalMemory() / (1024 * 1024));
    }

    public static double getUsageMemory() {
        Runtime runtime = Runtime.getRuntime();
        double totalMemory = (double) (runtime.totalMemory() / (1024 * 1024));
        double freeMemory = (double) (runtime.freeMemory() / (1024 * 1024));
        return totalMemory - freeMemory;
    }

    public static double getFreeMemory() {
        Runtime runtime = Runtime.getRuntime();
        return (double) (runtime.freeMemory() / (1024 * 1024));
    }

    public static double getUsageMemoryInPercentage() {
        double response = getUsageMemory() / getTotalMemory() * 100;
        return Double.parseDouble(decimalFormat.format(response));
    }

    // CPU Performance
    public static Integer getTotalCores() {
        OperatingSystemMXBean osBean = ManagementFactory.getOperatingSystemMXBean();
        return osBean.getAvailableProcessors();
    }
    public static Integer getUsageCores() {
        Runtime runtime = Runtime.getRuntime();
        return runtime.availableProcessors();
    }

    public static Integer getFreeCores() {
        return getTotalCores() - getUsageCores();
    }

    public static Integer getUsageCoresInPercentage() {
        return getTotalCores() / getUsageCores() * 100;
    }

    public static void main(String[] args) {
        System.out.println("getTotalMemory "+getTotalMemory()+" MB");
        System.out.println("getUsageMemory "+getUsageMemory()+" MB");
        System.out.println("getFreeMemory "+getFreeMemory()+" MB");
        System.out.println("getUsageMemoryInPercentage "+getUsageMemoryInPercentage()+" %");
        System.out.println("getTotalCores "+getTotalCores()+" cores");
        System.out.println("getUsageCores "+getUsageCores()+" cores");
        System.out.println("getFreeCores "+getFreeCores()+" cores");
        System.out.println("getUsageCoresInPercentage "+getUsageCoresInPercentage()+" %");
    }
}
