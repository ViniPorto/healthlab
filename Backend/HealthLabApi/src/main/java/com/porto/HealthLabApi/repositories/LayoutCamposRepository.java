package com.porto.HealthLabApi.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.porto.HealthLabApi.domain.layout.Layout;
import com.porto.HealthLabApi.domain.layout.LayoutCampos;

@Repository
public interface LayoutCamposRepository extends JpaRepository<LayoutCampos, Long> {

    List<LayoutCampos> findByLayout(Layout layout);
    
}
