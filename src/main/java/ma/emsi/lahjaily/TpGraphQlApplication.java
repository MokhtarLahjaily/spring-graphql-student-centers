package ma.emsi.lahjaily;


import ma.emsi.lahjaily.entity.Centre;
import ma.emsi.lahjaily.entity.Etudiant;
import ma.emsi.lahjaily.enums.Genre;
import ma.emsi.lahjaily.repository.CentreRepository;
import ma.emsi.lahjaily.repository.EtudiantRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TpGraphQlApplication {

    public static void main(String[] args) {
        SpringApplication.run(TpGraphQlApplication.class, args);
    }

    @Bean
    CommandLineRunner initDatabase(EtudiantRepository etudiantRepository, CentreRepository centreRepository){
        return args -> {

            // Création de centres
            Centre c1 = centreRepository.save(
                    Centre.builder().nom("Maarif").adresse("Biranzarane").build()
            );
            Centre c2 = centreRepository.save(
                    Centre.builder().nom("Oulfa").adresse("Hay Hassani").build()
            );

            // Création d'étudiants liés à des centres
            etudiantRepository.save(
                    Etudiant.builder().nom("Adnani").prenom("Brahim").genre(Genre.Homme).centre(c1).build()
            );
            etudiantRepository.save(
                    Etudiant.builder().nom("Karimi").prenom("Sara").genre(Genre.Femme).centre(c2).build()
            );
        };
    }
}
