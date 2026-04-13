package com.goldenCompass.countryExplorer.controller;

import org.springframework.ui.Model;
import com.goldenCompass.countryExplorer.model.Pais;
import com.goldenCompass.countryExplorer.service.PaisService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;

@Controller //Retorna nomes de views, e nao json
public class PaisController {

    @GetMapping("/")
    public String mostrarIndex() {
      return "index";
    }

    @GetMapping("/buscarPais")
    public String buscarPais(@RequestParam("nome") String nome, Model model) { //model = objeto que retorna pros templates os valores buscados
        try {
            Pais pais = PaisService.buscarPais(nome);
            model.addAttribute("pais", pais);
            System.out.println(pais.toString());
            return "resultado";
        }catch(Exception e) {
            System.out.println("Algo deu errado: " + e.getMessage());
            return null;
        }
    }


}
