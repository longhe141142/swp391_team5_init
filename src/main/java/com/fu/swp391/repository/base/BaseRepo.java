package com.fu.swp391.repository.base;

import com.fu.swp391.entities.base.BaseEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BaseRepo extends CrudRepository<BaseEntity, Long> {
}
