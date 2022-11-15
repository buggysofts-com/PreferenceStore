package com.buggysofts.preferencestoreimpl;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.buggysofts.preferencestore.BoundedPreference;
import com.buggysofts.preferencestore.PreferenceHandler;
import com.buggysofts.preferencestore.UnBoundedPreference;
import com.google.gson.reflect.TypeToken;

import java.util.HashSet;
import java.util.Set;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        UnBoundedPreference<String> k = new UnBoundedPreference<>(
            "k",
            "k"
        );
        UnBoundedPreference<Set<String>> kk = new UnBoundedPreference<>(
            "kk",
            "kk"
        );
        UnBoundedPreference<SerializableModel> kkk = new UnBoundedPreference<>(
            "kkk",
            "kkk"
        );

        BoundedPreference<String> v = new BoundedPreference<>(
            "v",
            "v",
            new String[]{"1v", "2v"},
            0
        );
        BoundedPreference<Set<String>> vv = new BoundedPreference<>(
            "v",
            "v",
            new Set[]{
                new HashSet<String>(0),
                new HashSet<String>(0)
            },
            0
        );
        BoundedPreference<SerializableModel> vvv = new BoundedPreference<>(
            "kkk",
            "kkk",
            new SerializableModel[]{
                new SerializableModel(0, "xxx"),
                new SerializableModel(1, "yyy")
            },
            0
        );

        PreferenceHandler preferenceHandler = new PreferenceHandler(
            MainActivity.this,
            "app_preferences",
            MODE_PRIVATE,
            true
        ) {
            @Override
            public void initializePreferenceValues(@NonNull Context context) {
                // already initialized the sample preference
            }
        };

        // bounded
        // string
        preferenceHandler.getPreferenceValue(
            v,
            ""
        );
        // string set
        preferenceHandler.getPreferenceValue(
            vv,
            new HashSet<String>(0)
        );
        // serializable
        preferenceHandler.getPreferenceValue(
            vvv,
            new TypeToken<SerializableModel>() {
            }
        );

        // unbounded
        // string
        preferenceHandler.getPreferenceValue(
            k,
            ""
        );
        // string set
        preferenceHandler.getPreferenceValue(
            kk,
            null
        );
        // serializable
        preferenceHandler.getPreferenceValue(
            kkk,
            new TypeToken<SerializableModel>() {
            }
        );
    }
}