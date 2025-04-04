package com.spacex.marsops.arescore.controller;

import com.spacex.marsops.arescore.model.Resource;
import com.spacex.marsops.arescore.repository.AresCoreRepository;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequestMapping("/resources")
public class AresCoreController {

    /**
     * AresCore Repository.
     */
    @Autowired
    private AresCoreRepository aresCoreRepository;

    /**
     * Shows all Resources.
     * 
     * @param model
     * @return resources view showing all Resources.
     */
    @GetMapping
    public String showResources(Model model) {
        model.addAttribute("resources", aresCoreRepository.findAll());
        return "resource-list";
    }

    /**
     * Shows form to add a new Resource.
     * 
     * @param model 
     * @return view containing the form for adding a Resource.
     */
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("resource", new Resource());
        model.addAttribute("categories", getResourceCategories());
        return "add-resource";
    }

    /**
     * Processes adding a new Resource.
     * 
     * @param resource
     * @return redirection to resources view after adding the new Resource.
     */
    @PostMapping("/add")
    public String addResource(@ModelAttribute Resource resource) {
        aresCoreRepository.save(resource);
        return "redirect:/resources";
    }

    /**
     * Shows edit form to edit a Resource.
     * 
     * @param id
     * @param model
     * @return view of the edit form for editing the Resource.
     */
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Resource resource = aresCoreRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid resource ID"));
        model.addAttribute("resource", resource);
        model.addAttribute("categories", getResourceCategories());
        return "edit-resource";
    }

    /**
     * Processes editing a Resource.
     * 
     * @param id
     * @param resource
     * @return redirection to resources view after editing the Resource.
     */
    @PostMapping("/edit/{id}")
    public String editResource(@PathVariable Long id, @ModelAttribute Resource resource) {
        resource.setId(id);
        aresCoreRepository.save(resource);
        return "redirect:/resources";
    }

    /**
     * Deletes a Resource.
     * 
     * @param id
     * @return redirection to resources view after deleting the Resource.
     */
    @GetMapping("/delete/{id}")
    public String deleteResource(@PathVariable Long id) {
        aresCoreRepository.deleteById(id);
        return "redirect:/resources";
    }

    /**
     * Provides categories for Resource selection.
     * 
     * @return List of possible categories.
     */
    private String[] getResourceCategories() {
        return new String[] {"Life Support", "Supplies", "Energy", "Safety", "Maintenance", "Equipment", "Other"};
    }
}
