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

/**
 * Created by Josip on 6.8.2017..
 */

@SuppressWarnings("deprecation")
//fragment koji postavlja listu na koju korisnik klikne i kuda može navigirati kroz aplikaciju
public class FragmentList extends ListFragment {
    //lista koja se nalazi na posebnom xml-u
    ListView listView;
    //lista aktivnosti
    String[] activities={"Hodanje","Trčanje","Bicikliranje","Rezultati","Indeks tjelesne mase","Mape"};



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_fragment,container,false);
         //list fragment je layout na kojem ne nalaze dijelovi liste


        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        ArrayAdapter<String> adapter =  new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,activities);

        setListAdapter(adapter);
        //spajanje liste na layout



    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        //kad se neki gumb klikne idi na tu aktivnost
        if(position==0){

            Intent intent = new Intent(v.getContext(),WalkActivity.class);

            startActivityForResult(intent,0);
        }else if(position==1){
            Intent intent = new Intent(v.getContext(),TrcanjeActivity.class);
            startActivityForResult(intent,1);
        }else if(position==2){
            Intent intent = new Intent(v.getContext(),Bicikliranje.class);
            startActivityForResult(intent,2);
        }else if(position==3){
            Intent intent = new Intent(v.getContext(),RezultActivity.class);
            startActivityForResult(intent,3);
        }
        else if(position==4){
            Intent intent = new Intent(v.getContext(),ITMActivity.class);
            startActivityForResult(intent,4);
        }else if(position==5){
            Intent intent = new Intent(v.getContext(),MapsActivity.class);
            startActivityForResult(intent,5);
        }
    }


}
