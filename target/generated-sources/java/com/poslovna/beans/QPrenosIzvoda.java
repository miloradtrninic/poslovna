package com.poslovna.beans;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPrenosIzvoda is a Querydsl query type for PrenosIzvoda
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QPrenosIzvoda extends EntityPathBase<PrenosIzvoda> {

    private static final long serialVersionUID = -1221194928L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPrenosIzvoda prenosIzvoda = new QPrenosIzvoda("prenosIzvoda");

    public final NumberPath<Integer> brojPreseka = createNumber("brojPreseka", Integer.class);

    public final NumberPath<Integer> brojPromena = createNumber("brojPromena", Integer.class);

    public final QRacunPravnogLica brojRacuna;

    public final DateTimePath<java.util.Date> datumNaloga = createDateTime("datumNaloga", java.util.Date.class);

    public final QDnevnoStanje naloziUIzvodu;

    public final QPravnaLica pravnaLica;

    public final NumberPath<Integer> status = createNumber("status", Integer.class);

    public final NumberPath<Double> ukupnoUKorist = createNumber("ukupnoUKorist", Double.class);

    public QPrenosIzvoda(String variable) {
        this(PrenosIzvoda.class, forVariable(variable), INITS);
    }

    public QPrenosIzvoda(Path<? extends PrenosIzvoda> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QPrenosIzvoda(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QPrenosIzvoda(PathMetadata metadata, PathInits inits) {
        this(PrenosIzvoda.class, metadata, inits);
    }

    public QPrenosIzvoda(Class<? extends PrenosIzvoda> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.brojRacuna = inits.isInitialized("brojRacuna") ? new QRacunPravnogLica(forProperty("brojRacuna"), inits.get("brojRacuna")) : null;
        this.naloziUIzvodu = inits.isInitialized("naloziUIzvodu") ? new QDnevnoStanje(forProperty("naloziUIzvodu")) : null;
        this.pravnaLica = inits.isInitialized("pravnaLica") ? new QPravnaLica(forProperty("pravnaLica")) : null;
    }

}

