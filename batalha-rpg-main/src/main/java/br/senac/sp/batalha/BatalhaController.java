package br.senac.sp.batalha;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class BatalhaController {

    private Jogador jogador;
    private Jogador inimigo;

    @GetMapping
    public String index() {
        jogador = null;
        inimigo = null;
        return "index"; 
    }

    @PostMapping("jogador")
    public String criarJogador(String nome, int vida, int ataque, int defesa) {
        jogador = new Jogador(nome, vida, ataque, defesa);
        return "redirect:batalha";
    }

    @GetMapping("batalha")
    public String batalha(Model model) {
        if (jogador == null) return "redirect:/";
        if (inimigo == null) inimigo = randomJogador();

        model.addAttribute("jogador", jogador);
        model.addAttribute("inimigo", inimigo);
        return "batalha"; 
    }

    @PostMapping("batalha")
    public String executarTurno(RedirectAttributes redirect){
        if (jogador == null || inimigo == null) return "redirect:/";

        StringBuilder msg = new StringBuilder();
        msg.append(jogador.atacar(inimigo)).append("<br>");
        if (inimigo.vida > 0) {
            msg.append(inimigo.atacar(jogador));
        }

        if (jogador.vida <= 0 || inimigo.vida <= 0) {
            msg.append("<br>🏆 Fim da batalha: ")
               .append(jogador.vida > 0 ? jogador.nome : inimigo.nome)
               .append(" venceu!");
        }

        redirect.addFlashAttribute("msg", msg.toString());
        return "redirect:batalha";
    }

    private Jogador randomJogador() {
        String[] nomes = {"Abysscaller", "Soulbinder", "Voxanomaly", "Galefiend", "Gigglegore"}; 
        int vida = (int) (Math.random() * 50) + 30;
        int ataque = (int) (Math.random() * 20) + 5;
        int defesa = (int) (Math.random() * 10) + 2;
        int i = (int) (Math.random() * nomes.length);
        return new Jogador(nomes[i], vida, ataque, defesa);
    }
}
