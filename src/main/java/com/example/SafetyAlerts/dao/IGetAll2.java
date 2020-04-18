package com.example.SafetyAlerts.dao;

import java.util.List;
import java.util.Optional;

public interface IGetAll2<T> {

    Optional<T> get(long id);

    List<T> getAll();

    void save(T t);

    void update(T t, int index);

    void delete(List<T> t);

}
