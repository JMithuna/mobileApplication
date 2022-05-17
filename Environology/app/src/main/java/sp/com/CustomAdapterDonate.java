package sp.com;

import android.content.Context;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.TextView;

        import androidx.annotation.NonNull;
        import androidx.recyclerview.widget.RecyclerView;

        import java.util.ArrayList;

public class CustomAdapterDonate extends RecyclerView.Adapter<CustomAdapterDonate.MyViewHolder> {

    private Context context;
    private ArrayList nameItemDonate, lat_donate, lon_donate;


    CustomAdapterDonate(Context context, ArrayList nameItemDonate,
                  ArrayList lat_donate, ArrayList lon_donate) {

        this.context = context;
        this.nameItemDonate = nameItemDonate;
        this.lat_donate = lat_donate;
        this.lon_donate = lon_donate;

    }

    @NonNull
    @Override
    public CustomAdapterDonate.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View view2 = inflater.inflate(R.layout.rowdonate, parent, false);
        return new MyViewHolder(view2);

    }


    @Override
    public void onBindViewHolder(@NonNull CustomAdapterDonate.MyViewHolder holder, int position) {

        holder.nameItemDonateTxt.setText(String.valueOf(nameItemDonate.get(position)));
        holder.lat_donateTxt.setText(String.valueOf(lat_donate.get(position)));
        holder.lon_donateTxt.setText(String.valueOf(lon_donate.get(position)));

    }

    @Override
    public int getItemCount() {
        return nameItemDonate.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView nameItemDonateTxt, lat_donateTxt, lon_donateTxt;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            nameItemDonateTxt = itemView.findViewById(R.id.nameofdonateitem);
            lat_donateTxt = itemView.findViewById(R.id.latofdonate);
            lon_donateTxt = itemView.findViewById(R.id.lonofdonate);

        }
    }
}
