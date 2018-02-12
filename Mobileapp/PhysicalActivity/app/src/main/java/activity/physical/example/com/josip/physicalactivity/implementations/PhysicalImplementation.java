package activity.physical.example.com.josip.physicalactivity.implementations;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.identity.intents.AddressConstants;

import activity.physical.example.com.josip.physicalactivity.activity.physical.example.com.josip.physicalactivity.interfaces.PhysicalInterface;
import activity.physical.example.com.josip.physicalactivity.model.Registration;
import activity.physical.example.com.josip.physicalactivity.rest.RestInterface;

import static android.content.Intent.getIntent;

/**
 * Created by Josip on 12.11.2017..
 */


public class PhysicalImplementation implements PhysicalInterface {

    @Override
    public Registration getUser(String email) {
        return null;
    }
}
