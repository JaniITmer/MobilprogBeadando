package com.janos.nagy.beadando_dreql5;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

public class SharedViewModel extends AndroidViewModel {
    private final MutableLiveData<String> selectedItem = new MutableLiveData<>();
    private final ItemRepository repository;
    private final LiveData<List<Item>> allItems;

    public SharedViewModel(Application application) {
        super(application);
        repository = new ItemRepository(application);
        allItems = repository.getAllItems();
    }

    public void selectItem(String item) {
        selectedItem.setValue(item);
        repository.insert(new Item(item));
    }

    public LiveData<String> getSelectedItem() {
        return selectedItem;
    }

    public LiveData<List<Item>> getAllItems() {
        return allItems;
    }
}