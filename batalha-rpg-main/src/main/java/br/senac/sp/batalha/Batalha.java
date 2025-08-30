package br.senac.sp.batalha;

public class Batalha {
    public static void main(String[] args) {
        Jogador j1 = new Jogador("Herói", 50, 15, 5);
        Jogador j2 = new Jogador("Vilão", 50, 12, 6);

        while (j1.vida > 0 && j2.vida > 0) {
            System.out.println(j1.atacar(j2));
            if (j2.vida > 0) {
                System.out.println(j2.atacar(j1));
            }
            System.out.println(j1.nome + ": " + j1.vida + " HP | "
                             + j2.nome + ": " + j2.vida + " HP\n");
        }

        System.out.println("🏆 " + (j1.vida > 0 ? j1.nome : j2.nome) + " venceu!");
    }
}