INSERT INTO genre (description,name)
VALUES  ('coca cola, pepsi, fanta','Pop'),
        ('sedimentary, metamorphic, igneous','Rock'),
        ('cyberpunk 2077','Punk'),
        ('bandage','Rap'),
        ('steel, alloy,copper','Metal');

INSERT INTO artist (name)
VALUES  ('Alice'),
        ('Boris'),
        ('Christopher'),
        ('Daniel');

INSERT INTO album (name,artist_id,genre_id)
VALUES  ('Alone in the Dark',2,4),
        ('Bright day outside',1,1),
        ('Can you hear the music',1,3),
        ('Did you know?',3,1),
        ('E',1,1);

INSERT INTO track (duration,lyrics,name,album_id)
VALUES  (3363,'Lorem Ipsum is simply dummy text of the printing and','Angsty Teen',1),
        (985,' it look like readable English. Many desktop','Benji the bulldog',1),
        (4517,'volved over the years, som','Can you feel it?',1),
        (400,'have suffered alteration in some f','Did you know?',1),
        (7781,'dle of text. All the Lorem Ipsum gener','everyone ate the cheese',1),
        (9303,'e, and going through the cites of the word in classical literature, discovered the undoubtable source. Lorem','Fnks fr mmrs',2),
        (2100,'d humour, or randomised words which dont look even slightly believable. If you are going to u','get over it',2),
        (4126,'f over 200 Latin words, combined','how long have you been reading this?',2),
        (4257,'rem Ipsum passage, and going through the cites of the word in classical literature, discovered','im loosing my sanity',2),
        (6155,'ssical Latin literature from 45 BC, making it over 2000 years old. Richard McClintock, a Lat','jk i already lost it',2),
        (5538,'Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using','kool kids',3),
        (6023,'e going to use a passage of Lorem Ipsum, you need to be sure there isnt anything embarr','lmao',3),
        (5778,' the 1500s, when an unknown printer took a galley of type and scrambled it to ','mr darkstreet',3),
        (527,'.10.32 and 1.10.33 of "de Finibus Bonorum et Malorum" (The Extremes of Good and Evil)','northside',3),
        (3617,'us Bonorum et Malorum" by Cicero are also reproduced in their exact original form, acco','Oh look!, a cat',3),
        (5211,'cted by the readable content of a page when looking at its layout. The point of using Lore','purfect',4),
        (4516,' galley of type and scrambled it to make a type specimen book. It has survived n','quiditch',4),
        (4732,'om a Lorem Ipsum passage, and going through the cites of the word in classical literature, discovered t','restlessness',4),
        (4517,'oots in a piece of classical Latin literature from 45 BC, making it over 2000 years ol','street fite',4),
        (3066,'f Lorem Ipsum used since the 1500s is reproduced below for those interested. Sections 1.10.32 and 1.10.33 from "de Finibus Bono','Thanks god im done',4),
        (12,'here can I get','u iz rite',5);