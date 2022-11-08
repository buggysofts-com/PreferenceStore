package com.buggysofts.preferencestore;

import androidx.annotation.NonNull;

/**
 * A preference which with an unknown value, and no default value.
 * */

public class UnBoundedPreference<T> {
    private final String key;
    private final String desc;

    /**
     * Create a bounded preference.
     * <br>
     * @param keyName Name of the preference. Should be of the form <b>pref_key_*</b>.
     * @param desc Description of the preference, its context, purpose & usability, and any other important information. This is not going to be used anywhere, the purpose of this is to remind the user what it is for and how to use it.
     * */
    public UnBoundedPreference(@NonNull String keyName,
                               @NonNull String desc) {
        this.key = keyName;
        this.desc = desc;
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
