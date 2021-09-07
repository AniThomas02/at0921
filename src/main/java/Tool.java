public class Tool {
    private final String toolCode;
    private final String toolType;
    private final String toolBrand;
    private final Pricing pricing;

    public Tool(String toolCode, String toolType, String toolBrand) {
        this.toolCode = toolCode;
        this.toolType = toolType;
        this.toolBrand = toolBrand;
        this.pricing = Database.getPricingByToolType(toolType);
    }

    //Getters
    public String getToolCode() {
        return toolCode;
    }

    public String getToolType() {
        return toolType;
    }

    public String getToolBrand() {
        return toolBrand;
    }

    public Pricing getToolPricing() {
        return pricing;
    }
}
