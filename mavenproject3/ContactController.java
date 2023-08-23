/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenproject3;

import javax.enterprise.inject.Model;

/**
 *
 * @author Allison
 */
@Controller
public class ContactController {
    private ContactRepository contactRepository;

    // Maneja la solicitud para mostrar el formulario de contacto
    @GetMapping("/contact/form")
    public String showContactForm(Model model) {
        model.addAttribute("contact", new Contact());
        return "contact-form"; // Esto renderizará el archivo contact-form.html
    }

    // Maneja la solicitud POST del formulario de contacto

    /**
     *
     * @param contact
     * @return
     */
    @PostMapping("/contact")
    public String createContact(@ModelAttribute Contact contact) {
        // Guarda el objeto Contact en la base de datos utilizando el ContactRepository
        contactRepository.save(contact);
        return "redirect:/contact/form"; // Redirecciona al formulario después de la creación
    }
}

