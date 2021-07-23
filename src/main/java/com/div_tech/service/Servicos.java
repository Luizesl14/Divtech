package com.div_tech.service;


import com.div_tech.entities.Empreendimentos;
import com.div_tech.entities.UsuarioApi;
import com.div_tech.repository.EmpreendimentoRepository;
import com.div_tech.repository.UsuarioApiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Service
public class Servicos {


    @Autowired
    EmpreendimentoRepository emr;
    @Autowired
    UsuarioApiRepository uapi;

    // retorna login
    public String formLgService(UsuarioApi user){
        List<UsuarioApi> consulta = uapi.findAll();
        for( UsuarioApi p : consulta){
            if(p.getEmail().equals(user.getEmail()) && p.getPassword().equals(user.getPassword())){
                return "redirect:/votar";
            }else if(user.getEmail().equals("admin@gmail.com") && user.getPassword().equals("admin")){
                return "redirect:/controle";
            }
        }
        return "redirect:/loginUser";
    }

    // retorna votacao
    public  String votacaoService(Empreendimentos emp){

        Integer val = 1;

        // recebendo iterable
        Iterable<Empreendimentos> valor = emr.findAll();

        // tamanho  iterable
        long tamanho =  valor.spliterator().getExactSizeIfKnown();

        // define somente 3 registros  de empreendimentos
        if (tamanho <= 2){

            // procura  valor iterable e compara se e igual
            for (Empreendimentos e1 : valor) {
                if(e1.getNome().equals(emp.getNome())){
                    System.out.println("Valor repetido");
                }
            }
            emr.save(emp);
        } else {
            // procura empreendimentos e faz Update
            for (Empreendimentos e1 : valor) {
                if (emp.getNome().equals("Le Jardim")) {
                    Empreendimentos leJardim = emr.findById(1).get();
                    leJardim.setVoto((leJardim.getVoto()) + emp.getVoto());
                    emr.save(leJardim);
                    System.out.println(emr.findById(1));
                    break;
                } else if (emp.getNome().equals("Evian")) {
                    Empreendimentos evian = emr.findById(2).get();
                    evian.setVoto((evian.getVoto()) + emp.getVoto());
                    emr.save(evian);
                    System.out.println(emr.findById(2));
                    break;
                } else if (emp.getNome().equals("Olimpia Thermas")) {
                    Empreendimentos olimpia = emr.findById(3).get();
                    olimpia.setVoto((olimpia.getVoto()) + emp.getVoto());
                    emr.save(olimpia);
                    System.out.println(emr.findById(3));
                    break;
                }
            }
        }

        return "redirect:/votar";
    }

    //exibir eventos
    public ModelAndView exibir(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("cpainel");
        mv.addObject("listarVotos1", emr.findById(1).get());
        mv.addObject("listarVotos2", emr.findById(2).get());
        mv.addObject("listarVotos3", emr.findById(3).get());

        mv.addObject("listUser", uapi.findAll());
        return mv;

    }

}
