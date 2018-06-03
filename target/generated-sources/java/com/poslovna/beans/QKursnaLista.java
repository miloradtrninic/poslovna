package com.poslovna.beans;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QKursnaLista is a Querydsl query type for KursnaLista
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QKursnaLista extends EntityPathBase<KursnaLista> {

    private static final long serialVersionUID = 1732165451L;

    public static final QKursnaLista kursnaLista = new QKursnaLista("kursnaLista");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final DateTimePath<java.util.Date> primenjujeSeOd = createDateTime("primenjujeSeOd", java.util.Date.class);

    public final SetPath<KursValuta, QKursValuta> valute = this.<KursValuta, QKursValuta>createSet("valute", KursValuta.class, QKursValuta.class, PathInits.DIRECT2);

    public QKursnaLista(String variable) {
        super(KursnaLista.class, forVariable(variable));
    }

    public QKursnaLista(Path<? extends KursnaLista> path) {
        super(path.getType(), path.getMetadata());
    }

    public QKursnaLista(PathMetadata metadata) {
        super(KursnaLista.class, metadata);
    }

}

