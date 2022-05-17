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

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private Context context;

    private ArrayList rowID, nameItemRecycle, lat_recycle, lon_recycle;


    CustomAdapter(Context context, ArrayList rowID, ArrayList nameItemRecycle, ArrayList lat_recycle, ArrayList lon_recycle) {


        this.context = context;
        this.rowID = rowID;
        this.nameItemRecycle = nameItemRecycle;
        this.lat_recycle = lat_recycle;
        this.lon_recycle = lon_recycle;

    }

    @NonNull
    @Override
    public CustomAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.rowrecycle, parent, false);
        return new MyViewHolder(view);

    }


    @Override
    public void onBindViewHolder(@NonNull CustomAdapter.MyViewHolder holder, final int position) {


        holder.nameItemRecycleTxt.setText(String.valueOf(nameItemRecycle.get(position)));
        holder.lat_recycleTxt.setText(String.valueOf(lat_recycle.get(position)));
        holder.lon_recycleTxt.setText(String.valueOf(lon_recycle.get(position)));
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(context, "Click to delete after collection.", Toast.LENGTH_LONG).show();
                EnvironmentHelper myDB = new EnvironmentHelper(context.getApplicationContext());
                myDB.deleteOneRowRecycle(String.valueOf(nameItemRecycle));
                Intent i = new Intent(context, DeleteRecycle.class);
                i.putExtra("id", String.valueOf(rowID.get(position)));
                i.putExtra("nameItemRecycle", String.valueOf(nameItemRecycle.get(position)));
                i.putExtra("lat_recycle", String.valueOf(lat_recycle.get(position)));
                i.putExtra("lon_recycle", String.valueOf(lon_recycle.get(position)));
                context.startActivity(i);

            }
        });

        double latitude1 = Double.parseDouble(String.valueOf(lat_recycle.get(position)));
        double longitude1 = Double.parseDouble(String.valueOf(lon_recycle.get(position)));

        holder.mainLayout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Intent i1 = new Intent(context, EnvironmentMap.class);
                i1.putExtra("LATITUDE", latitude1);
                i1.putExtra("LONGITUDE",longitude1);
                context.startActivity(i1);

                return true;
            }
        });

    }

    @Override
    public int getItemCount() {
        return nameItemRecycle.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView nameItemRecycleTxt, lat_recycleTxt, lon_recycleTxt;
        LinearLayout mainLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            nameItemRecycleTxt = itemView.findViewById(R.id.nameofrecycleitem);
            lat_recycleTxt = itemView.findViewById(R.id.latofrecycle);
            lon_recycleTxt = itemView.findViewById(R.id.lonofrecycle);
            mainLayout = itemView.findViewById(R.id.mainLayout);

        }
    }
}
