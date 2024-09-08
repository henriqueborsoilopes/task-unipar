package com.borsoitech.tasks.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.borsoitech.tasks.entity.Habito;
import com.borsoitech.tasks.entity.HabitoHistorico;
import com.borsoitech.tasks.entity.Tarefa;
import com.borsoitech.tasks.entity.Usuario;
import com.borsoitech.tasks.repository.HabitoHistoricoRepository;
import com.borsoitech.tasks.repository.HabitoRepository;
import com.borsoitech.tasks.repository.TarefaRepository;
import com.borsoitech.tasks.repository.UsuarioRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {

    @Autowired
    private TarefaRepository tarefaRepository;

    @Autowired
    private HabitoRepository habitoRepository;

    @Autowired
    private HabitoHistoricoRepository habitoHistoricoRepository;
    
	@Autowired
	private UsuarioRepository usuarioRepository;

    @GetMapping("/home")
    public String home(Model model, HttpSession session) {
		String username = (String) session.getAttribute("email");
		
		if (username == null) {
			return "redirect:/login";
		}

    	Usuario usuario = usuarioRepository.findByEmail(username).get();
    	
    	if (!usuario.isLogado()) {
    		return "redirect:/login";
    	}
    	
        List<Tarefa> tarefas = tarefaRepository.findByUsuario(usuario);
        List<Habito> habitos = habitoRepository.findByUsuario(usuario);
        List<HabitoHistorico> historicos = habitoHistoricoRepository.findByUsuario(usuario);

        model.addAttribute("usuario", usuario);
        model.addAttribute("tarefas", tarefas);
        model.addAttribute("habitos", habitos);
        model.addAttribute("historicos", historicos);
        return "home";
    }

    @PostMapping("/tarefa/concluir/{id}")
    public String concluirTarefa(@PathVariable Integer id) {
        Tarefa tarefa = tarefaRepository.findById(id).orElseThrow();
        tarefa.setConcluida(true);
        tarefaRepository.save(tarefa);
        return "redirect:/home";
    }

    @PostMapping("/habito/marcar/{id}")
    public String marcarHabito(@PathVariable Integer id) {
        Habito habito = habitoRepository.findById(id).orElseThrow();
        HabitoHistorico historico = new HabitoHistorico(null, LocalDate.now(), habito, habito.getUsuario());
        habitoHistoricoRepository.save(historico);
        return "redirect:/home";
    }
	
    @PostMapping("/habito/adicionar")
    public String adicionarHabito(@RequestParam String descricao, HttpSession session) {
    	String username = (String) session.getAttribute("email");
    	Usuario usuario = usuarioRepository.findByEmail(username).get();
        Habito habito = new Habito(null, descricao, usuario);
        habitoRepository.save(habito);
        return "redirect:/home";
    }
    
    @PostMapping("/tarefa/adicionar")
    public String adicionarTarefa(@RequestParam String descricao,
                                   @RequestParam String dataInicio,
                                   @RequestParam String dataLimite,
                                   HttpSession session) {
        String username = (String) session.getAttribute("email");
        Usuario usuario = usuarioRepository.findByEmail(username).get();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        LocalDateTime inicio;
        LocalDateTime limite;

        try {
            inicio = LocalDateTime.parse(dataInicio, formatter);
            limite = LocalDateTime.parse(dataLimite, formatter);
        } catch (DateTimeParseException e) {
            LocalDate dateInicio = LocalDate.parse(dataInicio);
            LocalDate dateLimite = LocalDate.parse(dataLimite);

            inicio = dateInicio.atStartOfDay();
            limite = dateLimite.atStartOfDay();
        }

        Tarefa tarefa = new Tarefa(null, descricao, inicio, limite, false, usuario);
        tarefaRepository.save(tarefa);
        return "redirect:/home";
    }
}
