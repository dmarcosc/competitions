package com.dmarcosc.competitions.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dmarcosc.competitions.models.Contest;

public interface ContestRepository extends JpaRepository<Contest, Long> {
	  List<Contest> findByStatus(boolean status);
	  List<Contest> findByTitleContaining(String title);
	}
