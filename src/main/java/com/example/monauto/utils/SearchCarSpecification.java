package com.example.monauto.utils;

import com.example.monauto.DTO.SearchDto;
import com.example.monauto.entity.Auto;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class SearchCarSpecification {

    public static Specification<Auto> withCriteria(SearchDto criteria) {
        return (root, query, builder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (StringUtils.hasText(criteria.getMarques())) {
                predicates.add(builder.equal(root.get("marques"), criteria.getMarques()));
            }

            if (StringUtils.hasText(criteria.getTypesCarrosserie())) {
                predicates.add(builder.equal(root.get("typesCarrosserie"), criteria.getTypesCarrosserie()));
            }

            if (criteria.getAnneeMin() != null ) {
                predicates.add(builder.greaterThanOrEqualTo(root.get("anneeDeFabrication"),Convertion.convertStringToDate( criteria.getAnneeMin())));
            }

            if (criteria.getAnneeMax() != null) {
                predicates.add(builder.lessThanOrEqualTo(root.get("anneeDeFabrication"),Convertion.convertStringToDate(criteria.getAnneeMax()) ));
            }

            if (StringUtils.hasText(criteria.getSelectedColor())) {
                predicates.add(builder.equal(root.get("couleurExt"), criteria.getSelectedColor()));
            }

            if (criteria.getPrixMin() != null) {
                predicates.add(builder.greaterThanOrEqualTo(root.get("prix"), Convertion.convertStringToFloat(criteria.getPrixMin())));
            }

            if (criteria.getPrixMax() != null) {
                predicates.add(builder.lessThanOrEqualTo(root.get("prix"), Convertion.convertStringToFloat(criteria.getPrixMax())));
            }

            if (criteria.getKilometrageMin() != null) {
                predicates.add(builder.lessThanOrEqualTo(root.get("kilometrage"), Convertion.convertStringToFloat(criteria.getKilometrageMin())));
            }

            if (criteria.getKilometrageMax() != null) {
                predicates.add(builder.lessThanOrEqualTo(root.get("kilometrage"), Convertion.convertStringToFloat(criteria.getKilometrageMax())));
            }

            if (StringUtils.hasText(criteria.getTypeCarburant())) {
                predicates.add(builder.equal(root.get("typeCarburant"), criteria.getTypeCarburant()));
            }

            if (StringUtils.hasText(criteria.getTypeMoteur())) {
                predicates.add(builder.equal(root.get("typeMoteur"), criteria.getTypeMoteur()));
            }

            if (StringUtils.hasText(criteria.getTypeTransmission())) {
                predicates.add(builder.equal(root.get("typeTransmission"), criteria.getTypeTransmission()));
            }

            if (StringUtils.hasText(criteria.getVilleDuBien())) {
                predicates.add(builder.equal(root.get("villeDuBien"), criteria.getVilleDuBien()));
            }

            return builder.and(predicates.toArray(new Predicate[0]));
        };
    }

}
