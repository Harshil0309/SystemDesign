public class DbConfig {

    private DbConfig() {
        System.out.println("DbConfig initialized");
    }

    private static class helper {
        private static final DbConfig dbObj = new DbConfig();

    }

    public static DbConfig getInstance() {
        return helper.dbObj;
    }

}
