package com.poslovna.beans;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QRacunPravnogLica is a Querydsl query type for RacunPravnogLica
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QRacunPravnogLica extends EntityPathBase<RacunPravnogLica> {

    private static final long serialVersionUID = 2038505335L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QRacunPravnogLica racunPravnogLica = new QRacunPravnogLica("racunPravnogLica");

    public final QBanka banka;

    public final StringPath brojRacuna = createString("brojRacuna");

    public final DateTimePath<java.util.Date> datumOtvaranja = createDateTime("datumOtvaranja", java.util.Date.class);

    public final SimplePath<Valuta> valuta = createSimple("valuta", Valuta.class);

    public final BooleanPath vazeci = createBoolean("vazeci");

    public final QPravnaLica vlasnik;

    public QRacunPravnogLica(String variable) {
        this(RacunPravnogLica.class, forVariable(variable), INITS);
    }

    public QRacunPravnogLica(Path<? extends RacunPravnogLica> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QRacunPravnogLica(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QRacunPravnogLica(PathMetadata metadata, PathInits inits) {
        this(RacunPravnogLica.class, metadata, inits);
    }

    public QRacunPravnogLica(Class<? extends RacunPravnogLica> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.banka = inits.isInitialized("banka") ? new QBanka(forProperty("banka")) : null;
        this.vlasnik = inits.isInitialized("vlasnik") ? new QPravnaLica(forProperty("vlasnik")) : null;
    }

}

