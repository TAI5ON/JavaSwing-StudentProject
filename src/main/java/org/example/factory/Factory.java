package org.example.factory;

import java.util.List;
import java.util.Optional;

public interface Factory<T> {

    Optional<T> findById(long id);
    List<T> findAll();

    default void registerUnit(T unit) {
        findAll().add(unit);
    }

    default void unregisterUnit(T unit) {
        findAll().remove(unit);
    }

    void loadAll(List<T> factoryContentToLoad);
}
