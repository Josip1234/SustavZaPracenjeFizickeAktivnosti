package activity.physical.example.com.josip.physicalactivity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

/**
 * Created by Josip on 6.8.2017..
 */

public class FragmentList extends ListFragment {

    String[] activities={"Hodanje","Brzo hodanje","Trčanje","Bicikliranje","Trbušnjaci","Sklekovi","Rezultati","ITM"};



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
}
