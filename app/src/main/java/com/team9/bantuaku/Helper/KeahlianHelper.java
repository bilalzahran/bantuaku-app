package com.team9.bantuaku.Helper;

import android.content.Context;
import android.widget.TextView;

import com.google.android.flexbox.FlexboxLayout;
import com.team9.bantuaku.R;

public class KeahlianHelper {
    public void setKeahlian(Context context, String keahlian, FlexboxLayout layout){
        if(keahlian.equals("Tidak ada keahlian")){
            TextView item = new TextView(context);
            item.setText("Tidak ada keahlian yang diperlukan");
            item.setTextSize(16);
            layout.addView(item);
        }else{
            String[] keahlianarr = keahlian.split(",");

            FlexboxLayout.LayoutParams params = new FlexboxLayout.LayoutParams(FlexboxLayout.LayoutParams.WRAP_CONTENT,FlexboxLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(0,0,5,5);
            //params.setMarginEnd(5);

            for (int i = 0; i<keahlianarr.length;i++){
                TextView item = new TextView(context);
                item.setText(keahlianarr[i]);
                item.setPadding(10,10,10,10);
                item.setBackgroundResource(R.drawable.keahlian_textview_background);
                item.setTextColor(context.getResources().getColor(R.color.White));
                item.setLayoutParams(params);
                layout.addView(item);
            }
        }
    }
}
