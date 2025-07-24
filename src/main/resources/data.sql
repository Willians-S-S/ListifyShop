-- ROLES ----------------------------------------------------------
INSERT INTO role_table (id, name) VALUES
  ('a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11', 'ADMIN'),
  ('a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a12', 'USER');
--  ('a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a13', 'MANAGER');

-- USERS ----------------------------------------------------------
-- senha = "123456" (BCrypt)
INSERT INTO user_table (id, name, cpf, email, password, url_image, creat_at, update_at) VALUES
  ('b1eebc99-9c0b-4ef8-bb6d-6bb9bd380b01',
   'Alice Santos',
   '123.456.789-09',
   'alice@listify.com',
   '$2a$12$v97p5npgCaHlHM0PZMLdCeKntEtUhiz7Qkt.EeG5a6c7XiSWkkXgy',
   'https://cdn.listify.com/avatars/alice.png', NOW(), NOW()),

  ('b2eebc99-9c0b-4ef8-bb6d-6bb9bd380b02',
   'Bruno Lima',
   '987.654.321-00',
   'bruno@listify.com',
   '$2a$10$V0./6xXkO7CjKQ8T7lHfbO6GGCtX7j6l7z5K8gYw4T9K3L7q7l9Lq',
   NULL, NOW(), NOW()),

  ('b3eebc99-9c0b-4ef8-bb6d-6bb9bd380b03',
   'Carla Oliveira',
   '111.222.333-44',
   'carla@listify.com',
   '$2a$10$V0./6xXkO7CjKQ8T7lHfbO6GGCtX7j6l7z5K8gYw4T9K3L7q7l9Lq',
   'https://cdn.listify.com/avatars/carla.png', NOW(), NOW());

-- RELAÇÕES user ↔ role ------------------------------------------
-- Alice é ADMIN e USER
INSERT INTO users_roles (user_id, role_id) VALUES
  ('b1eebc99-9c0b-4ef8-bb6d-6bb9bd380b01', 'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11'),
  ('b1eebc99-9c0b-4ef8-bb6d-6bb9bd380b01', 'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a12');

-- Bruno é USER
INSERT INTO users_roles (user_id, role_id) VALUES
  ('b2eebc99-9c0b-4ef8-bb6d-6bb9bd380b02', 'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a12');
