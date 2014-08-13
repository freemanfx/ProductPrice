package ro.freemanfx.productprice.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import ro.freemanfx.productprice.R;
import ro.freemanfx.productprice.service.FuelTypes;

public class FuelSelectionFragment extends Fragment {

    @InjectView(R.id.fuel_radio_group)
    RadioGroup radioGroup;
    @InjectView(R.id.button_bar)
    View buttonBar;
    private String selectedFuelType;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.select_fuel, container, false);

        ButterKnife.inject(this, view);
        buttonBar.setVisibility(View.GONE);

        radioGroup.addView(createTypeCategoryTextView(R.string.gasoline));
        addRadioButtonsForGroup(FuelTypes.GASOLINE_TYPES, radioGroup);

        radioGroup.addView(createTypeCategoryTextView(R.string.diesel));
        addRadioButtonsForGroup(FuelTypes.DIESEL_TYPES, radioGroup);

        return view;
    }

    @OnClick(R.id.search_button)
    public void onSearchButtonClick() {
        Toast.makeText(getActivity(), "Search...", Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.add_button)
    public void onAddButtonClick() {
        Toast.makeText(getActivity(), "Add...", Toast.LENGTH_SHORT).show();
    }

    private TextView createTypeCategoryTextView(int textResourceId) {
        TextView textView = new TextView(getActivity());
        textView.setText(getString(textResourceId));
        textView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 24);
        textView.setGravity(Gravity.CENTER);
        textView.setLayoutParams(new RadioGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        return textView;
    }

    private void addRadioButtonsForGroup(String[] group, ViewGroup parent) {
        for (String type : group) {
            RadioButton radioButton = new RadioButton(getActivity());
            radioButton.setText(type);
            radioButton.setTag(type);
            radioButton.setOnClickListener(fuelTypeClickListener());
            parent.addView(radioButton);
        }
    }

    private View.OnClickListener fuelTypeClickListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int checkedRadioButtonId = radioGroup.getCheckedRadioButtonId();
                if (checkedRadioButtonId != -1) {
                    View radioButton = radioGroup.findViewById(checkedRadioButtonId);
                    selectedFuelType = (String) radioButton.getTag();
                }

                buttonBar.setVisibility(View.VISIBLE);
            }
        };
    }
}
