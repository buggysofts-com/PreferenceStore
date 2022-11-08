package com.buggysofts.preferencestore;

import androidx.annotation.IntRange;
import androidx.annotation.NonNull;

/**
 * A preference which with a predefined set of non-null values, and a default value.
 * */

public class BoundedPreference<T> {
    private final String key;
    private final String desc;
    private final T[] allValues;
    private final Integer defaultValueIndex;

    /**
     * Create a bounded preference.
     * <br>
     * @param keyName Name of the preference. Should be of the form <b>pref_key_*</b>.
     * @param desc Description of the preference, its context, purpose & usability, and any other important information. This is not going to be used anywhere, the purpose of this is to remind the user what it is for and how to use it.
     * @param allValues All the supported values (by this preference). Each of the items must not be null.
     * @param defaultValueIndex Index of the value that should be used as default.
     *
     * @throws RuntimeException if any contract violation is found.
     * */
    public BoundedPreference(@NonNull String keyName,
                             @NonNull String desc,
                             @NonNull T[] allValues,
                             @NonNull Integer defaultValueIndex) {
        this.key = keyName;
        this.desc = desc;
        this.allValues = allValues;
        this.defaultValueIndex = defaultValueIndex;

        if(key == null){
            throw new RuntimeException("Key must not be null.");
        }
        if(allValues == null || allValues.length == 0){
            throw new RuntimeException("Empty value list is not allowed.");
        } else {
            for (int i = 0; i < allValues.length; i++) {
                if(allValues[i] == null){
                    throw new RuntimeException("Preference values can not be null.");
                }
            }
        }
        if(!(defaultValueIndex >= 0 && defaultValueIndex < allValues.length)){
            throw new RuntimeException("Invalid default value index. Make sure the default value index lies between 0 and allValue.length - 1.");
        }
    }

    /**
     * Get value at the specified index.
     * @throws RuntimeException if index is invalid.
     * */
    @NonNull
    public T getValueAtIndex(@IntRange(from = 0) int index){
        if(index > 0 && index < allValues.length){
            return allValues[index];
        }
        throw new RuntimeException("Invalid value index. Make sure the default value index lies between 0 and allValue.length - 1");
    }

    /**
     * Get all the available values.
     * */
    @NonNull
    public T[] getAllValues() {
        return allValues;
    }

    /**
     * Get the default value of this preference.
     * */
    @NonNull
    public T getDefaultValue() {
        return allValues[defaultValueIndex];
    }

    /**
     * Get key string.
     * */
    @NonNull
    public String getKey() {
        return key;
    }

    /**
     * Get key string.
     * */
    @NonNull
    public String getDescription() {
        return desc;
    }
}
