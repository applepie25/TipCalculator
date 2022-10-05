package com.example.tiptime;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.RadioGroup;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.tiptime.databinding.ActivityMainBinding;
import com.google.android.material.switchmaterial.SwitchMaterial;
import com.google.android.material.textfield.TextInputEditText;
import java.text.NumberFormat;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.Nullable;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding var10001 = ActivityMainBinding.inflate(this.getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(var10001, "ActivityMainBinding.inflate(layoutInflater)");
        this.binding = var10001;
        var10001 = this.binding;
        if (var10001 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        }

        this.setContentView((View)var10001.getRoot());
        ActivityMainBinding var10000 = this.binding;
        if (var10000 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        }

        var10000.calculateButton.setOnClickListener((OnClickListener) new OnClickListener() {
            public final void onClick(View it) {
                MainActivity.this.calculateTip();
            }
        });
        var10000 = this.binding;
        if (var10000 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        }

        var10000.costOfServiceEditText.setOnKeyListener((OnKeyListener)(new OnKeyListener() {
            public final boolean onKey(View view, int keyCode, KeyEvent $noName_2) {
                MainActivity var10000 = MainActivity.this;
                Intrinsics.checkNotNullExpressionValue(view, "view");
                return var10000.handleKeyEvent(view, keyCode);
            }
        }));
    }

    private final void calculateTip() {
        ActivityMainBinding var10000 = this.binding;
        if (var10000 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        }

        TextInputEditText var8 = var10000.costOfServiceEditText;
        Intrinsics.checkNotNullExpressionValue(var8, "binding.costOfServiceEditText");
        String stringInTextField = String.valueOf(var8.getText());
        Double cost = StringsKt.toDoubleOrNull(stringInTextField);
        if (cost != null && !Intrinsics.areEqual(cost, 0.0D)) {
            var10000 = this.binding;
            if (var10000 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            }

            RadioGroup var9 = var10000.tipOptions;
            Intrinsics.checkNotNullExpressionValue(var9, "binding.tipOptions");
            double var10;
            switch(var9.getCheckedRadioButtonId()) {
                case 1000046:
                    var10 = 0.2D;
                    break;
                case 1000129:
                    var10 = 0.18D;
                    break;
                default:
                    var10 = 0.15D;
            }

            double tipPercentage = var10;
            double tip = tipPercentage * cost;
            var10000 = this.binding;
            if (var10000 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            }

            SwitchMaterial var11 = var10000.roundUpSwitch;
            Intrinsics.checkNotNullExpressionValue(var11, "binding.roundUpSwitch");
            boolean roundUp = var11.isChecked();
            if (roundUp) {
                tip = Math.ceil(tip);
                this.displayTip(tip);
            } else {
                this.displayTip(tip);
            }

        } else {
            this.displayTip(0.0D);
        }
    }

    @SuppressLint("ResourceType")
    private final void displayTip(double tip) {
        String formattedTip = NumberFormat.getCurrencyInstance().format(tip);
        ActivityMainBinding var10000 = this.binding;
        if (var10000 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        }

        TextView var4;
        var4 = var10000.tipResult;
        Intrinsics.checkNotNullExpressionValue(var4, "binding.tipResult");
        var4.setText((CharSequence)this.getString(R.string.tip_amount, new Object[]{formattedTip}));
    }

    private final boolean handleKeyEvent(View view, int keyCode) {
        if (keyCode == 66) {
            Object var10000 = this.getSystemService(Context.INPUT_METHOD_SERVICE);
            if (var10000 == null) {
                throw new NullPointerException("null cannot be cast to non-null type android.view.inputmethod.InputMethodManager");
            } else {
                InputMethodManager inputMethodManager = (InputMethodManager)var10000;
                inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
                return true;
            }
        } else {
            return false;
        }
    }
}