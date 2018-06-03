package com.poslovna.beans;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUkidanje is a Querydsl query type for Ukidanje
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QUkidanje extends EntityPathBase<Ukidanje> {

    private static final long serialVersionUID = -348634029L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUkidanje ukidanje = new QUkidanje("ukidanje");

    public final DateTimePath<java.util.Date> datumUkidanja = createDateTime("datumUkidanja", java.util.Date.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QRacunPravnogLica naRacun;

    public final QRacunPravnogLica ukidaSe;

    public QUkidanje(String variable) {
        this(Ukidanje.class, forVariable(variable), INITS);
    }

    public QUkidanje(Path<? extends Ukidanje> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QUkidanje(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QUkidanje(PathMetadata metadata, PathInits inits) {
        this(Ukidanje.class, metadata, inits);
    }

    public QUkidanje(Class<? extends Ukidanje> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.naRacun = inits.isInitialized("naRacun") ? new QRacunPravnogLica(forProperty("naRacun"), inits.get("naRacun")) : null;
        this.ukidaSe = inits.isInitialized("ukidaSe") ? new QRacunPravnogLica(forProperty("ukidaSe"), inits.get("ukidaSe")) : null;
    }

}

