package com.ellen.demo.repository;

import com.ellen.demo.domain.Stuff;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StuffRepository extends JpaRepository<Stuff, Integer>{

    public List<Stuff> findStuffsByName(String name);
}
