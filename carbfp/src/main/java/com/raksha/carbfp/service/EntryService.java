package com.raksha.carbfp.service;

import com.raksha.carbfp.model.Entry;
import com.raksha.carbfp.model.User;
import com.raksha.carbfp.repository.EntryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EntryService {
    private final EntryRepository entryRepository;

    public EntryService(EntryRepository entryRepository) {
        this.entryRepository = entryRepository;
    }

    public Entry save(Entry entry) {
        return entryRepository.save(entry);
    }

    public List<Entry> findByUser(User user) {
        return entryRepository.findByUser(user);
    }
}
