package com.raksha.carbfp.controller;

import com.raksha.carbfp.model.Entry;
import com.raksha.carbfp.model.User;
import com.raksha.carbfp.service.EntryService;
import com.raksha.carbfp.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.*;

@Controller
public class DashboardController {

    private final UserService userService;
    private final EntryService entryService;

    public DashboardController(UserService userService, EntryService entryService) {
        this.userService = userService;
        this.entryService = entryService;
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model, Authentication auth) {
        if (auth == null || auth.getName() == null) {
            return "redirect:/login"; // ensure user is logged in
        }

        User user = userService.findByUsername(auth.getName());
        List<Entry> entries = entryService.findByUser(user);

        // Prepare chart data
        Map<String, Double> activityMap = new LinkedHashMap<>();
        for (Entry e : entries) {
            activityMap.put(
                e.getActivity(),
                activityMap.getOrDefault(e.getActivity(), 0.0) + e.getCarbonEmission()
            );
        }

        model.addAttribute("entries", entries);
        model.addAttribute("totalCO2", entries.stream().mapToDouble(Entry::getCarbonEmission).sum());
        model.addAttribute("chartLabels", new ArrayList<>(activityMap.keySet()));
        model.addAttribute("chartValues", new ArrayList<>(activityMap.values()));

        return "dashboard";
    }
        @GetMapping("/profile")
        public String profile(Authentication auth, Model model) {
        User user = userService.findByUsername(auth.getName());
        var entries = entryService.findByUser(user);
        double totalCO2 = entries.stream()
                .mapToDouble(e -> e.getCarbonEmission())
                .sum();

        model.addAttribute("role", user.getRole());
        model.addAttribute("totalCO2", totalCO2);
        return "profile";
    }
}
