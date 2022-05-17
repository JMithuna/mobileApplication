package sp.com;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapterRewards extends RecyclerView.Adapter<CustomAdapterRewards.MyViewHolder> {

    private Context context;

    private ArrayList rowID, greenPoints;

    private String latmall;
    private String lonmall;

    private String mallName;


    CustomAdapterRewards(Context context, ArrayList rowID, ArrayList greenPoints) {


        this.context = context;
        this.rowID = rowID;
        this.greenPoints = greenPoints;

    }

    @NonNull
    @Override
    public CustomAdapterRewards.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View view3 = inflater.inflate(R.layout.rv_rewards, parent, false);
        return new MyViewHolder(view3);

    }


    @Override
    public void onBindViewHolder(@NonNull CustomAdapterRewards.MyViewHolder holder, final int position) {


        holder.greenPoints_txt.setText(String.valueOf(greenPoints.get(position)));

        Intent imap = new Intent(context, InputMall.class);
        latmall = imap.getStringExtra("LATITUDE");
        lonmall = imap.getStringExtra("LONGITUDE");
        mallName = imap.getStringExtra("NAME");

        //double latitude1 = Double.parseDouble(latmall);
        //double longitude1 = Double.parseDouble(lonmall);

        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent i2 = new Intent(context, EnvironmentMap.class);
                    //i2.putExtra("LATITUDE", latitude1);
                    //i2.putExtra("LONGITUDE",longitude1);
                    context.startActivity(i2);
            }
        });

    }

    @Override
    public int getItemCount() {
        return greenPoints.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView greenPoints_txt;
        LinearLayout mainLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            greenPoints_txt = itemView.findViewById(R.id.greenPointstxt);
            //lat_recycleTxt = itemView.findViewById(R.id.latofrecycle);
            //lon_recycleTxt = itemView.findViewById(R.id.lonofrecycle);
            mainLayout = itemView.findViewById(R.id.mainLayout3);

        }
    }
}

