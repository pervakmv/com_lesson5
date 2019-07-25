package com.lesson5;

import com.lesson5.Exception.InternalServerErrorException;

import java.util.List;

public interface DAO<T> {
    public T save(T t) throws InternalServerErrorException;
    public T delete(T t) throws InternalServerErrorException;
    public T update(T t) throws InternalServerErrorException;
    public T findById(long id) throws InternalServerErrorException;


}
