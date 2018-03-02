package com.josip.physical.activity.planiranje;

import com.josip.physical.activity.biking.BikingActivity;
import com.josip.physical.activity.running.RunningActivity;
import com.josip.physical.activity.walking.WalkingActivity;

public interface PlaniranjeRepositorij {
       public ModelPlaniranja spremi(ModelPlaniranja modelPlaniranja);

}
