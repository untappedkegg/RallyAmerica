package com.untappedkegg.rally.home;


import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.untappedkegg.rally.AppState;
import com.untappedkegg.rally.R;

/**
 * A simple {@link Fragment} subclass.
 *
 */
public class AboutFragment extends Fragment {

    private TextView versionView, main, learnMore;

    public AboutFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.about_fragment, null, false);

        final short position = (short) getArguments().getInt(AppState.KEY_POSITION);
        final String[] modArray = getResources().getStringArray(R.array.action_bar_modules);
        if (position != 0) {
            try {
                getActivity().getActionBar().setTitle(modArray[position]);
                NavDrawerFragment.getListView().setItemChecked(position, true);
                ActivityMain2.setCurPosition(position);
            } catch (Exception e) {
            }
        }

        versionView = (TextView) view.findViewById(R.id.about_version);
        main = (TextView) view.findViewById(R.id.about_main);
        learnMore = (TextView) view.findViewById(R.id.about_learn_more);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        String version;
        try {
            version = getActivity().getPackageManager().getPackageInfo(getActivity().getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            Log.e(((Object) this).getClass().getSimpleName(), "Could not find our package. Initiate FUBAR sequence.");
            version = "";
        }
        versionView.setText(getString(R.string.about_version, version));
        main.setText(R.string.about_main);
        learnMore.setText(R.string.about_learn_more);

    }
}
