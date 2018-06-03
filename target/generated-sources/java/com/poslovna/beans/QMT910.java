package com.poslovna.beans;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMT910 is a Querydsl query type for MT910
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QMT910 extends EntityPathBase<MT910> {

    private static final long serialVersionUID = 1395505911L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMT910 mT910 = new QMT910("mT910");

    public final QPoruka _super = new QPoruka(this);

    public final QBanka bankaPoverioca;

    //inherited
    public final DateTimePath<java.util.Date> datumValute = _super.datumValute;

    //inherited
    public final NumberPath<Long> Id = _super.Id;

    //inherited
    public final NumberPath<Double> iznos = _super.iznos;

    //inherited
    public final StringPath porukaNalogaId = _super.porukaNalogaId;

    //inherited
    public final SimplePath<Valuta> valuta = _super.valuta;

    public QMT910(String variable) {
        this(MT910.class, forVariable(variable), INITS);
    }

    public QMT910(Path<? extends MT910> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMT910(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMT910(PathMetadata metadata, PathInits inits) {
        this(MT910.class, metadata, inits);
    }

    public QMT910(Class<? extends MT910> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.bankaPoverioca = inits.isInitialized("bankaPoverioca") ? new QBanka(forProperty("bankaPoverioca")) : null;
    }

}

