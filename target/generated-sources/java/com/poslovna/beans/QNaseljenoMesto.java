package com.poslovna.beans;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QNaseljenoMesto is a Querydsl query type for NaseljenoMesto
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QNaseljenoMesto extends EntityPathBase<NaseljenoMesto> {

    private static final long serialVersionUID = 400546253L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QNaseljenoMesto naseljenoMesto = new QNaseljenoMesto("naseljenoMesto");

    public final QDrzava drzava;

    public final StringPath naziv = createString("naziv");

    public final StringPath pttOznaka = createString("pttOznaka");

    public final NumberPath<Long> sifraMesta = createNumber("sifraMesta", Long.class);

    public QNaseljenoMesto(String variable) {
        this(NaseljenoMesto.class, forVariable(variable), INITS);
    }

    public QNaseljenoMesto(Path<? extends NaseljenoMesto> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QNaseljenoMesto(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QNaseljenoMesto(PathMetadata metadata, PathInits inits) {
        this(NaseljenoMesto.class, metadata, inits);
    }

    public QNaseljenoMesto(Class<? extends NaseljenoMesto> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.drzava = inits.isInitialized("drzava") ? new QDrzava(forProperty("drzava")) : null;
    }

}

