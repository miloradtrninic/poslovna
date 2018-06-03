package com.poslovna.beans;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QPoruka is a Querydsl query type for Poruka
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QPoruka extends EntityPathBase<Poruka> {

    private static final long serialVersionUID = 423598162L;

    public static final QPoruka poruka = new QPoruka("poruka");

    public final DateTimePath<java.util.Date> datumValute = createDateTime("datumValute", java.util.Date.class);

    public final NumberPath<Long> Id = createNumber("Id", Long.class);

    public final NumberPath<Double> iznos = createNumber("iznos", Double.class);

    public final StringPath porukaNalogaId = createString("porukaNalogaId");

    public final SimplePath<Valuta> valuta = createSimple("valuta", Valuta.class);

    public QPoruka(String variable) {
        super(Poruka.class, forVariable(variable));
    }

    public QPoruka(Path<? extends Poruka> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPoruka(PathMetadata metadata) {
        super(Poruka.class, metadata);
    }

}

