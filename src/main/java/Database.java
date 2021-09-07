import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class Database {
    private static final Map<String, Tool> dbTools = new HashMap<>();
    private static final Map<String, Pricing> dbPricing = new HashMap<>();

    static {
        generatePricing();
        generateTools();
    }

    public static void generatePricing() {
        dbPricing.put("Ladder", new Pricing(BigDecimal.valueOf(1.99), true, true, false));
        dbPricing.put("Chainsaw", new Pricing(BigDecimal.valueOf(1.49), true, false, true));
        dbPricing.put("Jackhammer", new Pricing(BigDecimal.valueOf(2.99), true, false, false));
    }

    public static void generateTools() {
        dbTools.put("LADW", new Tool("LADW", "Ladder", "Werner"));
        dbTools.put("CHNS", new Tool("CHNS", "Chainsaw", "Stihl"));
        dbTools.put("JAKR", new Tool("JAKR", "Jackhammer", "Ridgid"));
        dbTools.put("JAKD", new Tool("JAKD", "Jackhammer", "DeWalt"));
    }

    public static Tool getToolFromCode(String toolCode) {
        return dbTools.get(toolCode);
    }

    public static Pricing getPricingByToolType(String toolType) {
        return dbPricing.get(toolType);
    }
}
