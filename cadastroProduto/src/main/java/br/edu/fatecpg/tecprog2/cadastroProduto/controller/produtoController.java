package br.edu.fatecpg.tecprog2.cadastroProduto.controller;
import br.edu.fatecpg.tecprog2.cadastroProduto.model.Produto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.Period;
import java.util.ArrayList;
import java.util.List;

@Controller
public class produtoController {
    private static final List<Produto> Produtos = new ArrayList<>();
    private static final List<String> erros = new ArrayList<>();

    @GetMapping("/")
    public String index(){
        return "redirect:/cadastrar";
    }

    @GetMapping("/cadastrar")
    public String cadastrar(Model model){
        model.addAttribute("produto",new Produto());
        model.addAttribute("erros", erros);
        return "cadastrar";
    }

    @PostMapping("/cadastrar")
    public String cadastrarProduto(@ModelAttribute Produto produto){
        if(produto.getPreco() <=0) {
            erros.add("Preço inválido! Envie com um preço válido!");
            return "redirect:/cadastrar";
        }
        else {
            Produtos.add(produto);
        }
        return "redirect:/lista";
    }

    @GetMapping("/lista")
    public String exibirLista(Model model){
        erros.clear();
        model.addAttribute("produtos", Produtos);
        return "lista";
    }

    @PostMapping("/limparLista")
    public String limparLista() {
        erros.clear();
        return "redirect:/cadastrar";
    }

    @PostMapping("/removerItem/{indice}")
    public String removerItem(@PathVariable int indice) {
        try {
            System.out.println("PRODUTO: " + Produtos);
            Produtos.remove(indice);
            return "redirect:/lista";
        }catch(Exception e) {
            System.err.println("Algo deu errado: " + e.getMessage());
            return null;
        }
    }
}
