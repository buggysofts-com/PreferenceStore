package com.buggysofts.preferencestoreimpl;

import java.io.Serializable;

class SerializableModel implements Serializable {
    private int index;
    private String name;

    public SerializableModel(int index, String name) {
        this.index = index;
        this.name = name;
    }

    public int getIndex() {
        return index;
    }

    public String getName() {
        return name;
    }
}
