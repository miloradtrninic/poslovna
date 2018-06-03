package com.poslovna.beans;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QRTGSNalog is a Querydsl query type for RTGSNalog
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QRTGSNalog extends EntityPathBase<RTGSNalog> {

    private static final long serialVersionUID = 1159729545L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QRTGSNalog rTGSNalog = new QRTGSNalog("rTGSNalog");

    public final QAnalitikaIzvoda analitikaIzvoda;

    public final NumberPath<Long> Id = createNumber("Id", Long.class);

    public QRTGSNalog(String variable) {
        this(RTGSNalog.class, forVariable(variable), INITS);
    }

    public QRTGSNalog(Path<? extends RTGSNalog> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QRTGSNalog(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QRTGSNalog(PathMetadata metadata, PathInits inits) {
        this(RTGSNalog.class, metadata, inits);
    }

    public QRTGSNalog(Class<? extends RTGSNalog> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.analitikaIzvoda = inits.isInitialized("analitikaIzvoda") ? new QAnalitikaIzvoda(forProperty("analitikaIzvoda"), inits.get("analitikaIzvoda")) : null;
    }

}

