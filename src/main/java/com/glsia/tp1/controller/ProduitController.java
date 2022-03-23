package com.glsia.tp1.controller;

import com.glsia.tp1.models.Produit;
import com.glsia.tp1.service.ProduitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;

@Controller
@RequestMapping("/produit")
public class ProduitController {
    @Autowired
    private ProduitService produitService;

    @GetMapping("/show")
    public String afficherProduit(Model model)
    {
        model.addAttribute("listProduit", produitService.showAllProduit());
        return "produit/showProduct";
    }

    @GetMapping("/create")
    public String AfficherFormulaire()
    {
        return "produit/formProduit";
    }

    @PostMapping("/save")
    public String save(Produit produit)
    {
        produit.setQteStok(0);
        produit.setDateCreation(LocalDate.now());
        produitService.saveProduit(produit);
        return "redirect:/produit/show";
    }

    @GetMapping("/edit/{id}")
    public String formEditProduit(@PathVariable("id") int id, Model model)
    {
        model.addAttribute("unProduit", produitService.selectedProduit(id));
        return "produit/formEditProduit";
    }

}
