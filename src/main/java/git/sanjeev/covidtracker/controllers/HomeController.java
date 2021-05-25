package git.sanjeev.covidtracker.controllers;

import git.sanjeev.covidtracker.dataModels.LocationStats;
import git.sanjeev.covidtracker.services.covidDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    covidDataService dataService;

    @GetMapping("/")
    public String home(Model model){
        List<LocationStats> allStats = dataService.getAllStats();
        Integer totalCases = allStats.stream().mapToInt(stat -> stat.getTotalCases()).sum();
        Integer diffFromPreviousDay = allStats.stream().mapToInt(stat -> stat.getDiffFromPreviousDay()).sum();

        model.addAttribute("totalCases", totalCases);
        model.addAttribute("diffCases", diffFromPreviousDay);
        model.addAttribute("LocationStats", dataService.getAllStats());
        return "home";
    }
}
