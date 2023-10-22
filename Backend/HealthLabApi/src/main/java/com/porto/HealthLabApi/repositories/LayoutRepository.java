package com.porto.HealthLabApi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.porto.HealthLabApi.domain.layout.Layout;

@Repository
public interface LayoutRepository extends JpaRepository<Layout, Long> {
    
}
