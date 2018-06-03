package com.poslovna.beans;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QAnalitikaIzvoda is a Querydsl query type for AnalitikaIzvoda
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QAnalitikaIzvoda extends EntityPathBase<AnalitikaIzvoda> {

    private static final long serialVersionUID = 943482729L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QAnalitikaIzvoda analitikaIzvoda = new QAnalitikaIzvoda("analitikaIzvoda");

    public final DateTimePath<java.util.Date> datumValute = createDateTime("datumValute", java.util.Date.class);

    public final QRacunPravnogLica duznik;

    public final BooleanPath hitno = createBoolean("hitno");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Double> iznos = createNumber("iznos", Double.class);

    public final NumberPath<Integer> modelOdobrenja = createNumber("modelOdobrenja", Integer.class);

    public final NumberPath<Integer> modelZaduzenja = createNumber("modelZaduzenja", Integer.class);

    public final QRacunPravnogLica poverilac;

    public final StringPath pozivNaBroj = createString("pozivNaBroj");

    public final StringPath pozivNaBrojOdobrenja = createString("pozivNaBrojOdobrenja");

    public final DateTimePath<java.util.Date> primljen = createDateTime("primljen", java.util.Date.class);

    public final NumberPath<Integer> status = createNumber("status", Integer.class);

    public final StringPath svrhaPlacanja = createString("svrhaPlacanja");

    public final NumberPath<Integer> tipGreske = createNumber("tipGreske", Integer.class);

    public QAnalitikaIzvoda(String variable) {
        this(AnalitikaIzvoda.class, forVariable(variable), INITS);
    }

    public QAnalitikaIzvoda(Path<? extends AnalitikaIzvoda> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QAnalitikaIzvoda(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QAnalitikaIzvoda(PathMetadata metadata, PathInits inits) {
        this(AnalitikaIzvoda.class, metadata, inits);
    }

    public QAnalitikaIzvoda(Class<? extends AnalitikaIzvoda> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.duznik = inits.isInitialized("duznik") ? new QRacunPravnogLica(forProperty("duznik"), inits.get("duznik")) : null;
        this.poverilac = inits.isInitialized("poverilac") ? new QRacunPravnogLica(forProperty("poverilac"), inits.get("poverilac")) : null;
    }

}

