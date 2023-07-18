package com.websystem.gift_card_bongiorno.model;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name="transazioni")
public class Transazione {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer  idTransazione;
    @Column(nullable=false)
    private Float   importo;


    @Column(nullable=false)
    private Date data;

    public Transazione(Float importo, Date data, Utente utente, Carta carta) {
        this.importo = importo;
        this.data = data;
        this.Utente = utente;
        this.Carta = carta;
    }

    @ManyToOne
    @JoinColumn(name="codUtente")
    private Utente  Utente;

    @ManyToOne
    @JoinColumn(name="codCarta")
    private Carta  Carta;

    public Utente getUtente() {
        return Utente;
    }

    public void setUtente(Utente utente) {
        Utente = utente;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Transazione() {

    }

    public Integer getIdTransazione() {
        return idTransazione;
    }

    public void setIdTransazione(Integer idTransazione) {
        this.idTransazione = idTransazione;
    }

    public Float getImporto() {
        return importo;
    }

    public void setImporto(Float importo) {
        this.importo = importo;
    }



    public Carta getCarta() {
        return Carta;
    }

    public void setCarta(Carta Carta) {
        Carta = Carta;
    }
}