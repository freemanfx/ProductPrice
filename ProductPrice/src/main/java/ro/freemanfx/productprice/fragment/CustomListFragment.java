package ro.freemanfx.productprice.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import ro.freemanfx.productprice.R;

public class CustomListFragment extends Fragment {
    protected boolean dataLoaded = false;
    @InjectView(R.id.progressbar)
    View progressBar;
    @InjectView(android.R.id.list)
    ListView listView;
    @InjectView(android.R.id.empty)
    TextView emptyTextView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_fragment, container, false);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (!dataLoaded) {
            listView.setVisibility(View.GONE);
        } else {
            showListAndHideProgressBar();
        }
    }

    protected void showNoResults() {
        progressBar.setVisibility(View.GONE);
        listView.setVisibility(View.GONE);
        emptyTextView.setVisibility(View.VISIBLE);
    }

    protected void showListAndHideProgressBar() {
        progressBar.setVisibility(View.GONE);
        listView.setVisibility(View.VISIBLE);
    }
}
