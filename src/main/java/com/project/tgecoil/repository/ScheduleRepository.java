package com.project.tgecoil.repository;

import com.project.tgecoil.model.dto.Schedule;
import com.project.tgecoil.model.dto.StatusScheduler;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Set;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    List<Schedule> findAllByUserId(String userId);

    List<Schedule> findAllByStatusIn(Set<StatusScheduler> status);
}
