package com.cogy.launcher;

import java.util.ArrayList;
import java.util.List;
import android.util.Log;

public class LauncherManager {
    private static class LauncherConfig {
        String name;
        String language;
        String filePath;
        String arguments;

        LauncherConfig(String name, String language, String filePath, String arguments) {
            this.name = name;
            this.language = language;
            this.filePath = filePath;
            this.arguments = arguments;
        }
    }

    private static List<LauncherConfig> launchers = new ArrayList<>();

    public void addLauncher(String name, String language, String filePath, String arguments) {
        launchers.add(new LauncherConfig(name, language, filePath, arguments));
        Log.d("LauncherManager", "Added " + language + " launcher: " + name);
    }

    public void removeLauncher(int index) {
        if (index >= 0 && index < launchers.size()) {
            launchers.remove(index);
            Log.d("LauncherManager", "Launcher removed");
        }
    }

    public void launchAll() {
        Log.d("LauncherManager", "Launching all programs...");
        for (LauncherConfig launcher : launchers) {
            launch(launcher);
        }
    }

    private void launch(LauncherConfig launcher) {
        try {
            switch (launcher.language.toLowerCase()) {
                case "java":
                    launchJava(launcher);
                    break;
                case "kotlin":
                    launchKotlin(launcher);
                    break;
                case "c":
                    launchC(launcher);
                    break;
                case "c++":
                    launchCpp(launcher);
                    break;
                case "c#":
                    launchCSharp(launcher);
                    break;
            }
        } catch (Exception e) {
            Log.e("LauncherManager", "Error: " + e.getMessage());
        }
    }

    private void launchJava(LauncherConfig launcher) throws Exception {
        String command = "java -cp " + launcher.filePath + " Main " + launcher.arguments;
        executeCommand(command, launcher.name);
    }

    private void launchKotlin(LauncherConfig launcher) throws Exception {
        String command = "kotlin " + launcher.filePath + " " + launcher.arguments;
        executeCommand(command, launcher.name);
    }

    private void launchC(LauncherConfig launcher) throws Exception {
        String command = "gcc " + launcher.filePath + " -o temp_launcher";
        executeCommand(command, launcher.name);
    }

    private void launchCpp(LauncherConfig launcher) throws Exception {
        String command = "g++ " + launcher.filePath + " -o temp_launcher";
        executeCommand(command, launcher.name);
    }

    private void launchCSharp(LauncherConfig launcher) throws Exception {
        String command = "dotnet run --project " + launcher.filePath;
        executeCommand(command, launcher.name);
    }

    private void executeCommand(String command, String launcherName) throws Exception {
        Process process = Runtime.getRuntime().exec(command);
        int exitCode = process.waitFor();
        if (exitCode == 0) {
            Log.d("LauncherManager", "Launcher executed: " + launcherName);
        }
    }

    public List<LauncherConfig> getAllLaunchers() {
        return new ArrayList<>(launchers);
    }

    public int getLauncherCount() {
        return launchers.size();
    }
}
