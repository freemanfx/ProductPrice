package ro.freemanfx.productprice.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import butterknife.ButterKnife;
import butterknife.InjectView;
import ro.freemanfx.productprice.R;

public class MainFragment extends Fragment {

    @InjectView(R.id.find)
    Button find;
    @InjectView(R.id.add)
    Button add;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_fragment, container, false);
        ButterKnife.inject(this, view);

        find.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startScanForAdd();
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),"Add", Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }

    private void startScanForAdd() {
        IntentIntegrator integrator = new IntentIntegrator(getActivity());
        integrator.addExtra("SCAN_WIDTH", 800);
        integrator.addExtra("SCAN_HEIGHT", 200);
        integrator.addExtra("RESULT_DISPLAY_DURATION_MS", 3000L);
        integrator.addExtra("PROMPT_MESSAGE", "Custom prompt to scan a product");
        integrator.initiateScan(IntentIntegrator.PRODUCT_CODE_TYPES);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
        if (result != null) {
            Toast.makeText(getActivity(),result.toString(),Toast.LENGTH_LONG).show();
        }
    }
}
