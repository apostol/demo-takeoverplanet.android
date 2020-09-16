package ru.dpankratov.projects.takeoverplanet;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseUser;

import ru.dpankratov.projects.takeoverplanet.Auth.PhoneAuthActivity;

/**
 * Simple list-based Activity to redirect to one of the other Activities. This Activity does not
 * contain any useful code related to Firebase Authentication. You may want to start with
 * one of the following Files:
 *     {@link PhoneAuthActivity}
 */
public class SignInActivity extends BaseActivity implements AdapterView.OnItemClickListener {

    private static final Class[] CLASSES = new Class[]{
            PhoneAuthActivity.class,
    };

    private static final int[] DESCRIPTION_IDS = new int[] {
            R.string.desc_phone_auth
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseUser _user = AndroidLauncher.getUser();
        if (_user ==  null || _user.isAnonymous()) {
            setContentView(R.layout.activity_sign);
            // Set up ListView and Adapter
            ListView listView = findViewById(R.id.listView);
            MyArrayAdapter adapter = new MyArrayAdapter(this, android.R.layout.simple_list_item_2, CLASSES);
            adapter.setDescriptionIds(DESCRIPTION_IDS);
            listView.setAdapter(adapter);
            listView.setOnItemClickListener(this);
        }else{
            finish();
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Class clicked = CLASSES[position];
        startActivityForResult(new Intent(this, clicked), 0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // если пришло ОК
        if (resultCode == RESULT_OK) {
            // если вернулось не ОК
            finish();
        }
    }

    public static class MyArrayAdapter extends ArrayAdapter<Class> {

        private Context mContext;
        private Class[] mClasses;
        private int[] mDescriptionIds;

        public MyArrayAdapter(Context context, int resource, Class[] objects) {
            super(context, resource, objects);
            mContext = context;
            mClasses = objects;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = convertView;
            if (convertView == null) {
                LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(LAYOUT_INFLATER_SERVICE);
                view = inflater.inflate(android.R.layout.simple_list_item_2, null);
            }
            ((TextView) view.findViewById(android.R.id.text1)).setText(mClasses[position].getSimpleName());
            ((TextView) view.findViewById(android.R.id.text2)).setText(mDescriptionIds[position]);
            return view;
        }

        public void setDescriptionIds(int[] descriptionIds) {
            mDescriptionIds = descriptionIds;
        }
    }
}