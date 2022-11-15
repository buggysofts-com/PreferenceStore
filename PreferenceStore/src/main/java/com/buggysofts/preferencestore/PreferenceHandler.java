package com.buggysofts.preferencestore;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.Serializable;
import java.util.Set;

public abstract class PreferenceHandler {
    private final String preferenceName;
    private final SharedPreferences preferences;
    private final SharedPreferences.Editor preferencesEditor;

    public PreferenceHandler(@NonNull Context context,
                             @NonNull String preferenceName,
                             int mode,
                             boolean edit) {
        this.preferences = context.getSharedPreferences(
            (this.preferenceName = preferenceName),
            mode
        );

        if (edit) {
            this.preferencesEditor = this.preferences.edit();
        } else {
            this.preferencesEditor = null;
        }

        initializePreferenceValues(context);
    }

    /**
     * Define all the preference values in the implementation of this abstract method.
     * */
    public abstract void initializePreferenceValues(@NonNull Context context);


    //////////
    // getters
    //////////

    // bounded
    /**
     * Get value of the specified preference.
     * @param preference The preference from which we want our value.
     * @param defaultOverride Optional value to override the actual return from underlying {@link SharedPreferences} instance.
     *                        Although it is declared as a vararg, you should pass at most one value. Others will be discarded.
     *
     * @return If the key exists, returns its value. If the key doesn't exist and <b>defaultOverride</b> is specified,
     * <b>defaultOverride[0]</b> is returned, otherwise the default of the specified bounded preference will be returned.
     * */
    public boolean getPreferenceValue(@NonNull BoundedPreference<Boolean> preference,
                                      @NonNull boolean... defaultOverride) {
        return preferences.getBoolean(
            preference.getKey(),
            defaultOverride.length > 0 ? defaultOverride[0] : preference.getDefaultValue()
        );
    }
    /**
     * Get value of the specified preference.
     * @param preference The preference from which we want our value.
     * @param defaultOverride Optional value to override the actual return from underlying {@link SharedPreferences} instance.
     *                        Although it is declared as a vararg, you should pass at most one value. Others will be discarded.
     *
     * @return If the key exists, returns its value. If the key doesn't exist and <b>defaultOverride</b> is specified,
     * <b>defaultOverride[0]</b> is returned, otherwise the default of the specified bounded preference will be returned.
     * */
    public int getPreferenceValue(@NonNull BoundedPreference<Integer> preference,
                                  @NonNull int... defaultOverride) {
        return preferences.getInt(
            preference.getKey(),
            defaultOverride.length > 0 ? defaultOverride[0] : preference.getDefaultValue()
        );
    }
    /**
     * Get value of the specified preference.
     * @param preference The preference from which we want our value.
     * @param defaultOverride Optional value to override the actual return from underlying {@link SharedPreferences} instance.
     *                        Although it is declared as a vararg, you should pass at most one value. Others will be discarded.
     *
     * @return If the key exists, returns its value. If the key doesn't exist and <b>defaultOverride</b> is specified,
     * <b>defaultOverride[0]</b> is returned, otherwise the default of the specified bounded preference will be returned.
     * */
    public long getPreferenceValue(@NonNull BoundedPreference<Long> preference,
                                   @NonNull long... defaultOverride) {
        return preferences.getLong(
            preference.getKey(),
            defaultOverride.length > 0 ? defaultOverride[0] : preference.getDefaultValue()
        );
    }
    /**
     * Get value of the specified preference.
     * @param preference The preference from which we want our value.
     * @param defaultOverride Optional value to override the actual return from underlying {@link SharedPreferences} instance.
     *                        Although it is declared as a vararg, you should pass at most one value. Others will be discarded.
     *
     * @return If the key exists, returns its value. If the key doesn't exist and <b>defaultOverride</b> is specified,
     * <b>defaultOverride[0]</b> is returned, otherwise the default of the specified bounded preference will be returned.
     * */
    public float getPreferenceValue(@NonNull BoundedPreference<Float> preference,
                                    @NonNull float... defaultOverride) {
        return preferences.getFloat(
            preference.getKey(),
            defaultOverride.length > 0 ? defaultOverride[0] : preference.getDefaultValue()
        );
    }
    /**
     * Get value of the specified preference.
     * @param preference The preference from which we want our value.
     * @param defaultOverride Optional value to override the actual return from underlying {@link SharedPreferences} instance.
     *                        Although it is declared as a vararg, you should pass at most one value. Others will be discarded.
     *
     * @return If the key exists, returns its value. If the key doesn't exist and <b>defaultOverride</b> is specified,
     * <b>defaultOverride[0]</b> is returned, otherwise the default of the specified bounded preference will be returned.
     * */
    @NonNull
    public String getPreferenceValue(@NonNull BoundedPreference<String> preference,
                                     @NonNull String... defaultOverride) {
        return preferences.getString(
            preference.getKey(),
            defaultOverride.length > 0 ? defaultOverride[0] : preference.getDefaultValue()
        );
    }
    /**
     * Get value of the specified preference.
     * @param preference The preference from which we want our value.
     * @param defaultOverride Optional value to override the actual return from underlying {@link SharedPreferences} instance.
     *                        Although it is declared as a vararg, you should pass at most one value. Others will be discarded.
     *
     * @return If the key exists, returns its value. If the key doesn't exist and <b>defaultOverride</b> is specified,
     * <b>defaultOverride[0]</b> is returned, otherwise the default of the specified bounded preference will be returned.
     * */
    @NonNull
    public Set<String> getPreferenceValue(@NonNull BoundedPreference<Set<String>> preference,
                                          @NonNull Set<String>... defaultOverride) {
        return preferences.getStringSet(
            preference.getKey(),
            defaultOverride.length > 0 ? defaultOverride[0] : preference.getDefaultValue()
        );
    }
    /**
     * Get value of the specified preference.
     * @param preference The preference from which we want our value.
     * @param typeToken {@link TypeToken} of the return type.
     *
     * @return If the key exists, returns its value, otherwise the default of the specified bounded preference will be returned.
     * */
    @Nullable
    public <T extends Serializable> T getPreferenceValue(@NonNull BoundedPreference<T> preference,
                                                         @NonNull TypeToken<T> typeToken) {
        String json = null;
        try {
            json = preferences.getString(preference.getKey(), null);
        } catch (Exception e){
            e.printStackTrace();
        }
        if(json != null){
            return new Gson().fromJson(json, typeToken.getType());
        }
        return null;
    }

    // unbounded
    /**
     * Get value of the specified preference.
     * @param preference The preference from which we want our value.
     * @param defaultOverride Value to return if the preference is not present.
     *
     * @return If the key exists, returns its value, else, otherwise returns <b>defaultOverride</b>.
     * */
    public boolean getPreferenceValue(@NonNull UnBoundedPreference<Boolean> preference, boolean defaultOverride) {
        return preferences.getBoolean(
            preference.getKey(),
            defaultOverride
        );
    }
    /**
     * Get value of the specified preference.
     * @param preference The preference from which we want our value.
     * @param defaultOverride Value to return if the preference is not present.
     *
     * @return If the key exists, returns its value, else, otherwise returns <b>defaultOverride</b>.
     * */
    public int getPreferenceValue(@NonNull UnBoundedPreference<Integer> preference, int defaultOverride) {
        return preferences.getInt(
            preference.getKey(),
                defaultOverride
        );
    }
    /**
     * Get value of the specified preference.
     * @param preference The preference from which we want our value.
     * @param defaultOverride Value to return if the preference is not present.
     *
     * @return If the key exists, returns its value, else, otherwise returns <b>defaultOverride</b>.
     * */
    public Long getPreferenceValue(@NonNull UnBoundedPreference<Long> preference, long defaultOverride) {
        return preferences.getLong(
            preference.getKey(),
                defaultOverride
        );
    }
    /**
     * Get value of the specified preference.
     * @param preference The preference from which we want our value.
     * @param defaultOverride Value to return if the preference is not present.
     *
     * @return If the key exists, returns its value, else, otherwise returns <b>defaultOverride</b>.
     * */
    public float getPreferenceValue(@NonNull UnBoundedPreference<Float> preference, float defaultOverride) {
        return preferences.getFloat(
            preference.getKey(),
                defaultOverride
        );
    }
    /**
     * Get value of the specified preference.
     * @param preference The preference from which we want our value.
     * @param defaultOverride Value to return if the preference is not present.
     *
     * @return If the key exists, returns its value, else, otherwise returns <b>defaultOverride</b>.
     * */
    @Nullable
    public String getPreferenceValue(@NonNull UnBoundedPreference<String> preference,
                                     @NonNull String defaultOverride) {
        return preferences.getString(
            preference.getKey(),
                defaultOverride
        );
    }
    /**
     * Get value of the specified preference.
     * @param preference The preference from which we want our value.
     * @param defaultOverride Value to return if the preference is not present.
     *
     * @return If the key exists, returns its value, else, otherwise returns <b>defaultOverride</b>.
     * */
    @Nullable
    public Set<String> getPreferenceValue(@NonNull UnBoundedPreference<Set<String>> preference,
                                          @Nullable Set<String> defaultOverride) {
        return preferences.getStringSet(
            preference.getKey(),
                defaultOverride
        );
    }
    /**
     * Get value of the specified preference.
     * @param preference The preference from which we want our value.
     * @param typeToken {@link TypeToken} of the return type.
     *
     * @return If the key exists, returns its value, else, otherwise returns null.
     * */
    @Nullable
    public <T extends Serializable> T getPreferenceValue(@NonNull UnBoundedPreference<T> preference,
                                                         @NonNull TypeToken<T> typeToken) {
        String json = null;
        try {
            json = preferences.getString(preference.getKey(), null);
        } catch (Exception e){
            e.printStackTrace();
        }
        if(json != null){
            return new Gson().fromJson(json, typeToken.getType());
        }
        return null;
    }

    // setters
    // bounded
    /**
     * Set the specified value to the specified preference.
     * @param preference The preference which we want to modify (or add).
     * @param value Value that we want to apply to the preference.
     * @return If successful, returns the same value that we passed in, else an exception is thrown.
     * @throws RuntimeException if not in editor mode,, if the specified value is unsupported by the specified preference.
     * */
    @NonNull
    public Boolean setPreferenceValue(@NonNull BoundedPreference<Boolean> preference,
                                      @NonNull Boolean value) throws RuntimeException {
        if (preferencesEditor == null) {
            throw new RuntimeException("You are not in editor mode");
        } else {
            Boolean selectedValue = null;
            Boolean[] allValues = preference.getAllValues();
            for (int i = 0; i < allValues.length; i++) {
                if(allValues[i].equals(value)){
                    selectedValue = allValues[i];
                }
            }
            if(selectedValue != null) {
                preferencesEditor.putBoolean(
                    preference.getKey(),
                    selectedValue
                );
                // apply change inside in-memory preference object and schedule commit
                preferencesEditor.apply();
                // return the set value
                return selectedValue;
            } else {
                throw new RuntimeException("Request to insert an unsupported value.");
            }
        }
    }
    /**
     * Set the specified value to the specified preference.
     * @param preference The preference which we want to modify (or add).
     * @param value Value that we want to apply to the preference.
     * @return If successful, returns the same value that we passed in, else an exception is thrown.
     * @throws RuntimeException if not in editor mode,, if the specified value is unsupported by the specified preference.
     * */
    @NonNull
    public Integer setPreferenceValue(@NonNull BoundedPreference<Integer> preference,
                                      @NonNull Integer value) throws RuntimeException {
        if (preferencesEditor == null) {
            throw new RuntimeException("You are not in editor mode");
        } else {
            Integer selectedValue = null;
            Integer[] allValues = preference.getAllValues();
            for (int i = 0; i < allValues.length; i++) {
                if(allValues[i].equals(value)){
                    selectedValue = allValues[i];
                }
            }
            if(selectedValue != null) {
                preferencesEditor.putInt(
                    preference.getKey(),
                    selectedValue
                );
                // apply change inside in-memory preference object and schedule commit
                preferencesEditor.apply();
                // return the set value
                return selectedValue;
            } else {
                throw new RuntimeException("Request to insert an unsupported value.");
            }
        }
    }
    /**
     * Set the specified value to the specified preference.
     * @param preference The preference which we want to modify (or add).
     * @param value Value that we want to apply to the preference.
     * @return If successful, returns the same value that we passed in, else an exception is thrown.
     * @throws RuntimeException if not in editor mode,, if the specified value is unsupported by the specified preference.
     * */
    @NonNull
    public Long setPreferenceValue(@NonNull BoundedPreference<Long> preference,
                                   @NonNull Long value) throws RuntimeException {
        if (preferencesEditor == null) {
            throw new RuntimeException("You are not in editor mode");
        } else {
            Long selectedValue = null;
            Long[] allValues = preference.getAllValues();
            for (int i = 0; i < allValues.length; i++) {
                if(allValues[i].equals(value)){
                    selectedValue = allValues[i];
                }
            }
            if(selectedValue != null) {
                preferencesEditor.putLong(
                    preference.getKey(),
                    selectedValue
                );
                // apply change inside in-memory preference object and schedule commit
                preferencesEditor.apply();
                // return the set value
                return selectedValue;
            } else {
                throw new RuntimeException("Request to insert an unsupported value.");
            }
        }
    }
    /**
     * Set the specified value to the specified preference.
     * @param preference The preference which we want to modify (or add).
     * @param value Value that we want to apply to the preference.
     * @return If successful, returns the same value that we passed in, else an exception is thrown.
     * @throws RuntimeException if not in editor mode,, if the specified value is unsupported by the specified preference.
     * */
    @NonNull
    public Float setPreferenceValue(@NonNull BoundedPreference<Float> preference,
                                    @NonNull Float value) throws RuntimeException {
        if (preferencesEditor == null) {
            throw new RuntimeException("You are not in editor mode");
        } else {
            Float selectedValue = null;
            Float[] allValues = preference.getAllValues();
            for (int i = 0; i < allValues.length; i++) {
                if(allValues[i].equals(value)){
                    selectedValue = allValues[i];
                }
            }
            if(selectedValue != null) {
                preferencesEditor.putFloat(
                    preference.getKey(),
                    selectedValue
                );
                // apply change inside in-memory preference object and schedule commit
                preferencesEditor.apply();
                // return the set value
                return selectedValue;
            } else {
                throw new RuntimeException("Request to insert an unsupported value.");
            }
        }
    }
    /**
     * Set the specified value to the specified preference.
     * @param preference The preference which we want to modify (or add).
     * @param value Value that we want to apply to the preference.
     * @return If successful, returns the same value that we passed in, else an exception is thrown.
     * @throws RuntimeException if not in editor mode,, if the specified value is unsupported by the specified preference.
     * */
    @NonNull
    public String setPreferenceValue(@NonNull BoundedPreference<String> preference,
                                     @NonNull String value) throws RuntimeException {
        if (preferencesEditor == null) {
            throw new RuntimeException("You are not in editor mode");
        } else {
            String selectedValue = null;
            String[] allValues = preference.getAllValues();
            for (int i = 0; i < allValues.length; i++) {
                if(allValues[i].equals(value)){
                    selectedValue = allValues[i];
                }
            }
            if(selectedValue != null) {
                preferencesEditor.putString(
                    preference.getKey(),
                    selectedValue
                );
                // apply change inside in-memory preference object and schedule commit
                preferencesEditor.apply();
                // return the set value
                return selectedValue;
            } else {
                throw new RuntimeException("Request to insert an unsupported value.");
            }
        }
    }
    /**
     * Set the specified value to the specified preference.
     * @param preference The preference which we want to modify (or add).
     * @param value Value that we want to apply to the preference.
     * @return If successful, returns the same value that we passed in, else an exception is thrown.
     * @throws RuntimeException if not in editor mode,, if the specified value is unsupported by the specified preference.
     * */
    @NonNull
    public Set<String> setPreferenceValue(@NonNull BoundedPreference<Set<String>> preference,
                                          @NonNull Set<String> value) throws RuntimeException {
        if (preferencesEditor == null) {
            throw new RuntimeException("You are not in editor mode");
        } else {
            Set<String> selectedValue = null;
            Set<String>[] allValues = preference.getAllValues();
            for (int i = 0; i < allValues.length; i++) {
                if(allValues[i].equals(value)){
                    selectedValue = allValues[i];
                }
            }
            if(selectedValue != null) {
                preferencesEditor.putStringSet(
                    preference.getKey(),
                    selectedValue
                );
                // apply change inside in-memory preference object and schedule commit
                preferencesEditor.apply();
                // return the set value
                return selectedValue;
            } else {
                throw new RuntimeException("Request to insert an unsupported value.");
            }
        }
    }
    /**
     * Set the specified value to the specified preference.
     * @param preference The preference which we want to modify (or add).
     * @param value Value that we want to apply to the preference.
     * @return If successful, returns the same value that we passed in, else an exception is thrown.
     * @throws RuntimeException if not in editor mode,, if the specified value is unsupported by the specified preference.
     * */
    @NonNull
    public <T extends Serializable> T setPreferenceValue(@NonNull BoundedPreference<T> preference,
                                                         @NonNull T value) throws RuntimeException {
        if (preferencesEditor == null) {
            throw new RuntimeException("You are not in editor mode");
        } else {
            T selectedValue = null;
            T[] allValues = preference.getAllValues();
            for (int i = 0; i < allValues.length; i++) {
                if(allValues[i].equals(value)){
                    selectedValue = allValues[i];
                }
            }
            if(selectedValue != null) {
                preferencesEditor.putString(
                    preference.getKey(),
                    new Gson().toJson(selectedValue, TypeToken.get(selectedValue.getClass()).getType())
                );
                // apply change inside in-memory preference object and schedule commit
                preferencesEditor.apply();
                // return the set value
                return selectedValue;
            } else {
                throw new RuntimeException("Request to insert an unsupported value.");
            }
        }
    }
    // unbounded
    /**
     * Set the specified value to the specified preference.
     * @param preference The preference which we want to modify (or add).
     * @param value Value that we want to apply to the preference.
     * @return If successful, returns the same value that we passed in, else an exception is thrown.
     * @throws RuntimeException if not in editor mode,, if the specified value is unsupported by the specified preference.
     * */
    @NonNull
    public Boolean setPreferenceValue(@NonNull UnBoundedPreference<Boolean> preference,
                                      @NonNull Boolean value) throws RuntimeException {
        if (preferencesEditor == null) {
            throw new RuntimeException("You are not in editor mode");
        } else {
            preferencesEditor.putBoolean(
                preference.getKey(),
                value
            );
            // apply change inside in-memory preference object and schedule commit
            preferencesEditor.apply();
            // return the set value
            return value;
        }
    }
    /**
     * Set the specified value to the specified preference.
     * @param preference The preference which we want to modify (or add).
     * @param value Value that we want to apply to the preference.
     * @return If successful, returns the same value that we passed in, else an exception is thrown.
     * @throws RuntimeException if not in editor mode,, if the specified value is unsupported by the specified preference.
     * */
    @NonNull
    public Integer setPreferenceValue(@NonNull UnBoundedPreference<Integer> preference,
                                      @NonNull Integer value) throws RuntimeException {
        if (preferencesEditor == null) {
            throw new RuntimeException("You are not in editor mode");
        } else {
            preferencesEditor.putInt(
                preference.getKey(),
                value
            );
            // apply change inside in-memory preference object and schedule commit
            preferencesEditor.apply();
            // return the set value
            return value;
        }
    }
    /**
     * Set the specified value to the specified preference.
     * @param preference The preference which we want to modify (or add).
     * @param value Value that we want to apply to the preference.
     * @return If successful, returns the same value that we passed in, else an exception is thrown.
     * @throws RuntimeException if not in editor mode,, if the specified value is unsupported by the specified preference.
     * */
    @NonNull
    public Long setPreferenceValue(@NonNull UnBoundedPreference<Long> preference,
                                   @NonNull Long value) throws RuntimeException {
        if (preferencesEditor == null) {
            throw new RuntimeException("You are not in editor mode");
        } else {
            preferencesEditor.putLong(
                preference.getKey(),
                value
            );
            // apply change inside in-memory preference object and schedule commit
            preferencesEditor.apply();
            // return the set value
            return value;
        }
    }
    /**
     * Set the specified value to the specified preference.
     * @param preference The preference which we want to modify (or add).
     * @param value Value that we want to apply to the preference.
     * @return If successful, returns the same value that we passed in, else an exception is thrown.
     * @throws RuntimeException if not in editor mode,, if the specified value is unsupported by the specified preference.
     * */
    @NonNull
    public Float setPreferenceValue(@NonNull UnBoundedPreference<Float> preference,
                                    @NonNull Float value) throws RuntimeException {
        if (preferencesEditor == null) {
            throw new RuntimeException("You are not in editor mode");
        } else {
            preferencesEditor.putFloat(
                preference.getKey(),
                value
            );
            // apply change inside in-memory preference object and schedule commit
            preferencesEditor.apply();
            // return the set value
            return value;
        }
    }
    /**
     * Set the specified value to the specified preference.
     * @param preference The preference which we want to modify (or add).
     * @param value Value that we want to apply to the preference.
     * @return If successful, returns the same value that we passed in, else an exception is thrown.
     * @throws RuntimeException if not in editor mode,.
     * */
    @NonNull
    public String setPreferenceValue(@NonNull UnBoundedPreference<String> preference,
                                     @NonNull String value) throws RuntimeException {
        if (preferencesEditor == null) {
            throw new RuntimeException("You are not in editor mode");
        } else {
            preferencesEditor.putString(
                preference.getKey(),
                value
            );
            // apply change inside in-memory preference object and schedule commit
            preferencesEditor.apply();
            // return the set value
            return value;
        }
    }
    /**
     * Set the specified value to the specified preference.
     * @param preference The preference which we want to modify (or add).
     * @param value Value that we want to apply to the preference.
     * @return If successful, returns the same value that we passed in, else an exception is thrown.
     * @throws RuntimeException if not in editor mode,.
     * */
    @NonNull
    public Set<String> setPreferenceValue(@NonNull UnBoundedPreference<Set<String>> preference,
                                          @NonNull Set<String> value) throws RuntimeException {
        if (preferencesEditor == null) {
            throw new RuntimeException("You are not in editor mode");
        } else {
            preferencesEditor.putStringSet(
                preference.getKey(),
                value
            );
            // apply change inside in-memory preference object and schedule commit
            preferencesEditor.apply();
            // return the set value
            return value;
        }
    }
    /**
     * Set the specified value to the specified preference.
     * @param preference The preference which we want to modify (or add).
     * @param value Value that we want to apply to the preference.
     * @return If successful, returns the same value that we passed in, else an exception is thrown.
     * @throws RuntimeException if not in editor mode,.
     * */
    @NonNull
    public <T extends Serializable> T setPreferenceValue(@NonNull UnBoundedPreference<T> preference,
                                                         @NonNull T value) throws RuntimeException {
        if (preferencesEditor == null) {
            throw new RuntimeException("You are not in editor mode");
        } else {
            preferencesEditor.putString(
                preference.getKey(),
                new Gson().toJson(value, TypeToken.get(value.getClass()).getType())
            );
            // apply change inside in-memory preference object and schedule commit
            preferencesEditor.apply();
            // return the set value
            return value;
        }
    }

    // initializers
    /**
     * Initialize the bounded preference. This call will return the passed preference immediately (without any further operation) if the preference is already available within the current underlying shared preference.
     * @param preference The preference which we are initializing.
     * @param overrideDefault Optional value to override the default of the specified bounded preference.
     *                        If it is not provided, the default value of the specified bounded preference will be used to initialize the preference.
     *
     * @return the same bounded preference that was passed in.
     * */
    @NonNull
    public BoundedPreference<Boolean> initializePreference(@NonNull BoundedPreference<Boolean> preference,
                                                           @NonNull Boolean... overrideDefault) throws RuntimeException {
        // if already available, do not overwrite
        if(contains(preference)) return preference;

        Boolean defaultValue = preference.getDefaultValue();
        if(overrideDefault.length > 0){
            // this will not be null here
            setPreferenceValue(
                preference,
                overrideDefault[0]
            );
        } else {
            setPreferenceValue(
                preference,
                defaultValue
            );
        }
        return preference;
    }
    /**
     * Initialize the bounded preference. This call will return the passed preference immediately (without any further operation) if the preference is already available within the current underlying shared preference.
     * @param preference The preference which we are initializing.
     * @param overrideDefault Optional value to override the default of the specified bounded preference.
     *                        If it is not provided, the default value of the specified bounded preference will be used to initialize the preference.
     *
     * @return the same bounded preference that was passed in.
     * */
    @NonNull
    public BoundedPreference<Integer> initializePreference(@NonNull BoundedPreference<Integer> preference,
                                                           @NonNull Integer... overrideDefault) throws RuntimeException {
        // if already available, do not overwrite
        if(contains(preference)) return preference;

        Integer defaultValue = preference.getDefaultValue();
        if(overrideDefault.length > 0){
            // this will not be null here
            setPreferenceValue(
                preference,
                overrideDefault[0]
            );
        } else {
            setPreferenceValue(
                preference,
                defaultValue
            );
        }
        return preference;
    }
    /**
     * Initialize the bounded preference. This call will return the passed preference immediately (without any further operation) if the preference is already available within the current underlying shared preference.
     * @param preference The preference which we are initializing.
     * @param overrideDefault Optional value to override the default of the specified bounded preference.
     *                        If it is not provided, the default value of the specified bounded preference will be used to initialize the preference.
     *
     * @return the same bounded preference that was passed in.
     * */
    @NonNull
    public BoundedPreference<Long> initializePreference(@NonNull BoundedPreference<Long> preference,
                                                        @NonNull Long... overrideDefault) throws RuntimeException {
        // if already available, do not overwrite
        if(contains(preference)) return preference;

        Long defaultValue = preference.getDefaultValue();
        if(overrideDefault.length > 0){
            // this will not be null here
            setPreferenceValue(
                preference,
                overrideDefault[0]
            );
        } else {
            setPreferenceValue(
                preference,
                defaultValue
            );
        }
        return preference;
    }
    /**
     * Initialize the bounded preference. This call will return the passed preference immediately (without any further operation) if the preference is already available within the current underlying shared preference.
     * @param preference The preference which we are initializing.
     * @param overrideDefault Optional value to override the default of the specified bounded preference.
     *                        If it is not provided, the default value of the specified bounded preference will be used to initialize the preference.
     *
     * @return the same bounded preference that was passed in.
     * */
    @NonNull
    public BoundedPreference<Float> initializePreference(@NonNull BoundedPreference<Float> preference,
                                                         @NonNull Float... overrideDefault) throws RuntimeException {
        // if already available, do not overwrite
        if(contains(preference)) return preference;

        Float defaultValue = preference.getDefaultValue();
        if(overrideDefault.length > 0){
            // this will not be null here
            setPreferenceValue(
                preference,
                overrideDefault[0]
            );
        } else {
            setPreferenceValue(
                preference,
                defaultValue
            );
        }
        return preference;
    }
    /**
     * Initialize the bounded preference. This call will return the passed preference immediately (without any further operation) if the preference is already available within the current underlying shared preference.
     * @param preference The preference which we are initializing.
     * @param overrideDefault Optional value to override the default of the specified bounded preference.
     *                        If it is not provided, the default value of the specified bounded preference will be used to initialize the preference.
     *
     * @return the same bounded preference that was passed in.
     * */
    @NonNull
    public BoundedPreference<String> initializePreference(@NonNull BoundedPreference<String> preference,
                                                          @NonNull String... overrideDefault) throws RuntimeException {
        // if already available, do not overwrite
        if(contains(preference)) return preference;

        String defaultValue = preference.getDefaultValue();
        if(overrideDefault.length > 0){
            // this will not be null here
            setPreferenceValue(
                preference,
                overrideDefault[0]
            );
        } else {
            setPreferenceValue(
                preference,
                defaultValue
            );
        }
        return preference;
    }
    /**
     * Initialize the bounded preference. This call will return the passed preference immediately (without any further operation) if the preference is already available within the current underlying shared preference.
     * @param preference The preference which we are initializing.
     * @param overrideDefault Optional value to override the default of the specified bounded preference.
     *                        If it is not provided, the default value of the specified bounded preference will be used to initialize the preference.
     *
     * @return the same bounded preference that was passed in.
     * */
    @SafeVarargs
    @NonNull
    public final BoundedPreference<Set<String>> initializePreference(@NonNull BoundedPreference<Set<String>> preference,
                                                                     @NonNull Set<String>... overrideDefault) throws RuntimeException {
        // if already available, do not overwrite
        if(contains(preference)) return preference;

        Set<String> defaultValue = preference.getDefaultValue();
        if(overrideDefault.length > 0){
            // this will not be null here
            setPreferenceValue(
                preference,
                overrideDefault[0]
            );
        } else {
            setPreferenceValue(
                preference,
                defaultValue
            );
        }
        return preference;
    }
    /**
     * Initialize the bounded preference. This call will return the passed preference immediately (without any further operation) if the preference is already available within the current underlying shared preference.
     * @param preference The preference which we are initializing.
     * @param overrideDefault Optional value to override the default of the specified bounded preference.
     *                        If it is not provided, the default value of the specified bounded preference will be used to initialize the preference.
     *
     * @return the same bounded preference that was passed in.
     * */
    @SafeVarargs
    @NonNull
    public final <T extends Serializable> BoundedPreference<T> initializePreference(BoundedPreference<T> preference,
                                                                                    @NonNull T... overrideDefault) throws RuntimeException {
        // if already available, do not overwrite
        if(contains(preference)) return preference;

        T defaultValue = preference.getDefaultValue();
        if(overrideDefault.length > 0){
            // this will not be null here
            setPreferenceValue(
                preference,
                overrideDefault[0]
            );
        } else {
            setPreferenceValue(
                preference,
                defaultValue
            );
        }
        return preference;
    }

    // removers
    /**
     * Remove the preference from the persistent storage.
     * */
    public void remove(@NonNull BoundedPreference<?> preference) {
        // if not in editor mode, return immediately.
        // this will not remove the preference. but the caller is aware about this.
        // and in this case he wouldn't call any methods that uses the editor anyway - so no worries.
        if (preferencesEditor == null) return;

        // request change
        preferencesEditor.remove(preference.getKey());
        // apply change inside in-memory preference object and schedule commit
        preferencesEditor.apply();
    }
    /**
     * Remove the preference from the persistent storage.
     * */
    public void remove(@NonNull UnBoundedPreference<?> preference) {
        // if not in editor mode, return immediately.
        // this will not remove the preference. but the caller is aware about this.
        // and in this case he wouldn't call any methods that uses the editor anyway - so no worries.
        if (preferencesEditor == null) return;

        // request change
        preferencesEditor.remove(preference.getKey());
        // apply change inside in-memory preference object and schedule commit
        preferencesEditor.apply();
    }

    // availability check
    /**
     * Whether the preference store contains the specified entry.
     * */
    public boolean contains(@NonNull BoundedPreference<?> preference) {
        return preferences.contains(preference.getKey());
    }
    /**
     * Whether the preference store contains the specified entry.
     * */
    public boolean contains(@NonNull UnBoundedPreference<?> preference) {
        return preferences.contains(preference.getKey());
    }

    // others - if any
}