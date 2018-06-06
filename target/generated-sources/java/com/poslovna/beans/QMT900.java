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

    public final QPoruka _super;

    public final QBanka bankaDuznik;

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
        this._super = new QPoruka(type, metadata, inits);
        this.bankaDuznik = inits.isInitialized("bankaDuznik") ? new QBanka(forProperty("bankaDuznik")) : null;
        this.datumValute = _super.datumValute;
        this.Id = _super.Id;
        this.iznos = _super.iznos;
        this.porukaNalogaId = _super.porukaNalogaId;
        this.valuta = _super.valuta;
    }

}

