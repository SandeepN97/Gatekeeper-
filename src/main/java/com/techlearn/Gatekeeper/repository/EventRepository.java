package com.techlearn.Gatekeeper.repository;

import com.techlearn.Gatekeeper.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
}