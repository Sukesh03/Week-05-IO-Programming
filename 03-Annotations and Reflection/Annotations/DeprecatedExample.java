class LegacyAPI {

    @Deprecated
    public void oldFeature() {
        System.out.println("This is the old feature. Avoid using it.");
    }

    public void newFeature() {
        System.out.println("This is the new and recommended feature.");
    }
}

public class DeprecatedExample {
    public static void main(String[] args) {
        LegacyAPI api = new LegacyAPI();
        api.oldFeature();
        api.newFeature();
    }
}
