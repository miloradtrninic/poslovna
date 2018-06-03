package com.poslovna.beans;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QKursValuta is a Querydsl query type for KursValuta
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QKursValuta extends EntityPathBase<KursValuta> {

    private static final long serialVersionUID = -353163802L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QKursValuta kursValuta = new QKursValuta("kursValuta");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Double> kupovni = createNumber("kupovni", Double.class);

    public final QKursnaLista kursnaLista;

    public final SimplePath<Valuta> osnovnaValuta = createSimple("osnovnaValuta", Valuta.class);

    public final SimplePath<Valuta> premaValuti = createSimple("premaValuti", Valuta.class);

    public final NumberPath<Double> prodajni = createNumber("prodajni", Double.class);

    public final NumberPath<Double> srednji = createNumber("srednji", Double.class);

    public QKursValuta(String variable) {
        this(KursValuta.class, forVariable(variable), INITS);
    }

    public QKursValuta(Path<? extends KursValuta> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QKursValuta(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QKursValuta(PathMetadata metadata, PathInits inits) {
        this(KursValuta.class, metadata, inits);
    }

    public QKursValuta(Class<? extends KursValuta> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.kursnaLista = inits.isInitialized("kursnaLista") ? new QKursnaLista(forProperty("kursnaLista")) : null;
    }

}

