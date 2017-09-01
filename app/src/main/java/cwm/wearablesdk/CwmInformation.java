package cwm.wearablesdk;

/**
 * Created by user on 2017/8/31.
 */

public class CwmInformation extends Information{
    private int mId = 0;

    /************walk info**************/
    private int mWalkStep;
    private int mDistance;
    private int mCalories;
    private int mRunStep;
    private int mStatus;

    /**************heart beat*************/
    private int mHeartBeat;

     CwmInformation(){
          mId = 0;
          mWalkStep = 0;
          mDistance = 0;
          mCalories = 0;
          mStatus = 0;
          mHeartBeat = 0;
    }

    public void setId(int id){mId = id;}
    public void setHeartBeat(int heartBeat){mHeartBeat = heartBeat;}
    public void setWalkStep(int walkStep){mWalkStep = walkStep;}
    public void setDistance(int distance){mDistance = distance;}
    public void setCalories(int calories){mCalories = calories;}
    public void setStatus(int status){mStatus = status;}
    public int getWalkStep(){
       return mWalkStep;
    }
    public int getDistance(){
        return mDistance;
    }
    public int getCalories(){ return mCalories;}
    public int getRunStep(){ return mRunStep;}
    public int getHeartBeat(){return mHeartBeat;}
    public int getStatus(){return mStatus;}

    public String getId() {
        if (mId == 0)
            return "WalkInform";
        else if(mId == 1)
            return "RunInform";
        return null;
    }
}
