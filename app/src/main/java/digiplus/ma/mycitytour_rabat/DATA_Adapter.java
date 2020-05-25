package digiplus.ma.mycitytour_rabat;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;

/** Create a new {@link DATA_Adapter} object */

public class DATA_Adapter extends ArrayAdapter<DATA> {
    private int mColorRes_Id;
    public DATA_Adapter(Context context, ArrayList<DATA> words, int ColorRes_Id) {
        super(context, 0, words);
        mColorRes_Id = ColorRes_Id;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View list_Item_View = convertView;
        if (list_Item_View == null) {
            list_Item_View = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_items, parent, false);
        }

        DATA current_item = getItem(position);
        TextView Name_v = (TextView) list_Item_View.findViewById(R.id.textView_UP_ID);
        Name_v.setText(current_item.getName());

        TextView Description_v = (TextView) list_Item_View.findViewById(R.id.textView_Down_ID);
        Description_v.setText(current_item.getDescription());

        ImageView Image_v = (ImageView) list_Item_View.findViewById(R.id.imageView_ID);

        if (current_item.hasImage()) {
            Image_v.setImageResource(current_item.getImage());
            Image_v.setVisibility(View.VISIBLE);
        } else {
            Image_v.setVisibility(View.GONE);
        }

        View TXT_Container = list_Item_View.findViewById(R.id.Text_CONTAINER);
        int Color = ContextCompat.getColor(getContext(), mColorRes_Id);
        //TXT_Container.setBackgroundColor(Color);
        return list_Item_View;
    }
}