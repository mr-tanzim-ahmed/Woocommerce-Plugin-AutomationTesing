package config;

import io.github.cdimascio.dotenv.Dotenv;

public class EnvManager {

    private static Dotenv dotenv;

    static {
        // Check if user passed: gradle test -Denv=.env.staging
        String envFile = System.getProperty("env");

        if (envFile != null && !envFile.isEmpty()) {
            System.out.println("Loading custom environment file: " + envFile);
            dotenv = Dotenv.configure()
                    .filename(envFile)         // loads .env.staging, .env.dev, etc.
                    .directory("./")           // root directory
                    .ignoreIfMalformed()
                    .ignoreIfMissing()
                    .load();
        } else {
            System.out.println("Loading default .env file");
            dotenv = Dotenv.configure()
                    .directory("./")           // very important!
                    .ignoreIfMalformed()
                    .ignoreIfMissing()
                    .load();
        }
    }

    public static String get(String key) {

        return dotenv.get(key);
    }
    // Optional: dedicated methods
    public static String userName() {

        return get("userName");
    }

    public static String password() {

        return get("password");
    }

    public static String adminPageUrl(){
        return get("adminLoginURL");
    }

    public static String googleSheetURL() {
        return get("googleSheetURL");
    }

    public static String googleSheetUrlCSVFormat() {
        return get("googleSheetUrlCSVFormat");
    }

}
