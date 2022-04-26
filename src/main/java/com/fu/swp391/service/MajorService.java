package com.fu.swp391.service;

import com.fu.swp391.entities.Major;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public interface MajorService {
    List<Major> findAll();

    List<Major> findById(long id);

    List<Major> findAll(Sort sort);

    List<Major> findAllById(Iterable<Long> longs);

    <S extends Major> List<S> saveAll(Iterable<S> entities);

    void flush();

    <S extends Major> S saveAndFlush(S entity);

    <S extends Major> List<S> saveAllAndFlush(Iterable<S> entities);

    @Deprecated
    void deleteInBatch(Iterable<Major> entities);

    void deleteAllInBatch(Iterable<Major> entities);

    void deleteAllByIdInBatch(Iterable<Long> longs);

    void deleteAllInBatch();

    @Deprecated
    Major getOne(Long aLong);

    Major getById(Long aLong);

    <S extends Major> List<S> findAll(Example<S> example);

    <S extends Major> List<S> findAll(Example<S> example, Sort sort);

    Page<Major> findAll(Pageable pageable);

    <S extends Major> S save(S entity);

    Optional<Major> findById(Long aLong);

    boolean existsById(Long aLong);

    long count();

    void deleteById(Long aLong);

    void delete(Major entity);

    void deleteAllById(Iterable<? extends Long> longs);

    void deleteAll(Iterable<? extends Major> entities);

    void deleteAll();

    <S extends Major> Optional<S> findOne(Example<S> example);

    <S extends Major> Page<S> findAll(Example<S> example, Pageable pageable);

    <S extends Major> long count(Example<S> example);

    <S extends Major> boolean exists(Example<S> example);

    <S extends Major, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction);
}
