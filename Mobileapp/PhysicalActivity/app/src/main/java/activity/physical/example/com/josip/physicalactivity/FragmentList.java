package activity.physical.example.com.josip.physicalactivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONException;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import static android.content.Intent.getIntent;

/**
 * Created by Josip on 6.8.2017..
 */

@SuppressWarnings("deprecation")

public class FragmentList extends ListFragment {
    ListView listView;
    String[] activities={"Hodanje","Trčanje","Bicikliranje","Rezultati","ITM"};



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_fragment,container,false);



        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ArrayAdapter<String> adapter =  new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,activities);

        setListAdapter(adapter);




    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        if(position==0){

            Intent intent = new Intent(v.getContext(),WalkingActivity.class);

            startActivityForResult(intent,0);
        }else if(position==1){
            Intent intent = new Intent(v.getContext(),TrcanjeActivity.class);
            startActivityForResult(intent,1);
        }else if(position==2){
            Intent intent = new Intent(v.getContext(),BikingActivity.class);
            startActivityForResult(intent,2);
        }
        else if(position==4){
            Intent intent = new Intent(v.getContext(),ITMActivity.class);
            startActivityForResult(intent,4);
        }
    }


}
