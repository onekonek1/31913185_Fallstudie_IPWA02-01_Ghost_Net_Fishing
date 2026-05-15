package com.iu.ghostnet.controller;

import com.iu.ghostnet.model.*;
import com.iu.ghostnet.service.GhostNetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/nets")
public class GhostNetController {
    @Autowired
    private GhostNetService netService;

    @GetMapping("/all")
    public String listNets(Model model) {
        model.addAttribute("nets", netService.findAll());
        return "all-nets";
    }

    @GetMapping("/report")
    public String showReportForm(Model model) {
        GhostNet net = new GhostNet();
        net.setReportingPerson(new Person());
        model.addAttribute("ghostNet", net);
        return "report-net";
    }

    @PostMapping("/report")
    public String processReport(@ModelAttribute GhostNet ghostNet) {
        // Prüfung auf Anonymität: Wenn kein Name angegeben wurde, 
        // wird kein Personen-Objekt verknüpft.
        if (ghostNet.getReportingPerson().getName().isEmpty()) {
            ghostNet.setReportingPerson(null);
        }
        ghostNet.setStatus(Status.GEMELDET);
        netService.save(ghostNet);
        return "redirect:/nets/all";
    }

    @GetMapping("/salvage/{id}")
    public String showSalvageForm(@PathVariable Long id, Model model) {
        // Abruf des Netzes aus dem Service-Layer mittels Stream-API
        GhostNet net = netService.findAll().stream()
                .filter(n -> n.getId().equals(id))
                .findFirst()
                .orElseThrow();

        model.addAttribute("net", net); // Datenweitergabe an die View
        model.addAttribute("salvager", new Person());
        return "salvage-net";
    }

    @PostMapping("/salvage/{id}")
    public String processSalvage(@PathVariable Long id, @ModelAttribute Person salvager) {
        netService.startSalvaging(id, salvager);
        return "redirect:/nets/all";
    }

    @GetMapping("/salvage/complete/{id}")
    public String completeSalvage(@PathVariable Long id) {
        netService.markAsSalvaged(id);
        return "redirect:/nets/all";
    }

    @GetMapping("/missing/{id}")
    public String showMissingForm(@PathVariable Long id, Model model) {
        model.addAttribute("netId", id);
        model.addAttribute("person", new Person()); // Person für die Validierung (nicht anonym)
        return "report-missing";
    }

    @PostMapping("/missing/{id}")
    public String processMissing(@PathVariable Long id, @ModelAttribute Person person) {
        // Die Logik im Service prüft, ob ein Name angegeben wurde
        netService.markAsMissing(id, person);
        return "redirect:/nets/all";
    }
}