package sp.com;


//DAO - Data Access Object; Performs CRUD Operation.



import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class DAORecycle {
    private DatabaseReference databaseReference;
    public DAORecycle() {
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        databaseReference = db.getReference(Recycle.class.getSimpleName());
    }

    public Task<Void> add(Recycle recycle) {

        //if(recycle == null) //throw exception
        return databaseReference.push().setValue(recycle);

    }




}
