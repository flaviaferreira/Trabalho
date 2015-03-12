package br.unicesumar.restserver.manterDisciplina;

import java.util.List;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/disciplinas")
@Transactional
public class DisciplinaController {
    
    @Autowired
    private EntityManager em;
    
    @RequestMapping(method = RequestMethod.GET)
    public List<Disciplina> getVeiculos() {
        return em.createQuery("from Disciplina").getResultList();
    }
    
    @RequestMapping(method = RequestMethod.POST)
    public void criarVeiculo(@RequestBody Disciplina disciplina) {
        em.persist(disciplina);
    }        
    
    @RequestMapping(method = RequestMethod.PUT)
    public void alterarVeiculo(@RequestBody Disciplina disciplina) {
        disciplina = em.merge(disciplina);
        em.persist(disciplina);
    }        
    
    @RequestMapping(value="/{id}",method = RequestMethod.DELETE)
    public void excluirVeiculo(@PathVariable Long id) {
        em.remove(em.find(Disciplina.class, id));
    }        
}
