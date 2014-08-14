package ro.freemanfx.productprice.fragment;


import android.content.Intent;
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

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import ro.freemanfx.productprice.AppContext;
import ro.freemanfx.productprice.R;
import ro.freemanfx.productprice.activity.AddFuelPriceActivity;
import ro.freemanfx.productprice.activity.FindFuelPriceActivity;
import ro.freemanfx.productprice.service.FuelTypes;

public class SelectFuelFragment extends Fragment {

    @InjectView(R.id.fuel_radio_group)
    RadioGroup radioGroup;
    @InjectView(R.id.button_bar)
    View buttonBar;

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

    @OnClick(R.id.search)
    public void onSearchButtonClick() {
        startActivity(new Intent(getActivity(), FindFuelPriceActivity.class));
    }

    @OnClick(R.id.add)
    public void onAddButtonClick() {
        startActivity(new Intent(getActivity(), AddFuelPriceActivity.class));
    }

    private TextView createTypeCategoryTextView(int textResourceId) {
        TextView textView = new TextView(getActivity());
        textView.setText(getString(textResourceId));
        textView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 24);
        textView.setGravity(Gravity.CENTER);
        textView.setLayoutParams(new RadioGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        return textView;
    }

    private void addRadioButtonsForGroup(FuelTypes.FuelResource[] group, ViewGroup parent) {
        for (FuelTypes.FuelResource fuelResource : group) {
            RadioButton radioButton = new RadioButton(getActivity());
            radioButton.setText(fuelResource.resId);
            radioButton.setTag(fuelResource);
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
                    AppContext.setFuel((FuelTypes.FuelResource) radioButton.getTag());
                }

                buttonBar.setVisibility(View.VISIBLE);
            }
        };
    }
}
