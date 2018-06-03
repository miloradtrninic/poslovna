package com.poslovna.beans;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QBanka is a Querydsl query type for Banka
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QBanka extends EntityPathBase<Banka> {

    private static final long serialVersionUID = 1385787243L;

    public static final QBanka banka = new QBanka("banka");

    public final QPravnaLica _super = new QPravnaLica(this);

    //inherited
    public final StringPath adresa = _super.adresa;

    //inherited
    public final StringPath email = _super.email;

    //inherited
    public final StringPath fax = _super.fax;

    //inherited
    public final BooleanPath firma = _super.firma;

    public final SetPath<KursnaLista, QKursnaLista> kursneListe = this.<KursnaLista, QKursnaLista>createSet("kursneListe", KursnaLista.class, QKursnaLista.class, PathInits.DIRECT2);

    //inherited
    public final StringPath naziv = _super.naziv;

    public final StringPath obracunskiRacun = createString("obracunskiRacun");

    //inherited
    public final StringPath PIB = _super.PIB;

    public final SetPath<RacunPravnogLica, QRacunPravnogLica> racuni = this.<RacunPravnogLica, QRacunPravnogLica>createSet("racuni", RacunPravnogLica.class, QRacunPravnogLica.class, PathInits.DIRECT2);

    public final NumberPath<Integer> sifraBanke = createNumber("sifraBanke", Integer.class);

    public final StringPath swift = createString("swift");

    //inherited
    public final StringPath telefon = _super.telefon;

    //inherited
    public final StringPath web = _super.web;

    public QBanka(String variable) {
        super(Banka.class, forVariable(variable));
    }

    public QBanka(Path<? extends Banka> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBanka(PathMetadata metadata) {
        super(Banka.class, metadata);
    }

}

