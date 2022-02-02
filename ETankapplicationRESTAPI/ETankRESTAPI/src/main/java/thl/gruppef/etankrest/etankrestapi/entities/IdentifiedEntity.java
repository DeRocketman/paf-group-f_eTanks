package thl.gruppef.etankrest.etankrestapi.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * unique identifier for entities
 */
@MappedSuperclass
@Getter
@Setter
public abstract class IdentifiedEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
}
