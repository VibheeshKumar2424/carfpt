package com.raksha.carbfp.repository;

import com.raksha.carbfp.model.Entry;
import com.raksha.carbfp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EntryRepository extends JpaRepository<Entry, Long> {
    List<Entry> findByUser(User user);
}
