package br.senac.sp.batalha;

import java.util.Random;

public class Jogador {
    public String nome;
    public int vida;
    public int ataque;
    public int defesa;

    public Jogador(String nome, int vida, int ataque, int defesa) {
        this.nome = nome;
        this.vida = vida;
        this.ataque = ataque;
        this.defesa = defesa;
    }

    public void receberDano(int dano) {
        vida -= dano;
        if (vida < 0) vida = 0;
    }

    public String atacar(   Jogador inimigo) {
        Random r = new Random();
        int dano = ataque - inimigo.defesa;
        if (dano < 0) dano = 0;

       
        if (r.nextInt(100) < 10) {
            dano *= 2;
            inimigo.receberDano(dano);
            return "(CRÍTICO) " + nome + " atacou " + inimigo.nome + " e causou " + dano + " de dano!";
        } else {
            inimigo.receberDano(dano);
            return nome + " atacou " + inimigo.nome + " e causou " + dano + " de dano.";
        }
    }
}


    
