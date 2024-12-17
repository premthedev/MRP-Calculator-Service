package org.example.mrpapplication.Repository;

import org.example.mrpapplication.Model.Bom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BOMRepository extends JpaRepository<Bom, Long> {
    List<Bom> findByParentItem(String parentItem);
}

