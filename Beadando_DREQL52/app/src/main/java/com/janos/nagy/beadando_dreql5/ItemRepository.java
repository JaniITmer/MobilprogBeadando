package com.janos.nagy.beadando_dreql5;

import android.app.Application;
import androidx.lifecycle.LiveData;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ItemRepository {
    private final ItemDao itemDao;
    private final LiveData<List<Item>> allItems;
    private final ExecutorService executorService;

    ItemRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        itemDao = db.itemDao();
        allItems = itemDao.getAll();
        executorService = Executors.newSingleThreadExecutor();
    }

    LiveData<List<Item>> getAllItems() {
        return allItems;
    }

    void insert(Item item) {
        executorService.execute(() -> itemDao.insertAll(item));
    }
}