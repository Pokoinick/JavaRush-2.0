package com.javarush.task.task37.task3708.retrievers;

import com.javarush.task.task37.task3708.cache.LRUCache;
import com.javarush.task.task37.task3708.storage.Storage;

/**
 * Created by Станислав on 10.08.2017.
 */
public class CachingProxyRetriever implements Retriever {
    private LRUCache cache = new LRUCache(100);
    private OriginalRetriever retriever;

    public CachingProxyRetriever (Storage storage) {
        retriever = new OriginalRetriever(storage);
    }

    public Object retrieve(long id) {
        if (cache.find(id) == null) {
            Object o = retriever.retrieve(id);
            cache.set(id, o);
            return o;
        }
        return cache.find(id);
    }
}
