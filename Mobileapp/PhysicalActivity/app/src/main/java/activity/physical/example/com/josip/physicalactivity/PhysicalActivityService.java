package activity.physical.example.com.josip.physicalactivity;

import java.util.List;

/**
 * Created by Josip on 19.10.2017..
 */

public interface PhysicalActivityService {
    public List<Login> getListOfUsers();
    public List<BikingActivity> getResults();
    public List<TrcanjeActivity> getRunningResults();
    public List<WalkingActivity> getWalkingResults();


}
