package com.example.examplemod.action;

import io.github.apace100.calio.data.SerializableData;

public interface IActionData<T> {
    
    default SerializableData getSerializableData() {
        return new SerializableData();
    }
    
    void execute(SerializableData.Instance data, T instance);
    
}
