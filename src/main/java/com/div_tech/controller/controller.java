package com.div_tech.controller;

import com.div_tech.entities.Empreendimentos;
import com.div_tech.entities.UsuarioApi;
import com.div_tech.repository.EmpreendimentoRepository;
import com.div_tech.repository.UsuarioApiRepository;
import com.div_tech.service.Servicos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

@Controller
public class controller {

    @RequestMapping("/")
    public String homePage(){
        return "index";
    }

    // injecao de dependencia
    @Autowired
    private UsuarioApiRepository usuarioApiRepository;
    @Autowired
    private EmpreendimentoRepository empreendimentoRepository;
    @Autowired
    private Servicos servicos;



    // recebendo dados através do get  e post  Cadastro
    @RequestMapping(value = "/cadastroUser", method = RequestMethod.GET)
    public String formCadastro(){
        return "cadastro";
    }



    @RequestMapping(value = "/cadastroUser", method = RequestMethod.POST)
    public  String formCadastro(UsuarioApi user){
        usuarioApiRepository.save(user);
        return "redirect:/loginUser";
    }


    // recebendo dados através do get e post  Painel de Votacao
    @RequestMapping(value = "/votar", method = RequestMethod.GET)
    public String pVotacao(){
        return "painelVotacao";
    }


    @RequestMapping(value = "/votar", method = RequestMethod.POST)
    public  String pVotacao(Empreendimentos emp){
       return servicos.votacaoService(emp);
    }


    // recebendo dados através do get e Post Login Usuario
    @RequestMapping(value = "/loginUser", method = RequestMethod.GET)
    public String formLogin(){
        return "login";
    }

    @RequestMapping(value = "/loginUser", method = RequestMethod.POST)
    public  String formLogin(UsuarioApi user){
        return servicos.formLgService(user);
    }

    @RequestMapping("/controle")
    public String cpainel(){ return "cpainel";}


    // Exibe as informacoes no  Painel de Controle
    @GetMapping("/controle")
    public ModelAndView listaEventos(){
        return servicos.exibir();
    }


}
