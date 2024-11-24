package com.devsuperior.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.devsuperior.Entitie.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>{

	
}