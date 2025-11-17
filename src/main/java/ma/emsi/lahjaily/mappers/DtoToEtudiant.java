package ma.emsi.lahjaily.mappers;

import ma.emsi.lahjaily.dto.EtudiantDTO;
import ma.emsi.lahjaily.entity.Centre;
import ma.emsi.lahjaily.entity.Etudiant;
import ma.emsi.lahjaily.repository.CentreRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DtoToEtudiant {
    @Autowired
    CentreRepository centreRepository;

    public void toEtudiant (Etudiant et, EtudiantDTO dto) {
        Centre centre = centreRepository.findById(dto.centreId()).orElse(null);

        if (dto != null) {
            BeanUtils.copyProperties(dto, et);
            et.setCentre(centre);
        }
    }
}