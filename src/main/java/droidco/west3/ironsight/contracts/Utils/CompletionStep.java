package droidco.west3.ironsight.contracts.Utils;

import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.List;

public class CompletionStep {
    private final int stepNumber;
    private final HashMap<String, CompletionStep> steps = new HashMap<>();
    private List<String> taskDesc;
    private ItemStack requestedGoods;
    private String locationDesc;

    public CompletionStep(String stepKey, int stepNumber, List<String> taskDsc, ItemStack requestedGoods, String locationDesc) {
        this.stepNumber = stepNumber;
        this.taskDesc = taskDsc;
        this.requestedGoods = requestedGoods;
        this.locationDesc = locationDesc;
        steps.put(stepKey, this);
    }

    public int getStepNumber() {
        return this.stepNumber;
    }

    public List<String> getTaskDesc() {
        return taskDesc;
    }

    public void setTaskDesc(List<String> taskDesc) {
        this.taskDesc = taskDesc;
    }

    public ItemStack getRequestedGoods() {
        return requestedGoods;
    }

    public void setRequestedGoods(ItemStack requestedGoods) {
        this.requestedGoods = requestedGoods;
    }

    public String getLocationDesc() {
        return locationDesc;
    }

    public void setLocationDesc(String locationDesc) {
        this.locationDesc = locationDesc;
    }
}
