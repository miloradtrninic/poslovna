package com.poslovna.beans;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QKliringNalog is a Querydsl query type for KliringNalog
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QKliringNalog extends EntityPathBase<KliringNalog> {

    private static final long serialVersionUID = -292639309L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QKliringNalog kliringNalog = new QKliringNalog("kliringNalog");

    public final DateTimePath<java.util.Date> datum = createDateTime("datum", java.util.Date.class);

    public final DateTimePath<java.util.Date> datumValute = createDateTime("datumValute", java.util.Date.class);

    public final QBanka duznik;

    public final NumberPath<Long> Id = createNumber("Id", Long.class);

    public final SetPath<PojedinacnoPlacanje, QPojedinacnoPlacanje> placanja = this.<PojedinacnoPlacanje, QPojedinacnoPlacanje>createSet("placanja", PojedinacnoPlacanje.class, QPojedinacnoPlacanje.class, PathInits.DIRECT2);

    public final QBanka poverilac;

    public final NumberPath<Double> ukupanIznos = createNumber("ukupanIznos", Double.class);

    public final QValuta valuta;

    public QKliringNalog(String variable) {
        this(KliringNalog.class, forVariable(variable), INITS);
    }

    public QKliringNalog(Path<? extends KliringNalog> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QKliringNalog(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QKliringNalog(PathMetadata metadata, PathInits inits) {
        this(KliringNalog.class, metadata, inits);
    }

    public QKliringNalog(Class<? extends KliringNalog> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.duznik = inits.isInitialized("duznik") ? new QBanka(forProperty("duznik")) : null;
        this.poverilac = inits.isInitialized("poverilac") ? new QBanka(forProperty("poverilac")) : null;
        this.valuta = inits.isInitialized("valuta") ? new QValuta(forProperty("valuta")) : null;
    }

}

