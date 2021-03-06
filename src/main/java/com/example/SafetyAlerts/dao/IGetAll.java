package com.example.SafetyAlerts.dao;

import java.util.List;

public interface IGetAll<T> {

    List<T> getAll();

    void save(T t);

    void update(T t, int index);

    void delete(List<T> t);

}
