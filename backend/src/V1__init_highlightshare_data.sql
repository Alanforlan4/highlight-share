-- V1__init_highlightshare_data.sql
-- cria tabelas (caso precise) e insere dados de exemplo

-- CREATE TABLE statements dependem do dialect. Se você já tem schema via JPA, apenas INSERTs:

INSERT INTO users (id, username, password, role, display_name, avatar_url, email, created_at)
VALUES
    (1, 'joaosilva', '$2a$10$PLACEHOLDER', 'USER', 'João Silva', 'https://i.pravatar.cc/150?u=a042581f4e29026024d', 'joao@example.com', NOW() - INTERVAL '2 days'),
    (2, 'mariac',   '$2a$10$PLACEHOLDER', 'USER', 'Maria Costa','https://i.pravatar.cc/150?u=a042581f4e29026704d', 'maria@example.com', NOW() - INTERVAL '2 days'),
    (3, 'pedrinho', '$2a$10$PLACEHOLDER', 'USER', 'Pedro Santos','https://i.pravatar.cc/150?u=a04258114e29026302d', 'pedro@example.com', NOW() - INTERVAL '2 days');

INSERT INTO groups (id, name, description, created_at) VALUES (1, 'Peladeiros UFRN', 'Grupo local', NOW() - INTERVAL '10 days');

INSERT INTO group_members (id, group_id, user_id, role) VALUES (1,1,1,'MEMBER'), (2,1,2,'MEMBER'), (3,1,3,'MEMBER');

INSERT INTO posts (id, author_id, group_id, location, image_url, caption, created_at) VALUES
                                                                                          (1,1,1,'Arena das Dunas','https://images.unsplash.com/photo-1546519638-68e109498ffc?q=80&w=800&auto=format&fit=crop','Aquele basquete de sábado pra relaxar! 🏀 #basquete #ufrn', NOW() - INTERVAL '2 HOURS'),
                                                                                          (2,2,1,'Quadra do Setor 4','https://images.unsplash.com/photo-1574629810360-7efbbe195018?q=80&w=800&auto=format&fit=crop','Golaço que fala? O time hoje jogou muito! ⚽', NOW() - INTERVAL '4 HOURS'),
                                                                                          (3,3,1,NULL,'https://images.unsplash.com/photo-1519861531473-92002639313cc?q=80&w=800&auto=format&fit=crop','Fim de treino. Morto mas feliz.', NOW() - INTERVAL '1 DAY'),
                                                                                          (4,2,1,'Quadra do Setor 4','https://images.unsplash.com/photo-1574629810360-7efbbe195018?q=80&w=800&auto=format&fit=crop','Golaço que fala? O time hoje jogou muito! ⚽', NOW() - INTERVAL '4 HOURS');

INSERT INTO likes (id, post_id, user_id, created_at) VALUES
                                                         (1,1,1,NOW() - INTERVAL '1 HOUR'),
                                                         (2,1,2,NOW() - INTERVAL '30 MINUTE'),
                                                         (3,1,3,NOW() - INTERVAL '10 MINUTE'),
                                                         (4,2,1,NOW() - INTERVAL '3 HOUR'),
                                                         (5,2,2,NOW() - INTERVAL '2 HOUR'),
                                                         (6,3,3,NOW() - INTERVAL '20 HOUR'),
                                                         (7,4,1,NOW() - INTERVAL '3 HOUR'),
                                                         (8,4,3,NOW() - INTERVAL '1 HOUR');

INSERT INTO comments (id, post_id, author_id, content, created_at) VALUES
                                                                       (1,1,1,'Joguei muito hoje 🔥',NOW() - INTERVAL '1 HOUR'),
                                                                       (2,1,2,'Time tá voando!',NOW() - INTERVAL '55 MINUTE'),
                                                                       (3,1,3,'Bola caiu é o que importa 😂',NOW() - INTERVAL '30 MINUTE'),
                                                                       (4,1,2,'Partida braba',NOW() - INTERVAL '25 MINUTE'),
                                                                       (5,1,1,'Sábado que vem tem mais!',NOW() - INTERVAL '10 MINUTE'),
                                                                       (6,2,3,'Boa!',NOW() - INTERVAL '3 HOUR'),
                                                                       (7,2,1,'Jogão demais!',NOW() - INTERVAL '2 HOUR'),
                                                                       (8,4,3,'Ô time bom esse ein!',NOW() - INTERVAL '3 HOUR'),
                                                                       (9,4,1,'Partidaça!',NOW() - INTERVAL '1 HOUR');
