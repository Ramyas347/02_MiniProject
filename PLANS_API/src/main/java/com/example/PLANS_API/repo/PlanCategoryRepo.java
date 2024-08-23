package com.example.PLANS_API.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.PLANS_API.entity.PlanCategory;


public interface PlanCategoryRepo extends JpaRepository<PlanCategory, Integer>{

}