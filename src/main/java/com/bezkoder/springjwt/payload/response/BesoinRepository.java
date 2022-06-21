package com.bezkoder.springjwt.payload.response;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bezkoder.springjwt.models.Besoin;

@Repository
public interface BesoinRepository extends JpaRepository<Besoin, Integer> {

}
