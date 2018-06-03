package com.poslovna.beans;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMT900 is a Querydsl query type for MT900
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QMT900 extends EntityPathBase<MT900> {

    private static final long serialVersionUID = 1395505880L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMT900 mT900 = new QMT900("mT900");

    public final QPoruka _super = new QPoruka(this);

    public final QBanka bankaDuznik;

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

    public QMT900(String variable) {
        this(MT900.class, forVariable(variable), INITS);
    }

    public QMT900(Path<? extends MT900> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMT900(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMT900(PathMetadata metadata, PathInits inits) {
        this(MT900.class, metadata, inits);
    }

    public QMT900(Class<? extends MT900> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.bankaDuznik = inits.isInitialized("bankaDuznik") ? new QBanka(forProperty("bankaDuznik")) : null;
    }

}

