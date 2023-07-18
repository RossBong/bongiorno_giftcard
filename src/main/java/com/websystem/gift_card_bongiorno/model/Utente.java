package com.websystem.gift_card_bongiorno.model;



import jakarta.persistence.*;




@Entity
@Table(name="utenti")
public class Utente
{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable=false)
    private String name;

    @Column(nullable=false, unique=true)
    private String email;

    @Column(nullable=false, unique=true)
    private String ruolo;


    @Column(nullable=false)
    private String password;

    public void setRuolo(String ruolo) {

        this.ruolo = ruolo;
    }

    public String getRuolo() {
        return ruolo;
    }




    public Utente(Integer id, String name, String email, String ruolo, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.ruolo = ruolo;
        this.password = password;
    }

    public Utente() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}