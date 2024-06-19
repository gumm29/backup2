create sequence hibernate_sequence start with 1 increment by 1;

    create table tb_artigos (
       id bigint not null,
        created_at timestamp,
        imagem_post binary(255),
        postagem varchar(255),
        publicado boolean not null,
        subtitulo varchar(255),
        titulo varchar(255),
        updated_at timestamp,
        url_name varchar(255),
        primary key (id)
    );

    create table tb_user (
       id bigint not null,
        email varchar(255),
        nome varchar(255),
        perfil integer not null,
        senha varchar(255),
        primary key (id)
    );

    create table tb_user_perfil (
       id bigint not null,
        nome varchar(255),
        primary key (id)
    );

    create table tb_user_perfis (
       user_id bigint not null,
        perfis_id bigint not null
    );

    alter table tb_user_perfis 
       add constraint FKq13j7633krhjjlud74fw3hx34 
       foreign key (perfis_id) 
       references tb_user_perfil;

    alter table tb_user_perfis 
       add constraint FKi745f5q4w7kq6cbsgjbgjvep7 
       foreign key (user_id) 
       references tb_user;

    create table tb_cursos
       id bigint not null,
        nome varchar(30),
        icone binary(255),
        created_at timestamp,
        updated_at timestamp,
        primary key (id)

    create table tb_estrutura_curso
       id bigint not null,
        curso_id bigint not null,
        nome varchar(255),
        created_at timestamp,
        updated_at timestamp,
        primary key (id)

    alter table tb_estrutura_curso 
       add constraint FKi745f4q4w7kq6cbsgjbgjvep7 
       foreign key (curso_id) 
       references tb_cursos;

    create table tb_modulo_curso
       id bigint not null,
        conteudo_id bigint not null,
        estrutura_id bigint,
        nome varchar(255),
        created_at timestamp,
        updated_at timestamp,
        primary key (id)

    alter table tb_modulo_curso 
       add constraint FKi745f4q4w8kq6cbsgjbgjvep7 
       foreign key (estrutura_id) 
       references tb_estrutura_curso;

    alter table tb_modulo_curso 
       add constraint FKi745f4q4w8kq6cbsgjbgjvep7 
       foreign key (conteudo_id) 
       references tb_conteudo_curso;

    create table tb_conteudo_curso
       id bigint not null,
        modulo_id bigint not null,
        titulo varchar(255),
        texto varchar(255),
        video binary(255),
        created_at timestamp,
        updated_at timestamp,
        primary key (id)

      alter table tb_conteudo_curso 
       add constraint FKe745f4q4w8kq6cbsgjbgjvep7 
       foreign key (modulo_id) 
       references tb_modulo_curso;

      insert into TB_USER_PERFIL values(1, 'adm')