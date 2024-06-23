package droidco.west3.ironsight.contracts.utils;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.List;

@Getter @Setter
public class CompletionStep
{
    private List<String> taskDesc;
    private ItemStack requestedGoods;
    private String locationDesc;
    private int stepNumber;
    private HashMap<String, CompletionStep> steps = new HashMap<>();
    public CompletionStep(String stepKey,int stepNumber,List<String> taskDsc, ItemStack requestedGoods, String locationDesc){
        this.stepNumber=stepNumber;
        this.taskDesc=taskDsc;
        this.requestedGoods=requestedGoods;
        this.locationDesc=locationDesc;
        steps.put(stepKey,this);
    }
}
