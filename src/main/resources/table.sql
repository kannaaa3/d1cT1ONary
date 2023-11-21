CREATE TABLE IF NOT EXISTS wn_gloss (
    synset_id DECIMAL(10,0) NOT NULL default '0',
    gloss VARCHAR(255) default NULL,
    CONSTRAINT wn_gloss_pk PRIMARY KEY (synset_id)
);

CREATE TABLE IF NOT EXISTS wn_synset (
    synset_id DECIMAL(10,0) NOT NULL default '0',
    w_num DECIMAL(10,0) NOT NULL default '0',
    word VARCHAR(50) default NULL,
    phonetic VARCHAR(50) default NULL,
    ss_type char(2) default NULL,
    sense_number DECIMAL(10,0) NOT NULL default '0',
    tag_count DECIMAL(10,0) default NULL,
    CONSTRAINT wn_synset_pk PRIMARY KEY (synset_id, w_num),
    CONSTRAINT synset_id
        FOREIGN KEY (synset_id)
            REFERENCES wn_gloss (synset_id)

);

CREATE TABLE IF NOT EXISTS users (
    user_id VARCHAR(255) NOT NULL,
    username VARCHAR(255),
    password VARCHAR(255),
    CONSTRAINT users_pk PRIMARY KEY (user_id)
);

CREATE TABLE IF NOT EXISTS wn_antonym (
    synset_id_1 DECIMAL(10,0) default NULL,
    wnum_1 DECIMAL(10,0) default NULL,
    synset_id_2 DECIMAL(10,0) default NULL,
    wnum_2 DECIMAL(10,0) default NULL,
    CONSTRAINT wn_antonym_pk PRIMARY KEY (synset_id_1, wnum_1, synset_id_2, wnum_2),
    CONSTRAINT synset_id_1_fk
        FOREIGN KEY (synset_id_1, wnum_1)
            REFERENCES wn_synset (synset_id, w_num),
    CONSTRAINT synset_id_2_fk
        FOREIGN KEY (synset_id_2, wnum_2)
            REFERENCES wn_synset (synset_id, w_num)
);

CREATE TABLE IF NOT EXISTS wn_similar (
    synset_id_1 DECIMAL(10,0) default NULL,
    synset_id_2 DECIMAL(10,0) default NULL,
    CONSTRAINT wn_similar_pk PRIMARY KEY (synset_id_1, synset_id_2),
    CONSTRAINT synset_id_1_fk
        FOREIGN KEY (synset_id_1)
            REFERENCES wn_gloss (synset_id)
                ON DELETE CASCADE
                ON UPDATE NO ACTION,
    CONSTRAINT synset_id_2_fk
        FOREIGN KEY (synset_id_2)
            REFERENCES wn_gloss (synset_id)
            ON DELETE CASCADE
            ON UPDATE NO ACTION
);

CREATE TABLE IF NOT EXISTS user_word_review_data(
    user_id VARCHAR(255) NOT NULL,
    synset_id DECIMAL(10,0) NOT NULL default '0',
    w_num DECIMAL(10,0) NOT NULL default '0',
    timestamp INTEGER,
    fluency_level INTEGER,
    review_times INTEGER,
    CONSTRAINT user_word_review_data_pk PRIMARY KEY (user_id, synset_id, w_num),
    CONSTRAINT user_word_review_data_user_id_fk
        FOREIGN KEY (user_id)
            REFERENCES users (user_id)
                ON DELETE CASCADE
                ON UPDATE NO ACTION,
    CONSTRAINT user_word_review_data_synset_fk
        FOREIGN KEY (synset_id, w_num)
            REFERENCES wn_synset (synset_id, w_num)
                ON DELETE CASCADE
                ON UPDATE NO ACTION
);

CREATE TABLE IF NOT EXISTS user_word_list_data(
    user_id VARCHAR(255) NOT NULL,
    word_list_id INTEGER,
    synset_id DECIMAL(10,0) NOT NULL default '0',
    w_num DECIMAL(10,0) NOT NULL default '0',
    CONSTRAINT user_word_list_data_pk PRIMARY KEY (user_id, word_list_id, synset_id, w_num),
    CONSTRAINT user_word_list_data_word_list_id
        FOREIGN KEY (user_id, word_list_id)
            REFERENCES user_word_list_name_data (user_id, word_list_id)
                ON DELETE CASCADE
                ON UPDATE NO ACTION,
    CONSTRAINT user_word_list_data_synset_id
        FOREIGN KEY (synset_id, w_num)
            REFERENCES wn_synset (synset_id, w_num)
                ON DELETE CASCADE
                ON UPDATE NO ACTION
);

CREATE TABLE IF NOT EXISTS user_search_history_data(
    user_id VARCHAR(255) NOT NULL,
    synset_id DECIMAL(10,0) NOT NULL default '0',
    w_num DECIMAL(10,0) NOT NULL default '0',
    timestamp INTEGER,
    CONSTRAINT user_search_history_data_pk
        PRIMARY KEY (user_id, synset_id, w_num),
    CONSTRAINT user_search_history_data_user_id_fk
        FOREIGN KEY (user_id)
            REFERENCES users (user_id)
                ON DELETE CASCADE
                ON UPDATE NO ACTION,
    CONSTRAINT user_search_history_data_word_id_fk
        FOREIGN KEY (synset_id, w_num)
            REFERENCES wn_synset (synset_id, w_num)
                ON DELETE CASCADE
                ON UPDATE NO ACTION
);

CREATE TABLE IF NOT EXISTS user_word_list_name_data(
    user_id VARCHAR(255) NOT NULL,
    word_list_id INTEGER,
    word_list_name VARCHAR(255),
    CONSTRAINT user_word_list_name_data_pk PRIMARY KEY (user_id, word_list_id),
    CONSTRAINT user_word_list_name_data_user_id_fk
        FOREIGN KEY (user_id)
            REFERENCES users (user_id)
                ON DELETE CASCADE
                ON UPDATE NO ACTION
);