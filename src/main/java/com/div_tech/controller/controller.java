package com.div_tech.controller;

import com.div_tech.entities.Empreendimentos;
import com.div_tech.entities.UsuarioApi;
import com.div_tech.repository.EmpreendimentoRepository;
import com.div_tech.repository.UsuarioApiRepository;
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


    @Autowired
    private UsuarioApiRepository usuarioApiRepository;

    @Autowired
    private EmpreendimentoRepository empreendimentoRepository;

    // recebendo dados através do get
    @RequestMapping(value = "/cadastroUser", method = RequestMethod.GET)
    public String formCadastro(){
        return "cadastro";
    }

    // recebendo valores através do post
    @RequestMapping(value = "/cadastroUser", method = RequestMethod.POST)
    public  String formCadastro(UsuarioApi user){
        usuarioApiRepository.save(user);
        return "redirect:/loginUser";
    }


    // recebendo dados através do get
    @RequestMapping(value = "/votar", method = RequestMethod.GET)
    public String pVotacao(){
        return "painelVotacao";
    }

    // recebendo valores através do post
    @RequestMapping(value = "/votar", method = RequestMethod.POST)
    public  String pVotacao(Empreendimentos emp){

        Integer val = 1;

        Iterable<Empreendimentos> valor = empreendimentoRepository.findAll();
        // tamanho do interator
        long tamanho =  valor.spliterator().getExactSizeIfKnown();


        if (tamanho <= 2){
            for (Empreendimentos e1 : valor) {
                if(e1.getNome().equals(emp.getNome())){
                    System.out.println("Valor repetido");
                }
            }
            empreendimentoRepository.save(emp);
        } else {
            for (Empreendimentos e1 : valor) {
                if (emp.getNome().equals("Le Jardim")) {
                    Empreendimentos leJardim = empreendimentoRepository.findById(1).get();
                    leJardim.setVoto((leJardim.getVoto()) + emp.getVoto());
                    empreendimentoRepository.save(leJardim);
                    System.out.println(empreendimentoRepository.findById(1));
                    break;
                } else if (emp.getNome().equals("Evian")) {
                    Empreendimentos evian = empreendimentoRepository.findById(2).get();
                    evian.setVoto((evian.getVoto()) + emp.getVoto());
                    empreendimentoRepository.save(evian);
                    System.out.println(empreendimentoRepository.findById(2));
                    break;
                } else if (emp.getNome().equals("Olimpia Thermas")) {
                    Empreendimentos olimpia = empreendimentoRepository.findById(3).get();
                    olimpia.setVoto((olimpia.getVoto()) + emp.getVoto());
                    empreendimentoRepository.save(olimpia);
                    System.out.println(empreendimentoRepository.findById(3));
                    break;
                }
            }
        }

        return "redirect:/votar";
    }



    // recebendo dados através do get
    @RequestMapping(value = "/loginUser", method = RequestMethod.GET)
    public String formLogin(){
        return "login";
    }

    // recebendo valores através do post
    @RequestMapping(value = "/loginUser", method = RequestMethod.POST)
    public  String formLogin(UsuarioApi user){

        List<UsuarioApi> consulta = usuarioApiRepository.findAll();
        for( UsuarioApi p : consulta){
            if(p.getEmail().equals(user.getEmail()) && p.getPassword().equals(user.getPassword())){
                return "redirect:/votar";
            }
        }

        return "redirect:/loginUser";
    }

    @GetMapping("/controle")
    public ModelAndView listaEventos(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("cpainel");
        mv.addObject("listarVotos1", empreendimentoRepository.findById(1).get());
        mv.addObject("listarVotos2", empreendimentoRepository.findById(2).get());
        mv.addObject("listarVotos3", empreendimentoRepository.findById(3).get());

        mv.addObject("listUser", usuarioApiRepository.findAll());
        return mv;
    }





}
