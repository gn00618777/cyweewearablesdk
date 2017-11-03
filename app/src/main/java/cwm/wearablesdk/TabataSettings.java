package cwm.wearablesdk;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by user on 2017/9/14.
 */

public class TabataSettings {
    public static final int COUNT_DOWN = 1;
    public static final int COUNT_UP = 2;
    public enum ITEMS{
        NULL,
        PUSHUP,
        CRUNCH,
        SQUART,
        JUMPING_JACK,
        DIPS,
        HIGH_KNESSRUNNING,
        LUNGES,
        BURPEES,
        STEP_ON_CHAIR,
        PUSHUP_ROTATION
    };
    private int prepareTime; //sec
    private int actionType; //count down or count times
    private int actionTime; //sec
    private int actionTimes;
    private int intervalTime; //sec
    private int cycle;
    private boolean[] items;

    public TabataSettings(){
        prepareTime = 5;
        actionType = COUNT_DOWN;
        actionTime = 10;
        actionTimes = 5;
        intervalTime = 10;
        cycle = 1;
        items = new boolean[11];
        items[ITEMS.PUSHUP.ordinal()] = true;
        items[ITEMS.CRUNCH.ordinal()] = true;
        items[ITEMS.SQUART.ordinal()] = false;
        items[ITEMS.JUMPING_JACK.ordinal()] = false;
        items[ITEMS.DIPS.ordinal()] = false;
        items[ITEMS.HIGH_KNESSRUNNING.ordinal()] = false;
        items[ITEMS.LUNGES.ordinal()] = false;
        items[ITEMS.BURPEES.ordinal()] = false;
        items[ITEMS.STEP_ON_CHAIR.ordinal()] = false;
        items[ITEMS.PUSHUP_ROTATION.ordinal()] = false;

    }
    public void setPrepareTime(int prepareTime) {
        if(prepareTime >= 5 && prepareTime <= 255)
           this.prepareTime = prepareTime;
    }
    public void setActionType(int actionType){
        if(actionType == 0x01) {
            this.actionType = COUNT_DOWN;
        }
        else if(actionType == 0x02)
            this.actionType = COUNT_UP;
    }
    public void setActionTime(int actionTime){
        if(actionTime >= 5 && actionTime <= 65535 && actionType == COUNT_DOWN){
            this.actionTime = actionTime;
        }
    }
    public void setActionTimes(int actionTimes){
        if(actionTimes >= 5 && actionTimes <= 65535 && actionType == COUNT_UP){
            this.actionTimes = actionTimes;
        }
    }
    public void setIntervalTime(int intervalTime){
        if(intervalTime >= 5 && intervalTime <= 65535){
            this.intervalTime = intervalTime;
        }
    }
    public void setCycle(int cycle){
        if(cycle >= 1 && cycle <= 255){
            this.cycle = cycle;
        }
    }
    public int getTotalItemsNumber(){
        int total = 0;

        for(int i = ITEMS.PUSHUP.ordinal() ; i <= ITEMS.PUSHUP_ROTATION.ordinal() ; i++){
            if(items[i] == true)
                total++;
        }

        return total;
    }
    public int getPrepareTime(){
        return this.prepareTime;
    }
    public int getActionType(){
        return this.actionType;
    }
    public int getActionTime(){
        return this.actionTime;
    }
    public int getActionTimes(){
        return this.actionTimes;
    }
    public int getIntervalTime(){
        return this.intervalTime;
    }
    public int getCycle(){
        return this.cycle;
    }
    public void enableItem(int item){
        if(item == ITEMS.PUSHUP.ordinal()){
            this.items[item] = true;
        }
        else if(item == ITEMS.CRUNCH.ordinal()){
            this.items[item] = true;
        }
        else if(item == ITEMS.SQUART.ordinal()){
            this.items[item] = true;
        }
        else if(item == ITEMS.JUMPING_JACK.ordinal()){
            this.items[item] = true;
        }
        else if(item == ITEMS.DIPS.ordinal()){
            this.items[item] = true;
        }
        else if(item == ITEMS.HIGH_KNESSRUNNING.ordinal()){
            this.items[item] = true;
        }
        else if(item == ITEMS.LUNGES.ordinal()){
            this.items[item] = true;
        }
        else if(item == ITEMS.BURPEES.ordinal()){
            this.items[item] = true;
        }
        else if(item == ITEMS.STEP_ON_CHAIR.ordinal()){
            this.items[item] = true;
        }
        else if(item == ITEMS.PUSHUP_ROTATION.ordinal()){
            this.items[item] = true;
        }
    }
    public void disableItem(int item){
        if(item == ITEMS.PUSHUP.ordinal()){
            this.items[item] = false;
        }
        else if(item == ITEMS.CRUNCH.ordinal()){
            this.items[item] = false;
        }
        else if(item == ITEMS.SQUART.ordinal()){
            this.items[item] = false;
        }
        else if(item == ITEMS.JUMPING_JACK.ordinal()){
            this.items[item] = false;
        }
        else if(item == ITEMS.DIPS.ordinal()){
            this.items[item] = false;
        }
        else if(item == ITEMS.HIGH_KNESSRUNNING.ordinal()){
            this.items[item] = false;
        }
        else if(item == ITEMS.LUNGES.ordinal()){
            this.items[item] = false;
        }
        else if(item == ITEMS.BURPEES.ordinal()){
            this.items[item] = false;
        }
        else if(item == ITEMS.STEP_ON_CHAIR.ordinal()){
            this.items[item] = false;
        }
        else if(item == ITEMS.PUSHUP_ROTATION.ordinal()){
            this.items[item] = false;
        }
    }
    public boolean isEnabled(int item){
        if(item == ITEMS.NULL.ordinal()){
         return this.items[item];
        }
        if(item == ITEMS.PUSHUP.ordinal()){
            return this.items[item];
        }
        else if(item == ITEMS.CRUNCH.ordinal()){
            return this.items[item];
        }
        else if(item == ITEMS.JUMPING_JACK.ordinal()){
            return this.items[item];
        }
        else if(item == ITEMS.DIPS.ordinal()){
            return this.items[item];
        }
        else if(item == ITEMS.SQUART.ordinal()){
            return this.items[item];
        }
        else if(item == ITEMS.PUSHUP_ROTATION.ordinal()){
            return this.items[item];
        }
        else if(item == ITEMS.LUNGES.ordinal()){
            return this.items[item];
        }
        else if(item == ITEMS.BURPEES.ordinal()){
            return this.items[item];
        }
        else if(item == ITEMS.STEP_ON_CHAIR.ordinal()){
            return this.items[item];
        }
        else if(item == ITEMS.HIGH_KNESSRUNNING.ordinal()){
            return this.items[item];
        }
        else
            return false;
    }
    public Queue<TabataObject> getExerciseItems(){
        Queue<TabataObject> ItemQueue = new LinkedList<>();
        for(int i = ITEMS.PUSHUP.ordinal() ; i <= ITEMS.PUSHUP_ROTATION.ordinal() ; i++){
            if(items[i] == true){
                TabataObject obj = new TabataObject();
                if(i == ITEMS.PUSHUP.ordinal()) {
                    obj.setItemName(ITEMS.PUSHUP.toString());
                    obj.setItemPos(ITEMS.PUSHUP.ordinal());
                }
                else if(i == ITEMS.CRUNCH.ordinal()){
                    obj.setItemName(ITEMS.CRUNCH.toString());
                    obj.setItemPos(ITEMS.CRUNCH.ordinal());
                }
                else if(i == ITEMS.SQUART.ordinal()){
                    obj.setItemName(ITEMS.SQUART.toString());
                    obj.setItemPos(ITEMS.SQUART.ordinal());
                }
                else if(i == ITEMS.JUMPING_JACK.ordinal()){
                    obj.setItemName(ITEMS.JUMPING_JACK.toString());
                    obj.setItemPos(ITEMS.JUMPING_JACK.ordinal());
                }
                else if(i == ITEMS.DIPS.ordinal()){
                    obj.setItemName(ITEMS.DIPS.toString());
                    obj.setItemPos(ITEMS.DIPS.ordinal());
                }
                else if(i == ITEMS.HIGH_KNESSRUNNING.ordinal()){
                    obj.setItemName(ITEMS.HIGH_KNESSRUNNING.toString());
                    obj.setItemPos(ITEMS.HIGH_KNESSRUNNING.ordinal());
                }
                else if(i == ITEMS.LUNGES.ordinal()){
                    obj.setItemName(ITEMS.LUNGES.toString());
                    obj.setItemPos(ITEMS.LUNGES.ordinal());
                }
                else if(i == ITEMS.BURPEES.ordinal()){
                    obj.setItemName(ITEMS.BURPEES.toString());
                    obj.setItemPos(ITEMS.BURPEES.ordinal());
                }
                else if(i == ITEMS.STEP_ON_CHAIR.ordinal()){
                    obj.setItemName(ITEMS.STEP_ON_CHAIR.toString());
                    obj.setItemPos(ITEMS.STEP_ON_CHAIR.ordinal());
                }
                else if(i == ITEMS.PUSHUP_ROTATION.ordinal()){
                    obj.setItemName(ITEMS.PUSHUP_ROTATION.toString());
                    obj.setItemPos(ITEMS.PUSHUP_ROTATION.ordinal());
                }
                ItemQueue.add(obj);
            }
        }
        return ItemQueue;
    }
}
