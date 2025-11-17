package ma.emsi.lahjaily.dto;

import ma.emsi.lahjaily.enums.Genre;

public record EtudiantDTO(
        String nom,
        String prenom,
        Genre genre,
        Long centreId
) {}
