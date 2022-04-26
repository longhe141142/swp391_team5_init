package com.fu.swp391.service;

import com.fu.swp391.entities.JobPost;
import com.fu.swp391.entities.Major;
import com.fu.swp391.repository.JobPostRepository;
import com.fu.swp391.repository.MajorRepository;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Service
public class JobPostServiceImpl implements JobPostService {
    MajorRepository majorRepository;

    JobPostRepository jobPostRepository;
    @Override
    public List<JobPost> findJobPostByCompanyId(long id) {
        return jobPostRepository.findJobPostByCompanyId(id);
    }

    @Override
    public List<JobPost> findCompanyMajorsByCompanyId(long id) {
        return jobPostRepository.findCompanyMajorsByCompanyId(id);
    }

    @Override
    public <S extends JobPost> S save(S entity) {
        return jobPostRepository.save(entity);
    }

    public JobPostServiceImpl(MajorRepository majorRepository) {
        this.majorRepository = majorRepository;
    }

    @Override
    public List<Major> findAll() {
        return majorRepository.findAll();
    }

    @Override
    public List<Major> findAll(Sort sort) {
        return majorRepository.findAll(sort);
    }

    @Override
    public List<Major> findAllById(Iterable<Long> longs) {
        return majorRepository.findAllById(longs);
    }

    @Override
    public <S extends Major> List<S> saveAll(Iterable<S> entities) {
        return majorRepository.saveAll(entities);
    }

    @Override
    public void flush() {
        majorRepository.flush();
    }

    @Override
    public <S extends Major> S saveAndFlush(S entity) {
        return majorRepository.saveAndFlush(entity);
    }

    @Override
    public <S extends Major> List<S> saveAllAndFlush(Iterable<S> entities) {
        return majorRepository.saveAllAndFlush(entities);
    }

    @Override
    @Deprecated
    public void deleteInBatch(Iterable<Major> entities) {
        majorRepository.deleteInBatch(entities);
    }

    @Override
    public void deleteAllInBatch(Iterable<Major> entities) {
        majorRepository.deleteAllInBatch(entities);
    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Long> longs) {
        majorRepository.deleteAllByIdInBatch(longs);
    }

    @Override
    public void deleteAllInBatch() {
        majorRepository.deleteAllInBatch();
    }

    @Override
    @Deprecated
    public Major getOne(Long aLong) {
        return majorRepository.getOne(aLong);
    }

    @Override
    public Major getById(Long aLong) {
        return majorRepository.getById(aLong);
    }

    @Override
    public <S extends Major> List<S> findAll(Example<S> example) {
        return majorRepository.findAll(example);
    }

    @Override
    public <S extends Major> List<S> findAll(Example<S> example, Sort sort) {
        return majorRepository.findAll(example, sort);
    }

    @Override
    public Page<Major> findAll(Pageable pageable) {
        return majorRepository.findAll(pageable);
    }

    @Override
    public <S extends Major> S save(S entity) {
        return majorRepository.save(entity);
    }

    @Override
    public Optional<Major> findById(Long aLong) {
        return majorRepository.findById(aLong);
    }

    @Override
    public boolean existsById(Long aLong) {
        return majorRepository.existsById(aLong);
    }

    @Override
    public long count() {
        return majorRepository.count();
    }

    @Override
    public void deleteById(Long aLong) {
        majorRepository.deleteById(aLong);
    }

    @Override
    public void delete(Major entity) {
        majorRepository.delete(entity);
    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {
        majorRepository.deleteAllById(longs);
    }

    @Override
    public void deleteAll(Iterable<? extends Major> entities) {
        majorRepository.deleteAll(entities);
    }

    @Override
    public void deleteAll() {
        majorRepository.deleteAll();
    }

    @Override
    public <S extends Major> Optional<S> findOne(Example<S> example) {
        return majorRepository.findOne(example);
    }

    @Override
    public <S extends Major> Page<S> findAll(Example<S> example, Pageable pageable) {
        return majorRepository.findAll(example, pageable);
    }

    @Override
    public <S extends Major> long count(Example<S> example) {
        return majorRepository.count(example);
    }

    @Override
    public <S extends Major> boolean exists(Example<S> example) {
        return majorRepository.exists(example);
    }

    @Override
    public <S extends Major, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return majorRepository.findBy(example, queryFunction);
    }
}
