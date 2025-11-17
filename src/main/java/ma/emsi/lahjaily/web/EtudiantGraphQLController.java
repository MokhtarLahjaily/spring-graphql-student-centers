package ma.emsi.lahjaily.web;

import ma.emsi.lahjaily.entity.Centre;
import ma.emsi.lahjaily.entity.Etudiant;
import ma.emsi.lahjaily.dto.EtudiantDTO;
import ma.emsi.lahjaily.service.CentreService;
import ma.emsi.lahjaily.service.EtudiantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SubscriptionMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;

import java.util.List;

@Controller
public class EtudiantGraphQLController {

    // Injection des Services au lieu des Repositories [cite: 904, 906]
    @Autowired
    private EtudiantService etudiantService;
    @Autowired
    private CentreService centreService;

    @QueryMapping
    public List<Etudiant> getAllEtudiants(){ // Renommé selon le nouveau schéma [cite: 984]
        return etudiantService.getStudents();
    }

    @QueryMapping
    public Etudiant getEtudiantById(@Argument Long id){
        return etudiantService.getEtudiant(id);
    }

    @QueryMapping
    public List<Centre> getAllCentres(){ // Renommé selon le nouveau schéma [cite: 986]
        return centreService.getCentres();
    }

    @QueryMapping
    public Centre getCentreById(@Argument Long id){
        return centreService.getCentre(id);
    }


    @MutationMapping
    public Etudiant addEtudiant(@Argument EtudiantDTO etudiantDTO){
        return etudiantService.addEtudiant(etudiantDTO);
    }

    @MutationMapping
    public Etudiant updateEtudiant(@Argument Long id, @Argument EtudiantDTO etudiantDTO){
        return etudiantService.updateEtudiant(id, etudiantDTO);
    }

    @MutationMapping
    public String deleteEtudiant(@Argument Long id){
        return etudiantService.deleteEtudiant(id);
    }

    @SubscriptionMapping
    public Flux<Etudiant> etudiantAdded() {
        return etudiantService.getEtudiantAdded();
    }

    @SubscriptionMapping
    public Flux<String> etudiantMoved() {
        return etudiantService.etudiantMoved();
    }
}