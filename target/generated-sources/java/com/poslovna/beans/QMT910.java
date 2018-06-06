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

    public final QPoruka _super;

    public final QBanka bankaPoverioca;

    //inherited
    public final DateTimePath<java.util.Date> datumValute;

    //inherited
    public final NumberPath<Long> Id;

    //inherited
    public final NumberPath<Double> iznos;

    //inherited
    public final StringPath porukaNalogaId;

    // inherited
    public final QValuta valuta;

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
        this._super = new QPoruka(type, metadata, inits);
        this.bankaPoverioca = inits.isInitialized("bankaPoverioca") ? new QBanka(forProperty("bankaPoverioca")) : null;
        this.datumValute = _super.datumValute;
        this.Id = _super.Id;
        this.iznos = _super.iznos;
        this.porukaNalogaId = _super.porukaNalogaId;
        this.valuta = _super.valuta;
    }

}

