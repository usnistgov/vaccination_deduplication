package org.immregistries.vaccination_deduplication;

public class PropertyLoader {
    private static PropertyLoader instance = null;
    protected PropertyLoader() {
      this.getClass().getResource("/config.properties");
    }

    public static PropertyLoader getInstance() {
        if(instance == null) {
            instance = new PropertyLoader();
        }
        return instance;
    }


}
