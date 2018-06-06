package com.poslovna.beans;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QDrzava is a Querydsl query type for Drzava
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QDrzava extends EntityPathBase<Drzava> {

    private static final long serialVersionUID = 83038362L;

    public static final QDrzava drzava = new QDrzava("drzava");

    public final SetPath<NaseljenoMesto, QNaseljenoMesto> naseljenaMesta = this.<NaseljenoMesto, QNaseljenoMesto>createSet("naseljenaMesta", NaseljenoMesto.class, QNaseljenoMesto.class, PathInits.DIRECT2);

    public final StringPath nazivDrzave = createString("nazivDrzave");

    public final StringPath sifraDrzave = createString("sifraDrzave");

    public final SetPath<Valuta, QValuta> valute = this.<Valuta, QValuta>createSet("valute", Valuta.class, QValuta.class, PathInits.DIRECT2);

    public QDrzava(String variable) {
        super(Drzava.class, forVariable(variable));
    }

    public QDrzava(Path<? extends Drzava> path) {
        super(path.getType(), path.getMetadata());
    }

    public QDrzava(PathMetadata metadata) {
        super(Drzava.class, metadata);
    }

}

