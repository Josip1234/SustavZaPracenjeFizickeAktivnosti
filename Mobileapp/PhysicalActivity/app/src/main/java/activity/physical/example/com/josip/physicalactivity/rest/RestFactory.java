package activity.physical.example.com.josip.physicalactivity.rest;

import activity.physical.example.com.josip.physicalactivity.implementations.PhysicalImplementation;

/**
 * Created by Josip on 12.11.2017..
 */

public class RestFactory {
    public static final String ip="10.0.2.2";
    public static RestInterface getInstance(){
        return new PhysicalImplementation();
    }
}
