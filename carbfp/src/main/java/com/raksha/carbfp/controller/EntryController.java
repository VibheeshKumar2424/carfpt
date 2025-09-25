package com.raksha.carbfp.controller;

import com.raksha.carbfp.model.Entry;
import com.raksha.carbfp.model.User;
import com.raksha.carbfp.service.EntryService;
import com.raksha.carbfp.service.UserService;

import java.time.LocalDate;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class EntryController {
    private final EntryService entryService;
    private final UserService userService;

    public EntryController(EntryService entryService, UserService userService) {
        this.entryService = entryService;
        this.userService = userService;
    }

    @GetMapping("/add")
    public String addEntryPage() {
        return "addEntry";
    }

    @PostMapping("/entries/add")
    public String addEntry(@ModelAttribute Entry entry, Authentication auth) {
    User user = userService.findByUsername(auth.getName());  // ✅ Works now
    entry.setUser(user);

    // Simple emission calculation
    switch (entry.getActivity()) {
        case "car" -> entry.setCarbonEmission(entry.getAmount() * 0.21);
        case "electricity" -> entry.setCarbonEmission(entry.getAmount() * 0.5);
        case "flight" -> entry.setCarbonEmission(entry.getAmount() * 90);
        case "meat" -> entry.setCarbonEmission(entry.getAmount() * 27);
    }
    entry.setDate(LocalDate.now());

    entryService.save(entry);
    return "redirect:/dashboard";
}
}
