package com.example.PLANS_API.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.PLANS_API.entity.Plan;


public interface PlanRepo extends JpaRepository<Plan, Integer>{

}
