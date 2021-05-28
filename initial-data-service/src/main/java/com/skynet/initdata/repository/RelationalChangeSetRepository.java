package com.skynet.initdata.repository;

import com.skynet.initdata.entity.RelationalChangeSetEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RelationalChangeSetRepository extends JpaRepository<RelationalChangeSetEntity, Integer> {

}
