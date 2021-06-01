package com.skynet.commons.repository;

import com.skynet.commons.models.Network;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NetworkRepository extends JpaRepository<Network, Integer> {

    Network findByNetworkSeqId(Integer networkSeqId);

    List<Network> findAllByOrderByUpdatedAtDateDesc();
}