package com.poslovna.beans;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPoruka is a Querydsl query type for Poruka
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QPoruka extends EntityPathBase<Poruka> {

    private static final long serialVersionUID = 423598162L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPoruka poruka = new QPoruka("poruka");

    public final DateTimePath<java.util.Date> datumValute = createDateTime("datumValute", java.util.Date.class);

    public final NumberPath<Long> Id = createNumber("Id", Long.class);

    public final NumberPath<Double> iznos = createNumber("iznos", Double.class);

    public final StringPath porukaNalogaId = createString("porukaNalogaId");

    public final QValuta valuta;

    public QPoruka(String variable) {
        this(Poruka.class, forVariable(variable), INITS);
    }

    public QPoruka(Path<? extends Poruka> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QPoruka(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QPoruka(PathMetadata metadata, PathInits inits) {
        this(Poruka.class, metadata, inits);
    }

    public QPoruka(Class<? extends Poruka> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.valuta = inits.isInitialized("valuta") ? new QValuta(forProperty("valuta")) : null;
    }

}

