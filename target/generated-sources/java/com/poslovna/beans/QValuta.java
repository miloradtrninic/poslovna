package com.poslovna.beans;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QValuta is a Querydsl query type for Valuta
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QValuta extends EntityPathBase<Valuta> {

    private static final long serialVersionUID = 582265307L;

    public static final QValuta valuta = new QValuta("valuta");

    public final BooleanPath domaca = createBoolean("domaca");

    public final StringPath naziv = createString("naziv");

    public final StringPath sifra = createString("sifra");

    public QValuta(String variable) {
        super(Valuta.class, forVariable(variable));
    }

    public QValuta(Path<? extends Valuta> path) {
        super(path.getType(), path.getMetadata());
    }

    public QValuta(PathMetadata metadata) {
        super(Valuta.class, metadata);
    }

}

