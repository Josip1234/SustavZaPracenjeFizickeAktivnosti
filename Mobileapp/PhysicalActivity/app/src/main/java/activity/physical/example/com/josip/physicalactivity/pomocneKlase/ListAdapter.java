package activity.physical.example.com.josip.physicalactivity.pomocneKlase;

import android.content.Context;
import android.graphics.ColorSpace;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;

import activity.physical.example.com.josip.physicalactivity.R;

/**
 * Created by Korisnik on 6.3.2018..
 */

public class ListAdapter extends BaseAdapter {
    TextView mTekstDatumBrojacaKoraka;
    ArrayList<ModelKoraka> mListaBrojacaKoraka;
    Context mContext;
    LayoutInflater mInflater;
    public ListAdapter(ArrayList<ModelKoraka> mListaBrojacaKoraka,Context mContext){
        this.mListaBrojacaKoraka=mListaBrojacaKoraka;
        this.mContext=mContext;
        this.mInflater=(LayoutInflater) this.mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public int getCount() {
        return mListaBrojacaKoraka.size();
    }

    @Override
    public Object getItem(int position) {
        return mListaBrojacaKoraka.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null){
            convertView=mInflater.inflate(R.layout.list_rows,parent,false);
        }
        mTekstDatumBrojacaKoraka=(TextView) convertView.findViewById(R.id.ime_senzora);
        mTekstDatumBrojacaKoraka.setText(mListaBrojacaKoraka.get(position).mDatum+"- Ukupno koraka:"+String.valueOf(mListaBrojacaKoraka.get(position).mBrojacKoraka));
        return convertView;
    }
}
