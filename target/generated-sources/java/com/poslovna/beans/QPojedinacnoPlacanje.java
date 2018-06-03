package com.poslovna.beans;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPojedinacnoPlacanje is a Querydsl query type for PojedinacnoPlacanje
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QPojedinacnoPlacanje extends EntityPathBase<PojedinacnoPlacanje> {

    private static final long serialVersionUID = -665724834L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPojedinacnoPlacanje pojedinacnoPlacanje = new QPojedinacnoPlacanje("pojedinacnoPlacanje");

    public final QAnalitikaIzvoda analitikaIzvoda;

    public final NumberPath<Long> Id = createNumber("Id", Long.class);

    public QPojedinacnoPlacanje(String variable) {
        this(PojedinacnoPlacanje.class, forVariable(variable), INITS);
    }

    public QPojedinacnoPlacanje(Path<? extends PojedinacnoPlacanje> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QPojedinacnoPlacanje(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QPojedinacnoPlacanje(PathMetadata metadata, PathInits inits) {
        this(PojedinacnoPlacanje.class, metadata, inits);
    }

    public QPojedinacnoPlacanje(Class<? extends PojedinacnoPlacanje> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.analitikaIzvoda = inits.isInitialized("analitikaIzvoda") ? new QAnalitikaIzvoda(forProperty("analitikaIzvoda"), inits.get("analitikaIzvoda")) : null;
    }

}

