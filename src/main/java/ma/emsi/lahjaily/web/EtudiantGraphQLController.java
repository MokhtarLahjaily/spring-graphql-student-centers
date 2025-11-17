package ma.emsi.lahjaily.web;

import ma.emsi.lahjaily.entity.*;
import ma.emsi.lahjaily.dto.EtudiantDTO;
import ma.emsi.lahjaily.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.*;
import org.springframework.stereotype.Controller;
import java.util.List;

@Controller
public class EtudiantGraphQLController {
    @Autowired
    private EtudiantRepository etudiantRepository;
    @Autowired
    private CentreRepository centreRepository;
    @QueryMapping
    public List<Etudiant> listEtudiants(){
        return etudiantRepository.findAll();
    }
    @QueryMapping
    public Etudiant getEtudiantById(@Argument Long id){
        return etudiantRepository.findById(id).orElseThrow(
                ()->new RuntimeException(String.format("etudiant %d non trouvé ",id))
        );
    }@QueryMapping
    public List<Centre> centres(){
        return centreRepository.findAll();
    }
    @QueryMapping
    public Centre getCentreById(@Argument Long id){
        return centreRepository.findById(id).orElseThrow(
                ()->new RuntimeException(String.format("Centre %s non trouvé ",id))
        );
    }
    @MutationMapping
    public Etudiant addEtudiant(@Argument EtudiantDTO etudiant){
        Centre centre=centreRepository.findById(etudiant.centreId()).orElse(null);
        Etudiant et=new Etudiant();
        et.setNom(etudiant.nom());
        et.setPrenom(etudiant.prenom());
        et.setGenre(etudiant.genre());
        et.setCentre(centre);
        return etudiantRepository.save(et);
    }
    @MutationMapping
    public Etudiant updateEtudiant(@Argument Long id,@Argument EtudiantDTO etudiant){
        Centre centre=centreRepository.findById(etudiant.centreId()).orElse(null);
        Etudiant et=null;
        if( etudiantRepository.findById(id).isPresent()) {
            et = etudiantRepository.findById(id).get();
            et.setNom(etudiant.nom());
            et.setPrenom(etudiant.prenom());
            et.setGenre(etudiant.genre());
            et.setCentre(centre);
            return etudiantRepository.save(et);
        }
        return et;
    }
    @MutationMapping
    public String deleteEtudiant(@Argument Long id){
        Etudiant et;
        if(etudiantRepository.findById(id).isPresent())
        {
            etudiantRepository.deleteById(id);
            return String.format("L'étudiant %s bien supprimé ",id);}
        else return String.format("L'étudiant %s n'exite pas ",id);
    }
}