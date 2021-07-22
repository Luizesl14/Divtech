package com.div_tech.entities;

import javassist.SerialVersionUID;
import net.bytebuddy.dynamic.loading.InjectionClassLoader;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
public class Empreendimentos implements Serializable {

   private  static  final long SerialVersionUID = 1L;

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Integer id;

    private String nome;
    private  Integer voto;


    // construtores

    public Empreendimentos() {
    }

    public Empreendimentos(String nome, Integer voto) {
        this.nome = nome;
        this.voto = voto;
    }


     // Getter and Setter


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getVoto() {
        return voto;
    }

    public void setVoto(Integer voto) {
        this.voto = voto;
    }


    @Override
    public String toString() {
        return "Empreendimentos{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", voto=" + voto +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Empreendimentos)) return false;
        Empreendimentos that = (Empreendimentos) o;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getNome(), that.getNome()) && Objects.equals(getVoto(), that.getVoto());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getNome(), getVoto());
    }
}
